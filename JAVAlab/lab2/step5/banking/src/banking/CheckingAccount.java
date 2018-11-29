package banking;
//Create a new account type
public class CheckingAccount extends Account{
	private double overdraftProtection;
	
	public CheckingAccount(double balance) {//Constructor
		super(balance);
	}
	public CheckingAccount(double balance, double protect) {//Constructor
		super(balance);
		overdraftProtection=protect;
	}
	public boolean withdraw(double amt) {//Function for withdrawing
		if(amt<=balance)   //If balance is enough
		{
			balance=balance-amt;
			return true;
		}
		else if(amt>balance&&amt<=(balance+overdraftProtection))//Need to overdraft
		{
			balance=amt-balance;
			overdraftProtection+=balance;
			return true;
		}
		else  //Out of limited range
			return false;
	}
	public double getOverdraftProtection()   //Get the overdraft protection
	{
		return overdraftProtection;
	}
}