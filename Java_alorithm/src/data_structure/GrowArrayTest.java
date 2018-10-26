package data_structure;

import java.util.ArrayList; // used to be Vector

public class GrowArrayTest {
	
	public static void test1(int n) {
		Grow_Array a = new Grow_Array();
		for (int i = 0; i < n; i++)
			a.insertEnd(i);
	}

	public void test2(int n) {
		Grow_Array a = new Grow_Array(n);
		for (int i = 0; i < n; i++)
			a.insertEnd(i);
	}

	public static void test3(int n) {
		ArrayList<Integer> a = new ArrayList<>();
		for (int i = 0; i < n; i++)
			a.add(i);
	}

	public static void test4(int n) {
		ArrayList<Integer> a = new ArrayList<>(n);
		for (int i = 0; i < n; i++)
			a.add(i);
	}

public static void main(String[] args) {
	final int n = 1000000000;

	// warm up by running the function once
	//test1(n);

	long t0 = System.nanoTime();
	test1(n);
	long t1 = System.nanoTime();
	System.out.println((t1-t0)*1e-9);
	
	/*long t2 = System.nanoTime();
	test3(n);
	long t3 = System.nanoTime();
	System.out.println((t3-t2)*1e-9);*/
	
	//	test2(n);
	//	test3(n);
	// test4(n);
}
}
