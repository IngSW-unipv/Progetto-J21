package it.unipv.ingsw.electronicticketingsystem.util.sale;

import java.util.ArrayList;
import java.util.List;

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
	
	public void enterItem(double tipo) {
		//double tipo da cambiare, sostituire con 'TicketType t' 
		SaleLineItem s=new SaleLineItem(tipo); 
		items.add(s);
		this.quantity++; 
	}
	
	public void setCompleted() {
		this.completed=true;
	}
	
	public double getTotal() {
		double t=0;
		for(SaleLineItem i: items) {
			t=+i.getSubTotal();			
		}
		return t;
	}
	
	public void makePayment(double a) {
		Payment p=new Payment(a);
		//p sarà poi usato per salvare i relativi dati
	}
}