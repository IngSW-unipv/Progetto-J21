package electronicticketingsystem.model.util.sale;

import electronicticketingsystem.model.util.ticket.TicketCatalog;
import electronicticketingsystem.model.util.ticket.TravelDocument;
import electronicticketingsystem.model.util.ticket.*;


public class SaleLineItem {

	private double price;
	private long time;		//tempo di durata validità
	private String ticketID;	//codice univoco
	private int accesses;	//numero di corse
	
	public SaleLineItem(int type) {
		TravelDocument ticket=TicketCatalog.getSelectedTravelDocument(type);
		this.price=ticket.getPrice();
		this.time=ticket.getTimeToAdd();
		this.ticketID = generateTicketID(ticket);
		
		if(type==2) this.accesses=((Carnet) ticket).getAccessesNumber();
		else this.accesses=1;	
	}
	
	public double getSubTotal() {
		return this.price;
	}
	
	public String generateTicketID(TravelDocument ticket) {
		String typeCode = Integer.toString(ticket.getType());
		String descriptionCode = ticket.getDescription().substring(0, 3).toUpperCase();
		String randomCode = Integer.toString((int)(Math.random()*10000));
		return typeCode + descriptionCode + randomCode;
	}

	public String getTicketID() {
		return ticketID;
	}
	
	public long getTime() {
		return this.time;
	}
	
	public int getNumberAccesses() {
		return this.accesses;
	}
	
	public void setOneAccessLess() {
		this.accesses-=1;
	}
}