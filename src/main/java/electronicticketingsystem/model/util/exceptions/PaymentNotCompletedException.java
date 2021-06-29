package electronicticketingsystem.model.util.exceptions;

public class PaymentNotCompletedException extends Exception {

	public PaymentNotCompletedException(String errorMessage) {
		super(errorMessage);
	}
}
