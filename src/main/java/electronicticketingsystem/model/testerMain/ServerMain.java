package electronicticketingsystem.model.testerMain;

import electronicticketingsystem.view.server.*;

/**
 * Classe che contiene il metodo main per provare il funzionamento dello shop online. Il
 * main si limita a creare e avviare l'Application Server aprendo la WelcomeServlet sulla
 * porta 8080
 */
public class ServerMain {
	
	    public static void main(String[] argv) throws Exception {
	       new ApplicationServer(8080, new WelcomeServlet()).start();
	    }

}
