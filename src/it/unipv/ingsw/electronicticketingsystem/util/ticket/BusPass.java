package it.unipv.ingsw.electronicticketingsystem.util.ticket;

public class BusPass extends TravelDocument {

	public BusPass() {
		super(3, "Bus Pass", 12.00);
		this.timeToAdd=24; //buss pass come biglietto giornaliero
	}
}
