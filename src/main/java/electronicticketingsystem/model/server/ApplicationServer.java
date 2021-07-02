package electronicticketingsystem.model.server;

import java.util.*;

import javax.servlet.Servlet;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.rythmengine.Rythm;

/**
 * Classe che descrive l'ApplicationServer che svolge le attività di supporto e configurazione per 
 * l'applicazione web
 * @param port		- valore int che indica la porta su cui aprire la comunicazione
 * @param servlet	- oggetto di tipo Servlet per gestire l'interazione con il lato client
 * @param servet	- oggetto di tipo Server, la classe che contiene il main per le comunicazioni Http
 *
 */
public class ApplicationServer {
	
	private int port;
    private Servlet servlet;
    private Server server;

    /**
     * Costruttore della classe
     * @param port		- porta sulla quale iniziare la comunicazione
     * @param servlet   - servlet per la ricezione di richieste e l'invio di risposte
     */
    public ApplicationServer(int port, Servlet servlet) {
        this.port = port;
        this.servlet = servlet;
    }

    /**
     * Metodo per l'avvio dell'Application Server
     * @throws Exception
     */
    public void start() throws Exception {
    	initRythm();
        server = new Server(port);
        ServletContextHandler handler = new ServletContextHandler();
        handler.addServlet(new ServletHolder(servlet), "/*");
        addStaticFileServing(handler);
        server.setHandler(handler);
        server.start();
    }

    /**
     * Merodo che permette di fermare il funzionamento del server
     * @throws Exception
     */
    public void stop() throws Exception {
        server.stop();
    }
  
    /**
     * Metodo che permette di configurare il server indicandogli la posizione delle risorse
     * statiche necessarie
     * @param handler		- oggetto della classe ServletContextHandler
     */
    private void addStaticFileServing(ServletContextHandler handler) {
        ServletHolder holderPwd = new ServletHolder("default", new DefaultServlet());
        holderPwd.setInitParameter("resourceBase", "./src/main/resources/statics");
        holderPwd.setInitParameter("dirAllowed","false");
        holderPwd.setInitParameter("pathInfoOnly","true");
        handler.addServlet(holderPwd, "/statics/*");
    }
    
    /**
     * Metodo che permette di configurare come principale la pagina di home dell'applicazione web,
     * che si trova nella cartella dedicata templates.
     */
    private void initRythm() {
        Map<String, Object> conf = new HashMap<>();
        conf.put("home.template", "templates");
        Rythm.init(conf);
    }

}
