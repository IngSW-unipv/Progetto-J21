package electronicticketingsystem.model.util.exceptions;

/**
 * Classe che descrive l'eccezione che viene lanciata se il numero di accessii assoicati a un titolo
 * di viaggio è pari a 0: in questo caso, il biglietto non può più essere convalidato.
 * @param errorMessage    -  stringa costante che indica il motivo della generazione dell'eccezione
 *
 */
@SuppressWarnings("serial")
public class NotEnoughAccessesException extends Exception {
	
	 private static final String errorMessage="The ticket has already been validated!";
	 
	 /**
	  * Costruttore della classe, che si limita a richiamare il costruttore della superclasse
	  * Exception passandogli la stringa di errore errorMessage
	  */
	 public NotEnoughAccessesException() {
		 super(errorMessage);
	 }
}
