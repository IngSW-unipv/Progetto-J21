package electronicticketingsystem.model.util.sale;

/**
 * Classe che descrive il resto secondo la quantità di banconote e monete corrispondenti. 
 * Questa classe consente di simulare un pagamento in contanti.
 * Gli attributi di questa classe sono:
 * @param amount								valore double che rappresenta il prezzo da pagare
 * @param b50, b20, b10, b5 					il numero di banconote rispettivamente da 50, 20, 10 e 5 euro
 * @param m2, m1								il numero di monete rispettivamente da 2 e 1 euro
 * @param m050, m020, m010, m005, m002, m001	il numero di monete rispettivamente da 50, 20, 10, 5, 2 e 1 centesimo
 */
public class Cash {
	private double amount;
	private int b50, b20, b10, b5, m2, m1, m050, m020, m010, m005, m002, m001;
	
	/**
	 * Costruttore della classe, che richiede in ingresso l'ammontare di cui si vuole 
	 * calcolare la composizione in monete e banconote
	 * @param a (double)
	 */
	public Cash(double a) {
		this.amount=a;
		b50=(int)(amount / 50);     // numero di banconote da 50 euro
		double change = amount % 50;
		b20=(int)(change / 20);        // numero di banconote da 20 euro
		change=change % 20;
		b10=(int) (change / 10);       // numero di banconote da 10 euro
		change=change % 10;
	    b5=(int) (change / 5);         // numero di monete da 5 euro
		change=change % 5;
		m2=(int) (change / 2);         // numero di monete da 2 euro
		change=change % 2;
		m1=(int) (change / 1);		  // numero di monete da 1 euro
		change=change % 1;
		m050=(int)(change / 0.5);		  //numero di monete da 50 centesimi
		change=change % 0.5;
		m020=(int)(change / 0.2);		 //numero di monete da 20 centesimi
		change=change % 0.2;
		m010=(int)(change / 0.1);
		change=change % 0.1;
		m005=(int)(change / 0.05);
		change=change % 0.05;
		m002=(int)(change / 0.02);
		change=change % 0.02;
		m001=(int)change;
	}
	
	/**
	 * Costruttore alternativo della classe, che richiede in ingresso la quantità di 
	 * banconote e monete di ogni tipo e inizializza l'attributo amount come somma 
	 * pesata delle quantità fornite.
	 * @param b50 (int)
	 * @param b20 (int)
	 * @param b10 (int)
	 * @param b5 (int)
	 * @param m2 (int)
	 * @param m1 (int)
	 * @param m050 (int)
	 * @param m020 (int)
	 * @param m010 (int)
	 * @param m005 (int)
	 * @param m002 (int)
	 * @param m001 (int)
	 */
	public Cash(int b50, int b20, int b10, int b5, int m2, int m1, int m050, int m020, int m010, int m005, int m002, int m001) {
		this.b50=b50;
		this.b20=b20;
		this.b10=b10;
		this.b5=b5;
		this.m2=m2;
		this.m1=m1;
		this.m050=m050;
		this.m020=m020;
		this.m010=m010;
		this.m005=m005;
		this.m002=m002;
		this.m001=m001;
		this.amount=50*b50+20*b20+10*b10+5*b5+2*m2+m1+0.5*m050+0.2*m020+0.1*m010+0.05*m005+0.02*m002+0.01*m001;
	}
	
	/**
	 * Metodo get per ottenere l'ammontare del pagamento
	 * @return amount (double)
	 */
	public double getAmount() {
		return this.amount;
	}
	
	/**
	 * Metodo toString() che indica come convertire un oggetto della classe in stringa. Il metodo consentirà
	 * di stampare la quantità per ogni taglio di banconota o moneta.
	 */
	@Override
	public String toString() {
		return this.b50+" €50 bills\n"+
			   this.b20+" €20 bills\n"+
			   this.b10+" €10 bills\n"+
			   this.b5 +" €5 bills\n"+
			   this.m2 +" €2 coins\n"+
			   this.m1 +" €1 coins\n"+
			   this.m050+" €0.50 coins\n"+
			   this.m020+" €0.20 coins\n"+
			   this.m010+" €0.10 coins\n"+
			   this.m005+" €0.05 coins\n"+
			   this.m002+" €0.02 coins\n"+
			   this.m001+" €0.01 coins\n";
	}
}
