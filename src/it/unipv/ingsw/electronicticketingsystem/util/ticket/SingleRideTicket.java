package it.unipv.ingsw.electronicticketingsystem.util.ticket;

public class SingleRideTicket extends TravelDocument {
	
	public SingleRideTicket() {
		super(1, "Single Ride Ticket", 1.40);
		this.timeToAdd=1;
	}
	

}
