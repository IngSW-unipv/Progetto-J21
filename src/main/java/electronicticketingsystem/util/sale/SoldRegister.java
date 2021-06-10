package electronicticketingsystem.util.sale;

import java.util.HashMap;
import java.util.Map;

public class SoldRegister {
	private static Map<String, SaleLineItem> payedTickets;
	
	public SoldRegister() {
		SoldRegister.payedTickets=new HashMap<>();
	}
	
	public void addToRegister(SaleLineItem ticket) {
		payedTickets.put(ticket.getTicketID(), ticket); //carica nella mappa l'id generato dal sistema
	}
	
	public static SaleLineItem returnTicket(String id) {
		return payedTickets.get(id);
	}
}

