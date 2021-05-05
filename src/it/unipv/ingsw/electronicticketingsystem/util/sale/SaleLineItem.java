package it.unipv.ingsw.electronicticketingsystem.util.sale;

import java.util.ArrayList;
import java.util.List;

import it.unipv.ingsw.electronicticketingsystem.util.ticket.TicketCatalogProvvisorio;
import it.unipv.ingsw.electronicticketingsystem.util.ticket.TicketType;

public class SaleLineItem {
	private double price;
	private String description;
	private int nShots;
	private int time;
	
	private String id;

	
	public SaleLineItem(TicketType type) {
		//quando viene invocato il costruttore viene istanziato un oggetto 'SaleLineItem' con gli attributi attinenti
		//al tipo di biglietto
		
		this.price=TicketCatalogProvvisorio.getPrice(type); //completo il campo conoscendo solo il tipo di biglietto
		this.description=TicketCatalogProvvisorio.getDescription(type); 
		this.nShots=TicketCatalogProvvisorio.getShots(type);
		this.time=TicketCatalogProvvisorio.getTime(type);
		
		this.id=generateTicketID(type);


	}
	
	public double getSubTotal() {
		return price;
	}
	
	public String generateTicketID(TicketType t) {
		
		// rivisitazione del generatore di codice in TravelDocument
		
		//Da ultimare
		String randomCode = Integer.toString((int)(Math.random()*10000));
		return t + randomCode;
	}
	

}