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
		if (instance == null) {
			instance = new SoldRegister();
		}
		return instance;
	}
	
	public void addToRegister(SaleLineItem ticket) {
		payedTickets.add(ticket);
	}
	
	public static SaleLineItem returnTicket(String id) {
		int soldRegisterIndex = 0;
		for (SaleLineItem i : payedTickets) {
			if (i.getTicketID() == id) {
				soldRegisterIndex = payedTickets.indexOf(i);
			}
		}
		return payedTickets.get(soldRegisterIndex);
	}
	
	
}


