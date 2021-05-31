package it.unipv.ingsw.electronicticketingsystem.test;

import java.util.Scanner;

import it.unipv.ingsw.electronicticketingsystem.util.ticket.*;
import it.unipv.ingsw.electronicticketingsystem.view.TicketMachineUI;

public class TicketTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s=new Scanner(System.in);
		TicketCatalog catalog = new TicketCatalog();
		

		TicketMachineUI ui=new TicketMachineUI(s);
		
		
		ui.start(catalog);
		
	}
}
