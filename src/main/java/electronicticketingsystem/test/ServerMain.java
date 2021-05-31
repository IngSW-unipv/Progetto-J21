package electronicticketingsystem.test;

import electronicticketingsystem.server.ApplicationServer;
import electronicticketingsystem.server.WelcomeServlet;

public class ServerMain {
	
	    public static void main(String[] argv) throws Exception {
	       new ApplicationServer(8080, new WelcomeServlet()).start();
	    }

}
