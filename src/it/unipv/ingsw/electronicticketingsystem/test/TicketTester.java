package it.unipv.ingsw.electronicticketingsystem.test;

import it.unipv.ingsw.electronicticketingsystem.util.ticket.*;

public class TicketTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TicketCatalog catalog = new TicketCatalog();
		
		TicketDescription desc1 = new TicketDescription(1, TicketName.CORSA_SINGOLA, 1.40);
		TicketDescription desc2 = new TicketDescription(2, TicketName.CARNET, 12.00);
		TicketDescription desc3 = new TicketDescription(3, TicketName.ABBONAMENTO, 35.50);
		
		catalog.addTicketDescription(desc1);
		catalog.addTicketDescription(desc2);
		catalog.addTicketDescription(desc3);
		
		catalog.printTicketCatalog();
		
		
	}

}
