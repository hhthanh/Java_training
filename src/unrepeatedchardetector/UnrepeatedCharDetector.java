package unrepeatedchardetector;

import java.util.HashMap;
/*
 * This class simulate the program that detects the first string which has the unique characters
 */
public class UnrepeatedCharDetector {

	//A hashmap store the character and its frequency
	private HashMap<Character, Integer> map;
	
	public boolean init() {
		this.map = new HashMap<Character, Integer>();
		return !(this.map==null);
	}
	
	//first put all the character in the map with initial frequency 1, 
	//than increase the frequency whenever the character repeat 
	public String detect(String string) {
		//The map need to be initial again in order to flush all the previous data;
		//now we can detect multiple string with one instance!
		this.init();
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
		while(i<string.length() && map.get(string.charAt(i))>1) {
			i++;
		}
		if(i==string.length()) {
			return "";
		} else {
			first = i;
			last = i+1;
			while(last<string.length()&&map.get(string.charAt(last))==1) {
				last += 1;
			}
			return string.substring(first,last);
		}
	}
}
