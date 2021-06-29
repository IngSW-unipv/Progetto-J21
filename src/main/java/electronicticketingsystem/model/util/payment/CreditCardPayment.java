package electronicticketingsystem.model.util.payment;

import electronicticketingsystem.model.util.exceptions.PaymentNotCompletedException;

/**
 * Classe che descrive una procedura di pagamento con carta di credito. Questa classe implementa
 * l'interfaccia Payment.
 * Gli attributi di questa classe sono:
 * @param amount				valore double che indica il prezzo totale da pagare
 * @param creditCard 			oggetto della classe CreditCard che indica la carta di credito
 * 								da usare per la procedura di pagamento corrente
 * @param completed				valore booleano che indica se la procedura di pagamento è andata
 * 								a buon fine
 * 
 *
 */
public class CreditCardPayment implements Payment {

	private double amount;
	private CreditCard creditCard;
	private boolean completed;
	
	/**
	 * Costruttore della classe, che richiede in ingresso il totale da pagare e la carta di credito
	 * da usare e inizializza il valore dell'attributo completed a falso
	 * @param amountToPay		valore double che indica il prezzo totale da pagare
	 * @param cardToUse			oggetto della classe CreditCard che indica la carta di credito 
	 * 							da utilizzare per la procedura di pagamento			
	 */
	public CreditCardPayment(double amountToPay, CreditCard cardToUse) {
		this.amount = amountToPay;
		this.creditCard = cardToUse;
		this.completed = false;
	}
	
	/**
	 * Metodo che simula il pagamento con carta di credito. Il metodo richiede in ingresso il totale da pagare
	 * e simula il pagamento considerando che vada a buon fine in tutti i casi in cui la carta non è scaduta e 
	 * il numero di carta e il cvv sono della lunghezza corretta; se i dati immessi non sono validi, il pagamento
	 * non va a buon fine ed è lanciata un'eccezione
	 * @param amount (double)
	 * @throws PaymentNotCompletedException
	 */
	public void makePayment(double amount) throws PaymentNotCompletedException {
		if ((creditCard.checkExpiration())&&(creditCard.checkCardNumber())&&(creditCard.checkCVV())) {
			completed = true;
		} else {
			completed = false;
			throw new PaymentNotCompletedException("The credit card details are invalid");
		}
	}
	
	/**
	 * Metodo get che permette di risalire al totale da pagare
	 * @return amount (double)
	 */
	public double getAmount() {
		return amount;
	}
	
	/**
	 * Metodo che permette di sapere se il pagamento è andato a buon fine
	 * @return completed (boolean)
	 */
	public boolean isCompleted() {
		return completed;
	}

}
