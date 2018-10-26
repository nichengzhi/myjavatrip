package Suanfa;

public class Efficient_powern {
	public static long power(long n,long r) {//将算法复杂度降到log（n）
		if(r == 0) {
			return 1;
		}
		
		if(r == 1) {
			return n;
			
		}
		else {
			if(r%2 == 0) {
				return power(n*n,r/2);
			}
			else {
				return n*power(n*n,(r-1)/2);
			}
		}
	}                
	
	public static void main(String[] args) {
		System.out.println(power(3,10));
		System.out.println(1%5);
	}
}
