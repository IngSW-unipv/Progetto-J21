package electronicticketingsystem.model.util.sale;

/**
 * Classe che descrive una procedura di pagamento. 
 * Gli attributi di questa classe sono:
 * @param amount				valore double che indica il prezzo totale da pagare
 * @param change				oggetto della classe Cash usato per rappresentare il resto
 * 								simulando i vari tagli di banconote e monete
 * 
 *
 */
public class Payment {
	
	private double amount;
	private Cash change;
	
	/**
	 * Costruttore della classe, che richiede in ingresso il prezzo totale da pagare; il
	 * costruttore inizializza a 0 il resto e imposta l'attributo amount alla cifra inserita
	 * come parametro.
	 * @param amount (double)
	 */
	public Payment(double amount) {
		this.amount=amount;
		this.change=new Cash(0.0);
	}
	
	/**
	 * Costruttore alternativo della classe, che richiede in ingresso un oggetto della classe
	 * Cash che rappresenta la quantità di denaro inserita dall'utente.
	 * @param enteredMoney (Cash)
	 */
	public Payment(Cash enteredMoney) {
		this.amount=enteredMoney.getAmount(); 
		this.change=new Cash(0.0);
	}
	
	/**
	 * Metodo che simula il pagamento in contanti. Il metodo richiede in ingresso la quantità
	 * di denaro inserita dall'utente e calcola il resto creando un oggetto della classe Cash 
	 * che restituisca le varie monete e banconote di resto a fronte di un totale pari all'ammontare
	 * della spesa meno il denaro inserito.
	 * @param cash (double)
	 */
	public void makePayment(double cash) {
		change=new Cash(amount-cash); 
	}
	
	/**
	 * Metodo get che permette di ottenere il resto 
	 * @return change (Cash)
	 */
	public Cash getChange() {
		return change;
	}
	
	/**
	 * Metodo get che permette di ottenere il prezzo totale da pagare
	 * @return
	 */
	public double getAmount(){
		return amount;
	}
}
