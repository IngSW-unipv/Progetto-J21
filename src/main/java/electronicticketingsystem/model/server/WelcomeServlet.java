package electronicticketingsystem.model.server;

import java.io.*;
import java.time.YearMonth;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rythmengine.Rythm;

import electronicticketingsystem.model.util.exceptions.PaymentNotCompletedException;
import electronicticketingsystem.model.util.exceptions.TicketNotFoundException;
import electronicticketingsystem.model.util.payment.*;
import electronicticketingsystem.model.util.sale.SaleLineItem;
import electronicticketingsystem.model.util.sale.SoldRegister;
import electronicticketingsystem.model.util.ticket.*;
import electronicticketingsystem.model.util.validation.Validation;
import electronicticketingsystem.model.util.validation.ValidationRegister;

/**
 * Classe che descrive la servlet per la gestione della web application. Questa classe estende la superclasse
 * HttpServlet, della quale fa l'override dei metodi principali doGet() e doPost()
 * @param catalog		- oggetto della classe TicketCatalog
 * @param items			- array list di SaleLineItem che rappresenta l'insieme di articoli relativi a una 
 * 						  procedura di acquisto
 * @param sr			- istanza (unica) della classe SoldRegister che raccoglie tutti i biglietti venduti dal
 * 						  sistema
 * @param v				- oggetto della classe Validation che rappresenta una convalida
 * 
 */
@SuppressWarnings("serial")
public class WelcomeServlet extends HttpServlet{
	
	TicketCatalog catalog = new TicketCatalog();
	ArrayList<SaleLineItem> items=new ArrayList<>();
	SoldRegister sr = SoldRegister.getInstance();
	Validation v;
	
	/**
	 * Metodo per la gestione delle richieste HTTP GET. Il metodo si limita a gestire le eccezioni rimandando
	 * ai metodi relativi e a passare richieste e risposte al metodo requests che gestisce le azioni da 
	 * intraprendere a seconda del percorso.
	 * @param req	- HttpServletRequest che riporta le informazioni relative alle richieste
	 * @param resp	- HttpServletResponse che riporta le informazioni relative alle risposte da inviare
	 * @throws ServletException		se la servlet ha incontrato problemi
	 * @throws IOException			se si è verificato un problema nell'I/O
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		try {
			requests(req,resp);
		} catch (PaymentNotCompletedException e) {
			paymentFailed(req,resp);
		} catch (TicketNotFoundException e) {
			validationFailed(req,resp);
		}
		
	}
	
	/**
	 * Metodo per la gestione delle richieste HTTP POST. Il metodo si limita a gestire le eccezioni rimandando
	 * ai metodi relativi e a passare richieste e risposte al metodo requests che gestisce le azioni da 
	 * intraprendere a seconda del percorso.
	 * @param req	- HttpServletRequest che riporta le informazioni relative alle richieste
	 * @param resp	- HttpServletResponse che riporta le informazioni relative alle risposte da inviare
	 * @throws ServletException		se la servlet ha incontrato problemi
	 * @throws IOException			se si è verificato un problema nell'I/O
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		try {
			requests(req,resp);
		} catch (PaymentNotCompletedException e) {
			paymentFailed(req,resp);
		} catch (TicketNotFoundException e) {
			validationFailed(req,resp);
		}
		
	}
	
	/**
	 * Metodo che si limita a richiamare il metodo opportuno a seconda del percorso.
	 * @param req	- HttpServletRequest che riporta le informazioni relative alle richieste
	 * @param resp	- HttpServletResponse che riporta le informazioni relative alle risposte da inviare
	 * @throws ServletException		se la servlet ha incontrato problemi
	 * @throws IOException			se si è verificato un problema nell'I/O
	 * @throws PaymentNotCompletedException		nel caso in cui una procedura di pagamento non sia andata a buon fine
	 * @throws TicketNotFoundException			nel caso in cui l'ID richiesto non corrisponda a nessun biglietto venduto
	 */
	protected void requests(HttpServletRequest req,HttpServletResponse resp)throws ServletException, IOException, PaymentNotCompletedException, TicketNotFoundException {
		if(req.getPathInfo().equals("/")) {
			home(req,resp);}
		if(req.getPathInfo().equals("/purchase") )
			purchase(req,resp);
		if(req.getPathInfo().equals("/cart"))
			resp.getWriter().write(Rythm.render("cart.html" ,items, getSaleTotal()));	
		if(req.getPathInfo().equals("/save")) 
			cart(req,resp);
		if(req.getPathInfo().equals("/payment")) 
			payment(req,resp);
		if(req.getPathInfo().equals("/remove")) 
			removeFromCart(req,resp);
		if(req.getPathInfo().equals("/validation")) 
			validateTicket(req,resp);
		if(req.getPathInfo().equals("/expiration")) 
			expiration(req,resp);
	}
	
	/**
	 * Metodo che stampa la pagina principale, passando i titoli di viaggio perchè possa essere stampato il 
	 * loro prezzo
	 * @param req	- HttpServletRequest che riporta le informazioni relative alle richieste
	 * @param resp	- HttpServletResponse che riporta le informazioni relative alle risposte da inviare
	 * @throws ServletException		se la servlet ha incontrato problemi
	 * @throws IOException			se si è verificato un problema nell'I/O
	 */
	protected void home(HttpServletRequest req,HttpServletResponse resp)throws ServletException, IOException{
		TravelDocument sr=new SingleRideTicket();
		TravelDocument c=new Carnet();
		TravelDocument bp=new BusPass();
		resp.getWriter().write(Rythm.render("home.html", sr, c, bp));
	}
		
	/**
	 * Metodo che stampa la pagina di acquisto dei titoli di viaggio
	 * @param req	- HttpServletRequest che riporta le informazioni relative alle richieste
	 * @param resp	- HttpServletResponse che riporta le informazioni relative alle risposte da inviare
	 * @throws ServletException		se la servlet ha incontrato problemi
	 * @throws IOException			se si è verificato un problema nell'I/O
	 */
	protected void purchase(HttpServletRequest req,HttpServletResponse resp)throws ServletException, IOException{
		resp.getWriter().write(Rythm.render("purchase.html"));
	}
	
	/**
	 * Metodo che stampa il carrello, passandogli l'elenco dei biglietti selezionati e il totale da 
	 * pagare perchè queste informazioni possano essere stampate dinamicamente. Il metodo aggiunge
	 * element alla lista degli articoli selezionati ottenendo il tipo e la quantità inseriti 
	 * dall'utente nella pagina di acquisto.
	 * @param req	- HttpServletRequest che riporta le informazioni relative alle richieste
	 * @param resp	- HttpServletResponse che riporta le informazioni relative alle risposte da inviare
	 * @throws ServletException		se la servlet ha incontrato problemi
	 * @throws IOException			se si è verificato un problema nell'I/O
	 */
	protected void cart(HttpServletRequest req,HttpServletResponse resp)throws ServletException, IOException {
		int type = Integer.parseInt(req.getParameter("type"));
		int qty = Integer.parseInt(req.getParameter("qty"));
		for(int i=0;i<qty;i++) {
			TravelDocument ticket=TicketCatalog.getSelectedTravelDocument(type);
			SaleLineItem it=new SaleLineItem(ticket);
			items.add(it);
		}
		resp.getWriter().write(Rythm.render("cart.html", items, getSaleTotal()));
	}
	
	/**
	 * Metodo che stampa il carrello a fronte di un interazione con il bottone che svuota il carrello
	 * stesso: viene svuotata la lista di articoli e stampato il nuovo carrello (vuoto)
	 * @param req	- HttpServletRequest che riporta le informazioni relative alle richieste
	 * @param resp	- HttpServletResponse che riporta le informazioni relative alle risposte da inviare
	 * @throws ServletException		se la servlet ha incontrato problemi
	 * @throws IOException			se si è verificato un problema nell'I/O
	 */
	protected void removeFromCart(HttpServletRequest req,HttpServletResponse resp)throws ServletException, IOException {
		items.clear();
		resp.getWriter().write(Rythm.render("cart.html", items, getSaleTotal()));
	}
	
	/**
	 * Metodo che riceve le informazioni della carta di credito inserite nella pagina del carrello e le utilizza
	 * per creare un oggetto carta di credito da usare per una procedura di pagamento con carta. Se il pagamento
	 * va a buon fine, gli articoli sono aggiunti al SoldRegister e stampati nell'opportuna pagina di pagamento 
	 * andato a buon fine; altrimenti, è richiamato un metodo per gestire l'evento
	 * @param req	- HttpServletRequest che riporta le informazioni relative alle richieste
	 * @param resp	- HttpServletResponse che riporta le informazioni relative alle risposte da inviare
	 * @throws ServletException		se la servlet ha incontrato problemi
	 * @throws IOException			se si è verificato un problema nell'I/O
	 * @throws PaymentNotCompletedException		se la procedura di vendita non è andata a buon fine (dati della
	 * 											carta di credito non validi)
	 */
	protected void payment(HttpServletRequest req,HttpServletResponse resp)throws ServletException, IOException, PaymentNotCompletedException {
		String expDate = req.getParameter("expmonth");
		CreditCard cc = new CreditCard(req.getParameter("cardname"), req.getParameter("cardnumber"), YearMonth.parse(expDate), req.getParameter("cvv"));
		CreditCardPayment payment = new CreditCardPayment(this.getSaleTotal(), cc);
		payment.makePayment(getSaleTotal());
		if (payment.isCompleted()) {
			resp.getWriter().write(Rythm.render("payment_success.html", items));
			for (SaleLineItem i : items) {
				sr.addToRegister(i);
			}
			items.clear();
		} else {
			paymentFailed(req,resp);
		}
			
	}
	
	/**
	 * Metodo che consente di calcolare il totale sommando i prezzi parziali dei singoli articoli
	 * @return total	- valore double che indica il totale attuale del carrello
	 */
	protected double getSaleTotal() {
		double total=0.0;
		for(SaleLineItem i: items) {
			total+=i.getSubTotal();			
		}
		return total;
	}
	
	/**
	 * Metodo che stampa la pagina di pagamento non andato a buon fine
	 * @param req	- HttpServletRequest che riporta le informazioni relative alle richieste
	 * @param resp	- HttpServletResponse che riporta le informazioni relative alle risposte da inviare
	 * @throws ServletException		se la servlet ha incontrato problemi
	 * @throws IOException			se si è verificato un problema nell'I/O
	 */
	protected void paymentFailed(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().write(Rythm.render("payment_failed.html"));
	}
	
	/**
	 * Metodo che stampa la pagina che permette di convalidare un titolo di viaggio
	 * @param req	- HttpServletRequest che riporta le informazioni relative alle richieste
	 * @param resp	- HttpServletResponse che riporta le informazioni relative alle risposte da inviare
	 * @throws ServletException		se la servlet ha incontrato problemi
	 * @throws IOException			se si è verificato un problema nell'I/O
	 */
	protected void validateTicket(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().write(Rythm.render("validation.html"));
	}
	
	/**
	 * Metodo che riceve l'ID del titolo di viaggio da convalidare e crea una convalida associata ad esso, aggiungendola 
	 * al ValidationRegister. Se la convalida va a buon fine il metodo stampa una pagina di convalida avvenuta che 
	 * riporta l'orario di scadenza.
	 * @param req	- HttpServletRequest che riporta le informazioni relative alle richieste
	 * @param resp	- HttpServletResponse che riporta le informazioni relative alle risposte da inviare
	 * @throws ServletException		se la servlet ha incontrato problemi
	 * @throws IOException			se si è verificato un problema nell'I/O
	 * @throws TicketNotFoundException		se l'ID inserito non corrisponde a nessuno dei titoli di viaggio venduti
	 */
	protected void expiration(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException, TicketNotFoundException {
		String id = req.getParameter("id");
		v = new Validation(id);
		ValidationRegister.getInstance().addToRegister(v);
		String exp = v.getExpirationTime().toString();
		resp.getWriter().write(Rythm.render("exp.html", exp));
	}
	
	/**
	 * Metodo che stampa la pagina di convalida non andata a buon fine
	 * @param req	- HttpServletRequest che riporta le informazioni relative alle richieste
	 * @param resp	- HttpServletResponse che riporta le informazioni relative alle risposte da inviare
	 * @throws ServletException		se la servlet ha incontrato problemi
	 * @throws IOException			se si è verificato un problema nell'I/O
	 */
	protected void validationFailed(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().write(Rythm.render("validation_failed.html"));
	}

	
}




