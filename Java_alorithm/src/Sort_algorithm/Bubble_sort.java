package Sort_algorithm;

import java.util.Arrays;

public class Bubble_sort {
	
	public Bubble_sort() {}
	
	public static void sort(Comparable[] a) {
		
		for(int i = a.length-1;i >0;i--) {
			boolean sorted = true;
			for(int j = 0; j < i;j++) {
				if(a[j].compareTo(a[j+1])>0) {
					Comparable temp = a[j+1];
					a[j+1] = a[j];
					a[j] = temp;
					sorted = false;
				}
				
			}
			if(sorted) return;
		}
		
	}
	
	public static void main(String[] args) {
		
		Integer[] b = {4,5,7,8,1,2,3,6};
		Bubble_sort.sort(b);
		System.out.println(Arrays.toString(b));
	}

}
