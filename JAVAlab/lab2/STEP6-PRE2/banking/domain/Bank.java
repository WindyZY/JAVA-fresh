package banking.domain;

public class Bank {
	private static final Bank bankInstance=new Bank();//Create a public static variable
	public static Bank getBank() {  //A static method to return an instance of the Bank class
		return bankInstance;
	}
	
	private Bank() {
		customers=new Customer[5];
		numberOfCustomers=0;
	}
	private Bank(int num)
	{
		numberOfCustomers=0;
		customers=new Customer[num];
	}
	private Customer customers[];
	private int numberOfCustomers;
	public int getNumOfCustomers() {return numberOfCustomers;}
	public void addCustomer(String f, String l) {
		Customer cus=new Customer(f,l);
		customers[numberOfCustomers++]=cus;
	}
	public Customer getCustomer(int index) {return customers[index];}
}