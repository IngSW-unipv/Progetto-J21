package electronicticketingsystem.controller;

import electronicticketingsystem.model.database.DataBase;
import electronicticketingsystem.model.util.sale.SaleLineItem;
import electronicticketingsystem.model.util.validation.Validation;

public class PersistenceFacade {

	private DataBase db;
	private static PersistenceFacade instance = null;
	
	public PersistenceFacade() {
		
		db = new DataBase();
		
	}
	
	public static PersistenceFacade getInstance() {
		if (instance == null) {
			instance = new PersistenceFacade();
		}
		return instance;
	}
	
	public boolean Inspection(String ticketID, String idInspector) {
		
		return(db.addInspection(ticketID, idInspector));
		
	}
	
	public void addTicket(SaleLineItem ticket) {
		
		db.registerItem(ticket);
		
	}

	public void addValidation(Validation v) {
		
		db.addValidation(v);
		
	}

	public boolean login(String inspectorId, String psw) {
		return db.Login(inspectorId, psw);
	}
	
}