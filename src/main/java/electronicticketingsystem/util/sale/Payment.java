package electronicticketingsystem.util.sale;

public class Payment {
	
	private double amount;
	private Cash change;
	
	public Payment(double amount) {
		this.amount=amount;
		this.change=new Cash(0.0);
	}
	
	public Payment(Cash enteredMoney) {
		this.amount=enteredMoney.getImporto(); 
		this.change=new Cash(0.0);
	}
	
	public void makePayment(double cash) {
		change=new Cash(amount-cash); 
	}
	
	public Cash getChange() {
		return change;
	}
	
	public double getAmount(){
		return amount;
	}
}
