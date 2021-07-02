package electronicticketingsystem.model.util.sale;

import java.util.*;

import electronicticketingsystem.model.util.exceptions.InvalidAmountException;
import electronicticketingsystem.model.util.exceptions.InvalidQuantityException;
import electronicticketingsystem.model.util.exceptions.PaymentNotCompletedException;
import electronicticketingsystem.model.util.exceptions.TicketTypeNotExistingException;
import electronicticketingsystem.model.util.payment.CashPayment;
import electronicticketingsystem.model.util.payment.CreditCard;
import electronicticketingsystem.model.util.payment.CreditCardPayment;
import electronicticketingsystem.model.util.payment.Payment;


/**
 * Classe che descrive una Vendita, vista come una lista di singole componenti.
 * @param TYPE_ACCEPTED			- valore intero costante che indica i tipi di biglietto possibili
 * @param completed				- valore booleano che indica se la procedura di vendita è conclusa
 * @param items					- è una lista di oggetti di tipo SaleLineItem, che compongono la vendita stessa
 * @param total					- valore double che indica il prezzo totale di tutti gli articoli relativi alla vendita
 * 								  considerata
 * @param p						- oggetto di tipo Payment che rappresenta il pagamento
 * @param payedTickets			- oggetto di tipo SoldRegister (unica istanza), che contiene gli elementi delle vendite
 * 							      completate, ovvero delle quali è stato ricevuto il pagamento
 * 
 */
public class Sale {
	private final int TYPE_ACCEPTED=3;
	private boolean completed;
	private List<SaleLineItem> items;
	private double total; 
	private Payment p;
	private SoldRegister payedTickets;
	
	/**
	 * Costruttore della classe, che si occupa di inizializzare gli attributi completed, items e total
	 */
	public Sale() {
		this.completed=false;
		this.items=new ArrayList<>();
		this.total=0;
	}
	
	/**
	 * Metodo per l'aggiunta dei singoli elementi di una vendita all'interno dell'array list items e per
	 * l'aggiornamento del totale da pagare.
	 * @param type				- valore int, indica il tipo di biglietto selezionato
	 * @param qty				- valore int, indica la quantità di biglietti di quel tipo
	 * @throws TicketTypeNotExistingException	se il tipo di biglietto scelto non è un valore previsto dal sistema
	 * 											(maggiore di 3)
	 * @throws InvalidQuantityException 		se la quantità scelta non è valida (minore o uguale a 0)
	 */
	public void enterItem(int type,int qty) throws TicketTypeNotExistingException, InvalidQuantityException {
		if(type>TYPE_ACCEPTED || type<=0) {
			throw new TicketTypeNotExistingException();  
		} else if (qty <= 0) {
			throw new InvalidQuantityException();
		} else {
			for(int i=0;i<qty;i++) {
				SaleLineItem ticket=new SaleLineItem(type);
				items.add(ticket);
				this.total+=ticket.getSubTotal();
			}	 
		}
	}
	
	/**
	 * Metodo per marcare come completata una vendita (ovvero quando è ricevuto il pagamento). Quando viene invocato, 
	 * il metodo imposta a true l'attributo completed, aggiunge al registro dei biglietti venduti gli elementi che compongono 
	 * la vendita e stampa una copia del registro per visualizzare i biglietti venduti; se invece il pagamento non è andato
	 * a buon fine, la vendita non viene completata e viene sollevata un'eccezione
	 * @throws PaymentNotCompletedException			se il pagamento non va a buon fine
	 */
	public void setCompleted() throws PaymentNotCompletedException {
		if (p.isCompleted()) {
			System.out.println("Purchased travel documents:");
			payedTickets = SoldRegister.getInstance();
			this.completed=true;
			for (SaleLineItem i : items) {
				payedTickets.addToRegister(i);
				System.out.println("ID: " + i.getTicketID()+"\n");
			}
		} else 
			throw new PaymentNotCompletedException("Payment has not been completed successfully");
	}
	
	/**
	 * Metodo get per ottenere il prezzo totale della vendita
	 * @return total (double)		  - totale da pagare
	 * @throws InvalidAmountException	se il totale risulta non valido (minore o uguale a 0.0)
	 */
	public double getTotal() throws InvalidAmountException {
		if (this.total > 0.0) {
			return this.total;
		} else
			throw new InvalidAmountException();
	}
	
	/**
	 * Metodo per effettuare il pagamento della vendita in contanti. Viene creato un oggetto di tipo CashPayment con il totale 
	 * dovuto e si invoca il metodo della classe che sottrae al denaro inserito enteredMoney il totale per calcolare il
	 * resto
	 * @param enteredMoney (double)				  - denaro inserito dall'utente
	 * @throws InvalidAmountException 
	 * @throws PaymentNotCompletedException 		se il pagamento in contanti non è andato a buon fine (se il denaro inserito
	 * 												è insufficiente)
	 */
	public void makeCashPayment(double enteredMoney) throws InvalidAmountException {
		p=new CashPayment(total);
		try {
			p.makePayment(enteredMoney);
		} catch (PaymentNotCompletedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo per effettuare il pagamento della vendita con carta di credito. Viene creato un oggetto di tipo CreditCardPayment
	 * con il totale dovuto e la carta di credito da usare per la vendita e si invoca il metodo della classe che simula il 
	 * pagamento
	 * @param cc (CreditCard)					  - carta di credito da usare per il pagamento
	 * @throws PaymentNotCompletedException 		se il pagamento con carta di credito non può andare a buon fine (se i dati
	 * 											    della carta di credito non sono validi)
	 */
	public void makeCreditCardPayment(CreditCard cc) {
		p=new CreditCardPayment(total,cc);
		try {
			p.makePayment(total);
		} catch (PaymentNotCompletedException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Metodo get per ottenere il resto nel caso di pagamento in contanti.
	 * @return Cash 			- restituisce un oggetto di tipo Cash che corrisponda al resto da erogare
	 */
	public Cash getChange() {
		return ((CashPayment)p).getChange();
	}

}