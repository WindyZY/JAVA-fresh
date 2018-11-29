package banking.domain;

import java.text.NumberFormat;

public class Bank {
	private static final Bank bankInstance = new Bank();
	private Bank() {
		customers = new Customer[10];
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
	
	public static Bank getBank() {
		return bankInstance;
	}
	
	public void sortCustomers(){//Sort the customer by their name
		Customer tmp;
		for(int i=0;i<numberOfCustomers;i++) {//Selection sort
			int min=i;
			for(int j=i;j<numberOfCustomers;j++) {//Find the minimum
				if(customers[j].compareTo(customers[i])<0)
					min=j;
			}
			//Swap the current and the minimum
			tmp=customers[i];
			customers[i]=customers[min];
			customers[min]=tmp;
		}
	}
	
	public void searchCustomers(String name){//Find the customer according to his or her name
		for(int i=0;i<numberOfCustomers;i++)
		{
			if(name==customers[i].getFirstName()||name==customers[i].getLastName())
				print(customers[i]);
		}
	}
	
	public void print(Customer cus) { //Print the information of the customer
		 NumberFormat currency_format = NumberFormat.getCurrencyInstance();
	      System.out.println("Customer: "
				 + cus.getLastName() + ", "
				 + cus.getFirstName());

	      for ( int acct_idx = 0; acct_idx < cus.getNumOfAccounts(); acct_idx++ ) {
		Account account = cus.getAccount(acct_idx);
		String  account_type = "";

		// Determine the account type
		if ( account instanceof SavingsAccount ) {
		  account_type = "Savings Account";
		} 
		else if ( account instanceof CheckingAccount ) {
		  account_type = "Checking Account";
		} 
		else {
		  account_type = "Unknown Account Type";
		}
		// Print the current balance of the account
		System.out.println("    " + account_type + ": current balance is "
				 + currency_format.format(account.getBalance()));
	      }
	    }
}
