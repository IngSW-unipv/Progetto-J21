package electronicticketingsystem.model.util.sale;

import java.util.ArrayList;
import java.util.List;
import electronicticketingsystem.controller.PersistenceFacade;

/**
* Classe che descrive il registro degli articoli venduti.
* Questa classe è realizzata seguendo il design pattern Singleton della GoF in quanto ogni emettitrice
* deve possedere un'unica istanza del registro che contiene tutti i biglietti venduti. Questa classe
* è necessaria per memorizzare in modo persistente gli articoli che compongono una vendita una volta che
* è stata completata, ovvero nel momento in cui è stato effettuato il pagamento.
* @param payedTickets				- è una lista di oggetti di tipo SaleLineItem
* 
*/
public class SoldRegister {
	private static List<SaleLineItem> payedTickets;
	private static SoldRegister instance = null;
	
	/**
	 * Costruttore della classe che definisce il registro come ArrayList
	 */
	private SoldRegister() {
		SoldRegister.payedTickets=new ArrayList<>();
	}
	
	/**
	 * Metodo statico previsto dal pattern Singleton. Dovendo esistere un'unica istanza di questa classe,
	 * il metodo ne crea una e la restituisce nel caso non esista, oppure restituisce quella esistente 
	 * nel caso in cui ci sia già un'istanza.
	 * @return SoldRegister			- restituisce l'unica istanza esistente della classe stessa
	 */
	public static SoldRegister getInstance() {
		if (instance == null) {
			instance = new SoldRegister();
		}
		return instance;
	}
	
	/**
	 * Metodo che aggiunge un certo articolo della vendita al registro
	 * @param ticket				- l'oggetto della classe SaleLineItem da aggiungere
	 */
	public void addToRegister(SaleLineItem ticket) {
		payedTickets.add(ticket);
		PersistenceFacade pf = PersistenceFacade.getInstance();
		pf.addTicket(ticket);
	}
	
	/**
	 * Metodo che permette di risalire all'articolo a partire dal suo ID. Il metodo consulta il registro e,
	 * se esiste, restituisce l'oggetto della classe SaleLineItem che ha come ID quello posto in ingresso.
	 * @param id					- id dell'articolo da ricercare
	 * @return SaleLineItem         - oggetto di tipo SaleLineItem con l'ID richiesto (o null se non è stata
	 * 								  trovata corrispondenza)
	 */
	public SaleLineItem returnTicket(String id) {
		for(SaleLineItem i: payedTickets) {
			if(i.getTicketID().equals(id)) 
				return i;
		} return null; //se non viene trovato alcun elemento corrispondente ritorna null
	}
	
}



