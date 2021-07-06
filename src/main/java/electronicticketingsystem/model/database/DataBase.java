package electronicticketingsystem.model.database;

import java.sql.*;
import java.time.LocalTime;

import electronicticketingsystem.model.util.sale.SaleLineItem;
import electronicticketingsystem.model.util.ticket.TravelDocument;
import electronicticketingsystem.model.util.validation.Validation;

public class DataBase {

		private String url;
		private String name;
		private String pass;
		private String query;
		
		public DataBase() {
			//Copiare la propria JDBC connection string da MySQL Workbench
			this.url = "jdbc:mysql://localhost:3306/ProgettoJ21?serverTimezone=UTC";
			this.name = "admin";
			this.pass = "admin";
			
		}
		
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
		
		//Confronto i biglietti già convalidati
		
		public boolean addInspection(String TicketID, String idInspector)  {
			query = "Select * from validatedticket where TickID = '" + TicketID + "'";
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
		
		
		//Table per biglietti venduti 
		
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