package it.unipv.ingsw.electronicticketingsystem.util.sale;

import java.util.ArrayList;
import java.util.List;

public class SaleLineItem {
	private double price;
	private String id;

	
	public SaleLineItem(double p) {
		this.price=p; 
		//un'alternativa può essere il coinvolgimento del catalogo specificando 
		//solo il tipo del biglietto in questo costruttore.
		//idealmente dovremmo avere un metodo che ci permette di avere il dato desiderato specificando solo il tipo
	}
	
	public double getSubTotal() {
		return price;
	}
	

}