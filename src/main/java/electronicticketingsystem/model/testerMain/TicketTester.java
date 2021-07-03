package electronicticketingsystem.model.testerMain;

import java.util.Scanner;

import electronicticketingsystem.model.util.exceptions.NotEnoughAccessesException;
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
	 * dei prodotti, l'oggetto Controller e fa partire l'interfaccia utente, dopo aver permesso all'utente
	 * di selezionare la modalità di utilizzo (controllore o viaggiatore)
	 * @param args			- parametri in ingresso al metodo main (non utilizzati)
	 * @throws TicketTypeNotExistingException  se il tipo di biglietto selezionato non è previsto dal catalogo
	 * @throws NotEnoughAccessesException 	   se il titolo di viaggio non può più essere convalidato perchè ha
	 * 										   esaurito gli accessi associati
	 */
	public static void main(String[] args) throws TicketTypeNotExistingException, NotEnoughAccessesException {

		Scanner s=new Scanner(System.in);
		TicketCatalog catalog = TicketCatalog.getInstance();
		
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
 