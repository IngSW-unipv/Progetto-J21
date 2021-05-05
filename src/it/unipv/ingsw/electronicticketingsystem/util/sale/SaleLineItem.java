package it.unipv.ingsw.electronicticketingsystem.util.sale;

import java.util.ArrayList;
import java.util.List;

import it.unipv.ingsw.electronicticketingsystem.util.ticket.TicketCatalog;
import it.unipv.ingsw.electronicticketingsystem.util.ticket.TicketCatalogProvvisorio;
import it.unipv.ingsw.electronicticketingsystem.util.ticket.TicketType;
import it.unipv.ingsw.electronicticketingsystem.util.ticket.TravelDocument;

public class SaleLineItem {

	private TravelDocument ticket; //ticket contiene tutti i dati utili per questa linea
	
	public SaleLineItem(int type) {
		this.ticket=TicketCatalog.getSelectedTravelDocument(type); 
	}
	
	public double getSubTotal() {
		return ticket.getPrice();
	}

}