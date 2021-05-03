package it.unipv.ingsw.electronicticketingsystem.test;

import it.unipv.ingsw.electronicticketingsystem.util.ticket.*;

public class TicketTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TicketCatalog catalog = new TicketCatalog();
		
		// Test della corretta stampa del catalogo
		catalog.printTicketCatalog();
		
		
		// Test della corretta generazione dei TicketID
		TravelDocument td1 = new Carnet();
		TravelDocument td2 = new SingleRideTicket();
		TravelDocument td3 = new SingleRideTicket();
		TravelDocument td4 = new BusPass();
		
		System.out.println(td1.generateTicketID());
		System.out.println(td2.generateTicketID());
		System.out.println(td3.generateTicketID());
		System.out.println(td4.generateTicketID());
		
	}

}
