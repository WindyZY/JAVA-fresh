
public class Account {
	public Account(double init_balance){balance=init_balance;}
	private	double balance;
	public double getBalance() {return balance;}
	public void deposit(double amt) {balance=balance+amt;}
	public void withdraw(double amt) {balance=balance-amt;}
}
