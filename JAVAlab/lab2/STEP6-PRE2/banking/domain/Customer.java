package banking.domain;

import java.util.ArrayList;

public class Customer {
	private String firstName;
	private String lastName;
	private Account account;
	ArrayList<Account> list=new ArrayList<>();
	
	public Customer(String f,String l) {firstName=f;lastName=l;}
	public String getFirstName() {return firstName;}
	public String getLastName() {return lastName;}
	public Account getAccount() {return account;}
	public void setAccount(Account acct) {account=acct;}
	
	public void addAccount(Account acct) {
		list.add(acct);
	}
	public Account getAccount(int index) {
		return (Account)list.get(index);
	}
	public int getNumOfAccounts() {
		return list.size();
	}
}
