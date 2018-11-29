package banking.domain;

import java.lang.Comparable;
import java.util.ArrayList;
public class Customer implements Comparable<Customer>{//Define Customer as an instance of Comparable
	public int compareTo(Customer o) { //Override compareTo
		int f=firstName.compareTo(o.getFirstName());
		int l=lastName.compareTo(o.getLastName());
		//Judge which customer's name is small according to Unicode
		if(l>0||(l==0&&f>0)) 
			return 1;
		else if(l<0||(l==0&&f<0))
			return -1;
		else
			return 0;
	}
	
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
