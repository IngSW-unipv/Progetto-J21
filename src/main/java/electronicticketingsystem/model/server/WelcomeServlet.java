package electronicticketingsystem.model.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rythmengine.Rythm;

import electronicticketingsystem.controller.TicketMachine;
import electronicticketingsystem.model.util.ticket.BusPass;
import electronicticketingsystem.model.util.ticket.Carnet;
import electronicticketingsystem.model.util.ticket.SingleRideTicket;
import electronicticketingsystem.model.util.ticket.TravelDocument;
import electronicticketingsystem.model.util.sale.Sale;
import electronicticketingsystem.model.util.ticket.*;

public class WelcomeServlet extends HttpServlet{
	
	TicketMachine tm = new TicketMachine();
	Sale s = tm.makeSale();
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		//requests(req,resp);
		if(req.getPathInfo().equals("/")) {
			home(req,resp);}
		if(req.getPathInfo().equals("/purchase") )
			purchase(req,resp);
		if(req.getPathInfo().equals("/cart")) {
			resp.getWriter().write(Rythm.render("cart.html"));}
	}
	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		//requests(req,resp);
		if(req.getPathInfo().equals("/")) {
			home(req,resp);}
		if(req.getPathInfo().equals("/purchase") )
			purchase(req,resp);
		if(req.getPathInfo().equals("/cart")) {
			resp.getWriter().write(Rythm.render("cart.html",s));}
	
	}
	
	protected void requests(HttpServletRequest req,HttpServletResponse resp)throws ServletException, IOException{
		if(req.getPathInfo().equals("/")) {
			home(req,resp);
		} else if(req.getPathInfo().equals("/purchase") )
			purchase(req,resp);
		}
	
	protected void home(HttpServletRequest req,HttpServletResponse resp)throws ServletException, IOException{
		TravelDocument sr=new SingleRideTicket();
		TravelDocument c=new Carnet();
		TravelDocument bp=new BusPass();
		resp.getWriter().write(Rythm.render("home.html", sr, c, bp));
	}
		
	protected void purchase(HttpServletRequest req,HttpServletResponse resp)throws ServletException, IOException{
		resp.getWriter().write(Rythm.render("purchase.html"));
		if(req.getPathInfo().equals("purchase/cart")) {
			shop(req,resp);
		} 	
	}
	
	protected void shop(HttpServletRequest req,HttpServletResponse resp)throws ServletException, IOException {
		int type = Integer.parseInt(req.getParameter("type"));
		int qty = Integer.parseInt(req.getParameter("qty"));
		
		TicketMachine tm = new TicketMachine();
		Sale s = tm.makeSale();
		tm.enterItem(type, qty);
		resp.getWriter().write(Rythm.render("cart.html", s));
		
		if(req.getPathInfo().equals("/purchase")) {
			purchase(req,resp);
		} 	
		
		
		
	}

}
