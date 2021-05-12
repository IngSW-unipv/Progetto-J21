package it.unipv.ingsw.electronicticketingsystem.util.ticket;

public abstract class TravelDocument {
	
	private int type;
	private String description;
	private double price;
	
	public TravelDocument(int type, String description, double price) {
		this.type = type;
		this.description = description;
		this.price = price;
	}

	public int getType() {
		return type;
	}

	public String getDescription() {
		return description;
	}

	public double getPrice() {
		return price;
	}
	
	
}
