package electronicticketingsystem.model.util.ticket;

import java.util.*;

import electronicticketingsystem.model.util.exceptions.TicketTypeNotExistingException;

/**
* Classe che descrive il catalogo dei titoli di viaggio che si possono acquistare dal sistema.
* Questa classe è realizzata seguendo il design pattern Singleton della GoF in quanto ogni emettitrice
* deve possedere un'unica istanza del catalogo che contenga tutti gli articoli offerti. 
* @param catalog				- è una lista di oggetti di tipo TravelDocument
* 
*/
public class TicketCatalog {

	private static List<TravelDocument> catalog;
	private static TicketCatalog instance = null;
	
	/**
	 * Costruttore che definisce il catalogo come ArrayList e lo riempie con l'apposito metodo
	 */
	private TicketCatalog() {
		TicketCatalog.catalog = new ArrayList<>();
		loadTravelDocuments();
	}
	
	/**
	 * Metodo statico previsto dal pattern Singleton. Dovendo esistere un'unica istanza di questa classe,
	 * il metodo ne crea una e la restituisce nel caso non esista, oppure restituisce quella esistente 
	 * nel caso in cui ci sia già un'istanza.
	 * @return TicketCatalog			- restituisce l'unica istanza esistente della classe stessa
	 */
	public static TicketCatalog getInstance() {
		if (instance == null) {
			instance = new TicketCatalog();
		}
		return instance;
	}
	
	/**
	 * Metodo per ottenere il titolo di viaggio opportuno a seconda del tipo richiesto
	 * @param type 				- valore int che indica il tipo di biglietto
	 * @return TravelDocument	- l'oggetto di tipo TravelDocument che ha come tipo il valore specificato
	 * @throws TicketTypeNotExistingException	se il sistema non prevede alcun titolo di viaggio del tipo
	 * 										    richiesto
	 */
	public TravelDocument getSelectedTravelDocument(int type) throws TicketTypeNotExistingException {
		for(TravelDocument ticket: catalog) {
			if(ticket.getType() == type) 
				return ticket;
		} throw new TicketTypeNotExistingException(); 
	}
	
	/**
	 * Metodo per inizializzare il catalogo caricando al suo interno i tre tipi di titoli di viaggio 
	 * acquistabili attraverso il sistema
	 */
	private void loadTravelDocuments() {
		TravelDocument ticket;
		
		ticket = new SingleRideTicket();
		catalog.add(ticket);
		
		ticket = new Carnet();
		catalog.add(ticket);
		
		ticket = new BusPass();
		catalog.add(ticket);
	}
	
	/**
	 * Metodo di stampa del catalogo perchè possa essere visualizzato a schermo
	 */
	public void printTicketCatalog() {
		System.out.println("Purchasable tickets' catalog:\n");
		for (TravelDocument tickets : catalog) {
			System.out.println(tickets.getType() + " - " + tickets.getDescription() + " -> " + tickets.getPrice() + " €");
		}
	}
}
