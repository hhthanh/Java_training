package exercises;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import dinningphilosophies.Philosophy;
/*
 * This class is used to run, test the others class
 * The command is ./MainClass [option]
 * options are:
 * 				wordcounter: to test the WordCounter class
 * 				horse:		 to test the Horse class
 * 				unrepeat:	 to test the UnrepeatedCharDetector class
 * 				dictionary:  to test the Dictionary class
 * 				philosophy:  to test the Philosophy class
 */
public class MainClass {
	public static void main(String[] args) {
		if("wordcounter".equals(args[0])) {
			WordCounter counter = new WordCounter();
			counter.init();
			Scanner s = new Scanner(System.in);
			System.out.println("Type the sentence: ");
			String sentence = s.nextLine();
			counter.countWord(sentence);
			counter.printWords();
		}
		if("horse".equals(args[0])) {
			ExecutorService executor = Executors.newFixedThreadPool(10);
			
			for(int i = 0; i < 10; i++) {
				Horse horse = new Horse();
				horse.init(i);
				executor.submit(horse);
			}
			
			executor.shutdown();
			try {
				if(executor.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS)) {
					System.out.println("All the horse are finished!");
				};
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if("unrepeat".equals(args[0])) {
			UnrepeatedCharDetector detector = new UnrepeatedCharDetector();
			Scanner s = new Scanner(System.in);
			System.out.println("Type the string: ");
			String sentence = s.nextLine();
			detector.init(sentence);
			System.out.println("Result: "+detector.detect());
		}
		if("dictionary".equals(args[0])) {
			Dictionary<String, String, String> dict = new Dictionary<String, String, String>();
			dict.put("key1", "key2", "key1 + key2");
			dict.put("key1", "key3", "key1 + key3");
			dict.put("key2", "key1", "key2 + key1");
			dict.put("key1", "key2", "key1 + key2");
			System.out.println(dict.containsKey("key1", "key2"));
			System.out.println(dict.get("key1", "key2"));
			System.out.println(dict.get("key2", "key1"));
			
		}
		if("philosophy".equals(args[0])) {
			ExecutorService executor = Executors.newFixedThreadPool(5);
			ReentrantLock[] forkArray = new ReentrantLock[5];
			for(int i = 0; i < 5; i++) {
				forkArray[i] = new ReentrantLock();
			}
			for(int i = 0; i < 5; i++) {
				
				Philosophy pl = new Philosophy(i, forkArray[i], forkArray[(i+1)%5], 2000, 10000);
				executor.submit(pl);
			}
		}
	}
}
