package electronicticketingsystem.view.ui;

import java.util.Scanner;
import electronicticketingsystem.controller.PersistenceFacade;
import electronicticketingsystem.controller.TicketInspector;

/**
 * Classe che descrive la view del sistema. La classe gestisce l'interfaccia testuale dell'emettitrice dedicata
 * al controllore, limitandosi a effettuare alcune stampe e a richiamare gli opportuni metodi del controller 
 * TicketMachine quando sono richieste elaborazioni dei dati.
 * @param s				- oggetto della classe Scanner usato per ottenere le informazioni inserite su console da 
 * 						  parte del controllore
 */
public class TicketInspectorUI {
	Scanner s;
	
	/**
	 * Costruttore della classe, che richiede in ingresso un oggetto della classe Scanner. 
	 * @param s 		- oggetto della classe scanner
	 */
	public TicketInspectorUI(Scanner s) {
		this.s=s;
	}
	
	/**
	 * Metodo che gestisce le operazioni effettuate dall'interfaccia grafica. Il metodo si limita ad effettuare le
	 * stampe relative al login e all'operazione di controllo e a richiamare i metodi opportuni del controller per 
	 * permettere l'interazione con il controllore.
	 */
	public void start() {
		PersistenceFacade pf = PersistenceFacade.getInstance();
		System.out.println("Please submit your Ticket Inspector Id: ");
		String inspectorId = s.next();
		System.out.println("Please submit your Password: ");
		String psw = s.next();
		if(pf.login(inspectorId, psw) == true) {
			TicketInspector ti = new TicketInspector(inspectorId,psw);
			System.out.println("Enter the ID of the ticket to be checked: ");
			String ticketId = s.next();
			if(ti.inspection(ticketId)==false) 
				System.out.println("This travel document isn't valid!");
			else 
				System.out.println("The travel document with ID "+ticketId+" is valid.");
		}

	}


}
