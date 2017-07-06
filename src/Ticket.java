import java.util.Date;

public abstract class Ticket {
	
	protected int quantity;
	protected String customerID;
	protected String cardNumber;
	protected Date ticketDate;
	protected double price;
	
	public String getType() {
		return this.getClass().getName();
	}
	
	public Date getDate() {
		return this.ticketDate;
	}
	

	protected abstract double setPrice(double ticketPrice);


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}



}
