package homework;

import java.util.ArrayList;

/*
 * HW1a - Compute primes
 * CHENGZHI NI
 */
public class Hw1a_prime {
	public static long[] euler_prime_sieve(long max) {
		long[] primes = new long[(int)(max/3)+1];
		boolean[] isprime = new boolean[(int)max+1];
		int count =0;
		for(int m = 2; m<=max; m++) {
			if(!(isprime[m])) {
				primes[count++] =m;
			}
			for(int j=0;j<count && m*primes[j] <= max;j++) {
				isprime[(int)(primes[j]*m)] = true;
				if(m%primes[j]==0) {
					break;
				}
			}
			
		}
		return primes;
	}
	public static long segment_prime_sieve(long low, long high) {
		long[] sqrt_primes = euler_prime_sieve((int)Math.sqrt(high));
		boolean[] isprime = new boolean[(int) (high-low+1)];
		long count = 0;
		for(long prime:sqrt_primes) {
			if(prime == 0) {
				break;
			}
			long init = (low/prime)*prime;
			
			for(long i =init;i<=high;i=i+prime) {
				
				if(i>=low) {
					isprime[(int)(i-low)] = true;
					
				}
				
			}
			
		}
		for(int i = 0; i < isprime.length;i++) {
			if(!isprime[i]) {
				System.out.println(i+low);
			}
		}
		for(boolean check:isprime) {
			if(!check) {
				count++;
			}
		}
		return count;
	}
	public static ArrayList<Integer> segment_prime_sieve2(long low, long high) {
		long[] sqrt_primes = euler_prime_sieve((int)Math.sqrt(high));
		boolean[] isprime = new boolean[(int) (high-low+1)];
		long count = 0;
		for(long prime:sqrt_primes) {
			if(prime == 0) {
				break;
			}
			long init = (low/prime)*prime;
			
			for(long i =init;i<=high;i=i+prime) {
				
				if(i>=low) {
					isprime[(int)(i-low)] = true;
					
				}
				
			}
			
		}
		ArrayList<Integer> re = new ArrayList<>();
		for(int i = 0; i < isprime.length;i++) {
			if(!isprime[i]) {
				re.add((int)(i+low));
			}
		}
		return re;
	}
	public static void main(String[] args) {
		//long a = 2000000100000L;
		
		System.out.println(segment_prime_sieve2(9126l,10000l));
	}
}
