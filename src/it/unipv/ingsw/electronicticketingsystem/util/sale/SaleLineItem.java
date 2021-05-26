package it.unipv.ingsw.electronicticketingsystem.util.sale;

import it.unipv.ingsw.electronicticketingsystem.util.ticket.*;


public class SaleLineItem {

	private TravelDocument ticket; //ticket contiene tutti i dati utili per questa linea
	private String ticketID;
	
	public SaleLineItem(int type) {
		this.ticket=TicketCatalog.getSelectedTravelDocument(type);
		this.ticketID = generateTicketID();
	}
	
	public double getSubTotal() {
		return ticket.getPrice();
	}
	
	public String generateTicketID() {
		String typeCode = Integer.toString(ticket.getType());
		String descriptionCode = ticket.getDescription().substring(0, 3).toUpperCase();
		String randomCode = Integer.toString((int)(Math.random()*10000));
		return typeCode + descriptionCode + randomCode;
	}

	public String getTicketID() {
		return ticketID;
	}
	
	public long getTime() {
		return ticket.getTimeToAdd();
	}
}