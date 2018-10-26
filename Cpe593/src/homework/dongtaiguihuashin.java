package homework;

public class dongtaiguihuashin {
	 static int[] m = new int[600];
	 
	public static int g(int n) {
		
		
		if(m[n] != 0) {
			return m[n];
		}
		m[n] = g(n-1) - 2*g(n-2) + g(n-3);
		return m[n];
	}
	public static void main(String[] args) {
		m[0] = 1;
		 m[1] = 1;
		 m[2] = 1;
		 m[3] = 1;
		System.out.println(g(5));
	
		}
	
}
