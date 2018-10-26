package Sort_algorithm;

public class Insert_sort {
	private Insert_sort() {
		
	}
	
	private static void exch(Object[] a,int i, int j) {
		Object swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}
	public static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}
	
	public static void sort(Comparable[] a) {
		int n = a.length;
		
		for(int i =0; i <= n; i++) {
			for(int j = i; j>0 && less(a[j],a[j-1]);j--) {
				exch(a,j-1,j);
			}
		}
	}
	public static void main(String[] args) {
		String[] a = {"a","z","e","b"};
		Selection_sort.sort(a);
		for(String h:a) {
			System.out.println(h);
		}
	}
}
