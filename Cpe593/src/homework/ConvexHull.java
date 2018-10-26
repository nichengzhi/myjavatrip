package homework;
/*
 * HW4a - GrowArray
 * CHENGZHI NI
 */
import java.io.*;
import java.util.Arrays;

public class ConvexHull {
	int n;
	Grow_array<Point> points;
	double min_x,max_x,min_y,max_y;
	Grow_array[][] grid;
	Point min_x_p, max_x_p;
	Grow_array<Point> convex_points;
	public ConvexHull(int n) {
		this.n = n;
		this.max_x = 0;
		this.max_y = 0;
		this.min_x = 0;
		this.min_y = 0;
		this.max_x_p = null;
		this.min_x_p = null;
		this.points = new Grow_array<>();
		this.grid = new Grow_array[n][n];
		this.convex_points = new Grow_array<>();
		for(int i = 0; i<n;i++) {
        	for(int j = 0; j < n;j++) {
        		grid[i][j] = new Grow_array<Point>();
        	}
        }
	}
	 
	public void read(String fileName) {
		
		String line="";
        try
        {
                BufferedReader in=new BufferedReader(new FileReader(fileName));
                line=in.readLine();
                while (line!=null)
                {
                	String[] numbers = line.split("\\s+");
                	points.insertEnd(new Point(Double.parseDouble(numbers[0]),Double.parseDouble(numbers[1])));
                	line=in.readLine();
                }
                in.close();
        } catch (IOException e)
        {
                e.printStackTrace();
        }
      //find min x min y max x max y set range
        min_x = points.get(0).x;
    	max_x = points.get(0).x;
    	min_y = points.get(0).y;
    	max_y = points.get(0).y;
        for(int i = 1; i < points.size()-1;i++) {
        	
        	if(points.get(i).x > points.get(i+1).x) {
        		if(points.get(i).x > max_x) {
        			max_x = points.get(i).x;
        			max_x_p = points.get(i);
        		}
        		if(points.get(i+1).x < min_x) {
        			min_x = points.get(i+1).x;
        			min_x_p = points.get(i+1);
        		}
        	}
        	else {
        		if(points.get(i+1).x > max_x) {
        			max_x = points.get(i+1).x;
        			max_x_p = points.get(i+1);
        		}
        		if(points.get(i).x < min_x) {
        			min_x = points.get(i).x;
        			min_x_p = points.get(i);
        		}
        	}
        	
        	if(points.get(i).y > points.get(i+1).y) {
        		if(points.get(i).y > max_y) {
        			max_y = points.get(i).y;
        		}
        		if(points.get(i+1).y < min_y) {
        			min_y = points.get(i+1).y;
        		}
        	}
        	else {
        		if(points.get(i+1).y > max_y) {
        			max_y = points.get(i+1).y;
        		}
        		if(points.get(i).y < min_y) {
        			min_y = points.get(i).y;
        		}
        	}
        	
        }
        //put point in grid
        double range_x = (int)((max_x - min_x)/this.n)+1;
        double range_y = (int)((max_y - min_y)/this.n)+1;
        for(int i = 0; i < points.size();i++) {
        	double temp_x = points.get(i).x;
        	double temp_y = points.get(i).y;
        	int grid_x = (int)(temp_x/range_x);
        	int grid_y = (int)(temp_y/range_y);
        	grid[grid_x][grid_y].insertEnd(points.get(i));
        }
	}
	
	public void printAllListSizes() {
		StringBuilder sb = new StringBuilder(n*n);
		for(int i = 0; i<16;i++) {
        	for(int j = 0; j < 16;j++) {
        		sb.append(grid[i][j].size());
        		sb.append(" ");
        		
        	}
        	sb.append("\n");
        	
        }
		System.out.println(sb.toString());
	}
	
	public void printMinMax() {
		System.out.println("minx: " + min_x);
		System.out.println("maxx: " + max_x);
		System.out.println("miny: " + min_y);
		System.out.println("maxy: " + max_y);
	}
	public void find_covex() {
		Comparable<Point>[] min_max = points.getMinMax();
		Point min_p = (Point)min_max[0];
		Point max_p = (Point)min_max[1];
		
		convex_points.insertEnd(min_p);
		convex_points.insertEnd(max_p);
		points.delete(min_p);
		points.delete(max_p);
		Grow_array<Point> upper = new Grow_array<>();
		Grow_array<Point> lower = new Grow_array<>();
		double dis;
		for(int i = 0; i < points.size(); i++) {
			Point p = points.get(i);
			dis = area(min_p,max_p,p);
			if(dis > 0)
				upper.insertEnd(p);
			if(dis < 0)
				lower.insertEnd(p);
		}
		find_convex(min_p,max_p,upper);
		find_convex(min_p,max_p,lower);
	}
	private void find_convex(Point min_p, Point max_p, Grow_array<Point> part_points) {
		if(part_points.size() == 0)
			return;
		if(part_points.size() == 1) {
			convex_points.insertEnd(part_points.get(0));
		    return;
		}
			
		//find the point which can make greatest area
		Point area_p = part_points.get(0);
		double max_area = distance(min_p,max_p,area_p);
		for(int i = 1; i < part_points.size();i++) {
			double temp_area = distance(min_p,max_p,part_points.get(i));
			if(temp_area > max_area) {
				max_area = temp_area;
				area_p = part_points.get(i);
			}
		}
		convex_points.insertEnd(area_p);
		points.delete(area_p);
		Grow_array<Point> left_min = new Grow_array<>();
		Grow_array<Point> left_max = new Grow_array<>();
		for(int i = 0; i < part_points.size(); i ++) {
			Point temp_point = part_points.get(i);
			if(area(min_p,area_p,temp_point) > 0) {
				left_min.insertEnd(temp_point);
			}
				
			if(area(max_p,area_p,temp_point) > 0) {
				left_max.insertEnd(temp_point);
			}
				
		}
		
		find_convex(min_p,area_p,left_min);
		find_convex(area_p,max_p,left_max);
		
	}

	public double area(Point a, Point b, Point c) {
		return (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
	}
	public double distance(Point a, Point b, Point c) {
	        
	        double dis = (b.x - a.x) * (a.y - c.y) - (b.y - a.y) * (a.x - c.x);
	        
	        return Math.abs(dis);
	    
	}
	public void printPerimeterClockWiseOrder() {
		/*
		 * Grow_array[][] perimeter = new Grow_array[n][n];
		for(int i = 0; i<n;i++) {
        	for(int j = 0; j < n;j++) {
        		perimeter[i][j] = new Grow_array<Point>();
        	}
        }
		for(int i = 0; i < n;i++) {
			for(int j = 0; j < n; j++) {
				for(int c = 0; c < convex_points.size();c++) {
					if(grid[i][j].have(convex_points.get(c))) {
						perimeter[i][j].insertEnd(convex_points.get(c));
						convex_points.delete(c);
					}
				}
			}
		}
		StringBuilder sb = new StringBuilder(n*n);
		for(int i = 0; i<convex_points.size();i++) {
        	
        		sb.append(convex_points.get(i));
        		sb.append(" ");
        		
        }
		System.out.println(sb.toString());*/
		
		for(int i = 0; i < n; i ++) {
			System.out.println(Grow_array.getminmax_xy(grid[0][i]));
		}
		for(int i = 0; i < n; i ++) {
			System.out.println(Grow_array.getminmax_xy(grid[i][n-1]));
		}
		for(int i = n-1; i >= 0; i --) {
			System.out.println(Grow_array.getminmax_xy(grid[n-1][i]));
		}
		for(int i = n-1; i >= 0; i --) {
			System.out.println(Grow_array.getminmax_xy(grid[i][0]));
		}
	}
	
	public static void main(String[] args) {
		
		ConvexHull ch = new ConvexHull(16);
		ch.read("convexhullpoints.dat");
		//how many are in each list
		ch.printAllListSizes();
		// print minx, maxx, miny, maxy
		ch.printMinMax();
		
		//ch.find_covex();
		System.out.println();
		// p1,p2,p3,p4,p8,p12,p16.
		ch.printPerimeterClockWiseOrder();
		
		
	}
}
class Grow_array<T extends Comparable<T>>{
	int capacity;
	int size;
	T[] a;
	@SuppressWarnings("unchecked")
	Grow_array(){
		capacity = 10;//default capacity
		a = (T[])new Comparable[capacity];//create generic type array in java
		size = 0;
	}
	@SuppressWarnings("unchecked")
	Grow_array(int i){
		a = (T[])new Object[i];
		capacity = i;
		size = 0;
	}
	private boolean checkgrow() {
		if(size == capacity)
			return true;
		return false;
	}
	public void insertEnd(T t) {
		if(checkgrow()) {
			capacity *= 2;
			@SuppressWarnings("unchecked")
			T[] temp = (T[])new Comparable[capacity];
			for(int i = 0;i<size;i++) {
				temp[i] = a[i];
				
			}
			temp[size++] = t;
			a = temp;
		}
		else {
			a[size++] = t;
		}
	}
	public void insertStart(T t) {
		if(checkgrow()) {
			capacity *=2;
			@SuppressWarnings("unchecked")
			T[] temp = (T[])new Comparable[capacity];
			for(int i = 0; i<size;i++) {
				temp[i+1] = a[i];
			}
			temp[0] = t;
			size++;
			a = temp;
		}
		else {
			@SuppressWarnings("unchecked")
			T[] temp = (T[])new Object[capacity];
			for(int i = 0; i<size;i++) {
				temp[i+1] = a[i];
			}
			temp[0] = t;
			size++;
			a = temp;
		}
	}
	public static String getminmax_xy(Grow_array<Point> points) {
		try {
			double min_x = points.get(0).x;
	    	double max_x = points.get(0).x;
	    	double min_y = points.get(0).y;
	    	double max_y = points.get(0).y;
	    	for(int i = 1; i < points.size()-1;i++) {
	        	
	        	if(points.get(i).x > points.get(i+1).x) {
	        		if(points.get(i).x > max_x) {
	        			max_x = points.get(i).x;
	        			
	        		}
	        		if(points.get(i+1).x < min_x) {
	        			min_x = points.get(i+1).x;
	        			
	        		}
	        	}
	        	else {
	        		if(points.get(i+1).x > max_x) {
	        			max_x = points.get(i+1).x;
	        			
	        		}
	        		if(points.get(i).x < min_x) {
	        			min_x = points.get(i).x;
	        			
	        		}
	        	}
	        	
	        	if(points.get(i).y > points.get(i+1).y) {
	        		if(points.get(i).y > max_y) {
	        			max_y = points.get(i).y;
	        		}
	        		if(points.get(i+1).y < min_y) {
	        			min_y = points.get(i+1).y;
	        		}
	        	}
	        	else {
	        		if(points.get(i+1).y > max_y) {
	        			max_y = points.get(i+1).y;
	        		}
	        		if(points.get(i).y < min_y) {
	        			min_y = points.get(i).y;
	        		}
	        	}
	        	
	        }
	        return "min_x:" + Double.toString(min_x) + "max_x:" + Double.toString(max_x) + "min_y:" + Double.toString(min_y) + "max_y:" + Double.toString(max_y);
	    	
		}
		catch(NullPointerException e) {
			return "no emlemt in this array"; 
		}
        
	}
	public void delete(int i) {
		
		if(i == size-1) {
			size--;
			return;
		};
		if(i == 0) {
			
			this.deleteStart();
			return;
		}
		
		
		T[] temp = (T[]) new Comparable[capacity];
		for(int j =0;j <i;j++) {
			temp[j] = a[j];
		}
		for(int j = i+1;j<size;j++) {
			temp[j-1] = a[j];
		}
		a = temp;
		size--;
		
	}
	public void deleteEnd() {
		size--;
	}
	public void deleteStart() {
		@SuppressWarnings("unchecked")
		T[] temp = (T[]) new Comparable[capacity];
		for(int i = 1; i<size; i++) {
			temp[i-1] = a[i];
		}
		size--;
	}
	public boolean have(Object t) {
		for(int i = 0;i<size;i++) {
			if(a[i].equals(t)) {
				return true;
			}
		}
		return false;
	}
	public void delete(T t) {
		for(int i = 0;i<size;i++) {
			if(a[i].equals(t)) {
				delete(i);
				return;
			}
		}
	}
	public T get(int i) {
		return a[i];
	}
	public void insert( int pos, T v) {
		if(checkgrow()) {
			capacity *=2;
			@SuppressWarnings("unchecked")
			T[] temp = (T[]) new Comparable[capacity];
			for(int i = 0; i < pos; i ++) {
				temp[i] = a[i];
			}
			temp[pos] = v;
			for(int j = pos+1; j < size+1;j++) {
				temp[j] = a[j-1];
			}
			size ++;
			a = temp;
		}
		else {
			@SuppressWarnings("unchecked")
			T[] temp = (T[]) new Comparable[capacity];
			for(int i = 0; i < pos; i ++) {
				temp[i] = a[i];
			}
			temp[pos] = v;
			for(int j = pos+1; j < size+1;j++) {
				temp[j] = a[j-1];
			}
			size ++;
			a = temp;
		}
	}
	public int size() {
		return size;
	}
	
	public T[] getMinMax() {
		T[] min_max = (T[])new Comparable[2];
		min_max[0] = this.a[0];
		min_max[1] = this.a[0];
		
		for(int i = 1; i < size-1; i++) {
			if(a[i].compareTo(a[i+1]) > 0) {
        		if(a[i].compareTo((T)min_max[1])>0)
        			min_max[1] = a[i];
        		if(a[i+1].compareTo((T)min_max[0]) < 0)
        			min_max[0] = a[i+1];
        	}
        	else {
        		if(a[i+1].compareTo((T)min_max[1])>0)
        			min_max[1] = a[i+1];
        		if(a[i].compareTo((T)min_max[0]) < 0)
        			min_max[0] = a[i];
        		}
        	}
		return min_max;
	}
	
	public String toString() {
		StringBuilder b = new StringBuilder(size*2);
		for(int i =0;i<size;i++) {
			b.append(a[i].toString());
			b.append(" ");
		}
		return b.toString();
	}
}
class Point implements Comparable<Point>{
	double x;
	double y;
	Point(double x, double y){
		this.x = x;
		this.y = y;
	}
	public String toString() {
		return x + "," + y;
	}
	public int compareTo(Point p1) {
		if(this.x > p1.x){
            return 1;
        }
        else
            return -1;
       
	}
	public boolean equals(Point p) {
		return this.x==p.x && this.y == p.y;
	}
}