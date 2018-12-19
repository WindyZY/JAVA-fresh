package test;

public class test3 {
	static int[] arr={1,4,5,3,2};
	public static int temp;
	public static void selsort(int[] a)
	{
		for(int i=0;i<a.length-1;i++)
		{
			int min=a[i];
			for(int j=i+1;j<a.length;j++)
			{
				if(min>a[j]) {
					int tem=a[j];
					a[j]=a[i];
					a[i]=tem;
				}
			}
		}
	}
	public static void main(String args[])
	{
		selsort(arr);
		for(int e:arr)
			System.out.print(e+" ");
	}
}
