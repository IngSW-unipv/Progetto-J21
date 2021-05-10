package it.unipv.ingsw.electronicticketingsystem.controller;
import java.util.*;

import it.unipv.ingsw.electronicticketingsystem.util.sale.Sale;

public class TicketMachine {
	Sale s;
	
	public void makeSale() {
		 s=new Sale();
	}
	
	public void enterItem(int type,int qty) {
		s.enterItem(type, qty);
	}
	
	public double getTotal() {
		return s.getTotal();
	}
	
	public void makePayment(double am) {
		s.makePayment(am);
	}
	
	public void endSale() {
		s.setCompleted();
	}

	
}
