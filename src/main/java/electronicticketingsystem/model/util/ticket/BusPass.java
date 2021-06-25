package electronicticketingsystem.model.util.ticket;


/**
 * Classe che descrive un Abbonamento; questa classe estende la classe astratta Titolo di Viaggio.
 * @see TravelDocument
 */

public class BusPass extends TravelDocument {

	/**
	 * Costruttore 
	 * Un abbonamento � un titolo di viaggio di tipo 2 che costa 12 euro e che, nel periodo di validit�, pu�
	 * essere usato durante l'intero arco della giornata.
	 */
	public BusPass() {
		super(3, "Bus Pass", 12.00, 24);
	}
}
