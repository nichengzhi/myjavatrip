package Sort_algorithm;

import java.util.ArrayList;
import java.util.Arrays;

public class Heap_sort<T extends Comparable<T>> {//小顶堆
	
	ArrayList<T> list;
	public Heap_sort(int size) {
		list = new ArrayList<T>(size+1);
		list.add(null);
	}
	
	public Heap_sort() {
		list= new ArrayList<T>();
		list.add(null);
	}
	
	public void add(T a){
		list.add(a);
		heapup(list.size()-1);
		
	}
	
	public void heapup(int index) {//先把数加在末尾，一步步上升
		
		int parent_index = index/2;
		if(parent_index == 0) {
			return;
		}
		else {
			T parent = list.get(parent_index);
			if(parent.compareTo(list.get(index)) > 0) {
				list.set(parent_index, list.get(index));
				list.set(index, parent);
			}
			else {
				return;
			}
		}
		heapup(parent_index);
	}
	
	public void delete(int index) {//先用末尾数替代，一步步下降
		list.set(index, list.get(list.size()-1));
		heapdown(index);
		list.remove(list.size()-1);
		
	}
	
	public void heapdown(int index) {
		int child_index = -1;
		int n = list.size()-1;
		if(index * 2 > n) {
			return;
		}
		else if(index *2 == n) {
			child_index = index*2;
		}
		else if(index *2 < n) {
			T child1 = list.get(index*2);
			T child2 = list.get(index *2 +1);
			if(child1.compareTo(child2) < 0) {
				child_index = index*2;
			}
			else {
				child_index = index*2 +1;
			}
		}
		if(list.get(child_index).compareTo(list.get(index))<0) {
			T temp = list.get(child_index);
			list.set(child_index, list.get(index));
			list.set(index, temp);
		}
		else {
			return;
		}
		heapdown(child_index);
	}
	
	public T getmin() {
		if(list.size() == 1) {
			return null;
		}
		T min = list.get(1);
		delete(1);
		return min;
	}
	public String toString() {
		return Arrays.toString(list.toArray());
	}
	public static void main(String[] args) {
		
		Heap_sort<Integer> test = new Heap_sort<Integer>();
		Integer[] b = {4,5,7,8,1,2,3,6};
		//93,34,55,22
		//4,5,7,8,1,2,3,6
		for(Integer i:b) {
			test.add(i);
		}
		System.out.println(test);
		System.out.println(test.getmin());
		System.out.println(test);
	}
	
}
