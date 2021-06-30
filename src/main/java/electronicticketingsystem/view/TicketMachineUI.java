package electronicticketingsystem.view;

import java.util.*;
import java.time.YearMonth;

import electronicticketingsystem.controller.TicketMachine;
import electronicticketingsystem.model.util.payment.CreditCard;
import electronicticketingsystem.model.util.ticket.TicketCatalog;
import electronicticketingsystem.model.util.validation.TicketInspector;
import electronicticketingsystem.model.util.exceptions.*;

/**
 * Classe che descrive la view del sistema. La classe gestisce l'interfaccia utente testuale dell'emettitrice, 
 * limitandosi a effettuare alcune stampe e a richiamare gli opportuni metodi del controller TicketMachine quando
 * sono richieste elaborazioni dei dati.
 * Gli attributi sono:
 * @param s				oggetto della classe Scanner usato per ottenere le informazioni inserite su console da 
 * 						parte dell'utente
 * @param choice 		valore intero che rappresenta la scelta del servizio effettuata dall'utente
 * @param tm			oggetto della classe TicketMachine, ovvero della classe che fa da Controller del sistema
 *
 */
public class TicketMachineUI {
	
	Scanner s;
	int choice;
	TicketMachine tm;
	
	/**
	 * Costruttore della classe, che richiede in ingresso un oggetto della classe Scanner. Il costruttore inizializza 
	 * l'attributo choice e crea un oggetto Controller.
	 * @param s (Scanner)
	 */
	public TicketMachineUI(Scanner s) {
		this.s=s;
		choice=0;
		tm=new TicketMachine();
	}
	
	/**
	 * Metodo che gestisce le operazioni effettuate dall'interfaccia grafica. Il metodo si limita ad effettuare una serie
	 * di stampe di servizi selezionabili dall'utente e a richiamare i metodi opportuni del controller per permettere
	 * all'utente di visualizzare a schermo i risultati.
	 * @param cat		richiede in ingresso un oggetto della classe TicketCatalog perchè l'utente visualizzi i
	 * 					prodotti acquistabili
	 * @exception PaymentNotCompletedException
	 */
	public void start(TicketCatalog cat) {
		
		do {
			System.out.println("1 - Validation\n2 - Purchase\n9 - End");
			choice=s.nextInt();
			switch(choice) {
				case 1: 
					String id;
					System.out.println("Please, enter the ID of the ticket you want to validate");
					id = s.next();
					tm.validation(id);
				break;
				case 2:
					tm.makeSale();
					int type=0,qty=0;
					do {
						cat.printTicketCatalog();
						System.out.println("9 - Payment");
						type=s.nextInt();
						if(type!=9) {
							System.out.println("Please, enter quantity");
							qty=s.nextInt();
							tm.enterItem(type,qty);
						}
					}while(type !=9);
					System.out.println("The total is: "+ tm.getTotal());
					System.out.println("Please, choose your payment method:\n1 - Cash\n2 - Credit Card");
					int paymentMethod=s.nextInt();
					switch(paymentMethod) {
						case 1:
							System.out.println("Please, enter cash");
							double cs=s.nextDouble();
							tm.makeCashPayment(cs);
							System.out.println("The change is: \n" + tm.getChange());
							try {
								tm.endSale();
							} catch (PaymentNotCompletedException e) {
								e.printStackTrace();
							}
							
						break;
						case 2:
							System.out.println("Please submit your credit card details\nCard Number:");
							String cardNumber = s.next();
							System.out.println("Expiration Date:");
							String exp = s.next();
							YearMonth expDate = YearMonth.parse(exp);
							System.out.println("CVV:");
							String cvv = s.next();
							CreditCard cc = new CreditCard(cardNumber, expDate, cvv);
							tm.makeCreditCardPayment(cc);
							try {
								tm.endSale();
							} catch (PaymentNotCompletedException e) {
								e.printStackTrace();
							}
					};
				break;
				case 3:
					System.out.println("Please submit your Ticket Inspector Id: ");
					String inspectorId = s.next();
					TicketInspector ti = new TicketInspector(inspectorId);
					
					System.out.println("Enter the ID of the ticket to be checked: ");
					String ticketId = s.next();
					if(ti.newIspection(ticketId)==false) System.out.println("This travel document isn't valid!");
					else System.out.println("The travel document with ID "+ticketId+" is valid.");
				break;
			};
		}while(choice !=9);
	}


}
