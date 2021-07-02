package electronicticketingsystem.model.util.ticket;

/**
 * Classe che descrive un biglietto Corsa Singola; questa classe estende la classe astratta Titolo di Viaggio.
 * @see TravelDocument
 */
public class SingleRideTicket extends TravelDocument {
	
	/**
	 * Costruttore della classe: un biglietto corsa singola è un titolo di viaggio di tipo 1 che costa 1.40 euro e che risulta utilizzabile
	 * per un'ora dal momento della sua convalida.
	 */
	public SingleRideTicket() {
		super(1, "Single Ride Ticket", 1.40, 1);
	}
	

}
