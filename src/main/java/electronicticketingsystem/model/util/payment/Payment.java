package electronicticketingsystem.model.util.payment;

import electronicticketingsystem.model.util.exceptions.InvalidAmountException;
import electronicticketingsystem.model.util.exceptions.PaymentNotCompletedException;

/**
 * Interfaccia che descrive i metodi che una classe deve implementare per essere considerata un
 * metodo di pagamento, realizzata secondo il pattern GRASP Polymorphism
 */
public interface Payment {
	
	/**
	 * Metodo che permette di simulare la procedura di pagamento
	 * @param amount							- richiede in ingresso il valore double che indica il totale da pagare
	 * @throws PaymentNotCompletedException		  nel caso in cui il pagamento non possa essere marcato come completato
	 * @throws InvalidAmountException 
	 */
	public void makePayment(double amount) throws PaymentNotCompletedException, InvalidAmountException;
	
	/**
	 * Metodo get per risalire al valore double che indica il totale da pagare
	 * @return amount 				- valore double che indica il totale da pagare
	 */
	public double getAmount();
	
	/**
	 * Metodo che permette di sapere se il pagamento è andato a buon fine
	 * @return true					- se il pagamento è andato a buon fine
	 */
	public boolean isCompleted();

}
