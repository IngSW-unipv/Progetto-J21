package electronicticketingsystem.model.util.validation;

import java.time.LocalTime;

import electronicticketingsystem.model.util.exceptions.TicketNotFoundException;
import electronicticketingsystem.model.util.sale.SaleLineItem;
import electronicticketingsystem.model.util.sale.SoldRegister;

/**
 * Classe che descrive una convalida.
 * @param expirationTime		- valore di tipo LocalTime che indica l'orario di scadenza del titolo di viaggio
 * @param id					- valore di tipo String che indica l'id del biglietto a cui è associata la convalida
 *
 */
public class Validation {
	private LocalTime expirationTime;
	private String id;

	/**
	 * Costruttore della classe, che crea un oggetto di tipo Validation a partire dall'id del biglietto da convalidare.
	 * @param id				- id del biglietto da convalidare
	 * @throws TicketNotFoundException	- se l'id inserito non corrisponde a nessuno degli id dei biglietti presenti nel
	 * 									  SoldRegister
	 */
	public Validation(String id) throws TicketNotFoundException {
		SoldRegister sr=SoldRegister.getInstance();
		SaleLineItem ticket = sr.returnTicket(id);
		if (ticket != null) {
			this.id = ticket.getTicketID();
			this.expirationTime = LocalTime.now().plusHours(ticket.getTime());
			ticket.setOneAccessLess();
		} else
			throw new TicketNotFoundException();
	}

	 
	/**
	 * Metodo get per risalire all'orario di scadenza del biglietto 
	 * @return expirationTime 		- oggetto della classe LocalTime che indica l'orario di scadenza
	 */
	public LocalTime getExpirationTime() {
		return this.expirationTime;
	}
	
	/**
	 * Metodo get per risalire all'id del biglietto associato alla convalida
	 * @return id 					- stringa che indica l'id del biglietto convalidato
	 */
	public String getID() {
		return this.id;
	}
		
	
}
