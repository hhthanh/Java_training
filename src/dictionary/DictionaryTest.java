package dictionary;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DictionaryTest {
	Dictionary dict;
	//Dictionary with K1 and K2 are both String
	@Test
	public void testString() {
		dict = new Dictionary<String, String, String>();
		//add value to dictionary
		assertEquals(dict.put("key1", "key1", "value11"),null);
		assertNull(dict.put("key1", "key2", "value12"));
		assertEquals(dict.put("key1", "key2", "value122"), "value12");
		assertNull(dict.put("key3", "key4", "value34"));
		assertNull(dict.put("key2", "key1", "value21"));
		assertNull(dict.put("", "key2", "value 2"));
		assertNull(dict.put("key1", "", "value1 "));
		
		//get value
		assertEquals(dict.get("key1", "key2"),"value122");
		assertEquals(dict.get("key3", "key4"),"value34");
		assertEquals(dict.get("key2", "key1"),"value21");
		assertEquals(dict.get("", "key2"),"value 2");
		assertEquals(dict.get("key1", ""),"value1 ");
		assertNull(dict.get("key5", "key4"));
	}
	
	@Test
	public void testInt() {
		dict = new Dictionary<Integer, Integer, Integer>();
		
		//add value to dictionary
		assertEquals(dict.put(1, 1, 11),null);
		assertNull(dict.put(1, 2, 12));
		assertEquals(dict.put(1, 2, 122), 12);
		assertNull(dict.put(3, 4, 34));
		assertNull(dict.put(2, 1, 21));
		assertNull(dict.put(0, 2, 02));
		assertNull(dict.put(1, 0, 10));
		
		//get value
		assertEquals(dict.get(1, 2),122);
		assertEquals(dict.get(3, 4),34);
		assertEquals(dict.get(2, 1),21);
		assertEquals(dict.get(0, 2),02);
		assertEquals(dict.get(1, 0),10);
		assertNull(dict.get(5, 4));
	}

}
