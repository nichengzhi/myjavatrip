package Suanfa;

import java.math.BigInteger;

public class BinomialCoefficient
{
	public static void main(String[] args)
	{
		int n = 100000;
		int k = 30;
		System.out.println(binomial(new BigInteger[n+1][n+1], n, k));
 
	}
 
	/**
	 * 计算二项式系数
	 * @param recode
	 * @param n
	 * @param k
	 * @return
	 */
	public static BigInteger binomial(BigInteger[][] recode, int n, int k)
	{	BigInteger a = new BigInteger("1");
		for (int i = 0; i <= n; i++)
		{
			for (int j = 0; j <= Math.min(k, i); j++)
			{
				if (j == i || j == 0)
				{
					recode[i][j] = a;
				}
				else
				{
					recode[i][j] = recode[i - 1][j].add(recode[i - 1][j - 1]);
				}
			}
		}
		
		return recode[n][k];
 
	}
 
}