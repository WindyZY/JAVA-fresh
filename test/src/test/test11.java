package test;

public class test11 {
	static int[] a= {20,10,54,45,68,90,2};
	public static void main(String args[])
	{
		for(int i=0;i<a.length-1;i++)
		{
			int min=a[i];
			for(int j=i;j<a.length;j++)
			{
				if(a[j]<min)
				{
					int tmp=a[j];
					a[j]=a[i];
					a[i]=tmp;
				}
			}
		}
		for(int i=0;i<a.length;i++)
		{
			System.out.print(a[i]+" ");
		}
	}
}
