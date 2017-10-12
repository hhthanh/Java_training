package horse;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.Test;

public class HorseTest {
	//This test case check if step count equals to 100 when the horse finish
	@Test
	public void test() {
		ExecutorService exe = Executors.newFixedThreadPool(10);
		Horse[] horseArr = new Horse[10];
		for(int i = 0; i < 10; i++) horseArr[i] = new Horse(i);
		List<Horse> horseList = Arrays.asList(horseArr);
		
		try {
			exe.invokeAll(horseList).stream()
			.map(future -> {
			    try {
			        return future.get();
			    }
			    catch (Exception e) {
			        throw new IllegalStateException(e);
			    }
			}).forEach(x -> assertEquals(x,new Integer(100)));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
