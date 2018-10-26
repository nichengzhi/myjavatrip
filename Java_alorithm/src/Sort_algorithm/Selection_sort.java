package Sort_algorithm;

import java.util.Comparator;

public class Selection_sort {
	
	// This class should not be instantiated.
	private Selection_sort() {}
	private static void exch(Object[] a,int i, int j) {
		Object swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}
	//use comparable
	public static void sort(Comparable[] a) {
		int n = a.length;
		for(int i = 0; i < n;i++) {
			int min = i;
			for(int j = i+1; j<n;j++) {
				if(less(a[j],a[min])) min = j;
			}
			exch(a,i,min);
			assert isSorted(a,0,i);
		}
		assert isSorted(a);
	}
	
	public static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}
	public static boolean isSorted(Comparable[] a) {
		return isSorted(a, 0, a.length - 1);
	}
	public static boolean isSorted(Comparable[] a, int low,int high) {
		for(int i = low + 1; i < high; i++) {
			if(less(a[i],a[i-1])) {
				return false;
			}
		}
		return true;
	}
	// use comparator
	public static void sort(Object[] a, Comparator comparator) {
		int n = a.length;
		for(int i =0;i<n;i++) {
			int min = i;
			for(int j = i+1; i<n;j++) {
				if(less(a[j],a[min],comparator)) {
					min = j;
				}
			}
			exch(a,i,min);
		}
	}
	public static boolean less(Object a, Object b,Comparator comparator) {
		return comparator.compare(a, b) < 0;
	}
	
	public static void main(String[] args) {
		String[] a = {"a","g","c","s","k","o","p"};
		Selection_sort.sort(a);
		for(String h:a) {
			System.out.println(h);
		}
		
		
	}
}
