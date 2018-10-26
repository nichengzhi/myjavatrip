package Sort_algorithm;

public class Sort_test {
	public static boolean less(Comparable a,Comparable b) {
		return a.compareTo(b) < 0;
	}
	public static void merge2(Comparable[] a,Comparable[] temp, int low, int mid,int high) {
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
    
	
	public static void main(String[] args) {
		Integer[] a = {4,5,7,8,1,2,3,6};
		Integer[] temp = new Integer[8];
		merge2(a,temp,0,3,7);
		for(Integer h:a) {
			System.out.println(h);
		}
	}
}
