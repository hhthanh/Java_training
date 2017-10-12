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
	
	//Method simulate the action of the horse
	@Override
	public Integer call() throws Exception {
		// TODO Auto-generated method stub
		while(step!=100) {
			count++;
			
			//increase the step by a random number between 1 and 10
			step += rand.nextInt(10)+1;
			
			//if step is greater than 100 than reduce it by 100
			step = step>100 ? (step - 100) : step;
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//if the step equals 100 then the horse is finish
		if(step==100) {
			System.out.println("The "+(id+1)+"th horse finished in "+this.count+" steps!");
		}
		else {
			System.out.println("Something's wrong!");
		}
		
		return new Integer(this.step);
	}
	
	
}
