package zujian;

public class Test {
	public static void main(String[] args) {
		Counter c1 = new Counter("ones");
		c1.increment();
		Counter c2 = c1;
		c2.increment();
		StdOut.println(c1);
	}
}
