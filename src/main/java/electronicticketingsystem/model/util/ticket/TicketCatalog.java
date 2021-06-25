package electronicticketingsystem.model.util.ticket;

import java.util.*;

/**
 * Classe che descrive il Catalogo contentente i vari titoli di viaggio acquistabili
 * L'attributo
 * @param catalog è una lista di elementi di tipo TravelDocument
 */

public class TicketCatalog {

	private static List<TravelDocument> catalog;
	
	/**
	 * Costruttore che definisce il catalogo come ArrayList e lo riempie con l'apposito metodo
	 */
	public TicketCatalog() {
		catalog = new ArrayList<>();
		loadTravelDocuments();
	}
	
	/**
	 * Metodo per ottenere il titolo di viaggio opportuno a seconda del tipo richiesto
	 * @param type (int)
	 * @return TravelDocument		il metodo restituisce l'oggetto di tipo TravelDocument che ha come 
	 * 								tipo il valore specificato
	 */
	public static TravelDocument getSelectedTravelDocument(int type) {
		int catalogIndex = 0;
		for (TravelDocument tickets : catalog) {
			if (tickets.getType() == type) {
				catalogIndex = catalog.indexOf(tickets);
			}
		}
		return catalog.get(catalogIndex);
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
