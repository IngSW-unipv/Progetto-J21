package electronicticketingsystem.model.util.sale;

import java.util.*;



public class Sale {
	private boolean completed;
	private List<SaleLineItem> items;
	private double total; //meglio se viene recuperato da TicketDescription
	private Payment p;
	private SoldRegister payedTickets;
	
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
		System.out.println("Purchased travel documents:");
		payedTickets = SoldRegister.getInstance();
		this.completed=true;
		for (SaleLineItem i : items) {
			payedTickets.addToRegister(i);
			System.out.println("ID: " + i.getTicketID()+"\n");
		}
	}
	
	public double getTotal() {
		for(SaleLineItem i: items) {
			total+=i.getSubTotal();			
		}
		return total;
	}
	
	public void makePayment(double cs) {
		p=new Payment(total);
		p.makePayment(cs);
		//p sarà poi usato per salvare i relativi dati
	}
	
	public Cash getChange() {
		return p.getChange();
	}
}