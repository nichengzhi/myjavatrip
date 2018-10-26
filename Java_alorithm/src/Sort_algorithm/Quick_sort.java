package Sort_algorithm;

import java.util.Arrays;

public class Quick_sort {
	public Quick_sort() {}
	
	public static void sort(Comparable[] a) {
		int low = 0;
		int high = a.length-1;
		sort(a, low, high);
	}
	private static boolean less(Comparable a, Comparable b) {
		return a.compareTo(b) < 0;
	}
	public static void sort(Comparable[] a,int low, int high) {
		if(low >= high) return;
		int pivot_index = separate(a,low,high);
		sort(a,low,pivot_index);
		sort(a,pivot_index+1,high);
	}
	
	public static int separate(Comparable[] a, int low, int high) {
		Comparable pivot = a[low];
		int i = low;
		int j = high;
		while(i<j) {
			while(j>i && less(pivot,a[j])) {
				j--;
			}
			if(i<j) {
				a[i] = a[j];
				i++;
			}
			while(j>i&&less(a[i],pivot)) {
				i++;
			}
			if(i<j) {
				a[j] = a[i];
				j--;
			}
		}
		a[i] = pivot;
		return i;
	}
	/*
	 * three way quick sort
	 * In this algorithm the array partitioned into three parts:
		1)one with is <v
		2)equal to v
		3)one with >v
 		where, v is pivot
		Starting with i equal to lo we process a[i] using the 3-way compare given us by the Comparable interface to handle the three possible cases: 
		a[i] less than v: exchange a[lt] with a[i] and increment both lt and i
		a[i] greater than v: exchange a[i] with a[gt] and decrement gt
		a[i] equal to v: increment i
this is the 3-way quick sort.
	 * 
	 */
	public static void main(String[] args) {
		
		Integer[] b = {4,5,7,8,1,2,3,6};
		Quick_sort.sort(b);
		System.out.println(Arrays.toString(b));
	}
}
