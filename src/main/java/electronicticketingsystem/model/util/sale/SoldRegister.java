package electronicticketingsystem.model.util.sale;

import java.util.ArrayList;
import java.util.List;

//Singleton
public class SoldRegister {
	private static List<SaleLineItem> payedTickets;
	private static SoldRegister instance = null;
	
	private SoldRegister() {
		SoldRegister.payedTickets=new ArrayList<>();
	}
	
	public static SoldRegister getInstance() {
		if (instance == null)
			instance = new SoldRegister();
		return instance;
	}
	
	public void addToRegister(SaleLineItem ticket) {
		payedTickets.add(ticket);
	}
	
	public static SaleLineItem returnTicket(String id) {
		for (SaleLineItem i : payedTickets) {
			if (i.getTicketID() == id) return i;
		} return null;
	} 
	
}


