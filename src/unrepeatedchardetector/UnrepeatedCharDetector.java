package unrepeatedchardetector;

import java.util.HashMap;
/*
 * This class simulate the program that detects the first string which has the unique characters
 */
public class UnrepeatedCharDetector {
	private String string;
	
	//A hashmap store the character and its frequency
	private HashMap<Character, Integer> map;
	
	public void init(String s) {
		this.string = s;
		this.map = new HashMap<Character, Integer>();
	}
	
	//first put all the character in the map with initial frequency 1, than increase the frequency whenever the character repeat 
	public String detect() {
		for(char c : string.toCharArray()) {
			
			//if character c is already in the map than increase it's frequency by 1
			if(this.map.keySet().contains(c)) {
				this.map.put(c, this.map.get(c)+1);
			}
			//else, put it in the map with the frequency 1
			else {
				this.map.put(c, 1);
			}
		}
		
		//first is the index of first-unique character
		//last is the index of last-continuous-unique character starting from the 'first' unique character
		int i = 0, first = 0, last = 0;
		while(i<this.string.length() && map.get(this.string.charAt(i))>1) {
			i++;
		}
		if(i==this.string.length()) {
			return null;
		} else {
			first = i;
			last = i+1;
			while(last<this.string.length()&&map.get(this.string.charAt(last))==1) {
				last += 1;
			}
			return this.string.substring(first,last);
		}
	}
}
