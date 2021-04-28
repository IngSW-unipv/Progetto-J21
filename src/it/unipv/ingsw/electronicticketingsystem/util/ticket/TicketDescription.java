package it.unipv.ingsw.electronicticketingsystem.util.ticket;

public class TicketDescription {
	
	private int type;
	private TicketName name;
	private double price;
	
	public TicketDescription(int type, TicketName name, double price) {
		this.type = type;
		this.name = name;
		this.price = price;
		
	}

	public int getType() {
		return type;
	}
	
	public TicketName getName() {
		return name;
	}
	
	public double getPrice() {
		return price;
	}
	
	
}
