package electronicticketingsystem.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rythmengine.Rythm;

import electronicticketingsystem.controller.TicketMachine;
import electronicticketingsystem.util.ticket.SingleRideTicket;
import electronicticketingsystem.util.ticket.TravelDocument;

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
		TravelDocument ti=new SingleRideTicket();
		resp.getWriter().write(Rythm.render("home.html", ti));
	}
		
	protected void purchase(HttpServletRequest req,HttpServletResponse resp)throws ServletException, IOException{
		
		resp.getWriter().write(Rythm.render("purchase.html"));
		if((req.getPathInfo().equals("Return to the home page"))){
			home(req,resp);
		}else if((req.getPathInfo().equals("Reset")))
				purchase(req,resp);
		else if(req.getPathInfo().equals("Shop") || (req.getPathInfo().equals("Shop Now!")))
				shop(req,resp);
				
	}
	
	protected void shop(HttpServletRequest req,HttpServletResponse resp)throws ServletException, IOException{
		//tm.makeSale();
		//TicketMachine.enterItem(req.getParameter("type"), req.getParameter("qty"));
	}

}
