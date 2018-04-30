package task2;

import java.util.HashSet;
import java.util.Set;

public class Turing {
	
	int stateNumber;
	Set<State> states;
	int head = 0;
	State currentState;
	int stateLabel = 0;
	boolean nextMove; // right is true, left is false
	char[] tape = new char[100];
	
	Turing(int stateNumber){
		this.stateNumber = stateNumber;
		states = new HashSet<>();
		for(int i  = 0; i < 100; i++){
			tape[i] = 'B';
		}
	}
	
	public void addState(State s){
		states.add(s);
	}
	
	public int execute(String inTape){
		int result = 0;
		for(int i = 0; i < inTape.length(); i++){
			tape[i] = inTape.charAt(i);
		}
		while(stateLabel != 6){
			for(State s : states){
				if(s.label == stateLabel){
					currentState = s;
					break;
				}
			}
			for(Transition t : currentState.transitions){
				if(t.before == tape[head]){				
					tape[head] = t.after;
					stateLabel = t.nextState;
					nextMove = t.right;
					break;
				}
			}
			if(nextMove)
				head++;
			else
				head--;
		}
		
		for(int j = 0; j < tape.length; j++){
			if(tape[j] == '0')
				result++;
		}
		return result;
	}

}
