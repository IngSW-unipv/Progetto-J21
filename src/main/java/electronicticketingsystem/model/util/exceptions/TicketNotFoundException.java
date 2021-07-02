package electronicticketingsystem.model.util.exceptions;

public class TicketNotFoundException extends Exception {

	private final static String errorMessage = "The selected ID is not valid!";
	
	public TicketNotFoundException() {
		super(errorMessage);
	}
}
