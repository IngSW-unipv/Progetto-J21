package electronicticketingsystem.model.util.exceptions;

/**
 * Classe che descrive l'eccezione che viene lanciata se un pagamento non va a buon fine
 *
 */
@SuppressWarnings("serial")
public class PaymentNotCompletedException extends Exception {

	/**
	 * Costruttore della classe, che richiede in ingresso una generica stringa per permettere
	 * di differenziare il messaggio di errore a seconda del tipo di pagamento.
	 * @param errorMessage 	- stringa che indica il motivo della generazione dell'eccezione
	 */
	public PaymentNotCompletedException(String errorMessage) {
		super(errorMessage);
	}
}
