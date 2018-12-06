package matrix;

public class Matrix {
	double[][] mat;
	int rows;
	int cols;
	
	public Matrix(int i, int j) {
		int rows = i;
		int cols = j;
		mat = new double[i][j];
	}
	public Matrix(double[][] a) {
		rows = a.length;
		cols = a[0].length;
		mat = a.clone();
	}
	public Matrix ludecomposition() {
		double[][] temp = mat.clone();
		//calculate factor first
		//k times make lower dianglenal elements to 0
		// k is n-1
		for(int k = 0; k < cols-1; k ++) {
			for(int i = k+1; i < rows; i++) {
				temp[i][k] = temp[i][k]/temp[k][k];//get factor which use in get zero in kth column
				
			}
			for(int i = k+1; i < rows; i++) {//change element by factor 
				for(int j = k+1; j < cols; j++) {//for each column bigger than k, Rowi - factor*Rowk
					temp[i][j] = temp[i][j]- temp[i][k]*temp[k][j]; 
				}
			}
		}//l is the factor matrix, u is the result of a list of gaussian elimination result
		//the diagonal emelent belong to u, the diagonal element for l is 1
		return new Matrix(temp);
	}
	// lu decomposition is not robust need a permutation matrix when pivot is 0
	public Matrix[] pludecomposition() {
		Matrix[] temp = new Matrix[2];
		//build a dan wei ju zhen p
		Matrix p = new Matrix(new double[rows][cols]);
		for(int i = 0; i < cols; i++)
			p.mat[i][i] = 1;
		Matrix lu = new Matrix(mat.clone());
		for(int k = 0; k < cols - 1; k ++) {
			if(lu.mat[k][k] == ((double)0)) {
				
				//find a non-zero prvot change rows
				for(int i = k+1; i < rows; i ++) {
					if(lu.mat[i][i] != ((double)0)) {
						p.permuterow(k, i);
						lu.permuterow(k, i);
						break;
					}
				}
			}
			//System.out.println(k);
			for(int i = k+1; i < rows; i++) {
				lu.mat[i][k] = lu.mat[i][k]/lu.mat[k][k];//get factor which use in get zero in kth column
				
			}
			for(int i = k+1; i < rows; i++) {//change element by factor 
				for(int j = k+1; j < cols; j++) {//for each column bigger than k, Rowi - factor*Rowk
					lu.mat[i][j] = lu.mat[i][j]- lu.mat[i][k]*lu.mat[k][j]; 
				}
			}
			//System.out.println(lu);
		}
		temp[0] = p;
		temp[1] = lu;
		return temp;
		
	}
	public void permuterow(int r1, int r2) {
		double[] temp = mat[r1].clone();
		for(int i = 0; i < cols; i ++) {
			mat[r1][i] = mat[r2][i];
			mat[r2][i] = temp[i];
		}
	}
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j ++) {
				sb.append(mat[i][j]);
				sb.append(" ");
				if(j == (cols -1))
					sb.append("\n");
			}
		}
		return sb.toString();
	}
	
	//solve linear equation 
	public double solve(double b[]) {
		Matrix[] lupres = this.pludecomposition();
		//transform b by p
	}
	public void transpose() {
		
	}
	public static void main(String[] args) {
		Matrix test = new Matrix(new double[][] {
			{-8,8,1},
			{0,1,0},
			{2,-2,0}
		});
		//test.permuterow(0, 1);
		//System.out.println(test);
		Matrix[] res = test.pludecomposition();
		System.out.println(res[1]);
		
	}
}
