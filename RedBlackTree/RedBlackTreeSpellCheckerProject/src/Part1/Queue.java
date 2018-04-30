package Part1;

public class Queue {
	
	private int rear, front, itemsCount, size;
	private Object[] queue;
	
	/**
	 * Construct a small array to store the queue
	 * Pre: Memory is available. Post: Array created and indexes established.
	 */
	public Queue(){
		size = 5;
		queue = new Object[size];
		rear = 0;
		front = 0;
		itemsCount = 0;
	}
	
	/**
	 * Boolean method returns true on empty queue, false otherwise.
	 * @return
	 */
	public boolean isEmpty(){
		if (itemsCount == 0)
			return true;
		return false;
		
	}
	
	/**
	 * Boolean method returns true if queue is currently at capacity, false otherwise. 
	 * If full returns true and additional enqueue calls are made, the queue will expand in size. 
	 * @return
	 */
	public boolean isFull(){
		if((rear+1)%size == front){
			size *= 2;
			Object[] newQueue = new Object[size];
			for(int i = 0; i < queue.length; i++){
				newQueue[(front+i) % size] = queue[(front+i) % (size/2)];
			}
			queue = newQueue;
			return true;
		}
		else
			return false;
		
	}
	
	/**
	 * Object method removes and returns reference in front of queue. 
	 * Pre-condition: queue not empty
	 * @return
	 */
	public java.lang.Object deQueue(){
		Object o = queue[front];
		front = (front + 1) % size;
		itemsCount--;
		return o;
	}
	
	/**
	 * Add an object reference to the rear of the queue. 
	 * Pre-condition Memory is available for doubling queue capacity when full. 
	 * Post-condition: queue now contains x in the rear.
	 * @param x
	 */
	public void enQueue(java.lang.Object x){
		isFull();                // Check if the queue is full and expand if necessary 
		if(itemsCount == 0){
			front = 0;
			rear = 0;
		}
		else{
			rear = (rear + 1) % size;
		}
		
		queue[rear] = x;
		itemsCount++;
	}
	
	/**
	 * Method getFront returns the front of the queue without removing it. 
	 * Pre-condition: queue not empty
	 */
	public java.lang.Object getFront(){
		return queue[front];
	}
	
	/**
	 * The toString method returns a String representation of the current queue contents.
	 */
	public java.lang.String toString(){
		StringBuilder s = new StringBuilder();
		for(int i = 0; i < itemsCount; i++){
			s.append(queue[front+i]);
		}
		
		return s.toString();
	}
	
	public static void main(java.lang.String[] a){
		Queue q = new Queue();
		
		q.enQueue("Hi");
		q.enQueue(" I ");
		q.enQueue("am ");
		q.enQueue("testing!");
		q.enQueue("111");
		q.enQueue("222");
		q.enQueue("333");
		q.enQueue("444");
		q.enQueue("How");
		System.out.println(q.isFull());
		q.enQueue(" are");
		System.out.println(q.isFull());
		q.enQueue(" you");
		q.enQueue("????????????");
		System.out.println(q.toString());
		System.out.println(q.front);
		System.out.println(q.deQueue());
		System.out.println(q.deQueue());
		System.out.println(q.itemsCount);
		System.out.println(q.size);
		
	}





	
	

}
