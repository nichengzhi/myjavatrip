package data_structure;

import java.util.concurrent.atomic.AtomicLong;
public class test<H> {
	
	public static void main(String[] args) {
		System.out.println(null == null);
		char a = 'b';
		int index = a - 'a';
		System.out.println(index);
		AtomicLong atomicLong = new AtomicLong(123);  
		  
		long expectedValue = 123;  
		long newValue      = 1110; 
		if(atomicLong.get() < newValue){
			atomicLong.set(newValue);
		}
		//atomicLong.compareAndSet(expectedValue, newValue);
		System.out.println(atomicLong.get());

	}

}
