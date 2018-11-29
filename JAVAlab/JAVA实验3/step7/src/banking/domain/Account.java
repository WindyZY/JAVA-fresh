package banking.domain;

public class Account {
	protected double balance;
	public Account(double init_balance){balance=init_balance;}
	public double getBalance() {return balance;}
	public boolean deposit(double amt) 
	{
		balance=balance+amt;
		return true;
	}
	public void withdraw(double amt)throws OverdraftException //Declare exception
	{
		if(amt<=balance)
		{
			balance=balance-amt;
		}
		else
			throw new OverdraftException("Insufficient funds",amt-balance);//Throw exception when balance is insufficient
	}
}
