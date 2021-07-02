package electronicticketingsystem.model.util.exceptions;

/**
 * Classe che descrive l'eccezione che viene lanciata se il totale da pagare risulta minore
 * o uguale a 0.0
 * @param errorMessage    -  stringa costante che indica il motivo della generazione dell'eccezione
 *
 */
@SuppressWarnings("serial")
public class InvalidAmountException extends Exception {

	private final static String errorMessage = "Invalid amount!";
	
	/**
	 * Costruttore della classe, che si limita a richiamare il costruttore della superclasse
	 * Exception passandogli la stringa di errore errorMessage
	 */
	public InvalidAmountException() {
		super(errorMessage);
	}
	
	
}
