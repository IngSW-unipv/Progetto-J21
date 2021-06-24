package electronicticketingsystem.model.util.validation;

import java.time.LocalTime;

public class Check {
	private boolean validity;
	private Validation ticketToControl;
	
	public Check(String id)  {
		this.ticketToControl=ValidationRegister.findValidation(id);
		if(ticketToControl.equals(null)) this.validity=false;
		else {
			LocalTime t=LocalTime.now();
			this.validity=ticketToControl.getExpirationTime().isAfter(t);
		}
	}
	
	public String toString() {
		if(this.validity==true) return "The travel document with ID "+ticketToControl.getID()+"is valid";
		else return "This travel document isn't valid!";
	}
}
