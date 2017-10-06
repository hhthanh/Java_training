package dinningphilosophies;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Philosophy implements Runnable {
	
	private int id;
	private ReentrantLock left_fork;
	private ReentrantLock right_fork;
	private Random rand;
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
	
	public void returnRightFork() {
		
		try {this.right_fork.unlock();
			System.out.println(System.currentTimeMillis() + ": Philosophy + " + this.id + ": Return right fork");
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void eat() {
		
		try {
			System.out.println(System.currentTimeMillis() + ": Philosophy + " + this.id + ": Eating");
			Thread.sleep(rand.nextInt(this.randomTimeEating)+2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void doStuff() {
		thinking();
		synchronized(this.left_fork){
			pickLeftFork();
			if(pickRightFork()) {
				eat();
				returnRightFork();
			}
			else {
				System.out.println(System.currentTimeMillis() + ": Philosophy + " + this.id + ": Cant wait, drop fork!");
				returnLeftFork();
			}
			
		}
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			doStuff();
		}
	}
	
	

}
