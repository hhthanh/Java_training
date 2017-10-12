package dinningphilosophies;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Philosophy implements Runnable {
	
	private int id;
	//Left fork and right fork are ReentrantLocks
	private ReentrantLock left_fork;
	private ReentrantLock right_fork;
	
	//Random element
	private Random rand;
	
	//Random time for thinking and eating
	private int randomTimeThinking;
	private int randomTimeEating;
	
	
	public Philosophy(int id, ReentrantLock left_fork, ReentrantLock right_fork, int randomTimeThinking, int randomTimeEating) {
		
		this.id = id;
		this.left_fork = left_fork;
		this.right_fork = right_fork;
		this.rand = new Random();
		this.randomTimeThinking = randomTimeThinking;
		this.randomTimeEating = randomTimeEating;
	}
	
	//method for thinking, the philosophy will thinks for a random time between 2 and randomTimeThinking seconds 
	public void thinking() {
		System.out.println(System.currentTimeMillis() + ": Philosophy + " + this.id + ": Thinking");
		try {
			Thread.sleep(rand.nextInt(this.randomTimeThinking)+2000);
			System.out.println(System.currentTimeMillis() + ": Philosophy + " + this.id + ": Feel hungry!");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//method for pick the left fork, this action take 2 seconds
	public void pickLeftFork() {
		if(this.left_fork.tryLock()) {
			System.out.println(System.currentTimeMillis() + ": Philosophy + " + this.id + ": Picked left fork");
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//method for pick the right fork, this action take 2 seconds
	public boolean pickRightFork() {
		try {
			if(this.right_fork.tryLock(rand.nextInt(this.randomTimeThinking)+2000, TimeUnit.MILLISECONDS)) {
				
				System.out.println(System.currentTimeMillis() + ": Philosophy + " + this.id + ": Picked Right fork");
				Thread.sleep(2000);
				return true;
			}
			else return false;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	//method for put down the left fork, it takes 2 seconds too
	public void returnLeftFork() {
		
		try {
			this.left_fork.unlock();
			System.out.println(System.currentTimeMillis() + ": Philosophy + " + this.id + ": Return left fork");
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//method for put down the right fork, it takes 2 seconds too
	public void returnRightFork() {
		
		try {this.right_fork.unlock();
			System.out.println(System.currentTimeMillis() + ": Philosophy + " + this.id + ": Return right fork");
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//method for eating, it takes random time between 2 to randomTimeThinking seconds
	public void eat() {
		
		try {
			System.out.println(System.currentTimeMillis() + ": Philosophy + " + this.id + ": Eating");
			Thread.sleep(rand.nextInt(this.randomTimeEating)+2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//A cycle of action: thinking - hurry - eating (pick left fork, pick right fork, eat)
	public void doStuff() {
		thinking();
		//make sure that no one is using the left fork
		synchronized(this.left_fork){
			pickLeftFork();
			//if able to pick the right fork
			if(pickRightFork()) {
				eat();
				returnRightFork();
			}
			//if someone is using the right fork for too long, drop the left fork and continue to think
			else {
				System.out.println(System.currentTimeMillis() + ": Philosophy + " + this.id + ": Cant wait, drop fork!");
				returnLeftFork();
			}
			
		}
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		//This is the infinite loop
		while(true) {
			doStuff();
		}
	}
	
	

}
