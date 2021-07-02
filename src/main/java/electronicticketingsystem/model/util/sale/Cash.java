package electronicticketingsystem.model.util.sale;

import electronicticketingsystem.model.util.exceptions.InvalidAmountException;

/**
 * Classe che descrive una certa quantità di denaro secondo la quantità di banconote e monete corrispondenti. 
 * Questa classe è usata per calcolare il resto e consente di simulare un pagamento in contanti.
 * @param amount		- valore double che rappresenta l'ammontare da dividere in banconote e monete
 * @param b50			- il numero di banconote da 50 euro
 * @param b20		    - il numero di banconote da 20 euro
 * @param b10			- il numero di banconote da 10 euro
 * @param b5			- il numero di banconote da 5 euro
 * @param m2			- il numero di monete da 2 euro
 * @param m1     		- il numero di monete da 1 euro
 * @param m050			- il numero di monete da 50 centesimi
 * @param m020			- il numero di monete da 20 centesimi
 * @param m010			- il numero di monete da 10 centesimi
 * @param m005			- il numero di monete da 5 centesimi
 * @param m002			- il numero di monete da 2 centesimi
 * @param m001			- il numero di monete da 1 centesimo
 */
public class Cash {
	private double amount;
	private int b50, b20, b10, b5, m2, m1, m050, m020, m010, m005, m002, m001;
	
	/**
	 * Costruttore della classe, che richiede in ingresso l'ammontare di cui si vuole 
	 * calcolare la composizione in monete e banconote
	 * @param a (double)	- ammontare di cui calcolare la composizione
	 * @throws InvalidAmountException 	se l'ammontare di cui calcolare la composizione è invalido (minore di 0)
	 */
	public Cash(double a) throws InvalidAmountException {
		if(a<0) throw new InvalidAmountException();
		this.amount=a;
		b50=(int)(amount / 50);     
		double change = amount % 50;
		b20=(int)(change / 20);        
		change=change % 20;
		b10=(int) (change / 10);       
		change=change % 10;
	    b5=(int) (change / 5);        
		change=change % 5;
		m2=(int) (change / 2);         
		change=change % 2;
		m1=(int) (change / 1);		  
		change=change % 1;
		m050=(int)(change / 0.5);		  
		change=change % 0.5;
		m020=(int)(change / 0.2);		 
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
	 * @param b50			- il numero di banconote da 50 euro
	 * @param b20		    - il numero di banconote da 20 euro
	 * @param b10			- il numero di banconote da 10 euro
	 * @param b5			- il numero di banconote da 5 euro
	 * @param m2			- il numero di monete da 2 euro
	 * @param m1     		- il numero di monete da 1 euro
	 * @param m050			- il numero di monete da 50 centesimi
	 * @param m020			- il numero di monete da 20 centesimi
	 * @param m010			- il numero di monete da 10 centesimi
	 * @param m005			- il numero di monete da 5 centesimi
	 * @param m002			- il numero di monete da 2 centesimi
	 * @param m001			- il numero di monete da 1 centesimo
	 * @throws InvalidAmountException	se almeno una delle classi di banconote o monete ha una quantità negativa
	 */
	public Cash(int b50, int b20, int b10, int b5, int m2, int m1, int m050, int m020, int m010, int m005, int m002, int m001) throws InvalidAmountException {
		if(b50<0 || b20<0 || b10<0 || b5<0 || m2<0 || m1<0 || m050<0 || m020<0 || m010<0 || m005<0 || m002<0 || m001<0) throw new InvalidAmountException();
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
	 * @return amount		- valore double che indica la quantità di denaro
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
