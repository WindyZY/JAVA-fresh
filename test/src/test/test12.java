package test;

public class test12 {
	static String[] str= {"open","door","the","open","name"};
	public static void main(String args[])
	{
		int cnt=0;
		for(int i=0;i<str.length-1;i++)
		{
			if(str[i].equals("open"))
			cnt++;	
		}
		System.out.println(cnt);
		for(int j=str.length-1;j>=0;j--)
		{
			System.out.print(str[j]+" ");
		}
	}
}
