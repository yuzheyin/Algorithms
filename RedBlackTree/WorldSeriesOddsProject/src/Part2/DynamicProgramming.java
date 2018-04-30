package Part2;

public class DynamicProgramming {
	public static void main(String args[]){
		int i = 50;
		int j = 40;
		float p[][] = new float[i+1][j+1];
		
		for(int t = 0; t < i+1; t++){
			for(int q = 0; q < j+1; q++){
				if(t== 0 && q == 0)
					continue;
				if(t == 0 && q > 0)
					p[t][q] = 1;
				else if(q == 0 && t > 0)
					p[t][q] = 0;
				else
					p[t][q] = (p[t-1][q] + p[t][q-1])/2;
			}
		}
		System.out.println("P(i,j) = "+p[i][j]);
	}
}
