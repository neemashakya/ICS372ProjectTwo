import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class TicketList implements Serializable{


		private static TicketList ticketList;
		private List tickets = new LinkedList();

		/*
		 * Private constructor to create singleton
		 */
		private TicketList() {
		}

		/**
		 * ShowList singleton
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
		 * Adds a Ticket to the collection
		 * @param newTicket
		 *   Ticket newTicket
		 * @return
		 *   a boolean indicating successful addition to collection
		 */
		public boolean insertTicket(Ticket newTicket) {
			tickets.add(newTicket);
			return true;
		}
		

	    /**
	     * Returns an iterator for all
	     * of the tickets
	     */
	    public Iterator getTickets(Date showDate) {
	     
	    	List ticketsForDate = new LinkedList();
	        if (tickets != null && !tickets.isEmpty()) {
	        	
	        	for(Iterator iterator = tickets.iterator(); iterator.hasNext();) {
	        		Ticket ticket = (Ticket) iterator.next();
	        		if (ticket.getDate().equals(showDate)) {
	        			ticketsForDate.add(ticket);
	        		}
	        	}
	        }
	        return ticketsForDate.iterator();
	    }

		
		/**
		 * write objects for serialization
		 * @param output stream
		 */
		private void writeObject(ObjectOutputStream output) {
		    try {
		      output.defaultWriteObject();
		      output.writeObject(ticketList);
		    } 
		    catch(IOException ioe) {
		      System.out.println(ioe);
		    }
		  }
		
		  /**
		   * read serialized object
		   * @param input stream
		   */
		private void readObject(ObjectInputStream input) {
			try {
				if (ticketList != null) {
					return;
				} 
				else {
					input.defaultReadObject();
					if (ticketList == null) {
						ticketList = (TicketList) input.readObject();
					} 
					else {
						input.readObject();
					}
				}
			} 
			catch(IOException ioe) {
				System.out.println("in TicketList readObject \n" + ioe);
			} 
			catch(ClassNotFoundException cnfe) {
					cnfe.printStackTrace();
			}
		}
		
		/**
		 * String of the ticket
		 */
		@Override
		public String toString() {
			return tickets.toString();
		}

	
}
