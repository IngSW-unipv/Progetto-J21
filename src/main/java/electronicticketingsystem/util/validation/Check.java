package electronicticketingsystem.util.validation;

import java.time.LocalTime;

public class Check {
	private boolean validity;
	private Validation ticketToControl;
	
	public Check(String id) {
		this.ticketToControl=ValidationRegister.findValidation(id);
		if(ticketToControl.equals(null)) this.validity=false;
		else {
			LocalTime t=LocalTime.now();
			this.validity=ticketToControl.getExpirationTime().isAfter(t);
		}
	}
	
	public String toString() {
		if(this.validity==true) return "Il biglietto con codice ID "+ticketToControl.getID()+"è valido";
		else return "Il biglietto non è valido!";
	}
}
