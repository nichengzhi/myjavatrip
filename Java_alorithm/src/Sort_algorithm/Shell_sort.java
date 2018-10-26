package Sort_algorithm;

public class Shell_sort {
	public Shell_sort() {}
	
	private static void exch(Object[] a,int i, int j) {
		Object swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}
	
	public static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}
	
	
	/*public static void sort(Comparable[] a) {
		int n = a.length;
		
		for(int gap = n/2; gap>=1; gap/=2) {
			for(int i =0; i<=n-gap-1;i++) {
				for(int j=i;j<=n;j=j+gap) {
					for(int h = j; h>0&&less(a[h],a[h-gap]);h--) {//insert sort part
						exch(a,h-gap,h);
					}
				}
			}
		}
	}*/
	
	public static void sort(Comparable[] a) {
		int n = a.length;
		int gap = n/2;
		while(gap>=1) {
			for(int i =gap;i<n;i++) {
				for(int j = i; j>0&& less(a[j],a[j-gap]);j=j-gap) {
					exch(a,j-gap,j);
				}
				
			}
			gap = gap/2;
		}
	}
	
	public static void main(String[] args) {
		String[] a = {"a","g","c","s","k","o","p"};
		Shell_sort.sort(a);
		for(String h:a) {
			System.out.println(h);
		}
	}
}
