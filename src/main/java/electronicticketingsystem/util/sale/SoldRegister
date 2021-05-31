package electronicticketingsystem.util.sale;

import java.util.ArrayList;
import java.util.List;

public class SoldRegister {
	private static List<SaleLineItem> payedTickets;
	
	public SoldRegister() {
		SoldRegister.payedTickets=new ArrayList<>();
	}
	
	public void addToRegister(SaleLineItem ticket) {
		payedTickets.add(ticket); //aggiungi i biglietti comprati alla lista
	}
	
	public static SaleLineItem findByTicketId(String id) {
		for(SaleLineItem i: payedTickets) {
			if(i.generateTicketID().equals(id)) return i; //restituisci l'istanza in base al suo id
		}
		return null; //eventualmente inserire eccezione per biglietto non restituito
	}
}
