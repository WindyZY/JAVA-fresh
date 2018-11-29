package banking.domain;
import java.lang.Throwable;

public class OverdraftException extends Exception{//Define OverdraftException
	private double deficit;
	public double getDeficit() {
		return deficit;
	}
	
	public OverdraftException(String message,double def) {//Constructor
		super(message);
		deficit=def;
	}
}
