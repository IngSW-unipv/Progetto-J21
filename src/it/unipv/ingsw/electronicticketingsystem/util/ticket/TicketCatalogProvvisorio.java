package it.unipv.ingsw.electronicticketingsystem.util.ticket;

public class TicketCatalogProvvisorio {
	
	//inserire qui tutti i dati del catalogo ...
	//
	private final static String bpDescription="Bus Pass";
	private final static double bpPrice=12.00;
	private final static String srDescription="Single Ride Ticket";
	private final static double srPrice=1.40;
	private final static String cDescription="Carnet";
	private final static double cPrice=35.50;
	
	
	public TicketCatalogProvvisorio() {
		
	}
	
	public static double getPrice(TicketType type) {
		switch(type) {
		case BUSSPASS: return bpPrice;
		case SINGLE: return srPrice;
		case CARNET: return cPrice;
		//in caso di aggiunta di altri tipi di biglietto, inserire qui nuovi casi
		default: return 0;
		}
	}
	
	public static String getDescription(TicketType type) {
		switch(type) {
		case BUSSPASS: return bpDescription;
		case SINGLE: return srDescription;
		case CARNET: return cDescription;
		//in caso di aggiunta di altri tipi di biglietto, inserire qui nuovi casi
		default: return " ";
		}
	}
	
	public static int getTime(TicketType type) {
		//invocando questo metodo viene restituita la durata dei biglietti 
		//durata in ore
		switch(type) {
		default: return 1;
		}
	}
	
	public static int getShots(TicketType type) {
		//invocando questo metodo viene restituito il numero di volte che si può riutilizzare un singolo biglietto
		switch(type) {
		case CARNET: return 10;
		default: return 1;
		}
	}
}
