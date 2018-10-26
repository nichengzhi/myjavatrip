package Sort_algorithm;

import java.util.Arrays;

public class Merge_sort {
	//private static Comparable[] temp;
	public Merge_sort() {};
	
	public static boolean less(Comparable a,Comparable b) {
		return a.compareTo(b) < 0;
	}
	
	public static void merge(Comparable[] a,Comparable[] temp, int low, int mid,int high) {
		int i = low;
		int j = mid+1;
		int t = 0;
		
		while(i <= mid && j <=high) {
			if(less(a[j], a[i])) {
				temp[t++] = a[j++];
			}
			else {
				temp[t++] = a[i++];
			}
		}
		
		while(i <= mid) {
			temp[t++] = a[i++];
		}
		while(j <= high) {
			temp[t++] = a[j++];
		}
		t=0;
		while(low<=high) {
			a[low++] = temp[t++];
		}
	}
	public static void sort(Comparable[] a) {
		
		Comparable[] temp = new Comparable[a.length];
		sort(a,temp,0,a.length-1);// index 从0开始！！！
	}
	private static void sort(Comparable[] a,Comparable[] temp,int low,int high) {
		if(low >= high) return;
		int mid = low + (high - low)/2;
		sort(a,temp,low,mid);
		sort(a,temp,mid+1,high);
		merge(a,temp,low,mid,high);
	}
	public static void main(String[] args) {
		String[] a = {"a","g","c","s","k","o","p"};
		Merge_sort.sort(a);
		
		//Integer[] b = {4,5,7,8,1,2,3,6};
		// 93,34,55,22
		//Merge_sort.sort(b);
		System.out.println(Arrays.toString(a));
	}
}



