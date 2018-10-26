package Suanfa;
// 这样可以把算法复杂度从 2^n 降到 n
public class Dynamicfibnacci {
	public static void main(String[] args) {
		System.out.println(dynamicfib(5));
		System.out.println(5%3);
	}
	
	public static int dynamicfib(int n) {
		int fib0 = 0;
		int fib1 = 1;
		int fib2 = 1;
		if(n == 0)
			return fib0;
		if(n == 1)
			return fib1;
		if(n == 2)
			return fib2;
		
		for(int i = 1; i <= n; i++) {
			fib0 = fib1;
			fib1 = fib2;
			fib2 = fib0 + fib1;
		}
		return fib2;
	}
}
