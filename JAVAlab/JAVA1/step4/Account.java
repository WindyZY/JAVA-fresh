
public class Account {
	public Account(double init_balance){balance=init_balance;}
	private	double balance;
	public double getBalance() {return balance;}
	public boolean deposit(double amt) 
	{
		balance=balance+amt;
		return true;
	}
	public boolean withdraw(double amt) {
		if(amt<=balance)
		{
			balance=balance-amt;
			return true;
		}
		else
			return false;
	}
}
