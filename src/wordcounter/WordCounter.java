package wordcounter;

import java.util.HashMap;
import java.util.Map;

/*
 * This class simulates the program that count the number of word in the inputed sentence.
 */
public class WordCounter {
	private Map<String, Integer> wordCount;
	
	public boolean init() {
		wordCount = new HashMap<String, Integer>();
		return !(this.wordCount==null);
	}
	
	public void countWord(String s) {
		//init again to flush the old data, then we can count multiple string with one instance
		this.init();
		
		//if inputed string is empty
		if(s==null || "".equals(s))
		{
			System.out.println("String is empty!");
			return;
		}
		
		//change to lower case
		s = s.toLowerCase();
		String[] bag = s.split(" ");
		for(String w : bag) {
			//if there is already word w in the map, increase it's value by 1
			if(wordCount.keySet().contains(w)) {
				wordCount.put(w, wordCount.get(w)+1);
			}
			//else add it to the map with value 1
			else {
				wordCount.put(w, 1);
			}
		}
		//print result to screen
		this.printWords();
	}
	
	public void printWords() {
		if(wordCount.keySet()==null) {
			System.out.println("String is empty!");
		} else {
			wordCount.keySet().stream().forEach(s -> System.out.println(s+": "+wordCount.get(s)));
		}
	}
}