package electronicticketingsystem.controller;
import java.util.*;

import electronicticketingsystem.model.util.exceptions.InvalidAmountException;
import electronicticketingsystem.model.util.exceptions.InvalidQuantityException;
import electronicticketingsystem.model.util.exceptions.PaymentNotCompletedException;
import electronicticketingsystem.model.util.exceptions.TicketNotFoundException;
import electronicticketingsystem.model.util.exceptions.TicketTypeNotExistingException;
import electronicticketingsystem.model.util.payment.CreditCard;
import electronicticketingsystem.model.util.sale.Cash;
import electronicticketingsystem.model.util.sale.Sale;
import electronicticketingsystem.model.util.validation.Validation;
import electronicticketingsystem.model.util.validation.ValidationRegister;

/**
 * Classe che descrive l'emettitrice di biglietti.
 * Questa classe è realizzata seguendo il design pattern GRASP Controller: la classe
 * rappresenta il controller del sistema, che riceve le richieste dell'utente 
 * limitandosi a delegarle alle classi che contengono i metodi per elaborarle; in questo 
 * modo, un'eventuale interfaccia grafica potrebbe comodamente essere sostituita all'attuale 
 * interfaccia testuale.
 * @param s					oggetto di tipo Sale che rappresenta la procedura di vendita corrente
 * @see Sale
 * @param vr				oggetto di tipo ValidationRegister che rappresenta il registro delle 
 * 							convalide del sistema
 * @see ValidationRegister
 *
 */
public class TicketMachine {
	Sale s;
	ValidationRegister vr;
	
	/**
	 * Metodo che crea una procedura di vendita e la restituisce per eventuali elaborazioni
	 * @return s (Sale)
	 */
	public Sale makeSale() {
		 s=new Sale();
		 return s;
	}
	
	/**
	 * Metodo per l'aggiunta dei singoli elementi di una vendita all'interno dell'array list items.
	 * Questo metodo si limita a richiamare il metodo enterItem della classe Sale.
	 * @param type			valore int, indica il tipo di biglietto selezionato
	 * @param qty			valore int, indica la quantità di biglietti di quel tipo
	 * @throws TicketTypeNotExistingException 
	 */
	public void enterItem(int type,int qty) throws TicketTypeNotExistingException, InvalidQuantityException {
		s.enterItem(type, qty);
	}
	
	/**
	 * Metodo get per ottenere il prezzo totale della vendita.
	 * Questo metodo si limita a richiamare il metodo getTotal della classe Sale.
	 * @return total (double)
	 */
	public double getTotal() throws InvalidAmountException {
		return s.getTotal();
	}
	
	/**
	 * Metodo per effettuare il pagamento in contanti della vendita. 
	 * Questo metodo si limita a richiamare il metodo makeCashPayment della classe Sale.
	 * @param cs (double)
	 */
	public void makeCashPayment(double cs) {
		s.makeCashPayment(cs);
	}
	
	/**
	 * Metodo per effettuare il pagamento con carta di credito della vendita. 
	 * Questo metodo si limita a richiamare il metodo makeCreditCardPayment della classe Sale.
	 * @param cc (CreditCard)	richiede in ingresso la carta di credito da usare 
	 */
	public void makeCreditCardPayment(CreditCard cc) {
		s.makeCreditCardPayment(cc); 
	}
	
	/**
	 * Metodo get per ottenere il resto di un pagamento in contanti.
	 * Questo metodo si limita a richiamare il metodo getChange della classe Sale (che
	 * a sua volta richiama il metodo analogo della classe Payment)
	 * @return Cash 
	 */
	public Cash getChange() {
		return s.getChange();
		}
 
	/**
	 * Metodo per terminare la procedura di vendita.
	 * Questo metodo si limita a richiamare il metodo endSale della classe Sale.
	 * @throws PaymentNotCompletedException
	 */
	public void endSale() throws PaymentNotCompletedException {
		s.setCompleted();
	}
	
	/**
	 * Metodo per convalidare un biglietto. Il metodo richiede in ingresso l'ID del biglietto
	 * da convalidare e crea una convalida ad esso associata da aggiungere al registro delle
	 * convalide, richiamando i metodi opportuni della classe ValidationRegister.
	 * Il metodo richiama anche il metodo di stampa della classe ValidationRegister per visualizzare
	 * a video una lista degli ID dei biglietti convalidati.
	 * @param ticketID (String)
	 */
	public void validation(String ticketID) throws TicketNotFoundException {
		vr = ValidationRegister.getInstance();
		vr.addToRegister(new Validation(ticketID));
		vr.printValidationRegister();
		
	}

	
}
