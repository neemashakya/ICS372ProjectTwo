/**
 * @author Brahma Dathan and Sarnath Ramnath
 * @Copyright (c) 2010
 * Modified by Matt Carlson, Jamison Czech, Slava Makharovich, Prashant Shrestha
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.StringTokenizer;

/**
 * This class implements the user interface for the Theater project. The
 * commands are encoded as integers using a number of static final variables. A
 * number of utility methods exist to make it easier to parse the input.
 */
public class UserInterface {
    private static UserInterface userInterface;
    private BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));
    private static Theater theater;
    private static final int EXIT = 0;
    private static final int ADD_CLIENT = 1;
    private static final int REMOVE_CLIENT = 2;
    private static final int LIST_CLIENTS = 3;
    private static final int ADD_CUSTOMER = 4;
    private static final int REMOVE_CUSTOMER = 5;
    private static final int ADD_CARD = 6;
    private static final int REMOVE_CARD = 7;
    private static final int LIST_CUSTOMERS = 8;
    private static final int ADD_SHOW = 9;
    private static final int LIST_SHOWS = 10;
    private static final int SAVE = 11;
    private static final int RETRIEVE = 12;
    private static final int SELL_REGULAR_TICKETS = 13;
    private static final int SELL_ADVANCE_TICKETS = 14;
    private static final int SELL_STUDENT_ADVANCE_TICKETS = 15;
    private static final int PAY_CLIENT = 16;
    private static final int DISPLAY_TICKETS=17;
    private static final int HELP = 18;

    /**
     * Made private for singleton pattern. Conditionally looks for any saved
     * data. Otherwise, it gets a singleton Theater object.
     */
    private UserInterface() {
        if (yesOrNo("Search for previous theater data?")) {
            retrieve();
        } else {
            theater = Theater.instance();
        }
    }

    /**
     * Supports the singleton pattern
     *
     * @return the singleton object
     */
    public static UserInterface instance() {
        if (userInterface == null) {
            return userInterface = new UserInterface();
        } else {
            return userInterface;
        }
    }

    /**
     * Gets a token after prompting
     *
     * @param prompt - whatever the user wants as prompt
     * @return - the token from the keyboard
     */
    public String getToken(String prompt) {
        do {
            try {
                System.out.println(prompt);
                String line = reader.readLine();
                StringTokenizer tokenizer = new StringTokenizer(line, "\n\r\f");
                if (tokenizer.hasMoreTokens()) {
                    return tokenizer.nextToken();
                }
            } catch (IOException ioe) {
                System.exit(0);
            }
        } while (true);
    }

    /**
     * Queries for a yes or no and returns true for yes and false for no
     *
     * @param prompt The string to be prepended to the yes/no prompt
     * @return true for yes and false for no
     */
    private boolean yesOrNo(String prompt) {
        String more = getToken(prompt + " (Y|y)[es] or anything else for no");
        return !(more.charAt(0) != 'y' && more.charAt(0) != 'Y');
    }

    /**
     * Converts the string to a number
     *
     * @param prompt the string for prompting
     * @return the integer corresponding to the string
     */
    public int getNumber(String prompt) {
        do {
            try {
                String item = getToken(prompt);
                Integer number = Integer.valueOf(item);
                return number.intValue();
            } catch (NumberFormatException nfe) {
                System.out.println("Please input a number ");
            }
        } while (true);
    }

    /**
     * Prompts for a date and gets a date object
     *
     * @param prompt the prompt
     * @return the data as a Calendar object
     */
    public Calendar getDate(String prompt) {
        do {
            try {
                Calendar date = new GregorianCalendar();
                String item = getToken(prompt);
                DateFormat dateFormat = SimpleDateFormat.getDateInstance(DateFormat.SHORT);
                date.setTime(dateFormat.parse(item));
                return date;
            } catch (Exception fe) {
                System.out.println("Please input a date as mm/dd/yy");
            }
        } while (true);
    }
    

    /**
     * Prompts for a command from the keyboard
     *
     * @return a valid command
     */
    public int getCommand() {
        do {
            try {
                int value = Integer.parseInt(getToken("\nPlease select a choice from the menu," +
                        "\nEnter " + HELP + " for help: "));
                if (value >= EXIT && value <= HELP) {
                    return value;
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Enter a number");
            }
        } while (true);
    }

    /**
     * Displays the help screen
     */
    public void help() {
        System.out.println("Enter a number between 0 and 13 as explained below:");
        System.out.println(EXIT + " to Exit");
        System.out.println(ADD_CLIENT + " to add a client ");
        System.out.println(REMOVE_CLIENT + " to remove client ");
        System.out.println(LIST_CLIENTS + " to list all clients ");
        System.out.println(ADD_CUSTOMER + " to add a customer ");
        System.out.println(REMOVE_CUSTOMER + " to remove a customer ");
        System.out.println(ADD_CARD + " to add a credit card");
        System.out.println(REMOVE_CARD + " to remove a credit card");
        System.out.println(LIST_CUSTOMERS + " to list all customers and credit cards");
        System.out.println(ADD_SHOW + " to add a show");
        System.out.println(LIST_SHOWS + " to list all shows");
        System.out.println(SAVE + " to save data");
        System.out.println(RETRIEVE + " to retrieve data");
        System.out.println(SELL_REGULAR_TICKETS+ " to sell regular tickets");
        System.out.println(SELL_ADVANCE_TICKETS+ " to sell advanced tickets");
        System.out.println( SELL_STUDENT_ADVANCE_TICKETS + " tosell student advanced tickets");
        System.out.println(PAY_CLIENT  + " to pay client ");
        System.out.println(DISPLAY_TICKETS  + " to display tickets at certain day ");
        System.out.println(HELP + " for help");
       
    }

    /**
     * Method to be called for adding a client. Prompts the user for the
     * appropriate values and uses the appropriate Theater method for adding the
     * client.
     */
    public void addClient() {
        String name = getToken("Enter Client's name: ");
        String address = getToken("Enter address: ");
        String phone = getToken("Enter phone number: ");
        Client result;
        result = theater.addClient(name, address, phone);
        if (result == null) {
            System.out.println("Error, could not add client to database.");
        }
        System.out.println(result);
    }

    /**
     * Method to be called for removing a client. Prompts the user for the
     * appropriate value and uses the appropriate Theater method for removing
     * the client if possible.
     */
    public void removeClient() {
        int result;
        do {
            String clientID = getToken("Enter client id");
            result = theater.removeClient(clientID);

            switch (result) {
                case Theater.CLIENT_NOT_FOUND:
                    System.out.println("No such client");
                    break;
                case Theater.CLIENT_HAS_SHOW:
                    System.out.println(
                            "Client cannot be removed. Client has a scheduled show.");
                    break;
                case Theater.CLIENT_REMOVED:
                    System.out.println("Client was successfully removed");
                    break;
                default:
                    System.out.println("There was an error");
                    break;
            }
            if (!yesOrNo("Remove more clients?")) {
                break;
            }
        } while (true);
    }

    /**
     * Method to be called for displaying clients. Uses the appropriate Theater
     * method for displaying clients.
     * <b>PostCondition:</b>
     * Returns a collection of all available clients.
     */
    public void getClientList() {
        Iterator result;
        result = theater.getClients();
        if (result == null) {
            System.out.println("No clients have been added yet.");
        } else {
            while (result.hasNext()) {
                Client client = (Client) result.next();
                System.out.println(client.toString() + "\n");
            }
            System.out.println("\nThere are no more clients.\n");
        }
    }

    /**
     * Method to be called for adding a customer. Prompts the user for the
     * appropriate values and uses the appropriate Theater method for adding the
     * customer.
     *
     */
    public void addCustomer() {
        Customer result;
        do {
            String name = getToken("Enter name");
            String address = getToken("Enter address");
            String phoneNumber = getToken("Enter phone number #");
            // needs to call card validation method
            String cardNumber = getToken("Enter credit card number");
            // needs to call expiration validation method
            String expiration = getToken("Enter expiration date");

            result = theater.addCustomer(name, address, phoneNumber, cardNumber,
                    expiration);
            if (result != null) {
                System.out.println(result);
            } else {
                System.out.println("Customer could not be added");
            }
            if (!yesOrNo("Add more customers?")) {
                break;
            }
        } while (true);
    }


    /**
     * Method to be called for removing a customer. Prompts the user for the
     * appropriate value and uses the appropriate Theater method for removing
     * the customer if possible.
     */
    public void removeCustomer() {
        int result;
        do {
            String customerID = getToken("Enter customer id:");
            result = theater.removeCustomer(customerID);

            switch (result) {
                case Theater.CUSTOMER_NOT_FOUND:
                    System.out.println("No such customer");
                    break;
                case Theater.CUSTOMER_REMOVED:
                    System.out.println("Customer was successfully removed");
                    break;
                default:
                    System.out.println("There was an error");
                    break;
            }
            if (!yesOrNo("Remove more customers?")) {
                break;
            }
        } while (true);
    }

    /**
     * Method to be called for add a credit cart. Prompts the user for the
     * appropriate values and uses the appropriate Theater method for adding the
     * credit card if possible.
     */
    public void addCreditCard() {
        int result;
        do {
            String customerID = getToken("Enter customer id");
            // needs to call card validation method
            String cardNumber = getToken("Enter credit card number");
            // needs to call expiration validation method
            String expiration = getToken("Enter expiration date");

            result = theater.addCreditCard(customerID, cardNumber, expiration);

            switch (result) {
                case Theater.CUSTOMER_NOT_FOUND:
                    System.out.println("No such customer");
                    break;
                case Theater.DUPLICATE_CARD:
                    System.out.println("Card already in the system");
                    break;
                case Theater.CARD_ADDED:
                    System.out.println("Card successfully added!");
                    break;
                default:
                    System.out.println("There was an error");
                    break;
            }
            if (!yesOrNo("Add another card?")) {
                break;
            }
        } while (true);
    }

    /**
     * Method to be called for removing a credit card. Prompts the user for the
     * appropriate value and uses the appropriate Theater method for removing
     * the credit card if possible.
     */
    public void removeCard() {
        int result;
        do {
            String cardNumber = getToken("Enter cardNumber: ");
            result = theater.removeCard(cardNumber);

            switch (result) {
                case Theater.CARD_NOT_FOUND:
                    System.out.println("Credit ");
                    break;
                case Theater.ONLY_CARD:
                    System.out.println(
                            "Card cannot be removed. Customer must have at least one card on file.");
                    break;
                case Theater.CARD_REMOVED:
                    System.out.println("Card was successfully removed!");
                    break;
                default:
                    System.out.println("There was an error:");
                    break;
            }
            if (!yesOrNo("Remove more cards?")) {
                break;
            }
        } while (true);
    }

    /**
     * Method to be called for getting customer list. Uses the appropriate
     * Theater method for getting customers.
     */
    public void getCustomers() {
        Iterator result = theater.getCustomers();
        if (result == null) {
            System.out.println("No customers in list");
            return;
        } else {
            while (result.hasNext()) {
                Customer customer = (Customer) result.next();
                System.out.println("Customer: \n");
                System.out.println(customer.toString());

                Iterator cardList = customer.getCustomerCard();
                while (cardList.hasNext()) {
                    CreditCard creditCard = (CreditCard) cardList.next();
                    System.out.println("Card number: "
                            + creditCard.getCardNumber() + " Expiration: "
                            + creditCard.getExpirationDate());
                }
            }
            System.out.println("\n  There are no more customers \n");
        }
    }

    /**
     * Method to be called for add a show. Prompts the user for the appropriate
     * values and uses the appropriate Theater method for adding the show if
     * possible.
     * @throws ParseException 
     */
    public void addShow() throws ParseException {
        int result;
        do {
            String clientID = getToken("Enter client id:");
            String showName = getToken("Enter name of show:");
            String price = getToken("Enter the price for regular ticket:");
            Calendar startDate = getDate("Enter start date as mm/dd/yy:");
            Calendar endDate = getDate("Enter end date as mm/dd/yy:");
            double regularPrice= Double.parseDouble(price);
            result = theater.addShow(clientID, showName, startDate, endDate, regularPrice);

            switch (result) {
                case Theater.CLIENT_NOT_FOUND:
                    System.out.println("No such client!");
                    break;
                case Theater.DATE_NOT_OPEN:
                    System.out.println("Date range is unavailable:");
                    break;
                case Theater.SHOW_ADDED:
                    System.out.println("Show successfully added:");
                    break;
                default:
                    System.out.println("There was an error:");
                    break;
            }
            if (!yesOrNo("Add another show?")) {
                break;
            }
        } while (true);
    }

    /**
     * Method to be called for displaying shows. Uses the appropriate Theater
     * method for displaying shows.
     */
    public void getShows() {
        Iterator result;

        result = theater.getShows();
        if (result == null) {
            System.out.println("No shows have been added.");
        } else {
            while (result.hasNext()) {
                Show show = (Show) result.next();
                System.out.println(show.toString() + "\n");
            }
            System.out.println("\n There are no more shows in the list.\n");
        }
    }

    /**
     * Method to be called for saving the Theater object. Uses the appropriate
     * Theater method for saving.
     */
    private void save() {
        if (Theater.save()) {
            System.out.println(
                    " The theater has been successfully saved in the file TheaterData \n");
        } else {
            System.out.println("There has been an error in saving!\n");
        }
    }

    /**
     * Method to be called for retrieving saved data. Uses the appropriate
     * Theater method for retrieval.
     */
    private void retrieve() {
        try {
            Theater tempTheater = Theater.retrieve();
            if (tempTheater != null) {
                System.out.println(
                        "The theater has been successfully retrieved from the file 'TheaterData' \n");
                theater = tempTheater;
            } else {
                System.out.println("File doesnt exist; creating new theater");
                theater = Theater.instance();
            }
        } catch (Exception cnfe) {
            cnfe.printStackTrace();
        }
    }

    
    /**
     * this method sells regular tickets 
     * @throws ParseException 
     */
    public void sellRegularTicket() throws ParseException {
    	int result;
    	do {
            String customerID = getToken("Enter customer id:");
            String cardNumber = getToken("Enter cardNumber: ");
            Calendar date = getDate("Enter date as mm/dd/yy:");
            String numTickets = getToken("Enter requested number of tickets:");
            Integer quantity=Integer.parseInt(numTickets);

            result = theater.sellRegTickets(customerID, cardNumber,  date, quantity);

            switch (result) {
                case Theater.CUSTOMER_NOT_FOUND:
                    System.out.println("No such customer!");
                    break;
                case Theater.CARD_NOT_FOUND:
                    System.out.println("There is no such credit card!");
                    break;
                case Theater.SHOW_NOT_FOUND:
                    System.out.println("There is no show at this date!");
                    break;
                case Theater.SUCCEED:
                    System.out.println("You successfully bought tickets at regular price!");
                    break;
                    
                default:
                    System.out.println("There was an error:");
                    break;
            }
            if (!yesOrNo("Buy another ticket(s)?")) {
                break;
            }
        } while (true);
    	
    }
    
    
    /**
     * this method sells advanced tickets 
     * @throws ParseException 
     */
    public void sellAdvanceTicket() throws ParseException {
    	int result;
    	do {
            String customerID = getToken("Enter client id:");
            String cardNumber = getToken("Enter cardNumber: ");
            Calendar date = getDate("Enter date as mm/dd/yy:");
            String numTickets = getToken("Enter requested number of tickets:");
            Integer quantity=Integer.parseInt(numTickets);

            result = theater.sellAdvTickets(customerID, cardNumber,  date, quantity);

            switch (result) {
                case Theater.CUSTOMER_NOT_FOUND:
                    System.out.println("No such customer!");
                    break;
                case Theater.CARD_NOT_FOUND:
                    System.out.println("There is no such credit card!");
                    break;
                case Theater.SHOW_NOT_FOUND:
                    System.out.println("There is no show at this date!");
                    break;
                case Theater.INVALID_DATE:
                    System.out.println("Advanced tickets cannot be purchased in the same day all later");
                    break;
                case Theater.SUCCEED:
                    System.out.println("You successfully bought regular tickets");
                    break;
                    
                default:
                    System.out.println("There was an error:");
                    break;
            }
            if (!yesOrNo("Buy another advanced tickets?")) {
                break;
            }
        } while (true);
    	
    }


   
    
    /**
     * this method sells advanced student tickets 
     * @throws ParseException 
     */
    public void sellAdvanceStudentTicket() throws ParseException {
    	int result;
    	do {
            String customerID = getToken("Enter client id:");
            String cardNumber = getToken("Enter cardNumber: ");
            Calendar date = getDate("Enter date as mm/dd/yy:");
            String numTickets = getToken("Enter requested number of tickets:");
            Integer quantity=Integer.parseInt(numTickets);

            result = theater.sellAdvStudTickets(customerID, cardNumber,  date, quantity);

            switch (result) {
                case Theater.CUSTOMER_NOT_FOUND:
                    System.out.println("No such customer!");
                    break;
                case Theater.CARD_NOT_FOUND:
                    System.out.println("There is no such credit card!");
                    break;
                case Theater.SHOW_NOT_FOUND:
                    System.out.println("There is no show at this date!");
                    break;
                case Theater.INVALID_DATE:
                    System.out.println(" Student Advanced tickets cannot be purchased in the same day all later");
                    break;
                case Theater.SUCCEED:
                    System.out.println("You successfully bought student advanced tickets. \n Must show valid student id");
                    break;
                    
                default:
                    System.out.println("There was an error:");
                    break;
            }
            if (!yesOrNo("Buy another student advanced tickets?")) {
                break;
            }
        } while (true);
    	
    }
    
    
    /**
     * this method withdraws money from client balance
     */
    public void payClient() { 
    int result;
    do {
        String clientID = getToken("Enter client id:");
        String moneyRequest = getToken("Enter amount to withdraw:");
        double amount=Double.parseDouble(moneyRequest);
        result=theater.payForClient(clientID, amount);
        
        switch (result) {
            case Theater.CLIENT_NOT_FOUND:
                System.out.println("No such client!");
                break;
            case Theater.AMOUNT_EXCEEDS:
                System.out.println("Your requsted amount exceeds your balance ");
                break;
            case Theater.SUCCEED:
                System.out.println("You succesefully withdrew your money");
                break;
            default:
                System.out.println("There was an error:");
                break;
        }
        if (!yesOrNo("Do want to withdraw money again?")) {
            break;
        }
    } while (true);
}

    
    
    public void displayTickets() throws ParseException {
    	Iterator iter;
    	int numOfTickets=0;
    	
           
    	Calendar date = getDate("Enter date as mm/dd/yy:");
           
            iter=theater.getTickets();
            while(iter.hasNext()) {
            	Ticket ticket=(Ticket)iter.next();
            	Calendar ticketDate=ticket.getDate();
            	if (ticketDate.equals(date)) {
            		System.out.println(" " + ticket.toString());
            		numOfTickets++;
            	}
            	if(numOfTickets !=0) {
            		System.out.println("There are no more tickets  at this date " + date);
            	}else {
            		System.out.println("There are no tickets  at this date " + date);
            	}
            }
            

            
    		
    }
    
    
    
    /**
     * Orchestrates the whole process. Calls the appropriate method for the
     * different functionalities.
     * @throws ParseException 
     */
    public void process() throws ParseException {
        int command;
        help();
        while ((command = getCommand()) != EXIT) {
            switch (command) {
                case ADD_CLIENT:
                    addClient();
                    break;
                case REMOVE_CLIENT:
                    removeClient();
                    break;
                case LIST_CLIENTS:
                    getClientList();
                    break;
                case ADD_CUSTOMER:
                    addCustomer();
                    break;
                case ADD_CARD:
                    addCreditCard();
                    break;
                case REMOVE_CUSTOMER:
                    removeCustomer();
                    break;
                case REMOVE_CARD:
                    removeCard();
                    break;
                case LIST_CUSTOMERS:
                    getCustomers();
                    break;
                case ADD_SHOW:
                    addShow();
                    break;
                case LIST_SHOWS:
                    getShows();
                    break;
                case SAVE:
                    save();
                    break;
                case RETRIEVE:
                    retrieve();
                    break;
                case SELL_REGULAR_TICKETS:
                	sellRegularTicket();
                    break;
                case SELL_ADVANCE_TICKETS:
                	sellAdvanceTicket();
                case  SELL_STUDENT_ADVANCE_TICKETS:
                	sellAdvanceStudentTicket();
                case PAY_CLIENT:
                	payClient();
                    break;
                case DISPLAY_TICKETS:
                	displayTickets();
                    break;
                case HELP:
                    help();
                    break;
            }
        }
    }
    
    


  

    /**
     * The method to start the application. Simply calls process().
     *
     * @param args not used
     * @throws ParseException 
     */
    public static void main(String[] args) throws ParseException {
        UserInterface.instance().process();
    }
}
