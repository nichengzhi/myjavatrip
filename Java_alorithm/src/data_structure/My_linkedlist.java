package data_structure;

import java.util.NoSuchElementException;

public class My_linkedlist<Item> {
	
	//双向链表是每个节点有两个地址域的线性链表
	Node<Item> first;
	Node<Item> last;
	int n;
	private class Node<item>{
		Node<Item> prev;
		Node<Item> next;
		Item item;
	}
	
	public My_linkedlist() {
		first = null;
		last = null;
		n = 0;
	}
	public boolean isEmpty() {
		return n==0;
	}
	public void insertFirst(Item i) {
		if(isEmpty()) {
			first = new Node<>();
			first.item = i;
			first.prev = null;
			first.next = null;
			last = first;
			
			n++;
		}
		else {
			Node<Item> oldfirst = first;
			first = new Node<>();
			first.item = i;
			first.prev = null;
			first.next = oldfirst;
			n++;
		}
		
	}
	public void insertLast(Item i) {
		if(isEmpty()) {
			last = new Node<>();
			last.prev = null;
			last.next = null;
			last.item = i;
			first = last;
			n++;
		}
		else {
			Node<Item> oldlast = last;
			last = new Node<>();
			last.item = i;
			last.next = null;
			last.prev = oldlast;
		}
	}
	public Item deleteFirst() {
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		
		Node<Item> oldfirst = first;
		first = first.next;
		if(first == null) {//当只有一个元素时
			last = null;
		}
		else {
			first.prev = null;//别忘了这步！
		}
		n--;
		return oldfirst.item;
	}
	
	public Item deleteLast() {
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		Node<Item> oldlast = last;
		last = last.prev;
		if(last == null) {
			first = null;
		}
		else {
			last.next = null;
		}
		n--;
		return oldlast.item;
	}
	
	public int find(Item i) {
		Node<Item> temp = first;
		int index = 0;
		while(temp != null) {
			if(temp.item.equals(i)) {
				return index;
			}
			index ++;
			temp = temp.next;
		}
		
		return -1;
	}
	
	public void delete(Item i) {
		Node<Item> temp = first;
		int index = 0;
		while(temp != null) {
			if(temp.item.equals(i)) {
				break;
			}
			index ++;
			temp = temp.next;
		}
		if(temp == null)
			return;
		if(index == 0) {
			this.deleteFirst();
		}
		else if(index == n-1) {
			this.deleteLast();
		}
		else{
			
			temp.next.prev = temp.prev;//核心！！！
			temp.prev.next = temp.next;
			temp.prev = null;
			temp.next = null;
			n--;
		}
	}
	
	public void insert(int index, Item t) {
		if(index > n-1) {
			throw new IndexOutOfBoundsException();
		}
		int cursor = 0;
		Node<Item> temp = first;
		while(cursor != index) {
			temp = temp.next;
			cursor++;
		}
		if(cursor == 0) {
			this.insertFirst(t);
		}
		else if(cursor == n-1) {
			this.insertLast(t);
		}
		else {
			Node<Item> new_item = new Node<>();
			new_item.item = t;
			new_item.next = temp.next;
			new_item.prev = temp;
			temp.next.prev = new_item;
			temp.next = new_item;
			n++;
		}
		
	}
	public static void main(String[] args) {
		My_linkedlist<Integer> ll = new My_linkedlist<>();
		ll.insertFirst(1);
		ll.insertFirst(2);
		ll.insertLast(3);
		System.out.println(ll.last.item);
		System.out.println(ll.deleteFirst());
		System.out.println(ll.deleteLast());
	}
	
	
	
}
