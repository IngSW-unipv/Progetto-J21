package it.unipv.ingsw.electronicticketingsystem.util.sale;

import it.unipv.ingsw.electronicticketingsystem.util.ticket.*;


public class SaleLineItem {

	private TravelDocument ticket; //ticket contiene tutti i dati utili per questa linea
	
	public SaleLineItem(int type) {
		this.ticket=TicketCatalog.getSelectedTravelDocument(type); 
	}
	
	public double getSubTotal() {
		return ticket.getPrice();
	}

}