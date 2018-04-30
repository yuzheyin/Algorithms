package Part1;

public class RedBlackTree {
	
	private static final int BLACK = 0;
	private static final int RED = 1;
	private RedBlackNode n;
	private RedBlackNode root;
	private int size, recentCompares;
	
	public RedBlackTree(){
		
		n = new RedBlackNode("-1", BLACK, null, null, null);
		root = n;
		size = 0;
		
	}
	
	/**
	 * number of values inserted into the tree.
	 */
	public int getSize(){  // Big ¦È notation : ¦È(1)
		return size;
	}
	
	/**
	 * Perform an inorder traversal of the tree. 
	 * The inOrderTraversal(RedBlackNode) method is recursive and displays the content of the tree. 
	 * It makes calls on System.out.println(). This method would normally be private.
	 */
	public void inOrderTraversal(RedBlackNode t){   // Big ¦È notation : ¦È(n)
		if(t == n)
			return;
		inOrderTraversal(t.getLc());
		System.out.println("[Data = "+t.getData()+":Color = " + (t.getColor()==0? "BLACK":"RED")+
				":Parent = "+t.getP().getData()+":LC = "+t.getLc().getData()+":RC = "+t.getRc().getData()+"]" );
		inOrderTraversal(t.getRc());
	}
	
	/**
	 * The no argument inOrderTraversal() method calls the recursive inOrderTraversal(RedBlackNode) - passing the root.
	 */
	public void inOrderTraversal(){   // Big ¦È notation : ¦È(n)
		inOrderTraversal(root);
	}

	/**
	 * Perform a reverseOrder traversal of the tree. 
	 * The reverseOrderTraversal(RedBlackNode) method is recursive and displays the content of the tree in reverse order. 
	 * This method would normally be private.
	 * @param t
	 */
	public void reverseOrderTraversal(RedBlackNode t){   // Big ¦È notation : ¦È(n)
		if(t == n)
			return;
		reverseOrderTraversal(t.getRc());
		System.out.println("[Data = "+t.getData()+":Color = " + (t.getColor()==0? "BLACK":"RED")+
				":Parent = "+t.getP().getData()+":LC = "+t.getLc().getData()+":RC = "+t.getRc().getData()+"]" );
		reverseOrderTraversal(t.getLc());
	}
	
	/**
	 * The no argument reverseOrderTraversal() method calls the recursive reverseOrderTraversal(RedBlackNode) - passing the root.
	 */
	public void reverseOrderTraversal(){  // Big ¦È notation : ¦È(n)
		reverseOrderTraversal(root);
	}
	
	/**
	 * The insert() method places a data item into the tree.
	 * @param value
	 */
	public void insert(java.lang.String value){  // Big ¦È notation : ¦È(lg(n))
		RedBlackNode y = n;
		RedBlackNode x = root;
		RedBlackNode z = new RedBlackNode(value, RED, null, null, null);
		while(x != n){
			y = x;
			if(value.compareTo(x.getData()) < 0)
				x = x.getLc();
			else
				x = x.getRc();
		}
		
		z.setP(y);
		if(y == n){
			root = z;
		}
		else{
			if(value.compareTo(y.getData()) < 0)
				y.setLc(z);
			else
				y.setRc(z);
		}
		
		z.setLc(n);
		z.setRc(n);
		RBInsertFixup(z);
		
		size++;
		
	}
	
	/**
	 * leftRotate() performs a single left rotation. 
	 * It executes the following algorithm from CLR.
	 * pre: right[x] != nil[T]
	 * pre: root's parent is nill[T]
	 * @param x
	 */
	public void leftRotate(RedBlackNode x){ // Big ¦È notation : ¦È(1)
		RedBlackNode y = x.getRc();
		x.setRc(y.getLc());
		y.getLc().setP(x);
		y.setP(x.getP());
		
		if(x.getP() == n)
			root = y;
		else{
			if(x == x.getP().getLc())
				x.getP().setLc(y);
			else
				x.getP().setRc(y);
		}
		
		y.setLc(x);
		x.setP(y);
	}
	
	/**
	 * rightRotate() performs a single right rotation  
	 * It executes the following algorithm from CLR.
	 *  pre: left[x] != nil[T]
	 *  pre: root's parent is nill[T]
	 */
	public void rightRotate(RedBlackNode x){  // Big ¦È notation : ¦È(1)
		RedBlackNode y = x.getLc();
		x.setLc(y.getRc());
		y.getRc().setP(x);
		y.setP(x.getP());
		
		if(x.getP() == n)
			root = y;
		else{
			if(x == x.getP().getLc())
				x.getP().setLc(y);
			else
				x.getP().setRc(y);
		}
		
		y.setRc(x);
		x.setP(y);

	}
	
	/**
	 * Fixing up the tree so that Red Black Properties are preserved.
	 */
	public void RBInsertFixup(RedBlackNode z){ // Big ¦È notation :worst case ¦È(lg(n)) best case ¦È(1)
		RedBlackNode y;
		while(z.getP().getColor() == RED){
			if(z.getP() == z.getP().getP().getLc()){
				y = z.getP().getP().getRc();
				if(y.getColor() == RED){
					z.getP().setColor(BLACK);
					y.setColor(BLACK);
					z.getP().getP().setColor(RED);
					z = z.getP().getP();
				}
				else{
					if(z == z.getP().getRc()){
						z = z.getP();
						leftRotate(z);
					}
					z.getP().setColor(BLACK);
					z.getP().getP().setColor(RED);
					rightRotate(z.getP().getP());
				}
			}
			else{
				y = z.getP().getP().getLc();
				if(y.getColor() == RED){
					z.getP().setColor(BLACK);
					y.setColor(BLACK);
					z.getP().getP().setColor(RED);
					z = z.getP().getP();
				}
				else{
					if(z == z.getP().getLc()){
						z = z.getP();
						rightRotate(z);
					}
					z.getP().setColor(BLACK);
					z.getP().getP().setColor(RED);
					leftRotate(z.getP().getP());
				}
			}
		}
		
		root.setColor(BLACK);
	}
	
	/**
	 * The boolean contains() returns true if the String v is in the RedBlackTree and false otherwise.
	 *  It counts each comparison it makes in the variable recentCompares.
	 */
	public boolean contains(java.lang.String v){ // Big ¦È notation : best case ¦È(1) worst case ¦È(n)
		recentCompares = 0;
		RedBlackNode x = root;
		while(x != null){
			recentCompares++;
			if(v.compareTo(x.getData()) == 0){
				return true;
			}
			else if(v.compareTo(x.getData()) < 0){
				x = x.getLc();
			}
			else{
				x = x.getRc();
			}
		}
		
		return false;
	}
	
	public int getRecentCompares(){ // Big ¦È notation : ¦È(1)
		return recentCompares;
	}
	
	/**
	 * The method closeBy(v) returns a value close to v in the tree.
	 * If v is found in the tree it returns v.
	 * @param v
	 * @return
	 */
	public java.lang.String closeBy(java.lang.String v){  // Big ¦È notation : ¦È(lg(n))
		RedBlackNode x = root;
		RedBlackNode temp = x;
		while(x != n){
			temp = x;
			if(v.compareTo(x.getData()) == 0){
				return v;
			}
			else if(v.compareTo(x.getData()) < 0){
				x = x.getLc();
			}
			else{
				x = x.getRc();
			}
		}
		
		return temp.getData();
	}
	
	/**
	 * This a recursive routine that is used to compute the height of the red black tree. 
	 * It is called by the height() method. 
	 * The height() method passes the root of the tree to this method. 
	 * @param t
	 * @return
	 */
	public int height(RedBlackNode t){   // Big ¦È notation : ¦È(n)
		if(t == n)
			return -1;
		return Math.max(height(t.getLc())+1, height(t.getRc())+1);
	}

	/**
	 * This method calls the recursive method.
	 * @return
	 */
	public int height(){  // Big ¦È notation : ¦È(n)
		return height(root);
	}
	
	/**
	 * This method displays the RedBlackTree in level order.
	 *  It first displays the root. Then it displays all children of the root. 
	 *  Then it displays all nodes on level 3 and so on. 
	 */
	public void levelOrderTraversal(){  // Big ¦È notation : ¦È(n)
		Queue q = new Queue();
		RedBlackNode x = root;
		q.enQueue(x);
		while(!q.isEmpty()){
			x = (RedBlackNode) q.deQueue();

			if(x.getLc() != n)
				q.enQueue(x.getLc());
			if(x.getRc() != n)
				q.enQueue(x.getRc());
			
			System.out.println("[Data = "+x.getData()+":Color = " + (x.getColor()==0? "BLACK":"RED")+
					":Parent = "+x.getP().getData()+":LC = "+x.getLc().getData()+":RC = "+x.getRc().getData()+"]" );
		}
		
	}
	
	/*
	 * Compute the diameter of tree
	 */
	public int getDiameter(){  // Big ¦È notation : ¦È(n*n)
        if(root == n)
            return 0;
        Queue q = new Queue();
        RedBlackNode current;
        int max = 0;
        int temp;
        q.enQueue(root); 
        while(!q.isEmpty()){
            current = (RedBlackNode) q.deQueue();
            if(current.getLc() != n)
                q.enQueue(current.getLc());
            if(current.getRc() != n)
                q.enQueue(current.getRc()); 

            temp = height(current.getLc()) + height(current.getRc())+2;
            max = max<temp? temp:max;
        }
        
        return max;
	}
	
	 public static void main(String[] args) {

	        RedBlackTree rbt = new RedBlackTree();

	        for(int j = 1; j <= 5; j++) {
	        	rbt.insert(""+j);
	        }
	        
	        // after 1..5 are inserted
	        System.out.println("RBT in order");
	        rbt.inOrderTraversal();
	        System.out.println("RBT level order");
	        rbt.levelOrderTraversal();
	       // is 3 in the tree
	        
	        if(rbt.contains(""+3)) System.out.println("Found 3");
	        else System.out.println("No 3 found"); 
	        
	        // display the height
	        System.out.println("The height is " + rbt.height());   
	        System.out.println("diameter"+rbt.getDiameter());
	    }




}
