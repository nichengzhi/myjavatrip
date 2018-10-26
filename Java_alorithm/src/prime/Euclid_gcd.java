package prime;

public class Euclid_gcd {
	public static long gcd(long a, long b) {
		if(a%b == 0) {
			return b;
		}
		else {
			return gcd(b,a%b);
		}
	}
	
	public static void main(String[] main) {
		System.out.println(gcd(30,150));
	}
}
