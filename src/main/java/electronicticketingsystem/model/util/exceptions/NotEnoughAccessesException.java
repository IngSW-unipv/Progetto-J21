package electronicticketingsystem.model.util.exceptions;

@SuppressWarnings("serial")
public class NotEnoughAccessesException extends Exception {
	 private static final String errorMessage="The ticket has already been validated!";
	 
	 public NotEnoughAccessesException() {
		 super(errorMessage);
	 }
}
