package Suanfa;

import java.util.Random;

public class DoublingTest {
	public static double timeTrial(int N) {
		int MAX = 1000000;
		int[] a = new int[N];
		for(int i = 0;i < N; i++)
			a[i] = getRandomNumberInRange(-MAX,MAX);
		Stopwatch timer = new Stopwatch();
		int cnt = Threesome.count(a);
		return timer.elapsedTime();
		
	}
	
	private static int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();//random.nextInt(5) return a number between [0,5]
		return r.nextInt((max - min) + 1) + min;
	}
	
	public static void main(String[] args){
		for(int N = 250; N < 10000; N += 250) {
			double time = timeTrial(N);
			System.out.println(time);
		}
	}
	

}

class Threesome{
	public static int count(int[] a) {
		int N = a.length;
		int cnt = 0;
		for(int i = 0; i < N; i++)
			for(int j = i+1; j < N; j++)
				for(int k = j+1; k < N; k ++)
					if(a[i] + a[k] + a[j] == 0)
						cnt++;
						
						
		return cnt;
	}
}
