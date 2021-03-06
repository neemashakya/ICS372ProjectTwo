import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;

public class RegularTicket extends Ticket implements Serializable{
	private Integer serialNumber= 001;
	Calendar date;
	String type;
	double price;
	int quantity;
	double total;
	String showName;
	
	
	public RegularTicket( Calendar date, String showName, double price, int quantity) {
		this.serialNumber=serialNumber;
		serialNumber++;
		this.date=date;
		this.showName=showName;
		this.type= "Regular Ticket";
		this.price=price;
		this.quantety=quantity;
		this.total=price*quantity;
	}

	
	public Integer getSerialNumber() {
		return serialNumber;
	}


	public void setSerialNumber(Integer serialNumber) {
		this.serialNumber = serialNumber;
	}


	public Calendar getDate() {
		return date;
	}


	public void setDate(Calendar date) {
		this.date = date;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public double getTotal() {
		return total;
	}


	public void setTotal(double total) {
		this.total = total;
	}
	
	
	public String toString() {
		String outputString= "";
		outputString= "Ticket Serial Number: " + this.getSerialNumber();
		outputString= "Show Name: " + this.showName;
		outputString= "Type: " + this.type;
		outputString= "Date: " + this.date;
		outputString= "Price: " + this.price;
		outputString= "Total: " + this.getTotal();
		
		
		
		return outputString;
	}
	
	
	
	

}
