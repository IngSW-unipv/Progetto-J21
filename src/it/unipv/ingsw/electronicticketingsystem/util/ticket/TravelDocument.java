package it.unipv.ingsw.electronicticketingsystem.util.ticket;

public abstract class TravelDocument {
	
	private int type;
	private String description;
	private double price;
	private String TicketID;
	private boolean validated;
	
	public TravelDocument(int type, String description, double price) {
		this.type = type;
		this.description = description;
		this.price = price;
		this.TicketID = generateTicketID();
		this.validated = false;
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
	
	public String getTicketID() {
		return TicketID;
	}
	
	public void validateTravelDocument() {
		this.validated = true;
	}
	
	public String generateTicketID() {
		String typeCode = Integer.toString(getType());
		String descriptionCode = getDescription().substring(0, 3).toUpperCase();
		String randomCode = Integer.toString((int)(Math.random()*10000));
		return typeCode + descriptionCode + randomCode;
	}
	
}
