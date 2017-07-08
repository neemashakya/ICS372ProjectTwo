import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class TicketList {

	private static TicketList ticketList;
	private List<Ticket> tickets = new LinkedList();

	/*
	 * Private constructor to create singleton
	 */
	private TicketList() {
	}

	/**
	 * TicketList singleton
	 * @return the TicketList singleton object
	 */
	public static TicketList instance() {
		if (ticketList == null) {
			return (ticketList = new TicketList());
		} 
		else {
			return ticketList;
		}
	}
	
	
	/**
	 * Adds a Tickets to the collection
	 * @param ticket
	 *   
	 * @return
	 *   a boolean indicating successful addition to collection
	 */
	public boolean insertTickets(Ticket ticket) {
		if(tickets.add(ticket))
				return true;
		return false;
	}
	
	/**
	 * searches for a show in the collection
	 * @param showName
	 *   String showName
	 * @return a show if found or null if not found
	 */
	public Ticket search(int serialNumber) {
	    for (Iterator iterator = tickets.iterator(); iterator.hasNext(); ) {
	      Ticket ticket = (Ticket) iterator.next();
	      if (ticket.getSerialNumber().equals(serialNumber)) {
	        return ticket;
	      }
	    }
	    return null;
	  }
	
	
	public Iterator getIteratorTicketList() {
		return tickets.iterator();
	}



}
