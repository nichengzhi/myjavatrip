package bit_manapulation;

public class Bit_test {
	public static void main(String[] args) {
		int e = 0;
		System.out.println(e |=  1 << 10);//第10位置1 变成2的10次方
		int[] bits = new int[40];
		for (int m = 0; m < 40; m += 3) {
		    bits[m / 32] |= (1 << (m % 32));
		}
		
		System.out.println(bits[0]);
		System.out.printf(Integer.toBinaryString(1227133513));
		System.out.println();
				
		System.out.println(bits[1]);
		System.out.printf(Integer.toBinaryString(bits[1]));
		for (int m = 0; m < 40; m++) {
		    if (((bits[m / 32] >> (m % 32)) & 1) != 0)// 判断第n位是否为1
		        System.out.print('1');
		    else
		        System.out.print('0');
		}
	}
}
