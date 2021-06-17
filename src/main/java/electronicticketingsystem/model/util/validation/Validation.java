package electronicticketingsystem.model.util.validation;

import java.time.LocalTime;

import electronicticketingsystem.model.util.sale.SaleLineItem;
import electronicticketingsystem.model.util.sale.SoldRegister;
import electronicticketingsystem.model.util.sale.*;

public class Validation {
	private LocalTime expirationTime;
	private String id;

	
	public Validation(String id) {
		this.id=SoldRegister.returnTicket(id).getTicketID(); 
		setExpirationTime();
		SoldRegister.returnTicket(id).SetOneAccessLess(); //quando viene effettuata una convalida si abbassa di uno il contatore delle corse
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
