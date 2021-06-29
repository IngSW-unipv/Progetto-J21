package electronicticketingsystem.model.util.payment;

import java.time.YearMonth;

/**
 * Classe che descrive una Carta di Credito. Gli attributi della classe sono:
 * @param nameOnCard			stringa che indica il nome del proprietario della carta di credito
 * @param cardNumber			stringa che indica il numero della carta di credito
 * @param expirationDate		oggetto della classe YearMonth (Mese-Anno) che indica la data di scadenza
 * @param cvv					stringa che indica il Card Security Code
 *
 */
public class CreditCard {

	/**
	 * @param CARD_NUMBER_LENGTH		valore costante, il numero di carta di credito è sempre lungo 16 cifre
	 * @param CVV_LENGTH				valore costante, il cvv della carta di credito è sempre lungo 3 cifre
	 */
	public static final int CARD_NUMBER_LENGTH = 16;
	public static final int CVV_LENGTH = 3;
	
	private String nameOnCard;
	private String cardNumber;
	private YearMonth expirationDate;
	private String cvv;
	
	/**
	 * Costruttore della classe che richiede in ingresso il valore dei quattro attributi della classe
	 * @param name
	 * @param number
	 * @param exp
	 * @param cvv
	 */
	public CreditCard(String name, String number, YearMonth exp, String cvv) {
		this.nameOnCard = name;
		this.cardNumber = number;
		this.expirationDate = exp;
		this.cvv = cvv;
	}
	
	/**
	 * Costruttore alternativo della classe usato per la simulazione del pagamento con carta di credito
	 * tramite console. Questo costruttore non richiede il nome sulla carta.
	 * @param number
	 * @param exp
	 * @param cvv
	 */
	public CreditCard(String number, YearMonth exp, String cvv) {
		this.cardNumber = number;
		this.expirationDate = exp;
		this.cvv = cvv;
	}
	
	/**
	 * Metodo che controlla la validità della carta di credito: una carta di credito è valida (valore booleano
	 * true) se non risulta scaduta.
	 * @return boolean
	 */
	public boolean checkExpiration() {
		YearMonth currentDate = YearMonth.now();
		if (currentDate.isAfter(expirationDate)) {
			return false;
		} else 
			return true;
	}
	
	/**
	 * Metodo che controlla la validità del numero di carta di credito inserito: se la sua lunghezza è minore di
	 * 16, il numero di carta non è valido (valore booleano false)
	 * @return boolean
	 */
	public boolean checkCardNumber() {
		if (cardNumber.length() != CARD_NUMBER_LENGTH) {
			return false;
		} else 
			return true;
	}
	
	/**
	 * Metodo che controlla la validità del cvv della carta di credito inserito: se la sua lunghezza è minore di
	 * 3, il cvv non è valido (valore booleano false)
	 * @return boolean
	 */
	public boolean checkCVV() {
		if (cvv.length() != CVV_LENGTH) {
			return false;
		} else 
			return true;
	}

}
