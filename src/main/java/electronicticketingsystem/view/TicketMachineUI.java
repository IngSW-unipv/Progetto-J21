package electronicticketingsystem.view;

import java.util.*;


import electronicticketingsystem.controller.TicketMachine;
import electronicticketingsystem.util.ticket.TicketCatalog;

public class TicketMachineUI {
	
	Scanner s;
	int choice;
	TicketMachine tm;
	
	public TicketMachineUI(Scanner s) {
		this.s=s;
		choice=0;
		tm=new TicketMachine();
	}
	
	public void start(TicketCatalog cat) {
		
		do {
			System.out.println(" 1-Validation \n  2-Purchase \n 9-End ");
			choice=s.nextInt();
			switch(choice) {
				case 1: 
					//operazioni future
				break;
				
				case 2:
					tm.makeSale();
					int type=0,qty=0;
					do {
						cat.printTicketCatalog();
						System.out.println("9- Payment");
						type=s.nextInt();
						if(type!=9) {
							System.out.println("Please, enter quantity");
							qty=s.nextInt();
							tm.enterItem(type,qty);
						}
					}while(type !=9);
					System.out.println("The total is: "+ tm.getTotal());
					System.out.println("Please, enter cash");
					double cs=s.nextDouble();
					tm.makePayment(cs);
					System.out.println("The change is: " + tm.getChange());
					tm.endSale();
				break;
					
			};
		}while(choice !=9);
	}

}
