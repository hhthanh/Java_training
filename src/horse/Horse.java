package horse;

import java.util.Random;
import java.util.concurrent.Callable;
/*
 * This class simulates the horse which each 0.5 second it steps [1,10]
 * The horse is considered finish if the length is equal 100
 * If the length bigger than 100, it is reduced by 100
 */
public class Horse implements Callable<Integer>{
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
	public Horse(int id) {
		this.init(id);
	}
	
	@Override
	public Integer call() throws Exception {
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
		
		return new Integer(this.step);
	}
	
	
}
