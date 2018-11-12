package homework;

/*
 * HW8 -Expression Tree
 * Author: Chengzhi NI
 */
import java.util.Stack;

import java.io.*;

//input reverse polish notation print in order
public class ExpressionTree{
	
	Node root;
	Stack<Node> operands;
	
	
	private static class Node{
		String item;
		Node left;
		Node right;
		Node parent;
		public java.lang.String toString() {
			return (java.lang.String) item;
		}
		private String calculate(double num1, double num2, String operator) {
			double result = 0;
			char c = operator.charAt(0);
			switch(c) {
			case '*':
				result = num1 * num2;
				break;
			case '+':
				result = num1 + num2;
				break;
			case '-':
				result = num1 - num2;
				break;
			case '/':
				result = num1/num2;
				break;
			case '^':
				result = (int)Math.pow(num1, num2);
				break;
			}
			return Integer.toString((int)result);
		}
		public void evel(){
			if(isOperator(item)) {
				
				try {
					
					int num1 = Integer.parseInt(left.item);
					int num2 = Integer.parseInt(right.item);
					this.item = calculate(num1,num2,this.item);
					
				}catch (NumberFormatException n){
					if(left.item.contains("(") || right.item.contains(")")) {
						
						String num1_string = left.item.replaceAll("\\(.*\\)|[A-Za-z]", "");
						String num2_string = right.item.replaceAll("\\(.*\\)|[A-Za-z]", "");
						double num1 = ((num1_string.equals("")?1:Double.parseDouble(num1_string)));
						
						double num2 = ((num2_string.equals("")?1:Double.parseDouble(num2_string)));
						
						String num_part = calculate(num1,num2,item);
						
						//Pattern pattern = Pattern.compile("[A-Za-z]");  
						//Matcher matcher = pattern.matcher(left.item); 
						if(num_part.equals("0"))
							item = "0";
					}
					else if (left.item.replaceAll("[0-9]", "").equals("") || right.item.replaceAll("[0-9]", "").equals("")) {
						if(item.equals("+") || item.equals("-") || item.equals("^")) {
							StringBuilder sb = new StringBuilder();
							sb.append("(").append(left.item).append(item).append(right.item).append(")");
							item = sb.toString();
						}
						else {
							if(item.equals("*")) {
								StringBuilder sb = new StringBuilder();
								String num1_string = left.item.replaceAll("[A-Za-z]|\\(|\\)", "");
								String num2_string = right.item.replaceAll("[A-Za-z]|\\(|\\)", "");
								double num1 = ((num1_string.equals("")?1:Double.parseDouble(num1_string)));
								double num2 = ((num2_string.equals("")?1:Double.parseDouble(num2_string)));
								String num_part = calculate(num1,num2,item);
								if(num_part.equals("1"))
									item = sb.append(left.item.replaceAll("[0-9]", "")).append(right.item.replaceAll("[0-9]", "")).toString();
								else if(num_part.equals("0"))
									item = "0";
								else {
									item = sb.append(num_part).append(left.item.replaceAll("[0-9]", "")).append(right.item.replaceAll("[0-9]", "")).toString();
								}
							}
						}
					}
					else 
					{
						String var1 = left.item.replaceAll("[0-9]|\\(|\\)|/", "");
						String var2 = right.item.replaceAll("[0-9]|\\(|\\)|/", "");
						
						if(var1.equals(var2)) {
							String num1_string = left.item.replaceAll("[A-Za-z]|\\(|\\)", "");
							String num2_string = right.item.replaceAll("[A-Za-z]|\\(|\\)", "");
							double num1 = 0;
							double num2 = 0;
							if(num1_string.contains("/")) {
								String[] parts = num1_string.split("/");
								String numerator = parts[0].equals("")?"1":parts[0];
								String denumerator = parts[1].equals("")?"1":parts[1];
								num1 = Double.parseDouble(numerator)/Double.parseDouble(denumerator);
							}
							else {
								num1 = ((num1_string.equals("")?1:Double.parseDouble(num1_string)));
							}
							if(num2_string.contains("/")) {
								String[] parts = num2_string.split("/");
								String numerator = parts[0].equals("")?"1":parts[0];
								String denumerator = parts[1].equals("")?"1":parts[1];
								num2 = Double.parseDouble(numerator)/Double.parseDouble(denumerator);
							}
							else {
								num2 = ((num2_string.equals("")?1:Double.parseDouble(num2_string)));
							}
							
							String num_part = calculate(num1,num2,item);
							if(num_part.equals("1"))
								item = var1;
							else if(num_part.equals("0"))
								item = "0";
							else {
								StringBuilder sb = new StringBuilder();
								sb.append(num_part).append(var1);
								item = sb.toString();
							}
						}
						else {
							
							StringBuilder sb = new StringBuilder();
							sb.append("(").append(left.item).append(item).append(right.item).append(")");
							item = sb.toString();
						}
					}
					
				}
			}
		}
		public void badinorder() {
			
			if (left != null)
				left.badinorder();
			System.out.print(item + " ");
			if (right != null)
				right.badinorder();
		}
		public void inorder() {
			Stack<String> strs = new Stack<>();
			Stack<String> addmin = new Stack<>();
			Stack<Node> nodestack = new Stack<>();
			Node cursor = this;
			boolean needmerge = false;
			while(cursor != null || !(nodestack.isEmpty())) {
				
				while(cursor != null) {
					nodestack.push(cursor);
					cursor = cursor.left;
				}
				if(cursor == null) {
					cursor = nodestack.pop();
					if(cursor.item.equals("+") || cursor.item.equals("-")) {
						//String s1 = strs.pop();
						
						/*String newstr = (String) new StringBuilder()
								.append("(")
								.append(s1)
								.append(cursor.item)
								.append(cursor.right.item)
								.append(")")
								.toString();
						strs.push(newstr);*/
						//System.out.println(strs.peek());
						addmin.push(cursor.item);
						needmerge = true;
					}
					else 
					{
						
						if(!addmin.isEmpty()) {
							String operator = addmin.pop();
							String newstr = (String) new StringBuilder()
									.append("(")
									.append(strs.pop())
									.append(" ")
									.append(operator)
									.append(" ")
									.append(cursor.item)
									.append(")")
									.toString();
							strs.push(newstr);
						}
						else {
							strs.push(cursor.item);
						}
					}
					cursor = cursor.right;
				}
				
			}
			StringBuilder sb = new StringBuilder();
			for(String s: strs) {
				sb.append(s);
				sb.append(" ");
			}
			System.out.println(sb.toString());
			
		}
		
		public void preorder() {
			System.out.print(item + " ");
			if (left != null)
				left.preorder();
			if (right != null)
				right.preorder();
		}
		public void postorder() {
			if (left != null)
				left.postorder();
			if (right != null)
				right.postorder();
			System.out.print(item + " ");
		}
		
	}
	
	public ExpressionTree(String expression) {
		this.operands = new Stack<>();
		
		String[] splited = expression.split(" ");
		for(int i =0; i < splited.length; i++) {
			Node temp_node = new Node();
			
			temp_node.item = splited[i];
			
			if(!isOperator(splited[i])) {
				operands.push(temp_node);
			}
			else {
				
				Node temp_node1 = operands.pop();
				Node temp_node2 = operands.pop();
				temp_node.right = temp_node1;
				temp_node.left = temp_node2;
				temp_node1.parent = temp_node;
				temp_node2.parent = temp_node;
				operands.push(temp_node);
				
			}
			//System.out.println(Arrays.toString(operands.toArray()));
			
		}
		root = operands.pop();
	}
	public void inorder() {
		root.inorder();
	}
	public void preorder() {
		root.preorder();
	}
	public void postorder() {
		root.postorder();
	}
	public void badinorder() {
		
		root.badinorder();
	}
	public static boolean isOperator(String c) {
		switch(c) {
			case "+":
			case "-":
			case "*":
			case "/":
			case "^":
				return true;
		}
		return false;
	}
	public String getresult2() {
		Stack<Node> nodestack = new Stack<>();
		//use cursor to traversal
		Node cursor = root;
		Node lastvisit = root;
		while(cursor != null || !(nodestack.isEmpty())) {
			while(cursor != null) {
				nodestack.push(cursor);
				cursor = cursor.left;
			}
			Node peek_value = nodestack.peek();
			if(peek_value.right == null || peek_value.right.item.equals(lastvisit.item)) {
				cursor =  nodestack.pop();
				if(isOperator(cursor.item)) {
					
					cursor.evel();
					//System.out.println(cursor.item);
				}
				lastvisit = cursor;
				cursor = null;
			}
			else {
				cursor = peek_value.right;
				
				 
			}
		}
		return root.item;
	}
	public static void main(String[] args) {
		
		/*String test = "x x - a b + *";
		
		ExpressionTree et = new ExpressionTree("x x - a b + *");
		
		et.inorder();
		System.out.println();
		et.preorder();
		System.out.println();
		et.postorder();
		System.out.println();*/
		//et.getresult2();
		String filename = "hw8inp.dat";
		String line="";
		
        try
        {
                BufferedReader in=new BufferedReader(new FileReader(filename));
                line=in.readLine();
                while (line!=null)
                {
                	System.out.println("input is: " + line);
                	ExpressionTree et = new ExpressionTree(line);
                	System.out.print("inorder: ");
                	et.badinorder();
                	System.out.print("preorder: ");
                	et.preorder();
                	System.out.print("postorder: ");
                	et.postorder();
                	System.out.print("optimize inorder: ");
                	et.inorder();
                	System.out.print("result is: " + et.getresult2());
                	
                	System.out.println();
                	line=in.readLine();
                }
                in.close();
        } catch (IOException e)
        {
                e.printStackTrace();
        }
		
		
		
		
	}
	
	
}