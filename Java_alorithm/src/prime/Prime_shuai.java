package prime;

import java.util.Arrays;

public class Prime_shuai {
	public static void main(String[] args) {
		int max = 100;
		boolean[] flags = new boolean[max];
		int[] primes = new int[max/3 + 1];
		int init = 0;
		
		for(int m = 2; m<max; m++) {
			if(!flags[m]) {
				primes[init++] = m;
				//System.out.println(init);
				for(int n =m*m;n<max;n+=m) {//从m的平方开始筛选
					flags[n] = true;
				}
			}
		}
		System.out.println(Arrays.toString(primes));
		
	}
}
