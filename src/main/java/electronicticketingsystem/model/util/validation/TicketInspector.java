package electronicticketingsystem.model.util.validation;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import electronicticketingsystem.model.util.ticket.TravelDocument;


/**
 * Classe creator di check
 * 
 * Gli attributi che descrivono questa classe sono:
 * @param idInspector			stringa che identifica l'opertore
 * @param ispections			lista di tutti i check effettuati 
 *  
 */

public class TicketInspector {
	private String idInspector;

	/**
	 * Costruttore della classe, che si occupa di inizializzare gli attributi idInspector e inspections
	 */
	public TicketInspector(String id) {
		this.idInspector=id;
	}
	
	/**
	 * Questo metodo permette di controllare se un biglietto è in uso.
	 * @param idTicket 		stringa che identifica il biglietto da controllare 
	 * @return true se la scadenza della corsa è successiva al tempo attuale
	 */
	public boolean newInspection(String idTicket) {
		ValidationRegister vr=ValidationRegister.getInstance();
		Validation ticket=vr.findValidation(idTicket); 
		if (ticket.equals(null)) return false;
		else return ticket.getExpirationTime().isAfter(LocalTime.now());
	}
	
	public String getIdInspector() {
		return this.idInspector;
	}
	
}
