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

}
