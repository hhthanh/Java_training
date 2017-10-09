package wordcounter;

import java.util.HashMap;
import java.util.Map;

/*
 * This class simulates the program that count the number of word in the inputed sentence.
 */
public class WordCounter {
	Map<String, Integer> wordCount;
	
	public void init() {
		wordCount = new HashMap<String, Integer>();
	}
	
	public void countWord(String s) {
		if(s==null || "".equals(s))
		{
			System.out.println("String is empty!");
			return;
		}
		s = s.toLowerCase();
		String[] bag = s.split(" ");
		for(String w : bag) {
			if(wordCount.keySet().contains(w)) {
				wordCount.put(w, wordCount.get(w)+1);
			}
			else {
				wordCount.put(w, 1);
			}
		}
	}
	
	public void printWords() {
		if(wordCount.keySet()==null) {
			System.out.println("String is empty!");
		} else {
			wordCount.keySet().stream().forEach(s -> System.out.println(s+": "+wordCount.get(s)));
		}
	}
}