package test;
import java.lang.Math;
public class test4 {
	static double[] arr= new double[5];
	public static double choose(double[] a){
		for(int i=0;i<5;i++)
			a[i]=Math.random()*100;
		double min=a[0];
		for(int i=0;i<5;i++)
			if(a[i]<min)
				min=a[i];
		return min;
	}
	public static void main(String args[])
	{
		System.out.println("The minimum is "+choose(arr));
		System.out.println(arr[0]+" "+arr[1]);
		System.out.println(arr[2]+" "+arr[3]);
		System.out.println(arr[4]);
	}
}
