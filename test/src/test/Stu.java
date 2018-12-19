package test;

public class Stu {
	public final String classid="4";
	public static String school;
	public String id;
	public String name;
	private int age;
	
	Stu(String i,String n)
	{
		id=i;
		name=n;
	}
	public static String getSchool()
	{
		return school;
	}
}