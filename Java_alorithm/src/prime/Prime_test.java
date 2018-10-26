package prime;

import java.util.Arrays;

public class Prime_test {
	public static void main(String[] args) {
		int max = 10000;
		boolean[] flags = new boolean[max];
		int[] flags2 = new int[max / 32 + 1];
		int[] primes = new int[max/3 + 1];
		int[] primes2 = new int[max/3 + 1];
		int init = 0;
		int pi = 0;
		long start = System.currentTimeMillis();
		for(int m = 2; m<max; m++) {
			if(!flags[m]) {
				primes[init++] = m;
				for(int n =m*m;n<max;n+=m) {//从m的平方开始筛选
					flags[n] = true;
				}
			}
		}
		System.out.println(Arrays.toString(primes));
		long end = System.currentTimeMillis();
		System.out.println("total time is " + (end-start)/1000.0);
		long start2 = System.currentTimeMillis();
		for (int m = 2; m < max ; m ++) {
		    if ((((flags2[m / 32] >> (m % 32)) & 1) == 0)) {
		        primes2[pi++] = m;
		        for(int n = m; n < max; n += m) {
		            flags2[n / 32] |= (1 << (n % 32));
		        }
		    }
		}
		System.out.println(Arrays.toString(primes2));
		long end2 = System.currentTimeMillis();
		System.out.println("total time is " + (end2-start2)/1000.0);
	}
}
