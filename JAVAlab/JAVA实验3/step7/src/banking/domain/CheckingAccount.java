package banking.domain;

public class CheckingAccount extends Account{
	private double overdraftProtection;
	
	public CheckingAccount(double balance) {
		super(balance);
	}
	public CheckingAccount(double balance, double protect) {
		super(balance);
		overdraftProtection=protect;
	}
	public void withdraw(double amt) throws OverdraftException//Declare exception
	{
		if(amt>balance)
		{
			if(amt>overdraftProtection+balance)
			{
				if(overdraftProtection==0)  //Throw exception
					throw new OverdraftException("No overdraft protection",amt-balance);
				else
					throw new OverdraftException("Insufficient funds for overdraft protectoin",amt-balance);
			}
			else
			{
				overdraftProtection-=(amt-balance);
				balance=0;
			}
		}
		else
			balance-=amt;
	}
	public double getOverdraftProtection()
	{
		return overdraftProtection;
	}
}
