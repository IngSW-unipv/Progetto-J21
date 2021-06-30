package electronicticketingsystem.model.util.sale;

import electronicticketingsystem.model.util.ticket.TicketCatalog;
import electronicticketingsystem.model.util.ticket.TravelDocument;
import electronicticketingsystem.model.util.ticket.*;


/**
 * Classe che descrive un singolo componente di una certa vendita.
 * Gli attributi della classe sono:
 * @param price				valore double che indica il prezzo di un biglietto
 * @param time				valore long che indica il tempo di validità del biglietto
 * @param ticketID			stringa che indica l'ID del biglietto 
 * @param accesses			valore int che indica il numero di accessi consentiti con 
 * 							quel biglietto (valido solo per i biglietti di tipo 2, Carnet)
 *
 */
public class SaleLineItem {
	
	private double price;
	private long time;		//tempo di durata validità
	private String ticketID;	//codice univoco
	private int accesses;	//numero di corse
	
	/**
	 * Costruttore della classe, che accetta in ingresso il tipo di biglietto selezionato.
	 * A seconda del tipo di biglietto, il costruttore imposta gli attributi della classe
	 * con i valori corrispondenti al tipo selezionato. L'ID del biglietto è impostato 
	 * tramite un apposito metodo di generazione degli id.
	 * @param type (int)
	 */
	public SaleLineItem(int type) {
		TravelDocument ticket=TicketCatalog.getSelectedTravelDocument(type);
		this.price=ticket.getPrice();
		this.time=ticket.getTimeToAdd();
		this.ticketID = generateTicketID(ticket);
		
		if(type==2) this.accesses=((Carnet) ticket).getAccessesNumber();
		else this.accesses=1;	
	}
	
	public SaleLineItem(TravelDocument ticket) {
		
		this.price=ticket.getPrice();
		this.time=ticket.getTimeToAdd();
		this.ticketID = generateTicketID(ticket);
		
		if(ticket.getType()==2) this.accesses=((Carnet) ticket).getAccessesNumber();
		else this.accesses=1;	
	}
	
	/**
	 * Metodo get per ottenere il prezzo di un biglietto, che costrituisce il subtotale 
	 * della vendita
	 * @return price (double)
	 */
	public double getSubTotal() {
		return this.price;
	}
	
	/**
	 * Metodo per la generazione dell'ID di un biglietto. Il metodo riceve in ingresso 
	 * il titolo di viaggio di cui generare l'ID e lo genera nel formato:
	 * intero che indica il tipo + prime tre caratteri della descrizione + codice random
	 * @param ticket			oggetto di tipo TravelDocument di cui calcolare l'ID
	 * @return id del biglietto 
	 */
	public String generateTicketID(TravelDocument ticket) {
		String typeCode = Integer.toString(ticket.getType());
		String descriptionCode = ticket.getDescription().substring(0, 3).toUpperCase();
		String randomCode = Integer.toString((int)(Math.random()*10000));
		return typeCode + descriptionCode + randomCode;
	}

	/**
	 * Metodo get per risalire all'ID del titolo di viaggio
	 * @return ticketID (String)
	 */
	public String getTicketID() {
		return ticketID;
	}
	
	/**
	 * Metodo get per risalire al tempo di durata del titolo di viaggio
	 * @return time (long)
	 */
	public long getTime() {
		return this.time;
	}
	
	/**
	 * Metodo get per risalire al numero di accessi consentiti con il titolo di viaggio
	 * @return accesses (int)
	 */
	public int getNumberAccesses() {
		return this.accesses;
	}
	
	/**
	 * Metodo che decrementa di 1 il numero di accessi consentiti (ad ogni convalida, il 
	 * numero di accessi rimanenti va decrementato di 1)
	 */
	public void setOneAccessLess() {
		this.accesses-=1;
	}
}