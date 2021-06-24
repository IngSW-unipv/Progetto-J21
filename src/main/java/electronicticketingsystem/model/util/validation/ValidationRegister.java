package electronicticketingsystem.model.util.validation;

import java.util.ArrayList;
import java.util.List;

public class ValidationRegister {
	private static List<Validation> stampedTickets;
	private static ValidationRegister instance = null;
	
	public ValidationRegister() {
		ValidationRegister.stampedTickets=new ArrayList<>();
	}
	
	public static ValidationRegister getInstance() {
		if (instance == null) {
			instance = new ValidationRegister();
		}
		return instance;
	}
	
	public void addToRegister(Validation v) {
		stampedTickets.add(v); //aggiungi alla lista tutte le convalide	
	}
	
	public static Validation findValidation(String id) {
		for(Validation i: stampedTickets) {
			if(i.getID().equals(id)) return i;
		} return null; //se non viene trovato alcun elemento corrispondente ritorna null

	}
	
	public void printValidationRegister() {
		System.out.println("Validated tickets' register:");
		for (Validation i : stampedTickets) {
			System.out.println("ID: "+i.getID() + " Expiration Time: " + i.getExpirationTime());
		}
	}
	
}
