package test;

public class test7 {
	public static boolean isPrime(int n)
	{
		boolean flag=true;
		for(int i=2;i<=n/2;i++)
		{
			if(n%i==0)
			{
				flag=false;
				break;
			}
		}
		return flag;
	}
	public static void main(String args[])
	{
		for(int i=1;i<=100;i++)
		{
			if(isPrime(i))
				System.out.print(i+" ");
		}
	}
}
