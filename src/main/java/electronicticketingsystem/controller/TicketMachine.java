package electronicticketingsystem.controller;
import java.util.*;

import electronicticketingsystem.util.sale.Cash;
import electronicticketingsystem.util.sale.Sale;

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
	
	public void makePayment(double cs) {
		 s.makePayment(cs);
	}
	
	public Cash getChange() {
		return s.getChange();
		}
	
	public void endSale() {
		s.setCompleted();
	}

	
}
