package test;
import java.lang.reflect.*;
public class test6 {
	public static void test(Object obj) {
		Class<? extends Object> clazz = obj.getClass();
		Method[] ms = clazz.getDeclaredMethods();
		int len = ms.length;
		for(int i = 0; i < len; i++) {
			System.out.println("����" + clazz.getName() + "������" + ms[i].getName());
		}
	}
	public static void main(String[] args) {
		A a = new A();
		test(a);
	}
}
class A{
	public void aaa(){}
	public void aab(){}
	public void aadfsf(){}
	public void aadfds(){}
}
