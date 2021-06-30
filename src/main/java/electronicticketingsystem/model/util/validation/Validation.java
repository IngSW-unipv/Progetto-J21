package electronicticketingsystem.model.util.validation;

import java.time.LocalTime;
import electronicticketingsystem.model.util.sale.SaleLineItem;
import electronicticketingsystem.model.util.sale.SoldRegister;

/**
 * Classe che descrive una convalida. Una convalida è descritta dai seguenti attributi:
 * @param expirationTime		valore di tipo LocalTime che indica l'orario di validità del biglietto
 * @param id					valore di tipo String che indica l'id del biglietto a cui è associata la convalida
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
		SoldRegister sr=SoldRegister.getInstance();
		this.expirationTime=LocalTime.now().plusHours(sr.returnTicket(id).getTime()); //imposta il tempo di scadenza sommando il tempo now e la durata del biglietto
		sr.returnTicket(id).setOneAccessLess(); //quando viene effettuata una convalida si abbassa di uno il contatore delle corse
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
