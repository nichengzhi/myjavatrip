package data_structure;

public class RBtree<T extends Comparable<T>>{
	
	private class Node<T>{
		T item;
		Node<T> parent;
		Node<T> right;
		Node<T> left;
		boolean isRed;
	}
	
}