package data_structure;

import java.util.Iterator;
import java.util.NoSuchElementException;

//last in first out
public class Mystack<Item> implements Iterable<Item> {
	
	private Node<Item> first;
	private int N;
	
	private class Node<Item>{//initializes an empty stack
		private Item item;
		private Node<Item> next;
	}
	
	public Mystack(){
		first = null;
		N = 0;
	}
	
	
	public boolean isEmpty(){
		return first == null;
	}
	
	public int size() {
		return N;
	}
	
	public void push(Item item) {
		Node<Item> oldfirst = first;
		first = new Node<Item>();
		first.item = item;
		first.next = oldfirst;
		N++;
	}
	
	public Object pop() {
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		Object item = first.item;
		first = first.next;
		N--;
		return item;
	}
	public Object peek() {
		return first.item;
	}
	//迭代代码
	//@Override Iterable iterator
	public Iterator<Item> iterator(){
		return new ListIterator<Item>(first);
	}
	
	//创建一个新的内部类来定义迭代规则
	
	private class ListIterator<Item> implements Iterator<Item>{
		private Node<Item> current;
		public ListIterator(Node<Item> first) {
			current = first;
		}
		public boolean hasNext() {
			return current != null;
		}
		public void remove() {
			
		}
		public Item next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			Item item = current.item;
			current = current.next;
			return item;
		}
	}
	public String toString() {
        StringBuilder s = new StringBuilder();
        for (Object item : this) {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
    }
	
	
	public static void main(String[] args) {
		Mystack<String> a = new Mystack<>();
		a.push("nige");
		a.push("zuishuai");
		System.out.println(a.pop());
		a.push("haishizuishuai");
		
		
	}
}
