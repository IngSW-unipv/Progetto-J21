package electronicticketingsystem.model.util.validation;

import java.util.ArrayList;
import java.util.List;

import electronicticketingsystem.model.util.ticket.TravelDocument;


/**
 * Classe creator di check
 * 
 * Gli attributi che descrivono questa classe sono:
 * @param idInspector			stringa che identifica l'opertore
 * @param ispections			lista di tutti i check effettuati 
 *  
 */

public class TicketInspector {
	private String idInspector;
	private List<Check> inspections;

	/**
	 * Costruttore della classe, che si occupa di inizializzare gli attributi idInspector e inspections
	 */
	public TicketInspector(String id) {
		this.idInspector=id;
		this.inspections=new ArrayList<>();
	}
	
	public void newIspection(String idTicket) {
		inspections.add(new Check(idTicket));
	}
	
	public String getIdInspector() {
		return this.idInspector;
	}
	
	public void printInspectionsList() {
		System.out.println(this.idInspector+":\n");
		for (Check c : inspections) {
			System.out.println(c + "/n");
		}
	}
}
