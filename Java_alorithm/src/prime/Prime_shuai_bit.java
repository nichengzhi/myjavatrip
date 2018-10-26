package prime;

//import java.util.Arrays;
import Suanfa.Stopwatch;


public class Prime_shuai_bit {
	public static void main(String[] args) {
		int max = 10000000;
		
		int[] primes = new int[max/3 + 1];
		
		int[] flags2 = new int[max / 32 + 1];
		int pi = 0;
		Stopwatch a1 = new Stopwatch();
		for (int m = 2; m < max ; m ++) {
		    if ((((flags2[m / 32] >> (m % 32)) & 1) == 0)) {
		        primes[pi++] = m;
		        for(int n = m; n < max; n += m) {
		            flags2[n / 32] |= (1 << (n % 32));
		        }
		    }
		}
		System.out.println(a1.elapsedTime());
		//System.out.println(Arrays.toString(primes));
		
	}
}
