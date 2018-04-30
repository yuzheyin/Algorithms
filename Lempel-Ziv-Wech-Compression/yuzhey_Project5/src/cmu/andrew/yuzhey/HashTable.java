package cmu.andrew.yuzhey;

import colorado.nodes.ObjectNode;
//Use for compression
public class HashTable {
	SinglyLinkedList[] hashTable;
	
	public HashTable(){
		hashTable = new SinglyLinkedList[1024];
	}
	
	//Put the key value pair in the hashtable 
	public void put(String key, int value){
		KeyValuePair n = new KeyValuePair(key, value);
		int index = Math.abs(key.hashCode() %1024);
		if(hashTable[index] == null)
			hashTable[index] = new SinglyLinkedList();
		hashTable[index].addAtEndNode(n);
	}
	
	//Get the value according to key, return Integer.MIN_VALUE if not found
	public int get(String key){
		int index = Math.abs(key.hashCode()%1024);
		hashTable[index].reset();
		for(int i = 0; i < hashTable[index].countNodes(); i++){
			KeyValuePair k = (KeyValuePair) hashTable[index].next();
			if(key.equals(k.key))
				return k.value;
		}
		return Integer.MIN_VALUE;
	}
	
	//Check if value is in the table
	public boolean containsKey(String key){
		int index = Math.abs(key.hashCode()%1024);
		if(hashTable[index] == null)
			return false;
		hashTable[index].reset();
		for(int i = 0; i < hashTable[index].countNodes(); i++){
			KeyValuePair k = (KeyValuePair) hashTable[index].next();
			if(key.equals(k.key))
				return true;
		}
		return false;
	}
	
}

