package it.unipv.ingsw.electronicticketingsystem.util.validation;

import java.time.LocalTime;

import it.unipv.ingsw.electronicticketingsystem.util.sale.*;

public class Validation {
	private SaleLineItem ticket;
	private LocalTime expirationTime;
	
	public Validation(String id) {
		this.ticket=SoldRegister.findByTicketId(id);
		setExpirationTime();
	}
	
	private void setExpirationTime() {
		this.expirationTime=LocalTime.now(); //Obtains the current time from the system clock in the default time-zone.
		expirationTime.plusHours(ticket.getTime()); //Returns a copy of this LocalTime with the specified number of hours added.
	}
}
