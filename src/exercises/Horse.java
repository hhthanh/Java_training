package exercises;

import java.util.Random;
/*
 * This class simulates the horse which each 0.5 second it steps [1,10]
 * The horse is considered finish if the length is equal 100
 * If the length bigger than 100, it is reduced by 100
 */
public class Horse implements Runnable{
	private int step;
	private int count;
	private int id;
	private Random rand;
	
	public void init(int id) {
		this.id = id;
		this.step = 0;
		this.count = 0;
		rand = new Random();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(step!=100) {
			count++;
			step += rand.nextInt(10)+1;
			step = step>100 ? (step - 100) : step;
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(step==100) {
			System.out.println("The "+(id+1)+"th horse finished in "+this.count+" steps!");
		}
		else {
			System.out.println("Something's wrong!");
		}
	}
	
	
}
