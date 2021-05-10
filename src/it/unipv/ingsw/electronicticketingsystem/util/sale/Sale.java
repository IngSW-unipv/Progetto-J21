package it.unipv.ingsw.electronicticketingsystem.util.sale;

import java.util.*;



public class Sale {
	private boolean completed;
	private List<SaleLineItem> items;
	private double total; //meglio se viene recuperato da TicketDescription 
	
	public Sale() {
		this.completed=false; //posizione aperta appena viene invocato
		this.items=new ArrayList<>();
		this.total=0;
	}
	
	public void enterItem(int type,int qty) {
		for(int i=0;i<qty;i++) {
			items.add(new SaleLineItem(type));
		}	 
	}
	
	public void setCompleted() {
		this.completed=true;
		// inoltrare tutto a base dati...
	}
	
	public double getTotal() {
		for(SaleLineItem i: items) {
			total+=i.getSubTotal();			
		}
		return total;
	}
	
	public void makePayment(double a) {
		Payment p=new Payment(a);
		//p sar� poi usato per salvare i relativi dati
	}
}