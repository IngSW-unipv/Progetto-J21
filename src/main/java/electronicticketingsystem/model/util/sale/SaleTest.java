package electronicticketingsystem.model.util.sale;

import static org.junit.Assert.*;

import org.junit.Test;

import electronicticketingsystem.model.util.exceptions.InvalidAmountException;
import electronicticketingsystem.model.util.exceptions.InvalidQuantityException;
import electronicticketingsystem.model.util.exceptions.PaymentNotCompletedException;
import electronicticketingsystem.model.util.exceptions.TicketTypeNotExistingException;
import electronicticketingsystem.model.util.payment.CashPayment;
import electronicticketingsystem.model.util.ticket.SingleRideTicket;

/**
 * Classe di test tramite JUnit della classe Sale.  
 *
 */
public class SaleTest {

	Sale s=new Sale();
	
	@Test
	public void getTotalTest() throws TicketTypeNotExistingException, InvalidQuantityException, InvalidAmountException {
		s.enterItem(1, 1);
		SingleRideTicket ticket=new SingleRideTicket();
		assertTrue(s.getTotal()==ticket.getPrice());
	}
	
	@Test(expected=TicketTypeNotExistingException.class) 
	public void ticketTypeNotExistingTest() throws TicketTypeNotExistingException, InvalidQuantityException {
		s.enterItem(10, 10);
	}
	
	@Test(expected=InvalidQuantityException.class)
	public void quantityTest() throws TicketTypeNotExistingException, InvalidQuantityException {
		s.enterItem(1, -1);
	}
	
	@Test
	public void test() throws TicketTypeNotExistingException, InvalidQuantityException, PaymentNotCompletedException, InvalidAmountException {
		s.enterItem(1, 1);
		Cash c=new Cash(0, 0, 0, 0, 0, 1, 0, 2, 0, 0, 0, 0);
		s.makeCashPayment(c.getAmount());
		s.setCompleted();
	}
	
	

}
