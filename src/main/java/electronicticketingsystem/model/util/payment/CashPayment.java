package electronicticketingsystem.model.util.payment;

import electronicticketingsystem.model.util.exceptions.InvalidAmountException;
import electronicticketingsystem.model.util.exceptions.PaymentNotCompletedException;
import electronicticketingsystem.model.util.sale.Cash;

/**
 * Classe che descrive una procedura di pagamento in contanti. Questa classe implementa
 * l'interfaccia Payment.
 * @param amount			    - valore double che indica il prezzo totale da pagare
 * @param change				- oggetto della classe Cash usato per rappresentare il resto
 * 								  simulando i vari tagli di banconote e monete
 * @param completed 			- valore booleano che indica se la vendita è andata a buon fine
 *
 */
public class CashPayment implements Payment{
	
	private double amount;
	private Cash change;
	boolean completed;
	
	/**
	 * Costruttore della classe, che richiede in ingresso il prezzo totale da pagare; il
	 * costruttore inizializza a 0 il resto, imposta l'attributo amount alla cifra inserita
	 * come parametro e inizializza completed a false.
	 * @param amount		- valore double che indica il totale da pagare
	 * @throws InvalidAmountException 
	 */
	public CashPayment(double amount) throws InvalidAmountException {
		this.amount=amount;
		this.change=new Cash(0.0);
		this.completed = false;
	}
	
	/**
	 * Costruttore alternativo della classe, che richiede in ingresso un oggetto della classe
	 * Cash che rappresenta la quantità di denaro inserita dall'utente.
	 * @param enteredMoney 	- oggetto della classe Cash che indica il denaro inserito dall'utente
	 * @throws InvalidAmountException 
	 */
	public CashPayment(Cash enteredMoney) throws InvalidAmountException {
		this.amount=enteredMoney.getAmount(); 
		this.change=new Cash(0.0);
	}
	
	/**
	 * Metodo che simula il pagamento in contanti. Il metodo richiede in ingresso la quantità
	 * di denaro inserita dall'utente e calcola il resto creando un oggetto della classe Cash 
	 * che restituisca le varie monete e banconote di resto a fronte di un totale pari al denaro
	 * inserito meno l'ammontare della spesa. Il metodo contrassegna quindi il pagamento come completato
	 * se il denaro inserito è almeno pari al totale da pagare, altrimenti il pagamento non va a 
	 * buon fine ed è sollevata un'eccezione.
	 * @param cash 			- valore double che indica il denaro inserito dall'utente
	 * @throws PaymentNotCompletedException		se il denaro inserito dall'utente non è sufficiente
	 * @throws InvalidAmountException 
	 */
	public void makePayment(double enteredMoney) throws PaymentNotCompletedException{
		if (enteredMoney >= amount) {
		try {
			change=new Cash(enteredMoney-amount);
		} catch (InvalidAmountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		completed = true;
		} else 
			throw new PaymentNotCompletedException("The cash entered is not enough");
	}
	
	/**
	 * Metodo get che permette di ottenere il resto 
	 * @return change 		- oggetto della classe Cash che descrive il resto
	 */
	public Cash getChange() {
		return change;
	}
	
	/**
	 * Metodo get che permette di ottenere il prezzo totale da pagare
	 * @return amount 		- valore double che indica il totale da pagare
	 */
	public double getAmount(){
		return amount;
	}
	
	/**
	 * Metodo che permette di sapere se il pagamento è andato a buon fine
	 * @return true			- se il pagamento è andato a buon fine
	 */
	public boolean isCompleted() {
		return completed;
	}
}