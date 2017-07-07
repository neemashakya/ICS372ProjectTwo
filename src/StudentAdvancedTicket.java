import java.io.Serializable;
import java.util.Calendar;

public class StudentAdvancedTicket extends Ticket implements Serializable {
    private Integer serialNumber = 1;
    private Calendar date;
    private String type;
    private double price;
    private int quantety;
    private double total;
    private String showName;

    public StudentAdvancedTicket(Calendar date, String showName, double price, int quantity) {
        this.serialNumber = serialNumber;
        serialNumber++;
        this.date = date;
        this.showName = showName;
        this.type = "Student Advanced Ticket";
        this.price = price;
        this.quantety = quantity;
        this.total = price * quantity;
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

    public int getQuantety() {
        return quantety;
    }

    public void setQuantety(int quantety) {
        this.quantety = quantety;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String toString() {
        String outputString = "";
        outputString = "Ticket Serial Number: " + this.getSerialNumber();
        outputString = "Show Name: " + this.showName;
        outputString = "Type: " + this.type;
        outputString = "Date: " + this.date;
        outputString = "Price: " + this.price;
        outputString = "Total: " + this.getTotal();


        return outputString;
    }
}
