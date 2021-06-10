package electronicticketingsystem.util.validation;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class ValidationRegister {
	private static List<Validation> obliteratedTickets;
	
	public ValidationRegister() {
		ValidationRegister.obliteratedTickets=new ArrayList<>();
	}
	
	public void addToRegister(Validation v) {
		obliteratedTickets.add(v); //aggiungi alla lista tutte le convalide	
		//quando da controllore si vuole effettuare una convalida si genera una nuova Validation 'v' da aggiungere al registro delle convalide
	}
	
	public boolean check(String id) {
		//classe usata dal controllore per verificare che il tempo di scadenza della corsa sia dopo il tempo di verifica
		LocalTime t=LocalTime.now();
		for(Validation i: obliteratedTickets) {
			if(i.getID().equals(id)) return i.getExpirationTime().isAfter(t);
		} return false;
	}
	

}
