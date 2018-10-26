package data_structure;

public class Grow_Array {
	private int capacity; //the size of the block of memory
	private int size;
	private int[] p;
	
	public Grow_Array() {
		this.capacity = 10;
		this.p = new int[capacity];
		this.size = 0;
	}
	
	public Grow_Array(int i) {
		this.size = 0;
		this.capacity = i;
		this.p = new int[i];
		
	}
	
	public boolean checkGrow() {
		if(capacity == size) {
			return true;
			
		}
		else {
			return false;
		}
	}
	
	public void insertEnd(int v) {
		if(checkGrow()) {
			capacity *=2;
			int[] temp = new int[capacity];
			for(int i = 0; i<size;i++) {
				temp[i] = p[i];
				
			}
			temp[size++] = v;
			
			p = temp;
			temp = null;
			System.gc();
					
		}
		else {
			p[size++] = v;
		}
	}
	
	public void insertStart(int v) {
		if(checkGrow()) {
			capacity *=2;
			int[] temp = new int[capacity];
			for(int i = 1; i<size+1;i++) {
				temp[i] = p[i-1];
				
			}
			temp[0] = v;
			size++;
			p = temp;
		}
		else {
			int[] temp = new int[capacity];
			for(int i = 1; i<size+1;i++) {
				temp[i] = p[i-1];
				
			}
			temp[0] = v;
			size++;
			p = temp;
		}
	}
	
	public void insert( int pos, int v) {
		if(checkGrow()) {
			capacity *=2;
			int[] temp = new int[capacity];
			for(int i = 0; i < pos; i ++) {
				temp[i] = p[i];
			}
			temp[pos] = v;
			for(int j = pos+1; j < size+1;j++) {
				temp[j] = p[j-1];
			}
			size ++;
			p = temp;
		}
		else {
			int[] temp = new int[capacity];
			for(int i = 0; i < pos; i ++) {
				temp[i] = p[i];
			}
			temp[pos] = v;
			for(int j = pos+1; j < size+1;j++) {
				temp[j] = p[j-1];
			}
			size ++;
			p = temp;
		}
	}
	public void removeStart() {
		int[] temp = new int[capacity];
		for(int i = 1; i < size; i ++) {
			temp[i-1] = p[i];
		}
		size --;
		p = temp;
	}
	
	public void removeEnd() {
		
		size --;
		
	}
	
	public int size() {
		return size;
	}
	
	public String toString() {
		
		StringBuilder b = new StringBuilder(size*2);
		for(int i =0;i<size;i++) {
			b.append(p[i]);
			b.append(" ");
		}
		return b.toString();
	}
	
	public static void main(String[] args) {
		Grow_Array a = new Grow_Array();
		System.out.println(a.capacity);
		for(int i = 0; i < 10;i++) {
			a.insertStart(i);
		}
		
		System.out.println(a);
		System.out.println(a.capacity);
		a.insert(1, 2);
		System.out.println(a);
		a.removeEnd();
		System.out.println(a);
		a.removeStart();
		System.out.println(a);
		
	}
}
