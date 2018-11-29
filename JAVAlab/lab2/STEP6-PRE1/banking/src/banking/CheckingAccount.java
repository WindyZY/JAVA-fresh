package banking;

public class CheckingAccount extends Account{
	private double overdraftProtection;
	
	public CheckingAccount(double balance) {
		super(balance);
	}
	public CheckingAccount(double balance, double protect) {
		super(balance);
		overdraftProtection=protect;
	}
	public boolean withdraw(double amt) {
		if(amt<=balance)
		{
			balance=balance-amt;
			return true;
		}
		else if(amt>balance&&amt<=(balance+overdraftProtection))
		{
			balance=amt-balance;
			overdraftProtection+=balance;
			return true;
		}
		else
			return false;
	}
	public double getOverdraftProtection()
	{
		return overdraftProtection;
	}
}