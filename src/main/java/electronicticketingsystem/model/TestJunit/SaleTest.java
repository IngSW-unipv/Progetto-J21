package electronicticketingsystem.model.TestJunit;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import electronicticketingsystem.model.util.exceptions.InvalidAmountException;
import electronicticketingsystem.model.util.exceptions.InvalidQuantityException;
import electronicticketingsystem.model.util.exceptions.PaymentNotCompletedException;
import electronicticketingsystem.model.util.exceptions.TicketTypeNotExistingException;
import electronicticketingsystem.model.util.sale.Cash;
import electronicticketingsystem.model.util.sale.Sale;
import electronicticketingsystem.model.util.ticket.SingleRideTicket;

/**
 * Classe di test tramite JUnit della classe Sale. Questa classe definisce alcuni test da eseguire
 * per verificare il funzionamento dei metodi e delle funzionalità della classe Sale.
 *
 */
public class SaleTest {

	Sale s=new Sale();
	
	/**
	 * Test del metodo getTotal() della classe che permette di ottenere il totale della vendita. Si acquista
	 * un biglietto corsa singola e si verifica che il prezzo di questa vendita sia effettivamente uguale al 
	 * prezzo da catalogo (1.40).
	 * a 
	 * @throws TicketTypeNotExistingException
	 * @throws InvalidQuantityException
	 * @throws InvalidAmountException
	 */
	@Test
	public void getTotalTest() throws TicketTypeNotExistingException, InvalidQuantityException, InvalidAmountException {
		s.enterItem(1, 1);
		SingleRideTicket ticket=new SingleRideTicket();
		assertTrue(s.getTotal()==ticket.getPrice());
	}
	
	/**
	 * Test del metodo enterItem: si tenta di acquistare un titolo di viaggio di tipo non previsto dal sistema 
	 * e si verifica che sia sollevata l'opportuna eccezione.
	 * @throws TicketTypeNotExistingException
	 * @throws InvalidQuantityException
	 */
	@Test(expected=TicketTypeNotExistingException.class) 
	public void ticketTypeNotExistingTest() throws TicketTypeNotExistingException, InvalidQuantityException {
		s.enterItem(10, 10);
	}
	
	/**
	 * Test del metodo enterItem: si tenta di acquistare titoli di viaggio in quantità non valida e si 
	 * verifica che sia sollevata l'opportuna eccezione.
	 * @throws TicketTypeNotExistingException
	 * @throws InvalidQuantityException
	 */
	@Test(expected=InvalidQuantityException.class)
	public void quantityTest() throws TicketTypeNotExistingException, InvalidQuantityException {
		s.enterItem(1, -1);
	}
	
	/**
	 * Test più generale della classe Sale che segue una procedura di acquisto dall'inserimento di tipo e 
	 * quantità fino al pagamento in contanti
	 * @throws TicketTypeNotExistingException		se si richiede un tipo inesistente
	 * @throws InvalidQuantityException				se si richiede una quantità non valida
	 * @throws PaymentNotCompletedException			se il pagamento non può andare a buon fine (denaro
	 * 												inserito dall'utente non sufficiente)
	 * @throws InvalidAmountException				se il totale dovuto calcolato è non valido (minore
	 * 												o uguale a 0.0)
	 */
	@Test
	public void setCompletedTest() throws TicketTypeNotExistingException, InvalidQuantityException, PaymentNotCompletedException, InvalidAmountException {
		s.enterItem(1, 1);
		Cash c=new Cash(1.4);
		s.makeCashPayment(c.getAmount());
		s.setCompleted();
		assertTrue(s.getCompleted());
	}
	
	/**
	 * Test per verificare che venga sollevata l'eccezione PaymentNotCompletedException quando un pagamento 
	 * non va a buon fine
	 * 
	 * @throws InvalidQuantityException 			se si richiede una quantità non valida
	 * @throws TicketTypeNotExistingException 		se si richiede un tipo inesistente
	 * @throws InvalidAmountException 				se il totale dovuto calcolato è non valido (minore
	 * 												o uguale a 0.0)
	 * @throws PaymentNotCompletedException 		se il pagamento non può andare a buon fine (denaro
	 * 												inserito dall'utente non sufficiente)
	 */
	@Test(expected=PaymentNotCompletedException.class)
	public void paymentNotCompletedTest() throws TicketTypeNotExistingException, InvalidQuantityException, InvalidAmountException, PaymentNotCompletedException {
		s.enterItem(1, 1);
		s.makeCashPayment(0.0);
		s.setCompleted();
	}
	
	

}
