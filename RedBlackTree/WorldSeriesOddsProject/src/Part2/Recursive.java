package Part2;

public class Recursive {
	
	public static void main(String args[]){
		int i = 20;
		int j = 23;
		float P = compute(i, j);
		System.out.println("P(i,j) = "+P);
	}
	
	static float compute(int i, int j){
		if(i == 0 && j > 0)
			return 1;
		if(j == 0 && i > 0)
			return 0;
		return (float)(compute(i-1,j)+compute(i,j-1))/2;
	}

}
