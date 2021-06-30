package electronicticketingsystem.model.util.validation;

import java.time.LocalTime;

/**
 * Classe che descive il processo di controllo della validità di un biglietto.
 * Gli attributi della classe sono:
 * @param validity				valore booleano che indica se il biglietto considerato è valido
 * @param ticketToControl		oggetto di tipo Validation che rappresenta la convalida di cui
 * 								effettuare il controllo
 *
 */
public class Check {
	private boolean validity;
	private Validation ticketToControl;
	
	/**
	 * Costruttore della classe, che accetta in ingresso l'id del biglietto di cui verificare la validità.
	 * Il costruttore assegna all'attrributo ticketToControl l'oggetto convalida che corrisponde all'id
	 * inserito e, nel caso in cui esista, imposta validity a true nel caso in cui l'orario di convalida
	 * dell'oggetto considerato sia successivo all'orario attuale del controllo.
	 * (validity è impostato a false se non esiste una convalida corrispondente all'id inserito o se 
	 * l'orario corrente di controllo è più grande dell'orario di scadenza del biglietto).
	 * @param id				stringa che indica l'id del biglietto da controllare
	 */
	public Check(String id)  {
		ValidationRegister vr=ValidationRegister.getInstance();
		this.ticketToControl=vr.findValidation(id);
		if(ticketToControl.equals(null)) this.validity=false;
		else {
			LocalTime t=LocalTime.now();
			this.validity=ticketToControl.getExpirationTime().isAfter(t);
		}
	}
	
	/**
	 * Metodo toString() che indica come convertire un oggetto della classe in stringa. Il metodo consentirà
	 * di stampare un opportuno messaggio a seconda dell'esito del controllo della convalida.
	 */
	@Override
	public String toString() {
		if(this.validity==true) return "The travel document with ID "+ticketToControl.getID()+"is valid";
		else return "This travel document isn't valid!";
	}
}
