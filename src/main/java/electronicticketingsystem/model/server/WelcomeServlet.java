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
import electronicticketingsystem.model.util.payment.*;
import electronicticketingsystem.model.util.sale.SaleLineItem;
import electronicticketingsystem.model.util.sale.SoldRegister;
import electronicticketingsystem.model.util.ticket.*;

public class WelcomeServlet extends HttpServlet{
	
	TicketCatalog catalog = new TicketCatalog();
	ArrayList<SaleLineItem> items=new ArrayList<>();
	SoldRegister sr = SoldRegister.getInstance();
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		try {
			requests(req,resp);
		} catch (PaymentNotCompletedException e) {
			paymentFailed(req, resp);
		}
		
	}
		

	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		try {
			requests(req,resp);
		} catch (PaymentNotCompletedException e) {
			paymentFailed(req,resp);
		}
		
	}
	
	protected void requests(HttpServletRequest req,HttpServletResponse resp)throws ServletException, IOException,PaymentNotCompletedException{
		if(req.getPathInfo().equals("/")) {
			home(req,resp);}
		if(req.getPathInfo().equals("/purchase") )
			purchase(req,resp);
		if(req.getPathInfo().equals("/cart"))
			resp.getWriter().write(Rythm.render("cart.html" ,items));	
		if(req.getPathInfo().equals("/save")) 
			cart(req,resp);
		if(req.getPathInfo().equals("/payment")) 
			payment(req,resp);
		if(req.getPathInfo().equals("/remove")) 
			removeFromCart(req,resp);

	}
	
	protected void home(HttpServletRequest req,HttpServletResponse resp)throws ServletException, IOException{
		TravelDocument sr=new SingleRideTicket();
		TravelDocument c=new Carnet();
		TravelDocument bp=new BusPass();
		resp.getWriter().write(Rythm.render("home.html", sr, c, bp));
	}
		
	protected void purchase(HttpServletRequest req,HttpServletResponse resp)throws ServletException, IOException{
		resp.getWriter().write(Rythm.render("purchase.html"));
	}
	
	protected void cart(HttpServletRequest req,HttpServletResponse resp)throws ServletException, IOException {
		int type = Integer.parseInt(req.getParameter("type"));
		int qty = Integer.parseInt(req.getParameter("qty"));
		for(int i=0;i<qty;i++) {
			TravelDocument ticket=TicketCatalog.getSelectedTravelDocument(type);
			SaleLineItem it=new SaleLineItem(ticket);
			items.add(it);
		}
		resp.getWriter().write(Rythm.render("cart.html", items));
	}
	
	protected void removeFromCart(HttpServletRequest req,HttpServletResponse resp)throws ServletException, IOException {
		items.clear();
		resp.getWriter().write(Rythm.render("cart.html", items));
	}
	
	protected void payment(HttpServletRequest req,HttpServletResponse resp)throws ServletException, IOException, PaymentNotCompletedException {
		String expDate = req.getParameter("expmonth");
		CreditCard cc = new CreditCard(req.getParameter("cardname"), req.getParameter("cardnumber"), YearMonth.parse(expDate), req.getParameter("cvv"));
		CreditCardPayment payment = new CreditCardPayment(this.getSaleTotal(), cc);
		payment.makePayment(getSaleTotal());
		if (payment.isCompleted()) {
			resp.getWriter().write(Rythm.render("payment_success.html"));
			for (SaleLineItem i : items) {
				sr.addToRegister(i);
			}
			items.clear();
		} else {
			paymentFailed(req,resp);
		}
			
	}
	
	protected double getSaleTotal() {
		double total=0.0;
		for(SaleLineItem i: items) {
			total+=i.getSubTotal();			
		}
		return total;
	}
	
	protected void paymentFailed(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().write(Rythm.render("payment_failed.html"));
	}
	
}




