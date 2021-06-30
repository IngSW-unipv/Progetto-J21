package electronicticketingsystem.model.server;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rythmengine.Rythm;

import electronicticketingsystem.controller.TicketMachine;
import electronicticketingsystem.model.util.sale.Sale;
import electronicticketingsystem.model.util.sale.SaleLineItem;
import electronicticketingsystem.model.util.ticket.*;

public class WelcomeServlet extends HttpServlet{
	
	TicketCatalog catalog = new TicketCatalog();
	ArrayList<SaleLineItem> items=new ArrayList<>();
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		requests(req,resp);
		
	}
	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		requests(req,resp);
	}
	
	protected void requests(HttpServletRequest req,HttpServletResponse resp)throws ServletException, IOException{
		if(req.getPathInfo().equals("/")) {
			home(req,resp);}
		if(req.getPathInfo().equals("/purchase") )
			purchase(req,resp);
		if(req.getPathInfo().equals("/cart"))
			resp.getWriter().write(Rythm.render("cart.html" ,items));	
		if(req.getPathInfo().equals("/save")) 
			cart(req,resp);

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

}
