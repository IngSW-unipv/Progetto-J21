package electronicticketingsystem.test;

import java.util.Scanner;

import electronicticketingsystem.model.util.ticket.*;
import electronicticketingsystem.view.TicketMachineUI;

/**
 * Classe di test del sistema che contiene il metodo main per provare il funzionamento dell'emettitrice.
 */
public class TicketTester {

	/**
	 * Metodo main che crea l'oggetto Scanner per l'inserimento di comandi da pate dell'utente, il catalogo
	 * dei prodotti, l'oggetto Controller e fa partire l'interfaccia utente.
	 * @param args			parametri in ingresso al metodo main (non utilizzati)
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s=new Scanner(System.in);
		TicketCatalog catalog = new TicketCatalog();
		
		TicketMachineUI ui=new TicketMachineUI(s);
		
		
		ui.start(catalog); 
		
	}
}
 