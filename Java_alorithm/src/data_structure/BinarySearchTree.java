package data_structure;

public class BinarySearchTree<T extends Comparable> {
	BN<T> root;
	private static class BN<T extends Object>{
		T item;
		BN<T> left;
		BN<T> right;	
		BN<T> parent;
		int freq;
	}
	
	public BinarySearchTree() {
		root = null;
	}
	//key method is 遍历
	//preorder traversal
	public void preorder_recurisve(BN<T> node) {
		if(node != null) {
			System.out.print(node.item);
			preorder_recurisve(node.left);
			preorder_recurisve(node.right);
		}
		
	}
	public void preorder(BN<T> node) {
		Mystack<BN<T>> nodestack = new Mystack<>();
		//use cursor to traversal
		BN<T> cursor = node;
		//when cursor is null and nodestack is null, stop traversal
		while(cursor != null || !(nodestack.isEmpty())) {
			//store the node already traversaled, traversal their right node later
			while(cursor != null) {
				System.out.println(cursor.item);
				nodestack.push(cursor);
				cursor = cursor.left;
			}
			//until left node is empty
			//pop the tree node in the stack, traversal right tree node
			if(!nodestack.isEmpty()) {
				cursor = (BN<T>) nodestack.pop();
				cursor = cursor.right;
			}
		}
	}
	//midorder traversal 
	public void midorder_recurisve(BN<T> node) {
		if(node != null) {
			
			preorder_recurisve(node.left);
			System.out.print(node.item);
			preorder_recurisve(node.right);
		}
		
	}
	//print element when nodestack pop item
	public void midorder(BN<T> node) {
		Mystack<BN<T>> nodestack = new Mystack<>();
		//use cursor to traversal
		BN<T> cursor = node;
		while(cursor != null || !(nodestack.isEmpty())) {
			while(cursor != null) {
				nodestack.push(cursor);
				cursor = cursor.left;
			}
			if(cursor == null) {
				cursor = (BN<T>) nodestack.pop();
				System.out.println(cursor.item);
				cursor = cursor.right;
			}
		}
	}
	//postorder
	public void postorder_recurisve(BN<T> node) {
		if(node != null) {
			
			preorder_recurisve(node.left);
			System.out.print(node.item);
			preorder_recurisve(node.right);
		}
		
	}
	//print element when its child have traversaled 
	//add a last visit cursor to record its traversal history
	public void postorder(BN<T> node) {
		Mystack<BN<T>> nodestack = new Mystack<>();
		//use cursor to traversal
		BN<T> cursor = node;
		BN<T> lastvisit = node;
		while(cursor != null || !(nodestack.isEmpty())) {
			while(cursor != null) {
				nodestack.push(cursor);
				cursor = cursor.left;
			}
			BN<T> peek_value = (BN<T>)nodestack.peek();
			if(peek_value.right == null || peek_value.right.item.equals(lastvisit.item)) {
				cursor = (BN<T>) nodestack.pop();
				System.out.println(cursor.item);
				lastvisit = cursor;
				cursor = null;
			}
			else {
				cursor = peek_value.right;
				
				 
			}
		}
	}
	//insert a item always from the root
	public void insert_recursive(BN<T> currnode,T item) {
		if(currnode == null) {
			currnode = new BN<>();
			currnode.item = item;
			currnode.left = null;
			currnode.right = null;
		}
		else {
			int compare_result = item.compareTo(currnode.item);
			if(compare_result >0) {
				insert_recursive(currnode.right,item);
			}
			else if(compare_result < 0) {
				insert_recursive(currnode.left,item);
			}
			else {
				currnode.freq ++;
			}
		}
		
	}
	//non_recursive insert
	public void insert(T t) {
		
		BN<T> new_node = new BN<>();
		new_node.item = t;
		if(root == null) {
			root = new_node;
			root.parent = null;
			root.left = null;
			root.right = null;
		}
		else {
			BN<T> cursor = root;
			BN<T> lastvisit = null;
			while(cursor != null) {
				lastvisit = cursor;
				if(cursor.item.compareTo(t) > 0) {
					cursor = cursor.left;
				}
				else if(cursor.item.compareTo(t) < 0) {
					cursor = cursor.right;
				}
				else {
					cursor.freq ++;
				}
			}
			new_node.parent = lastvisit;
			if(lastvisit.item.compareTo(t) > 0)
				lastvisit.left = new_node;
			else if(lastvisit.item.compareTo(t) < 0)
				lastvisit.right = new_node;
		}
		
	}
	//search
	
	public BN<T> recursive_search(T item) {
		if(root == null) {
			return null;
		}
		return recursive_search(root,item);
	}
	public BN<T> recursive_search(BN<T> currnode,T item) {
		int compare_result = item.compareTo(currnode.item);
		if(compare_result >0) {
			recursive_search(currnode.right,item);
		}
		else if(compare_result < 0) {
			recursive_search(currnode.left,item);
		}
		//不能加else，程序会觉得输出不了boolean
		return currnode;
	}
	public BN<T> search(T t){
		if(root == null) {
			return null;
		}
	
		BN<T> cursor = root;
		while(cursor != null) {
			if(cursor.item.compareTo(t) > 0)
				cursor = cursor.left;
			else if(cursor.item.compareTo(t) < 0)
				cursor = cursor.right;
			else {
				return cursor;
			}
				
		}
		return cursor;
		
	}
	//delete
	//BN<T> wait_delete = search(t);
	public void delete(BN<T> wait_delete) {
		
		if(wait_delete == null)
			return;
		if(wait_delete == root) {
			root = null;
			return;
		}
		if(wait_delete.left == null && wait_delete.right == null) {
			if(wait_delete.parent.item.compareTo(wait_delete.item) > 0)
				wait_delete.parent.left = null;
			else {
				wait_delete.parent.right = null;
			}
		}
		else if(wait_delete.left == null || wait_delete.right == null) {
			if(wait_delete.left == null) {
				wait_delete.item = wait_delete.right.item;
				wait_delete.right = null;
			}
			else {
				wait_delete.item = wait_delete.left.item;
				wait_delete.left = null;
			}
		}
		//use the smallest item in right node exchange value
		else {
			BN<T> cursor = wait_delete.right;
			BN<T> min_node = null;
			while(cursor != null) {
				min_node = cursor;
				cursor = cursor.left;
			}
			wait_delete.item = min_node.item;
			delete(min_node);
		}
			
	}
	public static void post2(BN<Integer> node) {
		if(node != null) {
			
			post2(node.left);
			
			post2(node.right);
			System.out.print(node.item);
		}
	}
	public static void main(String[] args) {
		Integer[] test = {4,3,2,6,7,8};
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		for(Integer i: test) {
			bst.insert(i);
		}
		post2(bst.root);
	}
	
}