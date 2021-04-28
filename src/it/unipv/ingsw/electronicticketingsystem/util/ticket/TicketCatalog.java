package it.unipv.ingsw.electronicticketingsystem.util.ticket;

import java.util.*;

public class TicketCatalog {

	private List<TicketDescription> catalog;
	
	public TicketCatalog() {
		catalog = new ArrayList<>();
	}
	
	public TicketDescription getTicketDescription(int type) {
		return catalog.get(type);
	}
	
	public void addTicketDescription(TicketDescription ticket) {
		catalog.add(ticket);
	}
	
	public void printTicketCatalog() {
		System.out.println("Catalogo dei biglietti acquistabili:\n");
		for (TicketDescription description : catalog) {
			System.out.println(description.getName() + " , " + description.getPrice() + " €");
		}
	}
}
