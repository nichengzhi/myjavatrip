package Sort_algorithm;
import java.util.*;
public class Fisher_Yates {
	
	public static void shuffling(Comparable[] a) {
		
		Random r = new Random();
		for(int i = a.length-1; i >0;i--) {
			int temp = r.nextInt(i+1);
			
			Comparable temp_object = a[temp];
			a[temp] = a[i];
			a[i] = temp_object;
			
		}
	}
	
	public static void main(String[] args) {
		Integer[] b = {1,2,3,4,5,6,7,8};
		Fisher_Yates.shuffling(b);
		System.out.println(Arrays.toString(b));
		int a = 1;
		char b1 = (char)(a + 'a');
		System.out.println(Character.toString(b1));
	}

}
