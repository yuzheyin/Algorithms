package cmu.andrew.yuzhey;

//Use for decompression
public class HashTable2 {
	SinglyLinkedList[] hashTable;
	
	public HashTable2(){
		hashTable = new SinglyLinkedList[127];
	}
	
	//Put the key value pair in the hashtable 
	public void put(int key, String value){
		KeyValuePair2 n = new KeyValuePair2(key, value);
		int index = Math.abs(key%127);
		if(hashTable[index] == null)
			hashTable[index] = new SinglyLinkedList();
		hashTable[index].addAtEndNode(n);
	}
	
	//Get the value according to key, return Integer.MIN_VALUE if not found
	public String get(int key){
		int index = Math.abs(key%127);
		hashTable[index].reset();
		for(int i = 0; i < hashTable[index].countNodes(); i++){
			KeyValuePair2 k = (KeyValuePair2) hashTable[index].next();
			if(key == k.key)
				return k.value;
		}
		return null;
	}
	
	//Check if value is in the table
	public boolean containsKey(int key){
		int index = Math.abs(key%127);
		if(hashTable[index] == null)
			return false;
		hashTable[index].reset();
		for(int i = 0; i < hashTable[index].countNodes(); i++){
			KeyValuePair2 k = (KeyValuePair2) hashTable[index].next();
			if(key == k.key)
				return true;
		}
		return false;
	}
	
}