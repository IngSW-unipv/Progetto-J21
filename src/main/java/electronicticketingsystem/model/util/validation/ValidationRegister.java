package electronicticketingsystem.model.util.validation;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe che descrive il registro delle convalide.
 * Questa classe è realizzata seguendo il design pattern Singleton della GoF in quanto ogni emettitrice
 * deve possedere un'unica istanza del registro che contiene tutti i biglietti convalidati.
 * @param stampedTickets		è una lista di oggetti di tipo Validation
 * 
 */
public class ValidationRegister {
	private static List<Validation> stampedTickets;
	private static ValidationRegister instance = null;
	
	/**
	 * Costruttore della classe che definisce il registro come ArrayList
	 */
	public ValidationRegister() {
		ValidationRegister.stampedTickets=new ArrayList<>();
	}
	
	/**
	 * Metodo statico previsto dal pattern Singleton. Dovendo esistere un'unica istanza di questa classe,
	 * il metodo ne crea una e la restituisce nel caso non esista, oppure restituisce quella esistente 
	 * nel caso in cui ci sia già un'istanza
	 * @return ValidationRegister		restituisce l'unica istanza esistente della classe stessa
	 */
	public static ValidationRegister getInstance() {
		if (instance == null) {
			instance = new ValidationRegister();
		}
		return instance;
	}
	
	
	/**
	 * Metodo che aggiunge una certa convalida al registro
	 * @param v							richiede in ingresso l'oggetto della classe Validation da aggiungere
	 */
	public void addToRegister(Validation v) {
		stampedTickets.add(v); //aggiungi alla lista tutte le convalide	
	}
	
	/**
	 * Metodo che restituisce la convalida corrispondente all'id in ingresso
	 * @param id						l'ID del biglietto del quale si vuole ottenere la convalida
	 * @return Validation				restituisce un oggetto di tipo Validation corrispondente all'id inserito
	 */
	public Validation findValidation(String id) {
		for(Validation i: stampedTickets) {
			if(i.getID().equals(id)) return i;
		} return null; //se non viene trovato alcun elemento corrispondente ritorna null

	}
	
	/**
	 * Metodo di stampa del catalogo perchè possa essere visualizzato a schermo come ID + orario di convalida
	 */
	public void printValidationRegister() {
		System.out.println("Validated tickets' register:");
		for (Validation i : stampedTickets) {
			System.out.println("ID: "+i.getID() + " Expiration time: " + i.getExpirationTime());
		}
	}
	
}
