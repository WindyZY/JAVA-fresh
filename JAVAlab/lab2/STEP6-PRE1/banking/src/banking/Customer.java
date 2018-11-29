package banking;

import java.util.ArrayList;

public class Customer {
	private String firstName;
	private String lastName;
	private Account account;
	ArrayList<Account> list=new ArrayList<>();//Create unlimitd array to store the accounts
	
	public Customer(String f,String l) {firstName=f;lastName=l;}
	public String getFirstName() {return firstName;}
	public String getLastName() {return lastName;}
	public Account getAccount() {return account;}
	public void setAccount(Account acct) {account=acct;}
	
	public void addAccount(Account acct) {  //Add an acount
		list.add(acct);
	}
	public Account getAccount(int index) {   //Get the information of account
		return (Account)list.get(index);
	}
	public int getNumOfAccounts() {  //Get the number of accounts
		return list.size();
	}
}
