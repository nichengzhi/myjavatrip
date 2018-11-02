package data_structure;

public class Tries{
	//every node shoud have maximum 26 child
	final int max_child = 26;
	TriesNode root;
	private class TriesNode{
		boolean isEnd;//record this node is some word's end node
		int freq;
		int num;// how many char pass this node
		TriesNode[] child;
		public TriesNode() {
			child = new TriesNode[max_child];
			isEnd = false;
			num = 1;
		}
		
	}
	
	
	public Tries() {
		root = new TriesNode();
	}
	public boolean hasStr(String word) {
		TriesNode cursor = root;
		for(int i = 0; i < word.length(); i++) {
			//use char - 'a' can return the index in the 26 zimu
			int index = word.charAt(i) - 'a';
			if(cursor.child[index] == null || ((i+1) == word.length() && (cursor.child[index].isEnd == false))) {
				return false;
			}
			cursor = cursor.child[index];
		}
		return true;
	}
	public void insert(String word) {
		TriesNode cursor = root;
		for(int i = 0; i < word.length(); i++) {
			int index = word.charAt(i) - 'a';
			if(cursor.child[index] == null) {
				cursor.child[index] = new TriesNode();
				
			}else {
				cursor.child[index].num ++;//add one time a word pass this node
			}
			cursor = cursor.child[index];
		}
		cursor.isEnd = true;//record a word
	}
	
}
