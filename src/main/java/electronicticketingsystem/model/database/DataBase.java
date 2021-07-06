package electronicticketingsystem.model.database;

import java.sql.*;
import electronicticketingsystem.model.util.sale.SaleLineItem;
import electronicticketingsystem.model.util.validation.Validation;

/**
 * Classe che realizza la connessione al DataBase creato tramite il DBMS MySQL. Questa classe
 * permette di effettuare, tramite JDBC, il collegamento tra il DataBase e codice Java e contiene
 * tutti i metodi che permettono di ricavare o salvare informazioni sul DataBase tramite opportune 
 * query in linguaggio SQL.
 * @param url		- stringa che indica l'url della connessione MySQL che abbiamo creato per il progetto 
 * @param name		- stringa che indica il nome dell'utente che può gestire la connessione, che abbiamo creato
 * 					  appositamente per questa connessione
 * @param pass		- stringa che indica la password della connessione 
 * @param query		- stringa in cui vengono memorizzate le query con le quali viene interrogato il DataBase 
 *
 */
public class DataBase {

		private String url;
		private String name;
		private String pass;
		private String query;
		
		/***
		 * Costruttore della classe, che inizializza l'url, l'id e la password relativi alla connessione ai valori 
		 * corretti per poter effettuare il collegamento
		 */
		public DataBase() {
			this.url = "jdbc:mysql://localhost:3306/ProgettoJ21?serverTimezone=UTC";
			this.name = "admin";
			this.pass = "admin";
			
		}
		
		/**
		 * Metodo che implementa la procedura di login. Il metodo interroga la table relativa ai controllori del database
		 * ricercando una coppia ID-Password che corrisponda a quella inserita dall'utente.
		 * @param inspectorId	- ID del controllore
		 * @param password      - password del controllore
		 * @return true			- se la procedura di login è andata a buon fine, false altrimenti
		 */
		public boolean Login(String inspectorId, String password) {
			
			query = "Select * from ticketinspector where ID = '" + inspectorId + "' and password='" + password + "'";
			try {
			Connection con = DriverManager.getConnection(url, name, pass);
			Statement st = con.createStatement();
			ResultSet rs;
			rs = st.executeQuery(query);
			if(rs.next()) {
				
				System.out.println("Login completed");
				st.close();
				con.close();
				return true;
				
			}
			else {
				System.out.println("Your ID or Password is wrong. Try again!");
				st.close();
				con.close();
				return false;
			}
			
			} catch (SQLException e) {
				
				System.out.println(e);
				return false;
			}
			

		
	    }
		
		/**
		 * Metodo che permette di ricercare nella table contenente i titoli di viaggio convalidati quello richiesto
		 * per il controllo; se la procedura di controllo va a buon fine (il titolo di viaggio è valido), l'ID del 
		 * titolo di viaggio controllato e l'ID del controllore che ha effettuato il controllo sono memorizzati
		 * nell'opportuna table.
		 * @param TicketID			- ID del titolo di viaggio di cui verificare la validità
		 * @param idInspector		- ID del controllore che effettua la verifica
		 * @return true 			- se il titolo di viaggio è valido, false altrimenti
		 */
		public boolean addInspection(String TicketID, String idInspector)  {
			query = "Select * from validatedticket where TickID = '" + TicketID + "' && ExpTime > current_time()" ;
			try {
			Connection con = DriverManager.getConnection(url, name, pass);
			Statement st = con.createStatement();
			ResultSet rs;
			rs = st.executeQuery(query);
			
			while (rs.next()) {
				
				int count = st.executeUpdate("Insert into verifiedticket (TickID, InspID) values ('" + TicketID +"','" + idInspector + "')");
				System.out.println(count + " row/s affected");
				st.close();
				con.close();
				return true;
				
			}
			
			//else {
				
				st.close();
				con.close();
				return false;
				
			//}
			
		}
			catch (SQLException e) {
				
				System.out.println(e);
				return false;
				
			}
		}
		
		
		/**
		 * Metodo che permette di aggiungere i titoli di viaggio acquistati nell'opportuna table una volta 
		 * completata la procedura di vendita.
		 * @param ticket		- oggetto di tipo SaleLineItem che indica il titolo di viaggio da aggiungere
		 */
		public void registerItem(SaleLineItem ticket) {
	
			String TicketID = ticket.getTicketID();
			query = "Insert into soldregister (TickID) values ('" + TicketID + "')";  
			Connection con;
			try {
				con = DriverManager.getConnection(url, name, pass);
				Statement st = con.createStatement();
				int count = st.executeUpdate(query);
				System.out.println(count + " row/s affected");
				
				st.close();
				con.close();
				
			} catch (SQLException e) {
				
				System.out.println(e);
			}
	
			
		}

		/**
		 * Metodo che permette di aggiungere i titoli di viaggio convalidati nell'opportuna table una volta 
		 * completata la procedura di convalida.
		 * @param v		- oggetto di tipo Validation che indica il titolo di viaggio convalidato da aggiungere
		 */
		public void addValidation(Validation v) {
			String time = v.getExpirationTime().toString();
			String id = v.getID();
			query = "Insert into validatedticket (TickID, ExpTime) values ('" + id + "','" + time +"')";  
			Connection con;
			try {
				con = DriverManager.getConnection(url, name, pass);
				Statement st = con.createStatement();
				int count = st.executeUpdate(query);
				System.out.println(count + " row/s affected");
				
				st.close();
				con.close();
				
			} catch (SQLException e) {
				
				System.out.println(e);
			}
	
		}
		
}