package electronicticketingsystem.model.util.exceptions;

/**
 * Classe che descrive l'eccezione che viene lanciata se la quantità di prodotto richiesta
 * è minore o uguale a 0 
 * @param errorMessage    -  stringa costante che indica il motivo della generazione dell'eccezione
 * 
 */
@SuppressWarnings("serial")
public class InvalidQuantityException extends Exception {

	private final static String errorMessage = "The quantity you selected is not valid!"; 
	
	/**
	 * Costruttore della classe, che si limita a richiamare il costruttore della superclasse
	 * Exception passandogli la stringa di errore errorMessage
	 */
	public InvalidQuantityException() {
		super(errorMessage);
	}
}
