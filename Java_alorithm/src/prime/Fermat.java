package prime;
import java.util.Random;

import Suanfa.Efficient_powern;

public class Fermat {
	public static boolean isPrime(long n, int times) {
		/** base case **/
        if (n == 0 || n == 1)
            return false;
        /** base case - 2 is prime **/
        if (n == 2)
            return true;
        /** an even number other than 2 is composite **/
        if (n % 2 == 0)
            return false;
        
        Random rand = new Random();
        for(int i =0;i<times;i++) {
        	long r = Math.abs(rand.nextLong());
        	long a = r%(n-1) +1;//保证生成一个1 到 n间的随机数
        	if(fastpowermod(a,n-1,n) != 1){
        		return false;
        	}
        	
        }
        return true;
	}
	
	public static long powermod(long a,long b, long c) {
		/*long d = a % c;
		long e = Efficient_powern.power(d,b);
		return e%c;*/
		long res = 1;
        for (int i = 0; i < b; i++)
        {
            res = (res*a)%c; 
        }
        return res % c;
	}
	public static long fastpowermod(long a,long b,long c) {
		long result = 1;
		long r = a;
		while(b>0) {
			if((b & 1) == 1) {
				result = (result * r) % c;
			}
			r = (r*r) % c;
			b >>= 1;
		}
		return result;
	}
	public static void main(String[] args) {
		
		long a = Efficient_powern.power(5,8);
		System.out.println(a);
		System.out.println(fastpowermod(11,560,561));
		System.out.println(isPrime(561, 5));
		
		
	}
}
