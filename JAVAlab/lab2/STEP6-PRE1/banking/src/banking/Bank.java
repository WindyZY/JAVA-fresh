package banking;

public class Bank {
	public Bank() {
		customers=new Customer[5];
		numberOfCustomers=0;
	}
	public Bank(int num)
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