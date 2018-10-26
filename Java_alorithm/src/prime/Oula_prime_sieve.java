package prime;
import Suanfa.Stopwatch;
import java.util.Arrays;
import java.util.Collections;

public class Oula_prime_sieve {
	public static void main(String[] main) {
		int max = 10000000;
		int[] primes = new int[(max+1)/3+1];
		boolean[] isprime = new boolean[max+1];
		int count = 0;
		Stopwatch a1 = new Stopwatch();
		for(int m = 2; m <= max; m++) {
			if(!isprime[m]) {
				primes[count++] = m;
			}
			for(int j = 0; j < count && primes[j]*m <=max;j++ ) {
				isprime[primes[j]*m] = true;
				if(m % primes[j]==0) {
					break;
				}
			}
		}
		System.out.println(a1.elapsedTime());
		
	}
}
