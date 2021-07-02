package electronicticketingsystem.model.util.exceptions;

/**
 * Classe che descrive l'eccezione che viene lanciata se il tipo di biglietto selezionato
 * non corrisponde ad uno acquistabile dal sistema.
 * @param errorMessage    -  stringa costante che indica il motivo della generazione dell'eccezione
 *
 */
public class TicketTypeNotExistingException extends Exception {

	private final static String errorMessage = "The ticket type you selected is not valid!";
	
	/**
	 * Costruttore della classe, che si limita a richiamare il costruttore della superclasse
	 * Exception passandogli la stringa di errore errorMessage
	 */
	public TicketTypeNotExistingException() {
		super(errorMessage);
	}
}
