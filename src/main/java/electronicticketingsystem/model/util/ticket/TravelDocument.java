package electronicticketingsystem.model.util.ticket;

/** Classe astratta che descrive un generico Titolo di Viaggio 
 *  Gli attributi di questa classe sono:
 *  @param type				valore intero tra 1 e 3 che indica il tipo di titolo di viaggio
 *  @param description		stringa che descrive lo specifico titolo di viaggio (nome)
 *  @param price			valore double che indica il prezzo del titolo di viaggio
 *  @param timeToAdd		valore long che indica il numero di ore di validità del titolo di viaggio
 *  */

public abstract class TravelDocument {
	
	private int type;
	private String description;
	private double price;
	private long timeToAdd; 
	
	/**
	 * Costruttore della classe, che richiede in ingresso i seguenti argomenti
	 * @param type
	 * @param description
	 * @param price
	 * @param timeToAdd
	 */
	public TravelDocument(int type, String description, double price, long timeToAdd) {
		this.type = type;
		this.description = description;
		this.price = price;
		this.timeToAdd = timeToAdd;
	}

	/**
	 * Metodo get per risalire al tipo del titolo di viaggio
	 * @return type (int)
	 */
	public int getType() {
		return type;
	}

	/**
	 * Metodo get per risalire alla descrizione del titolo di viaggio
	 * @return description (String)
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Metodo get per risalire al prezzo del titolo di viaggio
	 * @return price (double)
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * Metodo get per risalire al numero di ore di validità del titolo di viaggio
	 * @return timeToAdd (long)
	 */
	public long getTimeToAdd() {
		return timeToAdd;
	}
	
	
}
