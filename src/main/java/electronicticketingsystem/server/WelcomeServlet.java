package electronicticketingsystem.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rythmengine.Rythm;

import electronicticketingsystem.controller.TicketMachine;
import electronicticketingsystem.util.ticket.*;

public class WelcomeServlet extends HttpServlet{
	
	
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		requests(req,resp);
	} 
	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		requests(req,resp);
		
	}
	
	protected void requests(HttpServletRequest req,HttpServletResponse resp)throws ServletException, IOException{
		if(req.getPathInfo().equals("/")) {
			home(req,resp);
		}else if(req.getPathInfo().equals("/purchase") )
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
		if(req.getPathInfo().equals("/save")) {
			shop(req,resp);
		} else if(req.getPathInfo().equals("/cart")) {
			resp.getWriter().write(Rythm.render("cart.html"));
		}
		
				
	}
	
	protected void shop(HttpServletRequest req,HttpServletResponse resp)throws ServletException, IOException{
		TicketMachine tm = new TicketMachine();
		tm.makeSale();
		
		//tm.enterItem(req.getParameter("id"), req.getParameter("qty"));
	}

}
