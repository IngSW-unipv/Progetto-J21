package it.unipv.ingsw.electronicticketingsystem.util.sale;

import java.util.ArrayList;
import java.util.List;

import it.unipv.ingsw.electronicticketingsystem.util.ticket.TicketType;

public class Sale {
	private boolean completed;
	private List<SaleLineItem> items;
	private int quantity; // provvisorio, da togliere
	private double total; //meglio se viene recuperato da TicketDescription 
	
	public Sale() {
		this.completed=false; //posizione aperta appena viene invocato
		this.items=new ArrayList<>();
		//this.quantity=0;
	}
	
	public void enterItem(TicketType type) {
		SaleLineItem s=new SaleLineItem(type); 
		items.add(s);
		this.quantity++; 
	}
	
	public void setCompleted() {
		this.completed=true;
		// inoltrare tutto a base dati...
	}
	
	public double getTotal() {
		double total=0;
		for(SaleLineItem i: items) {
			total=+i.getSubTotal();			
		}
		return total;
	}
	
	public void makePayment(double a) {
		Payment p=new Payment(a);
		//p sarà poi usato per salvare i relativi dati
	}
}