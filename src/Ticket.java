import java.util.Calendar;

 
 public abstract class Ticket {
 	
 	protected int quantity;
 	protected Calendar date;
 	protected double price;
 	protected int serialNumber;
 	protected String ticketType;
 	
 	public String getType() {
 		return this.getClass().getName();
 	}
 	
 	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public int getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}

	public double getPrice() {
		return price;
	}
	
	public double getTotal() {
		return quantity * price;
	}

	protected abstract double setPrice(double ticketPrice);

 }
