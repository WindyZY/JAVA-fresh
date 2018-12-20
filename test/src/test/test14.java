package test;
import java.util.Scanner;
public class test14 {
	public static void main(String args[])
	{
		Scanner input=new Scanner(System.in);
		String a=input.next();
		String b=input.next();
		boolean flag=true;
		boolean p[]= new boolean[b.length()];
		if(a.length()!=b.length())
			System.out.println("NO!");
		else {
		for(int i=0;i<a.length();i++)
		{
			for(int j=0;j<b.length();j++)
			{
				if(a.charAt(i)==b.charAt(j))
				{
					if(p[j]==false)
						p[j]=true;
				}
			}
		}
		for(int k=0;k<b.length();k++)
		{
			if(p[k]==false)
				flag=false;
		}
		if(flag)
			System.out.println("YES!");
		else
			System.out.println("NO!");
		}
	}
}
