package it.unipv.ingsw.electronicticketingsystem.util.ticket;

import java.util.*;

public class TicketCatalog {

	private List<TravelDocument> catalog;
	
	public TicketCatalog() {
		catalog = new ArrayList<>();
		loadTravelDocuments();
	}
	
	public TravelDocument getSelectedTravelDocument(int type) {
		return catalog.get(type);
	}
	
	private void loadTravelDocuments() {
		TravelDocument ticket;
		
		ticket = new SingleRideTicket();
		catalog.add(ticket);
		
		ticket = new Carnet();
		catalog.add(ticket);
		
		ticket = new BusPass();
		catalog.add(ticket);
	}
	
	public void printTicketCatalog() {
		System.out.println("Catalogo dei biglietti acquistabili:\n");
		for (TravelDocument tickets : catalog) {
			System.out.println(tickets.getType() + " - " + tickets.getDescription() + " , " + tickets.getPrice() + " €");
		}
	}
}
