package Suanfa;

import java.math.BigInteger;

public class Cal_binomial_coe {
	public static void main(String[] args) {
		
		//System.out.println(recurisve_chose(10000,3));
		Stopwatch timer1 = new Stopwatch();
		System.out.println(burte_bin(10000,30));
		System.out.println(timer1.elapsedTime());
		
		Stopwatch timer2 = new Stopwatch();
		System.out.println(dynamic_chose(10000,30));
		System.out.println(timer2.elapsedTime());
		
	}
	public static  BigInteger dynamic_chose(int n, int k) {
		BigInteger[][] triangle = new BigInteger[n+1][n+1];
		
		for(int i = 1; i <= n; i ++) {
			for(int j = 0; j <= Math.min(k, i); j++) {
				if(i == j || j== 0) {
					triangle[i][j] = BigInteger.valueOf(1);
				}
				else {
					triangle[i][j] = triangle[i-1][j].add(triangle[i-1][j-1]);
				}
			}
		}
		
		return triangle[n][k];
		
		
		
		
	}
	public static long recurisve_chose(long n, long k) {
		
		if(k == 0 || n == k) {
			return 1;
		}
		else {
			return recurisve_chose(n-1,k-1) + recurisve_chose(n-1,k);
		}
	}
	public static BigInteger burte_bin(long n, long k) {
		BigInteger a = new BigInteger("1");
		BigInteger num1 = a;
		BigInteger num2 = a;
		for(long i = n; i > n-k; i--) {
			BigInteger temp_i = BigInteger.valueOf(i);
			num1 = num1.multiply(temp_i);
		}
		for(long j = 1; j < k+1; j++) {
			num2 = num2.multiply(BigInteger.valueOf(j));
		}
		return num1.divide(num2);
	}
	
}
