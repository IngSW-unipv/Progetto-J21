package electronicticketingsystem.util.validation;

import java.time.LocalTime;

import electronicticketingsystem.util.sale.*;

public class Validation {
	private LocalTime expirationTime;
	private String id;

	
	public Validation(String id) {
		this.id=SoldRegister.returnTicket(id).getTicketID(); 
		setExpirationTime();
	}
	
	private void setExpirationTime() {
		this.expirationTime=LocalTime.now(); //Obtains the current time from the system clock in the default time-zone.
		SaleLineItem ticket=SoldRegister.returnTicket(id);
		expirationTime.plusHours(ticket.getTime()); //Returns a copy of this LocalTime with the specified number of hours added.
	}
	
	public LocalTime getExpirationTime() {
		return this.expirationTime;
	}
	
	public String getID() {
		return this.id;
	}
	
}
