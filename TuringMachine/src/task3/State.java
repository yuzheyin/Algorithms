package task3;

import java.util.HashSet;
import java.util.Set;

public class State {
	
	int label;
	Set<Transition> transitions;
	
	State(int label){
		this.label = label;
		transitions = new HashSet<>();
	}
	
	public void addTransition(Transition t){
		
		transitions.add(t);
	}
	
	
}
