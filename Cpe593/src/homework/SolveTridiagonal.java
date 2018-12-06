package homework;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class SolveTridiagonal {
	
	public static double[] tdma(double[][] coe, double[] y) {
		int rows = coe.length;
		int cols = coe[0].length+1;
		double[][] res = new double[rows][cols];
		for(int i = 0; i < rows; i ++) {
			for(int j = 0; j < cols-1; j++)
				res[i][j] = coe[i][j];
			res[i][cols-1] = y[i];
		}
		//gussian elemenation
		res[0][0] = coe[0][0]/coe[0][0];
		res[0][1] = coe[0][1]/coe[0][0];
		res[0][cols-1] = y[0]/coe[0][0];
		for(int i = 1; i < rows-1; i ++) {
			double factor = res[i][i-1];
			for(int j = i - 1; j < i + 2; j ++) {
				res[i][j] = res[i][j] - factor * res[i-1][j];
			}
			double divid = res[i][i];
			//make diagnal element is 1
			for(int j = i - 1; j < i + 2; j ++) {
				res[i][j] = res[i][j]/divid;
			}
			res[i][cols-1] = (res[i][cols-1] - res[i-1][cols-1]*factor)/divid;
			
		}
		double last_factor = res[rows-1][cols-3]/res[rows-2][cols-3];
		for(int i = cols-3; i < cols; i ++) {
			res[rows-1][i] = res[rows-1][i] - last_factor * res[rows-2][i];
		}
		double last_divid = res[rows-1][rows-1];
		res[rows-1][cols-2] /= last_divid;
		res[rows-1][cols-1] /= last_divid;
		//back substitute
		double[] ds  = new double[rows];
		ds[rows-1] = res[rows-1][cols-1];
		for(int i = rows-2; i >= 0; i --) {
			ds[i] = res[i][cols-1] - ds[i+1] * res[i][i+1];
		}
		return ds;
		
	}
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	public static void printmatrix(double[][] a) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < a[0].length; j ++) {
				sb.append(round(a[i][j],1));
				sb.append(" ");
				if(j == (a[0].length -1))
					sb.append("\n");
			}
		}
		System.out.println(sb.toString());
	}
	public static double[][] generatetridagonalmatrix(int n) {
		double[][] res = new double[n][n];
		res[0][0] = 2;
		res[0][1] = 1;
		res[n-1][n-1] = 2;
		res[n-1][n-2] = 1;
		for(int i = 1; i < n-1; i ++) {
			for(int j = i-1; j < i + 2; j ++) {
				res[i][j] = 1;
				if(i == j)
					res[i][j] = 4;
			}
		}
		return res;
	}
	public static void curbicspline(double[] input) {
		double[] y = new double[input.length];
		for(int i = 1; i < input.length; i ++) {
			y[i-1] = (input[i] - input[i-1]) * 3;
		}
		double[] ds = tdma(generatetridagonalmatrix(input.length), y);
		StringBuilder sb = new StringBuilder();
		double a,b,c,d;
		for(int i = 0; i < ds.length-1; i++) {
			a = input[i];
			b = ds[i];
			c = 3 * (input[i+1] - input[i]) - 2 * ds[i] - ds[i+1];
			d = 2 * (input[i] - input[i+1]) + ds[i] +ds[i+1];
			sb.append("D = ");
			sb.append(round(ds[i],2)).append(" :");
			
			for(double t = 0.0; t <= 0.9; t =round(t + 0.1,1)) {
				sb.append("t = ");
				sb.append(t);
				sb.append(";");
				sb.append("result is:");
				sb.append(round(a + b * t + c * t * t + d * t * t * t,2)).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	public static void main(String[] args) {
		
		ArrayList<Double> xs = new ArrayList<>();
		ArrayList<Double> ys = new ArrayList<>();
		String filename = "hw9.dat";
		String line="";
        try
        {
                BufferedReader in=new BufferedReader(new FileReader(filename));
                line=in.readLine();
                while ((line = in.readLine())!=null)
                {
                	String[] numbers = line.split("\\s+");
                	xs.add(Double.valueOf(numbers[0]));
                	ys.add(Double.valueOf(numbers[1]));
                	
                }
                in.close();
        } catch (IOException e)
        {
                e.printStackTrace();
        }
        System.out.println("for x values:");
        double[] xsd = new double[xs.size()];
        for(int i = 0; i < xsd.length; i++)
        	xsd[i] = xs.get(i);
        curbicspline(xsd);
        System.out.println("for y values:");
        double[] ysd = new double[ys.size()];
        for(int i = 0; i < ysd.length; i++)
        	ysd[i] = ys.get(i);
        curbicspline(ysd);
		
	}
}
