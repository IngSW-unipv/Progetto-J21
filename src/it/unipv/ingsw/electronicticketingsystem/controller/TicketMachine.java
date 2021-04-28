package it.unipv.ingsw.electronicticketingsystem.controller;
import java.util.*;

import it.unipv.ingsw.electronicticketingsystem.util.sale.Sale;

public class TicketMachine {

	static Scanner s=new Scanner(System.in);
	public static void main(String[] args) {
		//ipotetica versione tramite interfaccia testuale
		
		int scelta;
		do {
			System.out.println("1-convalida \n  2-acquisto \n 9-termina ");
			scelta=s.nextInt();
			switch(scelta) {
			case 1: 
				//operazioni future
				System.out.println("convalida effettuata");
			break;
			case 2:
				int type;//da sostituire con tipo biglietto
				Sale sal=new Sale();
				do {
					System.out.println("1-corsa singola 2-carnet 3-abbonamento 9-pagamento");
					type=s.nextInt();
					/*TicketMachine.enterItem(type);*/ //funzione che chiede la qta e poi chiama la funz di sale per creare un item
				}while(type !=9);
				sal.setCompleted();
				System.out.println("il totale è:" + sal.getTotal());
				System.out.println("inserire contanti");
				double a=s.nextDouble();
				sal.makePayment(a);
			break;
				
			}
			
			
		}while(scelta != 9);
		
		/*public void enterItem(int type) {
			System.out.println("inserire qta");
			int qta=s.nextInt();
			//chiamerà la funzione di sale che crea un nuovo saleLineItems
		}*/
	}
	

}
