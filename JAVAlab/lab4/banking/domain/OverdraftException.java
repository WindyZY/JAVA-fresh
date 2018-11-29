package banking.domain;
import java.lang.Throwable;

public class OverdraftException extends Exception{
	private double deficit;
	public double getDeficit() {
		return deficit;
	}
	
	public OverdraftException(String message,double def) {
		super(message);
		deficit=def;
	}
}
