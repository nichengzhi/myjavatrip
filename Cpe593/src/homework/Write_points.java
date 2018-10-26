package homework;
import java.io.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Random;
public class Write_points {
	public static void main(String[] main) {
		String fileName="convexhullpoints.dat";
	    try
	    {		DecimalFormat df = new DecimalFormat("#.##");
	    		df.setRoundingMode(RoundingMode.CEILING);
	    		Random r = new Random();
	            BufferedWriter out=new BufferedWriter(new FileWriter(fileName));
	            for(int i = 0; i < 1000; i ++) {
	            	double j = r.nextDouble()*1000;
	            	double b = r.nextDouble()*1000;
	            	out.write(String.valueOf(df.format(j)));
	            	out.write(" ");
	            	out.write(String.valueOf(df.format(b)));
	            	out.newLine();
	            }
	       
	            out.close();
	    } catch (IOException e)
	    {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	    }
	}
	
}
