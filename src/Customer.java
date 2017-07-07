
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * This class creates a Customer and assigns a unique ID to the Customer upon
 * creation
 * 
 * @author Matt Carlson, Jamison Czech, Slava Makharovich, Prashant Shrestha
 */
public class Customer implements Serializable{

	// Unique identifier assigned to a new customer
	private static long ID = 1L;

	private String name;
	private String address;
	private String phoneNumber;
	private String customerID;
    private static final String CUSTOMER_ID_STRING = "cust ";
	private List customerCard = new LinkedList();
	private List  <Ticket> ticketList = new LinkedList();
	/**
	 * Creates a new Customer assigning a unique ID and default balance
	 * 
	 * @param name
	 *   String name
	 * @param address
	 *   String address
	 * @param phoneNumber
	 *   String phoneNumber
	 */
	public Customer(String name, String address, String phoneNumber) {
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
	//	this.customerID = Long.toString(ID);
	//	ID++;
        customerID = CUSTOMER_ID_STRING + (CreateIdServer.instance().getId());
	}
	
	
	
	/**
	 * add tickets to the Customer ticket list
	 * @param ticket
	 * @return
	 */
	public boolean addTickets(Ticket ticket) {
		
		return ticketList.add(ticket);
	}
	
	
	/**
	 *check if card exist in the customer card list 
	 * @param cardNumber
	 * @return  credit card if card was found, otherwise null
	 */
	public CreditCard searchCard(String cardNumber) {
		Iterator iter=this.getCustomerCard();
		while(iter.hasNext()) {
			CreditCard card = (CreditCard)iter.next();
			String cardNum= card.getCardNumber();
			if(cardNum.equals(cardNumber))
				return card;
		}
		return null; 
	}

	// INCOMPLETE
	public boolean insertCard(CreditCard creditCard) {
		return customerCard.add(creditCard);
	}

	// INCOMPLETE
	public void removeCard(CreditCard creditCard) {
		customerCard.remove(creditCard);
	}

	/**
	 * Getter for list of cards for a Customer
	 * 
	 * @return an iterator of a customer's credit cards
	 */
	public Iterator getCustomerCard() {
		return customerCard.listIterator();
	}

	/**
	 * Gets number of cards a customer has on file
	 * 
	 * @return an int of the number of cards a customer has on file
	 */
	public int cardCount() {
		return customerCard.size();
	}

	/**
	 * Gets customer's name
	 * 
	 * @return a string of the customer's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets customer's address
	 * 
	 * @return a string of the customer's address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Gets customer's phone number
	 * 
	 * @return a string of the customer's phone number
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Get the customer's ID number
	 * 
	 * @return a long of the customer's ID number
	 */
	public String getCustomerID() {
		return customerID;
	}

	/**
	 * String representing a customer
	 * 
	 * @return a string representing a customer
	 */
	@Override
	public String toString() {
		String outputString = " ";
		outputString += "Customer ID: " + customerID + ", Name: " + name + ", Address: "
				+ address + ", Phone number: #" + phoneNumber;
		outputString +="\n The credit card(s): " + customerCard.toString(); 
		outputString +="\n The ticket (s): " + ticketList.toString(); 
		
		return outputString;
		
	}

}
