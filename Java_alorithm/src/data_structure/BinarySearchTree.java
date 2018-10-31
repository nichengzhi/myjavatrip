package data_structure;

public class BinarySearchTree<T extends Comparable> {
	BN<T> root;
	class BN<T>{
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
	public void insert(BN<T> currnode,T item) {
		if(currnode == null) {
			currnode = new BN<>();
			currnode.item = item;
			currnode.left = null;
			currnode.right = null;
		}
		else {
			int compare_result = item.compareTo(currnode.item);
			if(compare_result >0) {
				insert(currnode.right,item);
			}
			else if(compare_result < 0) {
				insert(currnode.left,item);
			}
			else {
				currnode.freq ++;
			}
		}
		
	}
	//search
	
	public boolean search(T item) {
		if(root == null) {
			return false;
		}
		return search(root,item);
	}
	public boolean search(BN<T> currnode,T item) {
		int compare_result = item.compareTo(currnode.item);
		if(compare_result >0) {
			search(currnode.right,item);
		}
		else if(compare_result < 0) {
			search(currnode.left,item);
		}
		return true;//不能加else，程序会觉得输出不了boolean
		
	}
	//delete
	
}