package electronicticketingsystem.util.ticket;

public abstract class TravelDocument {
	
	private int type;
	private String description;
	private double price;
	protected long timeToAdd; //uso di protected per non dover modificare il costruttore
	
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
	
	public long getTimeToAdd() {
		return timeToAdd;
	}
	
	
}
