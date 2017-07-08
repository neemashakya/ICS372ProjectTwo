import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * The collection class for Customer objects
 * 
 * 
 * @author Matt Carlson, Jamison Czech, Slava Makharovich, Prashant Shrestha
 */

public class CustomerList implements Serializable {

	private static CustomerList customerList;
	private List customers = new LinkedList();

	/*
	 * Private constructor to create singleton
	 */
	private CustomerList() {
	}

	/**
	 * CustomerList singleton
	 * @return the CustomerList singleton object
	 */
	public static CustomerList instance() {
		return customerList == null ? (customerList = new CustomerList()) : customerList;
	}
	

	/**
	 * Adds a Customer to the collection
	 * @param customer
	 * @return a boolean indicating successful addition to collection
	 */
	public boolean insertCustomer(Customer customer) {
		customers.add(customer);
		return true;
	}
	
	/**
	 * searches for a customer in the collection
	 * @param customerID
	 * @return a Customer if found or null if not found
	 */
	public Customer search(String customerID) {
	    for (Iterator iterator = customers.iterator(); iterator.hasNext(); ) {
	      Customer customer = (Customer) iterator.next();
	      if (customer.getCustomerID().equals(customerID)) {
	        return customer;
	      }
	    }
	    return null;
	  }


	/**
	 * Returns an iteration for all
	 * of the customers and their cards.
	 */
	public Iterator getCustomers() {
	    //ToDO need to output credit cards as well to screen
//		Iterator result = customers.iterator();
//		if (customers != null && !customers.isEmpty()) {
//			System.out.println("The Customers are: ");
//			while (result.hasNext()) {
//				System.out.println(result.next());
//			}
//		}
		Iterator result = customers.listIterator();

		return result;
	}


	/**
	 * removes a customer with the given customerID from the collection
	 * @param customerID
	 * @return true if Customer exists in the collection, or false otherwise
	 */
	public boolean removeCustomer(String customerID) {
		Customer customer = search(customerID);
		if (customer == null) {
			return false;
		}
		else {
			return customers.remove(customer);
		}
	}

    /**
     * write objects for serialization
     *
     * @param output stream
     */
    private void writeObject(ObjectOutputStream output) {
        try {
            output.defaultWriteObject();
            output.writeObject(customerList);

        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    /**
     * read serialized object from
     * persistent data
     *
     * @param input ObjectInputStream stream
     */
    private void readObject(ObjectInputStream input) {
        try {
            if (customerList != null) {
                return;
            } else {
                input.defaultReadObject();
                if (customerList == null) {
                    customerList = (CustomerList) input.readObject();
                } else {
                    input.readObject();
                }
            }
        } catch (IOException ioe) {
            System.out.println("in ClientList readObject \n" + ioe);
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
    }

	/**
	 * String of the customer
	 */
	@Override
	public String toString() {
		return customers.toString();
	}

}
