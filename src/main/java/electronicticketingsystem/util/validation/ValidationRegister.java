package electronicticketingsystem.util.validation;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class ValidationRegister {
	private static List<Validation> stampedTickets;
	
	public ValidationRegister() {
		ValidationRegister.stampedTickets=new ArrayList<>();
	}
	
	public void addToRegister(Validation v) {
		stampedTickets.add(v); //aggiungi alla lista tutte le convalide	
	}
	
	public static Validation findValidation(String id) {
		for(Validation i: stampedTickets) {
			if(i.getID().equals(id)) return i;
		} return null; //se non viene trovato alcun elemento corrispondente ritorna null

	}
	
}
