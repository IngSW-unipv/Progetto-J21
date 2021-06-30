package electronicticketingsystem.model.util.validation;

import java.time.LocalTime;
import electronicticketingsystem.model.util.sale.SaleLineItem;
import electronicticketingsystem.model.util.sale.SoldRegister;

/**
 * Classe che descrive una convalida. Una convalida � descritta dai seguenti attributi:
 * @param expirationTime		valore di tipo LocalTime che indica l'orario di validit� del biglietto
 * @param id					valore di tipo String che indica l'id del biglietto a cui � associata la convalida
 *
 */
public class Validation {
	private LocalTime expirationTime;
	private String id;

	/**
	 * Costruttore della classe, che crea un oggetto di tipo Validation a partire dall'id del biglietto da convalidare.
	 * @param id				id del biglietto da convalidare
	 */
	public Validation(String id) {
		this.id = id;
		setExpirationTime();
		SoldRegister.returnTicket(id).setOneAccessLess(); //quando viene effettuata una convalida si abbassa di uno il contatore delle corse
	}
	
	/**
	 * Metodo per il calcolo dell'orario di scadenza del biglietto tramite la classe LocalTime del package java.time
	 * Il metodo aggiunge le ore di validit� previste dal tipo di biglietto all'orario di convalida (cio� quello 
	 * corrente).
	 */
	private void setExpirationTime() {
		SaleLineItem ticket=SoldRegister.returnTicket(id);
	    this.expirationTime=LocalTime.now().plusHours(ticket.getTime()); //Returns a copy of this LocalTime with the specified number of hours added.
		
	}
	
	/**
	 * Metodo get per risalire all'orario di scadenza del biglietto 
	 * @return expirationTime (LocalTime)
	 */
	public LocalTime getExpirationTime() {
		return this.expirationTime;
	}
	
	/**
	 * Metodo get per risalire all'id del biglietto associato alla convalida
	 * @return id (String)
	 */
	public String getID() {
		return this.id;
	}
	
	
	
}
