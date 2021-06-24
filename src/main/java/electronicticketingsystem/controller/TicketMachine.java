package electronicticketingsystem.controller;
import java.util.*;

import electronicticketingsystem.model.util.sale.Cash;
import electronicticketingsystem.model.util.sale.Sale;
import electronicticketingsystem.model.util.validation.Validation;
import electronicticketingsystem.model.util.validation.ValidationRegister;

public class TicketMachine {
	Sale s;
	ValidationRegister vr;
	
	public Sale makeSale() {
		 s=new Sale();
		 return s;
	}
	
	public void enterItem(int type,int qty) {
		s.enterItem(type, qty);
	}
	
	public double getTotal() {
		return s.getTotal();
	}
	
	public void makePayment(double cs) {
		 s.makePayment(cs);
	}
	
	public Cash getChange() {
		return s.getChange();
		}
	
	public void endSale() {
		s.setCompleted();
	}
	
	public void validation(String ticketID) {
		vr = ValidationRegister.getInstance();
		vr.addToRegister(new Validation(ticketID));
		vr.printValidationRegister();
	}

	
}
