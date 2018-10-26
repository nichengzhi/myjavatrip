package data_structure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Simple_heap{
	List<Integer> a;
	public Simple_heap(List<Integer> a) {
		this.a = a;//数组堆的index0不保存元素
		a.add(0);
	}
	public void insert(Integer g) {
		a.add(g);
		if(a.size()==2) {
			return;
		}
		else {
			heapup(a.size()-1);
		}
	}
	public void heapup(int i) {
		int parent_index = i/2;
		Integer parent = a.get(parent_index);
		if(parent_index>0) {
			if(parent>a.get(i)) {
				a.set(parent_index, a.get(i));
				a.set(i, parent);
			}
		}
		else {
			return;
		}
		heapup(parent_index);
		
	}
	public void delete(int i) {
		a.set(i, a.get(a.size()-1));
		heapdown(i);
		a.remove(a.size()-1);
		
	}
	
	public void heapdown(int i) {
		int child_index = -1;
		int n = a.size()-1;
		if(i * 2 > n) {
			return;
		}
		else if(i * 2 == n) {
			child_index = i*2;
		}
		else if(i*2 < n) {
			if(a.get(i*2) < a.get(i*2 + 1)) {
				child_index = i*2;
			}
			else {
				child_index = i*2+1;
			}
		}
		if(a.get(i) > a.get(child_index)) {
			int temp = a.get(i);
			a.set(i, a.get(child_index));
			a.set(child_index, temp);
		}
		heapdown(child_index);
		
	}
	public String toString() {
		return Arrays.toString(a.toArray());
	}
	public static void main(String[] args) {
		ArrayList<Integer> a = new ArrayList<>();
		Simple_heap sh = new Simple_heap(a);
		sh.insert(93);
		sh.insert(34);
		sh.insert(55);
		sh.insert(22);
		sh.delete(1);
		System.out.println(sh);
	}
}
