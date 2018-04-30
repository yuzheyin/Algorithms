package task3;

public class TuringDecider {

	 public static void main( String args[]) {
        Turing machine1 = new Turing(7);   

        State s0 = new State(0);
        State s1 = new State(1);
        State s2 = new State(2);
        State s3 = new State(3);
        State s4 = new State(4);
        State s5 = new State(5);
        State s6 = new State(6);
        
   
        s0.addTransition(new Transition('0','B',Transition.RIGHT,1));
        s0.addTransition(new Transition('1','0',Transition.RIGHT,4)); // 1 appears first, reject
        s0.addTransition(new Transition('K','B',Transition.RIGHT,3)); // 0s all parsed, check if 1s all parsed or not 
        
        s1.addTransition(new Transition('0','0',Transition.RIGHT,1));
        s1.addTransition(new Transition('K','K',Transition.RIGHT,1));
        s1.addTransition(new Transition('1','K',Transition.LEFT,2));
        s1.addTransition(new Transition('B','B',Transition.LEFT,5)); //not enough 1, reject
        
        s2.addTransition(new Transition('K','K',Transition.LEFT,2));
        s2.addTransition(new Transition('0','0',Transition.LEFT,2));
        s2.addTransition(new Transition('B','B',Transition.RIGHT,0));
        
        s3.addTransition(new Transition('K','B',Transition.RIGHT,3));
        s3.addTransition(new Transition('1','B',Transition.RIGHT,4)); // Too much 1, reject
        s3.addTransition(new Transition('0','B',Transition.RIGHT,4)); // Too much 0, reject
        s3.addTransition(new Transition('B','B',Transition.RIGHT,6)); // accept
        
        s4.addTransition(new Transition('1','B',Transition.RIGHT,4));
        s4.addTransition(new Transition('K','B',Transition.RIGHT,4));
        s4.addTransition(new Transition('0','B',Transition.RIGHT,4));
        s4.addTransition(new Transition('B','B',Transition.RIGHT,7));// reject

        s5.addTransition(new Transition('K','B',Transition.LEFT,5));
        s5.addTransition(new Transition('0','B',Transition.LEFT,5));
        s5.addTransition(new Transition('B','B',Transition.RIGHT,7));// reject
   
        machine1.addState(s0); 
        machine1.addState(s1);
        machine1.addState(s2);
        machine1.addState(s3);
        machine1.addState(s4);
        machine1.addState(s5);
        
        String inTape = "00000000001111111111";     // Define some input

        System.out.println(inTape);

        String decision = machine1.execute(inTape);  // Execute the machine

        System.out.println(decision);  // Show the machine¡¯s output
	 }


}