import java.io.Serializable;
import java.util.Calendar;

public class RegularTicket extends Ticket implements Serializable {

	public RegularTicket(int quantity, double price, Calendar date) {
		this.quantity = quantity;
		this.date = date;
		this.ticketType = "Regular Ticket";
		this.price = setPrice(price);
	}

	@Override
	protected double setPrice(double ticketPrice) {
		this.price = ticketPrice;
		return this.price;
	}

	public String toString() {
		String outputString = "Ticket Serial Number: " + this.getSerialNumber()
				+ "Type: " + this.ticketType 
				+ "Date: " + this.date 
				+ "Price: " + this.price 
				+ "Quantity: " + this.quantity
				+ "Total: " + this.getTotal();

		return outputString;
	}

}
