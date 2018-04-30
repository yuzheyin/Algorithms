package Part1;

public class RedBlackNode {
	
	private int color;
	private String data;
	private RedBlackNode p;
	private RedBlackNode lc;
	private RedBlackNode rc;
	
	/**
	 * Construct a RedBlackNode with data, color, parent pointer, left child pointer and right child pointer.
	 * @param data a simple value held in the tree
	 * @param color either RED or BLACK
	 * @param p parent pointer
	 * @param lc pointer to the left child (will be null only for the node that represents all external nulls.
	 * @param rc pointer to the right child (will be null only for the node that represents all external nulls.
	 */
	public RedBlackNode(java.lang.String data,
            int color,
            RedBlackNode p,
            RedBlackNode lc,
            RedBlackNode rc){
		
		this.data = data;
		this.color = color;
		this.p = p;
		this.lc = lc;
		this.rc = rc;
		
	}
	
	/**
	 * The toString() methods returns a string representation of the RedBlackNode.
	 */
	public java.lang.String toString(){
		return data+ String.valueOf(color);
	}
	
	/**
	 * The getColor() method returns RED or BLACK.
	 */
	public int getColor(){
		return color;
	}

	/**
	 * The getData() method returns the data in the node.
	 * @return
	 */
	public java.lang.String getData(){
		return data;
	}

	/**
	 * The getLc() method returns the left child of the RedBlackNode.
	 * @return
	 */
	public RedBlackNode getLc(){
		return lc;
	}

	/**
	 * The getRc() method returns the right child of the RedBlackNode.
	 * @return
	 */
	public RedBlackNode getRc(){
		return rc;
	}
	
	/**
	 * The getP() method returns the parent of the RedBlackNode.
	 * @return
	 */
	public RedBlackNode getP(){
		return p;
	}

	/**
	 * The setColor() method sets the color of the RedBlackNode.
	 * @param color
	 */
	public void setColor(int color){
		this.color = color;
	}

	/**
	 * The setData() method sets the data or key of the RedBlackNode.
	 * @param data
	 */
	public void setData(java.lang.String data){
		this.data = data;
	}
	
	/**
	 * The setLc() method sets the left child of the RedBlackNode.
	 * @param lc
	 */
	public void setLc(RedBlackNode lc){
		this.lc = lc;
	}

	/**
	 * The setRc() method sets the right child of the RedBlackNode.
	 * @param rc
	 */
	public void setRc(RedBlackNode rc){
		this.rc = rc;
	}
	
	/**
	 * The setP() method sets the parent of the RedBlackNode.
	 * @param p
	 */
	public void setP(RedBlackNode p){
		this.p = p;
	}

}
