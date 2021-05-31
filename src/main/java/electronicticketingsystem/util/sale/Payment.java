package electronicticketingsystem.util.sale;

public class Payment {
	
	private double amount,change;
	
	public Payment(double amount) {
		this.amount=amount;
		this.change=0.0;
	}
	
	public void makePayment(double cash) {
		change=cash-this.amount;
	}
	
	public double getChange() {
		return change;
	}
	
	public double getAmount(){
		return amount;
	}
}
