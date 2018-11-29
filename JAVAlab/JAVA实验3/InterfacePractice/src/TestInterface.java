import java.util.Scanner;
public class TestInterface {
	public static void main(String[] args) {
	    Dog d=new Dog();
	    d.speak(); d.run();
	    Person p=new Person();
	    p.speak();  p.run();
	    
	    //Create an instance of Bird
	    Bird b=new Bird();
	    b.speak(); b.run();
	  }
}