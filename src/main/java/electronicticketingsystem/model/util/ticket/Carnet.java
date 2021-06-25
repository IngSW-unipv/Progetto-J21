package electronicticketingsystem.model.util.ticket;

/**
 * Classe che descrive un Carnet di biglietti; questa classe estende la classe astratta Titolo di Viaggio.
 * @see TravelDocument
 * Attributo di questa classe:
 * @param accessesNumber		valore int che indica il numero di accessi consentiti (uguale al numero di 
 * 								biglietti corsa singola di cui � composto il carnet)
 */

public class Carnet extends TravelDocument {
	
	public static final int MAX_ACCESSES_NUMBER = 10;
	private int  accessesNumber;
	
	/**
	 * Costruttore 
	 * Un carnet � un titolo di viaggio di tipo 2 che costa 35.5 euro; ogni biglietto singolo da cui � costituito �
	 * utilizzabile per un'ora dopo la sua convalida.
	 */
	public Carnet() {
		super(2, "Carnet of Single Ride Tickets", 35.50, 1);
		this.accessesNumber = MAX_ACCESSES_NUMBER;
	}

	/**
	 * Metodo get per risalire al numero di accessi permessi 
	 * @return accessesNumber (int)
	 */
	public int getAccessesNumber() {
		return accessesNumber;
	}

}
