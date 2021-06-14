package electronicticketingsystem.util.sale;

public class Cash {
	private double importo;
	private int b50, b20, b10, b5, m2, m1, m050, m020, m010, m005, m002, m001;
	
	public Cash(double imp) {
		this.importo=imp;
		b50=(int)(imp/ 50);     // numero di banconote da 50 euro
		double resto=importo % 50;
		b20=(int)(resto / 20);        // numero di banconote da 20 euro
		resto=resto % 20;
		b10=(int) (resto / 10);       // numero di banconote da 10 euro
		resto=resto % 10;
	    b5=(int) (resto / 5);         // numero di monete da 5 euro
		resto=resto % 5;
		m2=(int) (resto / 2);         // numero di monete da 2 euro
		m1=(int) (resto % 2);		  // numero di monete da 1 euro
		resto-=m1;
		m050=(int)(resto/0.5);		  //numero di monete da 50 centesimi
		resto-=m050*0.5;
		m020=(int)(resto/0.2);		 //numero di monete da 20 centesimi
		resto-=m020*0.2;
		m010=(int)(resto/0.1);
		resto-=m010*0.1;
		m005=(int)(resto/0.05);
		resto-=m005*0.05;
		m002=(int)(resto/0.02);
		resto-=m002*0.02;
		m001=(int)(resto/0.01);
	}
	
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
		this.importo=50*b50+20*b20+10*b10+5*b5+2*m2+m1+0.5*m050+0.2*m020+0.1*m010+0.05*m005+0.02*m002+0.01*m001;
	}
	
	public double getImporto() {
		return this.importo;
	}
	
	public String toString() {
		return "Banconote: "+this.b50+" "+this.b20+" "+this.b10+" "+this.b5+"\nMonete: "+this.m2+" "+this.m1+" "+this.m050+" "+this.m020+" "+this.m010+" "+this.m005+" "+this.m002+" "+this.m001+"\n";
	}
}
