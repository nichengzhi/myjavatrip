package homework;
import java.io.*;
/*
 * HW5 - Linked List class
 * CHENGZHI NI
 */
public class SinglyLinkedList<T extends Comparable<T>> {
	Node<T> first;
	Node<T> last;
	int n;
	private static class Node<T>{
		T t;
		Node<T> next;
	}
	
	public SinglyLinkedList() {
		first = null;
		last = null;
		n = 0;
	}
	
	public boolean isEmpty() {
		return n==0;
	}
	
	public void enqueue(T t) {
		Node<T> oldlast = last;
		last = new Node<>();
		last.t = t;
		last.next = null;
		if(isEmpty()) {
			first = last;
		}
		else {
			oldlast.next = last;
		}
		n++;
	}
	public void dequeue() {
		
		first = first.next;
		if(isEmpty()) {
			last = null;
		}
		n--;
	}
	public void deleteEnd() {
		
		Node<T> temp = first;
		while(temp.next != last) {
			temp = temp.next;
		}
		temp.next = null;
		last = temp;
		n--;
	}
	
	public void delete(Node<T> node) {
		if(isEmpty()) {
			return;
		}
		if(node == first) {
			dequeue();
		}
		else if(node == last) {
			deleteEnd();
		}
		else {
			node.t = node.next.t;
			node.next = node.next.next;
			this.n --;
		}
		
	}
	
	public void reverse() {
		Node<T> p;
		Node<T> q;
		Node<T> temp;
		if(n <= 1)
			return;
		p = first.next;
		q = first.next.next;
		while(q != null) {
			temp = q.next;
			q.next = p;
			p = q;
			q = temp;
		}
		
		first.next.next = first;
		first.next = null;
		first= p;
		
	}
	public String toString() {
		StringBuilder sb = new StringBuilder(n*2);
		Node<T> temp = first;
		while(temp.next != null) {
			sb.append(temp.t.toString());
			sb.append("-->");
			temp = temp.next;
		}
		sb.append(temp.t.toString());
		return sb.toString();
		
	}
	
	/*public void deletebyrule(int d) {
		int total =  (n-1)/d + 1;
		//System.out.println(total);
		Node[] nodelist = new Node[total];
		int index = 0;
		int cursor = 0;
		Node<T> temp = first;
		while(cursor <= n-1) {
			if(cursor % d == 0) {
				nodelist[index++] = temp;
				
			}
			temp = temp.next;
			cursor++;
		}
		for(Node n: nodelist) {
			this.delete(n);
		}
	}*/
	public void deletebyrule2(int d) {
		int total =  (n-1)/d + 1;
		
		int index = 0;
		int cursor = 0;
		Node<T> temp = first;
		while(index < total) {
			if(cursor % d == 0) {
				Node<T> oldtemp = temp;
				temp = temp.next;
				
				this.delete(oldtemp);
				
				
				cursor++;
				index ++;
				
			}
			else {
				temp = temp.next;
				cursor++; 
			}
			
			
		}
		
	}
	public void insertSort() {
		if(first == null || first.next == null) {
			return;
		}
		else {
			int cursor = 0;
			Node<T> temp = first;
			while(cursor < n) {
				Node<T> compare_node = first;
				
				for(int index = 0; index <cursor;index++) {
					if(compare_node.t.compareTo(temp.t) > 0) {
						T temp_t = temp.t;
						temp.t = compare_node.t;
						compare_node.t = temp_t;
					}
					compare_node = compare_node.next;
				}
				cursor++;
				temp = temp.next;
			}
		}
	}
	//merge sort for singly linked list
	public Node<T> findmiddle(Node<T> begin){
		if(begin == null) {
			return begin;
		}
		Node<T> slow,fast;
		slow = begin;
		fast = begin;
		while(fast.next != null & fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}
	public Node<T> mergeSort(Node<T> head) {
		if(head == null || head.next == null) {
			return head;
		}
		Node<T> middle_node = findmiddle(head);
		Node<T> next_middle = middle_node.next;
		middle_node.next = null;//split to two
		return merge(mergeSort(head),mergeSort(next_middle));
	}
	
	public Node<T> merge(Node<T> a, Node<T> b){
		Node<T> dummynode,cur;
		dummynode = new Node<>();
		cur = dummynode;
		
		while(a != null && b != null) {
			if(a.t.compareTo(b.t) <= 0) {
				cur.next = a;
				a = a.next;
			}
			else {
				cur.next = b;
				b = b.next;
			}
			cur = cur.next;
		}
		cur.next = (a == null) ? b : a;
		/*if(a == null)
			cur.next = b;
		else
			cur.next = a;*/
		return dummynode.next;
	}
	public static SinglyLinkedList<Integer> read(String path){
		SinglyLinkedList<Integer> sl = new SinglyLinkedList<Integer>();
		String[] line;
		try
        {
                BufferedReader in=new BufferedReader(new FileReader(path));
                line=in.readLine().split(" ");
                for(String i:line) {
                	sl.enqueue(Integer.valueOf(i));
                }
                in.close();
        } catch (IOException e)
        {
                e.printStackTrace();
        }
		return sl;
	}
	public static void main(String[] args) {
		/*SinglyLinkedList<Integer> sl = new SinglyLinkedList<>();
		for(int i = 10; i > 0; i-- ) {
			sl.enqueue(i);
		}*/
		SinglyLinkedList<Integer> sl = read("linkedlist.dat");
		System.out.println(sl);
		sl.insertSort();
		//sl.mergeSort(sl.first);
		System.out.println(sl);
		sl.deletebyrule2(3);
		System.out.println(sl);
		sl.deletebyrule2(3);
		
		System.out.println(sl);
		sl.reverse();
		System.out.println(sl);
		System.out.println("-----------q2------");
		Grow_array ga = new Grow_array();
		
		ga.add_Front(1, 2, 10);
		ga.add_Back(5, 5, 25);
		System.out.println(ga);
		ga.remove_Front(2);
		ga.remove_Back(2);
		System.out.println(ga);
	}
	private static class Grow_array{
		int size, capacity;
		Object[] a;
		int default_size = 10;
		Grow_array(){
			a = new Object[default_size];
			capacity = default_size;
			size = 0;
		}
		void grow(){
			capacity *= 2;
			Object[] temp = new Object[capacity];
			for(int i =0; i < size; i++) {
				temp[i] = a[i];
			}
			a = temp;
		}
		public void addStart(Object o) {
			if(size == capacity)
				grow();
			for(int i = size; i > 0; i--) {
				a[i] = a[i-1];
			}
			a[0] = o;
			size++;
		}
		public void addEnd(Object o) {
			if(size == capacity)
				grow();
			a[size++] = o;
			
		}
		public void removeEnd() {
			size--;
		}
		public void removeStart() {
			for(int i = 0; i < size-1; i++)
				a[i] = a[i+1];
			size--;
		}
		public void add_Front(int start, int step, int end ) {
			for(int i = start; i <= end; i += step) {
				
				addStart(i);
			}
		}
		public void add_Back(int start, int step, int end ) {
			for(int i = start; i <= end; i += step) {
				addEnd(i);
			}
		}
		public void remove_Front(int n) {
			for(int i = n; i > 0; i--) {
				removeStart();
			}
		}
		public void remove_Back(int n) {
			for(int i = n; i > 0; i--) {
				removeEnd();
			}
		}
		public String toString() {
			StringBuilder sb = new StringBuilder(size * 2);
			for(int i = 0; i < size-1; i++) {
				sb.append(a[i]);
				sb.append(",");
			}
			sb.append(a[size-1]);
			return sb.toString();
		}
	}
}
