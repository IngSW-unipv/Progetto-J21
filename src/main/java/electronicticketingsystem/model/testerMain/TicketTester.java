package electronicticketingsystem.model.testerMain;

import java.util.Scanner;

import electronicticketingsystem.model.util.exceptions.TicketTypeNotExistingException;
import electronicticketingsystem.model.util.ticket.*;
import electronicticketingsystem.view.TicketInspectorUI;
import electronicticketingsystem.view.TicketMachineUI;

/**
 * Classe di test del sistema che contiene il metodo main per provare il funzionamento dell'emettitrice.
 */
public class TicketTester {

	/**
	 * Metodo main che crea l'oggetto Scanner per l'inserimento di comandi da pate dell'utente, il catalogo
	 * dei prodotti, l'oggetto Controller e fa partire l'interfaccia utente.
	 * @param args			parametri in ingresso al metodo main (non utilizzati)
	 * @throws TicketTypeNotExistingException 
	 */
	public static void main(String[] args) throws TicketTypeNotExistingException {
		// TODO Auto-generated method stub
		Scanner s=new Scanner(System.in);
		TicketCatalog catalog = new TicketCatalog();
		
		TicketMachineUI uiM=new TicketMachineUI(s);
		TicketInspectorUI uiI=new TicketInspectorUI(s);
		
		int choice;
		do {
			System.out.println("1-Traveler\n2-TicketInspector");
			choice=s.nextInt();
			if(choice==1)
				uiM.start(catalog);
			else if(choice==2)
				uiI.start();
			else 
				System.out.println("The option you selected is not valid, please try again\n");	
		}while(true);
	}
}
 