package electronicticketingsystem.model.util.ticket;

import java.util.*;

import electronicticketingsystem.model.util.exceptions.TicketTypeNotExistingException;
import electronicticketingsystem.model.util.sale.SaleLineItem;
import electronicticketingsystem.model.util.sale.SoldRegister;

/**
 * Classe che descrive il Catalogo contentente i vari titoli di viaggio acquistabili
 * @param catalog 	- è una lista di elementi di tipo TravelDocument
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
	 */
	public TravelDocument getSelectedTravelDocument(int type) throws TicketTypeNotExistingException {
		for(TravelDocument ticket: catalog) {
			if(ticket.getType() == type) 
				return ticket;
		} throw new TicketTypeNotExistingException(); //se non viene trovato alcun elemento corrispondente ritorna null
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
