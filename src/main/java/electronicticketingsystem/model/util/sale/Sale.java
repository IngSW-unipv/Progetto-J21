package electronicticketingsystem.model.util.sale;

import java.util.*;


/**
 * Classe che descrive una Vendita, vista come una lista di singole componenti.
 * Gli attributi che descrivono questa classe sono:
 * @param completed				valore booleano che indica se la procedura di vendita � conclusa
 * @param items					� una lista di oggetti di tipo SaleLineItem, che compongono la vendita stesso
 * @param total					valore double che indica il prezzo totale di tutti gli articoli relativi alla vendita
 * 								considerata
 * @param p						oggetto di tipo Payment che rappresenta il pagamento
 * @param payedTickets			oggetto di tipo SoldRegister, che contiene gli elementi delle vendite completate, ovvero 
 * 								delle quali � stato ricevuto il pagamento
 * 
 */
public class Sale {
	private boolean completed;
	private List<SaleLineItem> items;
	private double total; //meglio se viene recuperato da TicketDescription
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
	 * @param qty				valore int, indica la quantit� di biglietti di quel tipo
	 */
	public void enterItem(int type,int qty) {
		for(int i=0;i<qty;i++) {
			items.add(new SaleLineItem(type));
		}	 
	}
	
	/**
	 * Metodo per marcare come completata una vendita. Quando viene invocato, il metodo imposta a true
	 * l'attributo completed, aggiunge al registro dei biglietti venduti gli elementi che compongono 
	 * la vendita e stampa una copia del registro per visualizzare i biglietti venduti.
	 */
	public void setCompleted() {
		System.out.println("Purchased travel documents:");
		payedTickets = SoldRegister.getInstance();
		this.completed=true;
		for (SaleLineItem i : items) {
			payedTickets.addToRegister(i);
			System.out.println("ID: " + i.getTicketID()+"\n");
		}
	}
	
	/**
	 * Metodo per il calcolo del prezzo totale della vendita
	 * @return total (double)
	 */
	public double getTotal() {
		for(SaleLineItem i: items) {
			total+=i.getSubTotal();			
		}
		return total;
	}
	
	/**
	 * Metodo per effettuare il pagamento della vendita. Viene creato un oggetto di tipo Payment con il totale 
	 * dovuto e si invoca il metodo della classe che sottrae al denaro inserito cs il totale per calcolare il
	 * resto
	 * @param cs (double)
	 */
	public void makePayment(double cs) {
		p=new Payment(total);
		p.makePayment(cs);
		//p sar� poi usato per salvare i relativi dati
	}
	
	/**
	 * Metodo get per ottenere il resto
	 * @return Cash 			restituisce un oggetto di tipo Cash che corrisponda al resto da erogare
	 */
	public Cash getChange() {
		return p.getChange();
	}

	/**
	 * Metodo get per risalire alla lista di elementi che costituiscono la vendita
	 * @return items (List<SaleLineItem>)
	 */
	public List<SaleLineItem> getItems() {
		return items;
	}
}