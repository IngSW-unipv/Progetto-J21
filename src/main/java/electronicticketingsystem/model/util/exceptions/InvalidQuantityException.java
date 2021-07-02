package electronicticketingsystem.model.util.exceptions;

public class InvalidQuantityException extends Exception {

	private final static String errorMessage = "The quantity you selected is not valid!"; 
	
	public InvalidQuantityException() {
		super(errorMessage);
	}
}
