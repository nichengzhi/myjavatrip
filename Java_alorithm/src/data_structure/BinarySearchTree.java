package data_structure;

public class BinarySearchTree<T> {
	BN<T> root;
	class BN<T>{
		T item;
		BN<T> left;
		BN<T> right;	
		BN<T> parent;
	}
	
	public BinarySearchTree() {
		root = null;
	}
	//key method is 遍历
	public void preorder_recurisve(BN<T> node) {
		System.out.print(node.item);
		preorder_recurisve(node.left);
		preorder_recurisve(node.right);
	}
	public void preorder(BN<T> node) {
		Mystack<BN<T>> nodestack = new Mystack<>();
		//use cursor to traversal
		BN<T> cursor = node;
		//when cursor is null and nodestack is null, stop traversal
		while(cursor != null || !(nodestack.isEmpty())) {
			//store the node already traversaled, traversal their right node later
			
		}
	}
	public void add(T item) {
		if(root == null) {
			root = new BN<>();
			root.item = item;
			root.left = null;
			root.right = null;
		}
		else {
			
		}
	}

}
