package homework;
/*
 * HW1b: Compute choose(n,r)
 * author:chengzhi ni
 */
import java.util.*;

public class hw1b {
	public static double choose(double[][] tri,int n, int r) {
		return tri[n][r];
	}

	public static void main(String[] args) {
		
		double[][] triangle = new double[501][501];
		for(int i = 1; i <501; i++) {
			for(int j = 0; j <= i;j++) {
				if(i == j || j== 0) {
					triangle[i][j] = 1.0;
				}
				else {
					triangle[i][j] = triangle[i-1][j] + triangle[i-1][j-1];
				}
			}
		}
		
		int numTrials = 100000000;
		Random rnd = new Random(0);
		long start = System.currentTimeMillis();
		for (int i = 0; i < numTrials; i++) {
			int n = rnd.nextInt(501);
			int r = rnd.nextInt(n+1);
			choose(triangle,n,r);
		}
		long end = System.currentTimeMillis();
		System.out.println("total time is " + (end-start)/1000.0);
		
		
	}
	
	
}