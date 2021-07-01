package electronicticketingsystem.model.util.sale;

import java.util.*;

import electronicticketingsystem.model.util.exceptions.PaymentNotCompletedException;
import electronicticketingsystem.model.util.exceptions.TicketTypeNotExistingException;
import electronicticketingsystem.model.util.payment.CashPayment;
import electronicticketingsystem.model.util.payment.CreditCard;
import electronicticketingsystem.model.util.payment.CreditCardPayment;
import electronicticketingsystem.model.util.payment.Payment;


/**
 * Classe che descrive una Vendita, vista come una lista di singole componenti.
 * Gli attributi che descrivono questa classe sono:
 * @param completed				valore booleano che indica se la procedura di vendita è conclusa
 * @param items					è una lista di oggetti di tipo SaleLineItem, che compongono la vendita stesso
 * @param total					valore double che indica il prezzo totale di tutti gli articoli relativi alla vendita
 * 								considerata
 * @param p						oggetto di tipo Payment che rappresenta il pagamento
 * @param payedTickets			oggetto di tipo SoldRegister, che contiene gli elementi delle vendite completate, ovvero 
 * 								delle quali è stato ricevuto il pagamento
 * 
 */
public class Sale {
	private final int TYPEACCEPTED=3;
	private boolean completed;
	private List<SaleLineItem> items;
	private double total; 
	private Payment p;
	private SoldRegister payedTickets;
	
	/**
	 * Costruttore della classe, che si occupa di inizializzare gli attributi completed, items e total
	 */
	public Sale() {
		this.completed=false; //posizione aperta appena viene invocato
		this.items=new ArrayList<>();
		this.total=0;
	}
	
	/**
	 * Metodo per l'aggiunta dei singoli elementi di una vendita all'interno dell'array list items.
	 * @param type				valore int, indica il tipo di biglietto selezionato
	 * @param qty				valore int, indica la quantità di biglietti di quel tipo
	 * @throws TicketTypeNotExistingException 
	 */
	public void enterItem(int type,int qty) throws TicketTypeNotExistingException {
		if(type>TYPEACCEPTED) throw new TicketTypeNotExistingException("Ticket Type has not been accepted!");
		for(int i=0;i<qty;i++) {
			SaleLineItem sli=new SaleLineItem(type);
			items.add(sli);
			this.total+=sli.getSubTotal();
		}	 
	}
	
	/**
	 * Metodo per marcare come completata una vendita (ovvero quando è ricevuro il pagamento). Quando viene invocato, 
	 * il metodo imposta a true l'attributo completed, aggiunge al registro dei biglietti venduti gli elementi che compongono 
	 * la vendita e stampa una copia del registro per visualizzare i biglietti venduti.
	 * @throws PaymentNotCompletedException			se il pagamento non risulta completato viene segnalato all'utente, la
	 * 												procedura di vendita non si conclude e quindi gli articoli che la 
	 * 											    compongono non vengono aggiunti al SoldRegister
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
	 * Metodo per il calcolo del prezzo totale della vendita
	 * @return total (double)
	 */
	public double getTotal() {
		return this.total;
	}
	
	/**
	 * Metodo per effettuare il pagamento della vendita in contanti. Viene creato un oggetto di tipo CashPayment con il totale 
	 * dovuto e si invoca il metodo della classe che sottrae al denaro inserito cs il totale per calcolare il
	 * resto
	 * @param cs (double)
	 * @throws PaymentNotCompletedException 		gestione dell'eccezione di pagamento non completato
	 */
	public void makeCashPayment(double cs) {
		p=new CashPayment(total);
		try {
			p.makePayment(cs);
			//p sarà poi usato per salvare i relativi dati
		} catch (PaymentNotCompletedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo per effettuare il pagamento della vendita con carta di credito. Viene creato un oggetto di tipo CreditCardPayment
	 * con il totale dovuto e la carta di credito da usare per la vendita e si invoca il metodo della classe che simula il 
	 * pagamento
	 * @param cc (CreditCard)
	 *  @throws PaymentNotCompletedException 		gestione dell'eccezione di pagamento non completato
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
	 * @return Cash 			restituisce un oggetto di tipo Cash che corrisponda al resto da erogare
	 */
	public Cash getChange() {
		return ((CashPayment)p).getChange();
	}

	/**
	 * Metodo get per risalire alla lista di elementi che costituiscono la vendita
	 * @return items (List<SaleLineItem>)
	 */
	public List<SaleLineItem> getItems() {
		return items;
	}
}