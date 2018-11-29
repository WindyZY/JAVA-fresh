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
	
	public void sortCustomers(){
		Customer tmp;
		for(int i=0;i<numberOfCustomers;i++) {
			int min=i;
			for(int j=i;j<numberOfCustomers;j++) {
				if(customers[j].compareTo(customers[i])<0)
					min=j;
			}
			tmp=customers[i];
			customers[i]=customers[min];
			customers[min]=tmp;
		}
	}
	
	public void searchCustomers(String name){
		for(int i=0;i<numberOfCustomers;i++)
		{
			if(name==customers[i].getFirstName()||name==customers[i].getLastName())
				print(customers[i]);
		}
	}
	
	public void print(Customer cus) {
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
