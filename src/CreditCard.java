import java.io.Serializable;

/**
 * This class creates a Credit Card with a customer ID, cardNumber, and expiration date
 * creation
 * 
 * @author Matt Carlson, Jamison Czech, Slava Makharovich, Prashant Shrestha
 */

public class CreditCard implements Serializable{

	private String customerID;
	private String cardNumber;
	private String expirationDate;

	/**
	 * Creates a new CreditCard
	 * 
	 * @param customerID
	 *  String customerID
	 * @param cardNumber
	 *   cardNumber
	 * @param expirationDate
	 *   expirationDate
	 */
	public CreditCard(String customerID, String cardNumber, String expirationDate) {
		this.customerID = customerID;
		this.cardNumber = cardNumber;
		this.expirationDate = expirationDate;

	}


	/**
	 * Getter for customerID of credit card
	 * 
	 * @return a string of the customer ID
	 */
	public String getCustomerID() {
		return customerID;
	}

	/**
	 * Getter for credit card number
	 * 
	 * @return a string of the credit card number
	 */
	public String getCardNumber() {
		return cardNumber;
	}

	/**
	 * Getter for expiration date
	 * 
	 * @return a string of the expiration date
	 */
	public String getExpirationDate() {
		return expirationDate;
	}



	/**
	 * String representing a credit card
	 * 
	 * @return a string representing a credit card
	 */
	@Override
	public String toString() {
		return "Customer ID: " + customerID + ", Card Number: " + cardNumber + ", Expiration Date: "
					+ expirationDate;
	}
}
