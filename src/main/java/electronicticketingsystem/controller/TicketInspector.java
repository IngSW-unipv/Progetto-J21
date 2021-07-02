package electronicticketingsystem.controller;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import electronicticketingsystem.model.util.ticket.TravelDocument;
import electronicticketingsystem.model.util.validation.Validation;
import electronicticketingsystem.model.util.validation.ValidationRegister;


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
	private String psw;

	/**
	 * Costruttore della classe, che si occupa di inizializzare gli attributi idInspector e inspections
	 */
	public TicketInspector(String id,String psw) {
		this.idInspector=id;
		this.psw=psw;
	}
	
	/**
	 * Questo metodo permette di controllare se un biglietto è in uso.
	 * @param idTicket 		stringa che identifica il biglietto da controllare 
	 * @return true se la scadenza della corsa è successiva al tempo attuale
	 */
	public boolean inspection(String TicketID)  {
		ValidationRegister vr=ValidationRegister.getInstance();
		Validation ticket=vr.findValidation(TicketID); 
		if (ticket.equals(null)) 
			return false;
		else 
			return ticket.getExpirationTime().isAfter(LocalTime.now());
	}
	
	public String getIdInspector() {
		return this.idInspector;
	}
	
	public String getPassword(){
		return this.psw;
	}
	
}
