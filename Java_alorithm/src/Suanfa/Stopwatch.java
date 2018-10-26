package Suanfa;

public class Stopwatch {
	private final long start;
	public Stopwatch(){
		start = System.currentTimeMillis();
	}
	
	public double elapsedTime() {
		long end = System.currentTimeMillis();
		return (end - start)/1000.0;
	}
	
}
