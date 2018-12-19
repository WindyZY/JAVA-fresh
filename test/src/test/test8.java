package test;

public class test8 {
	static char[] ch=new char[26];
	public static void fresh(char[] c) {
		for(int i=0;i<c.length/2;i++)
		{
			c[i]=(char)(65+i);
			c[c.length-i-1]=(char)(65+i+1);
		}
	}
	public static void main(String args[])
	{
		fresh(ch);
		for(char e:ch)
		{
			System.out.print(e+" ");
		}
	}
}
