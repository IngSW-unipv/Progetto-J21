package electronicticketingsystem.controller;

import electronicticketingsystem.model.database.DataBase;
import electronicticketingsystem.model.util.sale.SaleLineItem;
import electronicticketingsystem.model.util.validation.Validation;

/**
 * Classe usata come Facade (pattern GoF) per permettere agli oggetti del dominio di sfruttare le tecniche di accesso
 * ai dati offerte dal DataBase senza richiamarne direttamente i metodi. Questa classe è inoltre un Singleton, in quanto
 * vogliamo che questo punto di accesso sia unico per tutto il sistema.
 * @param db	- oggetto di tipo DataBase di cui richiamare i metodi  
 */
public class PersistenceFacade {

	private DataBase db;
	private static PersistenceFacade instance = null;
	
	/**
	 * Costruttore della classe, che si limita a creare, attraverso il suo costruttore, un oggetto di 
	 * tipo DataBase di cui richiamare i metodi
	 */
	public PersistenceFacade() {
		
		db = new DataBase();
		
	}
	
	/**
	 * Metodo statico previsto dal pattern Singleton. Dovendo esistere un'unica istanza di questa classe,
	 * il metodo ne crea una e la restituisce nel caso non esista, oppure restituisce quella esistente 
	 * nel caso in cui ci sia già un'istanza.
	 * @return PersistenceFacade			- restituisce l'unica istanza esistente della classe stessa
	 */
	public static PersistenceFacade getInstance() {
		if (instance == null) {
			instance = new PersistenceFacade();
		}
		return instance;
	}
	
	/**
	 * Metodo che richiama il metodo addInspection() della classe DataBase per verificare la validità del
	 * biglietto e aggiungere i dati relativi al controllo nell'opportuna table del DataBase.
	 * @param ticketID			- ID del titolo di viaggio di cui verificare la validità
	 * @param idInspector		- ID del controllore che ha effettuato la verifica
	 * @return true				- se il titolo di viaggio risulta valido, false altrimenti
	 */
	public boolean Inspection(String ticketID, String idInspector) {
		
		return(db.addInspection(ticketID, idInspector));
		
	}
	
	/**
	 * Metodo che richiama il metodo addTicket() della classe DataBase per aggiungere alla table opportuna
	 * l'ID dei biglietti acquistati
	 * @param ticket			- oggetto della classe SaleLineItem che indica il titolo di viaggio da aggiungere
	 */
	public void addTicket(SaleLineItem ticket) {
		
		db.registerItem(ticket);
		
	}

	/**
	 * Metodo che richiama il metodo addValidation() della classe DataBase per aggiungere alla table opportuna
	 * l'ID e la scadenza del titolo di viaggio convalidato
	 * @param v					- oggetto della classe Validation da aggiungere
	 */
	public void addValidation(Validation v) {
		
		db.addValidation(v);
		
	}

	/**
	 * Metodo che richiama il metodo Login() della classe DataBase per implementare il meccanismo di login del
	 * controllore
	 * @param inspectorId		- ID del controllore
	 * @param psw				- password del controllore
	 * @return true				- se la coppia ID-password risulta corretta, false altrimenti
	 */
	public boolean login(String inspectorId, String psw) {
		return db.Login(inspectorId, psw);
	}
	
}