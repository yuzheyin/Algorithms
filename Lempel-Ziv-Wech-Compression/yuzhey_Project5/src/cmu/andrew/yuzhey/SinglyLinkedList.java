package cmu.andrew.yuzhey;

import java.math.BigInteger;
import java.util.Random;

import colorado.nodes.ObjectNode;

public class SinglyLinkedList {
	
	private ObjectNode head;
	private ObjectNode tail;
	private ObjectNode iterator;
	private int countNodes;
	
	/**
	 * Constructs a new SinglyLinkedList object.
	 */
	public SinglyLinkedList(){
		head = null;
		tail = head;
		iterator = head;
		countNodes = 0;
	}
	
	/**
	 * reset the iterator to the beginning of the list That is, set a reference to the head of the list.
	 */
	public void reset(){
		iterator = head;
	}
	
	/**
	 * return the Object pointed to by the iterator and increment the iterator to the next node in the list. 
	 * This reference becomes null if the object returned is the last node on the list.
	 */
	public java.lang.Object next(){
		ObjectNode temp = iterator;
		iterator = iterator.getLink();
		return temp.getData();
	}
	
	/**
	 * true if the iterator is not null
	 */
	public boolean hasNext(){
		if(iterator != null)
			return true;
		return false;
	}
	
	/**
	 * Add a node containing the Object c to the head of the linked list.
	 * @Parameters:
	 * c -- a single Object This method increments a count of the number of nodes on the list. 
	 * The count is returned by countNodes.
	 */
	public void addAtFrontNode(java.lang.Object c){
		if(head == null){
			head = new ObjectNode(c,null);
			tail = head;
		}
		else{
			ObjectNode n = new ObjectNode(c,head); 
			head = n;
		}
		countNodes++;
	}
	
	/**
	 * Add a node containing the Object c to the end of the linked list. 
	 * No searching of the list is required. The tail pointer is used to access the last node in O(1) time.
	 * @Parameters:
	 * c - -- a single Object
	 */
	public void addAtEndNode(java.lang.Object c){
		if(tail == null){
			tail = new ObjectNode(c,null);
			head = tail;
		}
		else{
			tail.setLink(new ObjectNode(c, null));
		    tail = tail.getLink();
		}
		countNodes++;
	}
	
	/**
	 * Counts the number of nodes in the list
	 * @Returns:
	 * the number of nodes in the list between head and tail inclusive. No list traversal is performed. 
	 * Simply return the value of countNodes.
	 */
	public int countNodes(){
		return countNodes;
	}
	
	/**
	 * Returns a reference (0 based) to the object with list index i. *
	 * Parameters:
	 * i -
	 * @Returns:
	 * reference to an object with list index i. The first object in the list is at position 0.
	 */
	public java.lang.Object getObjectAt(int i){
		ObjectNode temp = head;
		while(i != 0){
			temp = temp.getLink();
			i--;
		}
		return temp;
	}
	
	/**
	 * Returns the data in the tail of the list
	 * @Returns:
	 * the data in the tail of the list
	 */
	public java.lang.Object getLast(){
		return tail.getData();
	}
	
	/**
	 * Returns the list as a String
	 * @Overrides:
	 * toString in class java.lang.Object
	 * @Returns:
	 * a String containing the Objects in the list
	 */
	public java.lang.String toString(){
	   StringBuilder str = new StringBuilder();
	   reset();
	   if(iterator == null)
		   return null;
	   while(hasNext()){
		   str.append(next());
	   }
	   reset();
	   return str.toString();
	}
	

}
