package electronicticketingsystem.controller;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import electronicticketingsystem.model.util.ticket.TravelDocument;
import electronicticketingsystem.model.util.validation.Validation;
import electronicticketingsystem.model.util.validation.ValidationRegister;


/**
 * Classe che descrive un controllore.
 * Questa classe è realizzata seguendo il design pattern GRASP Controller: la classe
 * rappresenta il controller del sistema, che riceve le richieste dell'utente 
 * limitandosi a delegarle alle classi che contengono i metodi per elaborarle; il sistema
 * prevede due controller per disaccoppiare la gestione dell'interfaccia testuale dedicata
 * al cliente da quella dedicata al controllore.
 * 
 * @param idInspector			- stringa che indica l'ID del controllore
 * @param password				- stringa che indica la password associata al particolare 
 * 								  controllore per poter accedere alle procedure di controllo
 *  
 */

public class TicketInspector {
	private String idInspector;
	private String password;

	/**
	 * Costruttore della classe, che si occupa di inizializzare gli attributi idInspector e password
	 * @param id				- stringa che indica l'ID del controllore
	 * @param psw				- stringa che indica la password associata a quell'ID
	 */
	public TicketInspector(String id,String psw) {
		this.idInspector=id;
		this.password=psw;
	}
	
	/**
	 * Metodo che permette di controllare che la convalida di un biglietto sia valida.
	 * @param idTicket 		- stringa che indica l'ID del biglietto di cui verificare la validità 
	 * @return true 		- se la scadenza della corsa è successiva al tempo attuale, cioè se il biglietto
	 * 						  è valido
	 */
	public boolean inspection(String TicketID)  {
		ValidationRegister vr=ValidationRegister.getInstance();
		Validation ticket=vr.findValidation(TicketID); 
		if (ticket.equals(null)) 
			return false;
		else 
			return ticket.getExpirationTime().isAfter(LocalTime.now());
	}
	
	/**
	 * Metodo get che permette di ottenere l'ID di un controllore
	 * @return idInspector	- stringa che indica l'ID del controllore
	 */
	public String getIdInspector() {
		return this.idInspector;
	}
	
	/**
	 * Metodo get che permette di ottenere la password di un controllore
	 * @return password	   - stringa che indica la password del controllore
	 */
	public String getPassword(){
		return this.password;
	}
	
}
