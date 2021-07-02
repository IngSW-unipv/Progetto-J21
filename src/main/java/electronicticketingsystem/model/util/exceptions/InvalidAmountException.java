package electronicticketingsystem.model.util.exceptions;

public class InvalidAmountException extends Exception {

	private final static String errorMessage = "Invalid amount!";
	
	public InvalidAmountException() {
		super(errorMessage);
	}
	
	
}
