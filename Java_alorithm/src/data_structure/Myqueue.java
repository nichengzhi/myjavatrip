package data_structure;
// first in first out
import java.util.*;

public class Myqueue<Item> implements Iterable{
	Node<Item> first;
	Node<Item> last;
	int N;
	
	private class Node<Item>{
		Item item;
		Node<Item> next;
	}
	
	public Myqueue() {
		first = null;
		last = null;
		N = 0;
	}
	
	public boolean isEmpty() {
		return N == 0;
	}
	
	public void enqueue(Item item) {
		Node<Item> oldlast = last;
		Node<Item> last = new Node<>();
		last.item = item;
		last.next = null;
		if(isEmpty()) {
			first = last;
		}
		else {
			oldlast.next = last;
		}
		N++;
		
	}
	public Item dequeue() {
		Node<Item> oldfirst = first;
		first = oldfirst.next;
		if(isEmpty()) {
			last = null;
		}
		N--;
		return oldfirst.item;
	}
	public Iterator<Item> iterator(){
		return new ListIterator<Item>(first);
	}
	private class ListIterator<Item> implements Iterator<Item>{
		Node<Item> current;
		public ListIterator(Node<Item> first) {
			current = first;
		}
		public boolean hasNext() {
			return current != null;
		}
		public Item next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			Item item = current.item;
			current = current.next;
			return item;
		}
		public void remove() {
			
		}
		
	}
	
	
}
