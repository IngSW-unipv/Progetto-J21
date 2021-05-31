package it.unipv.ingsw.electronicticketingsystem.util.ticket;

public class Carnet extends TravelDocument {
	
	public static final int MAX_ACCESSES_NUMBER = 10;
	private int  accessesNumber;
	
	public Carnet() {
		super(2, "Carnet of Single Ride Tickets", 35.50);
		this.accessesNumber = MAX_ACCESSES_NUMBER;
		this.timeToAdd=1;
	}

	public int getAccessesNumber() {
		return accessesNumber;
	}

}
