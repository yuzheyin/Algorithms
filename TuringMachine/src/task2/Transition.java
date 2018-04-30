package task2;

public class Transition {
	
	static boolean RIGHT = true;
	static boolean LEFT = false;
	char before;
	char after;
	boolean right; //true is right, false is left
	int nextState;
	
	Transition(char before, char after, boolean right, int nextState){
		this.before = before;
		this.after = after;
		this.right = right;
		this.nextState = nextState;
	}

}
