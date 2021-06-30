package electronicticketingsystem.view;

import java.util.Scanner;

import electronicticketingsystem.controller.TicketInspector;
import electronicticketingsystem.controller.TicketMachine;

public class TicketInspectorUI {
	Scanner s;
	
	public TicketInspectorUI(Scanner s) {
		this.s=s;
	}
	
	public void start() {
		System.out.println("Please submit your Ticket Inspector Id: ");
		String inspectorId = s.next();
		System.out.println("Please submit your Password: ");
		String psw = s.next();
		TicketInspector ti = new TicketInspector(inspectorId,psw);
		System.out.println("Enter the ID of the ticket to be checked: ");
		String ticketId = s.next();
		if(ti.inspection(ticketId)==false) 
			System.out.println("This travel document isn't valid!");
		else 
			System.out.println("The travel document with ID "+ticketId+" is valid.");
	}

}
