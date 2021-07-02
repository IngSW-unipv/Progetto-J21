package electronicticketingsystem.model.util.exceptions;

/**
 * Classe che descrive l'eccezione che viene lanciata se l'ID inserito per la convalida
 * non è valido, cioè non corrisponde a un biglietto venduto 
 * @param errorMessage    -  stringa costante che indica il motivo della generazione dell'eccezione
 *
 */
@SuppressWarnings("serial")
public class TicketNotFoundException extends Exception {

	private final static String errorMessage = "The selected ID is not valid!";
	
	/**
	 * Costruttore della classe, che si limita a richiamare il costruttore della superclasse
	 * Exception passandogli la stringa di errore errorMessage
	 */
	public TicketNotFoundException() {
		super(errorMessage);
	}
}
