package Suanfa;

public class weighted_quick_union {
	int[] id;
	int[] sz;
	int count;//输入的总链接的数量
	public weighted_quick_union(int n){
		
		id = new int[n];
		sz = new int[n];
		
		for(int i = 0; i < n; i++) {
			id[i] = i;
			sz[i] = 1;
		}
	}
	
	public int find(int num) {
		while(id[num] != num) {
			num = id[num];//不断迭代回到根结点
		}
		
		return num;
	}
	public void union(int p, int q) {
		int rootp = find(p);
		int rootq = find(q);
		if(rootp == rootq)return;
		if(sz[rootp] > sz[rootq]) {
			id[rootq] = rootp;
			sz[rootp] =+ sz[rootq];//确保小树加到大树的根结点上
		}
		else {
			id[rootp] = rootq;
			sz[rootq] += sz[rootp];
		}
	}
}
