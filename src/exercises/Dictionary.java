package exercises;

import java.util.HashMap;

/*
 * @author Hoang Hai Thanh
 * This class implements the dictionary in which user can lookup for value by two keys.
 * To implement this dictionary, a hashmap and a class Key is used
 * Two inputed keys map to a key in Key class, then the composite key is passed in to the 
 * hashmap, which return the value.
 */
public class Dictionary<K1, K2, V > {
	
	//HashMap is used to hold the composite key and value
	HashMap<Key<K1,K2>,V> bank;
	
	//Initial the dictionary
	public Dictionary(){
		this.init();
	}
	
	
	//Initial the hashmap
	public void init() {
		this.bank = new HashMap<Key<K1,K2>,V>();
	}
	
	//Put a key-value pair to the dictionary
	public void put(K1 key1, K2 key2 , V value) {
		Key<K1, K2> key = new Key<K1, K2>(key1, key2);
		this.bank.put(key, value);
	}
	
	//Get the value of (key1, key2) key
	public V get(K1 key1, K2 key2) {
		Key<K1, K2> key = new Key<K1, K2>(key1, key2);
		if(this.bank.keySet().contains(key)) return this.bank.get(key);
		else return null;
	}
	
	//Return true if key (key1, key2) is in the dictionary
	public boolean containsKey(K1 key1, K2 key2) {
		Key<K1, K2> key = new Key<K1, K2>(key1, key2);
		return bank.containsKey(key);
	}

	/*
	 * This class is used to combine two inputed keys in to a single key
	 */
@SuppressWarnings("hiding")
class Key<K1, K2>{
	
	private K1 key1;
	private K2 key2;
	
	public Key(K1 k1, K2 k2){
		this.key1 = k1;
		this.key2 = k2;
	}
	
	public K1 getK1() {
		return key1;
	}
	
	public K2 getK2() {
		return key2;
	}
	
	//Two keys are considered equal if k1.key1 == k2.key1 and k1.key2 == k2.key2
	@SuppressWarnings("unchecked")
	public boolean equals(Object key) {
		if(key instanceof Key) {
			if(((K1)((Key<K1, K2>) key).getK1()).equals(this.key1)&&((K2)((Key<K1, K2>) key).getK2()).equals(this.key2)) return true;
			return false;
		}
		return false;
	}
	
	//The hashCode of the composite key is the sum of two component keys
	public int hashCode() {
		return key1.hashCode() + key2.hashCode();
	}
	}
}