package bit_manapulation;

public class Bit_mana {
	public static void main(String[] main) {
		System.out.printf("%032x\n",1);//二进制输出方法 32位输出
		// 判断偶数
		for (int i = 0; i < 10; i ++) {
	        if ((i & 1) == 0) { // 偶数
	            System.out.println(i);
	        }
	    }
		//交换两数
		int c = 1, d = 2;
		c ^= d;
		d ^= c;
		c ^= d;
		System.out.println("c=" + c);
		System.out.println("d=" + d);
		//变换符号
		int a = -15, b = 15;
		System.out.println(~a + 1);
		System.out.println(~b + 1);
		//求绝对值
		int i = a >> 31;
		System.out.println(i);
		System.out.println(i == 0 ? a : (~a + 1));
		int j = a >> 31;
		System.out.println((a ^ j) - j);
		// 在一个数指定位上置1
		int e = 0;
		e |=  1 << 10;
		System.out.println(e);
		//判断指定位上是0还是1
		if ((e & (1 << 10)) != 0)//判断2进制第10位是0还是1
		    System.out.println("指定位上为1");
		else
		    System.out.println("指定位上为0");

	}
}
