package prime;

import java.util.Random;

public class Miller_Rabin {
	public Miller_Rabin() {}
	
	public static long powermod(long a, long b, long c) {
		long res = 1;
		long r = a;
		while(b > 0) {
			if((b&1) == 1) {
				res = (res * r) % c;
			}
			r = (r*r)%c;
			b>>=1;
		}
		return res;
	}
	
	public static boolean miller(long n) {
		/** base case **/
        if (n == 0 || n == 1)
            return false;
        /** base case - 2 is prime **/
        if (n == 2)
            return true;
        /** an even number other than 2 is composite **/
        if (n % 2 == 0)
            return false;
        
		int k = 0;
		long d = n -1;
		
		while((d&1)==0) {
			k++;
			d>>=1;
		}
		long q = d/k;//这里有错
		Random rand = new Random();
		long r = Math.abs(rand.nextLong());
		long a = r%(n-1) +1;
		boolean isprime = false;
		if(powermod(a,q,n) == 1) {
			return true;
		}
		else {
			long h = 2;
			for(int i =0; i<=k-1;i++) {
				if(powermod(a,h * q,n) == -1) {
					isprime = true;
					break;
				}
				h = h*2;
			}
		}
		return isprime;
		
	}
	
	public static void main(String[] args) {
		System.out.println(miller(561));
	}
}
