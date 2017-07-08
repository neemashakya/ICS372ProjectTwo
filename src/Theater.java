import java.io.*;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class Theater implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final int CLIENT_NOT_FOUND = 1;
    public static final int CLIENT_HAS_SHOW = 2;
    public static final int CLIENT_REMOVED = 3;
    public static final int CUSTOMER_NOT_FOUND = 4;
    public static final int CUSTOMER_REMOVED = 5;
    public static final int DUPLICATE_CARD = 6;
    public static final int CARD_ADDED = 7;
    public static final int CARD_NOT_FOUND = 8;
    public static final int CARD_REMOVED = 9;
    public static final int ONLY_CARD = 10;
    public static final int DATE_NOT_OPEN = 11;
    public static final int SHOW_ADDED = 12;
    public static final int SHOW_NOT_ADDED = 13;
    public static final int SUCCEED = 14;
    public static final int SHOW_NOT_FOUND = 15;
    public static final int INVALID_DATE = 16;
    public static final int AMOUNT_EXCEEDS = 17;

    private ClientList clientList;
    private CustomerList customerList;
    private ShowList showList;
    private CardList cardList;
    private TicketList ticketList;

    private static Theater theater;
    private List cards = new LinkedList();
    private List client = new LinkedList();

    /*
     * Private constructor to create singleton
     */
    private Theater() {
        cardList = CardList.instance();
        clientList = ClientList.instance();
        showList = ShowList.instance();
        customerList = CustomerList.instance();
    }

    /**
     * Theater singleton
     *
     * @return the Theater singleton object
     * <CODE></CODE>
     */
    public static Theater instance() {
        return theater == null ? (theater = new Theater()) : theater;
    }

    /**
     * @param name    <CODE>String Name</CODE>
     * @param address <CODE>String address</CODE>
     * @param phone   <CODE>String phone</CODE>
     * @return adds a client to the clientList if successful.
     */
    public Client addClient(String name, String address, String phone) {
        Client client = new Client(name, address, phone);
        if (clientList.insertClient(client)) {
            return (client);
        }
        return null;
    }


    /**
     * @param clientID <CODE>String clientID</CODE>
     * @return <b>Postcondition:</b>
     * returns a message to the user indicating the
     * return value of a client
     */
    public int removeClient(String clientID) {
        Client client = clientList.search(clientID);
        if (client == null) {
            return (CLIENT_NOT_FOUND);
        } else {
            clientList.removeClient(clientID);
            return (CLIENT_REMOVED);
        }
    }


    public Iterator getClients() {

        return clientList.getClients();

    }

    /**
     * @param name
     * @param address
     * @param phoneNumber
     * @param cardNumber
     * @param expiration
     * @return
     */

    public Customer addCustomer(String name, String address, String phoneNumber,
                                String cardNumber, String expiration) {
        CreditCard card = cardList.search(cardNumber);
        if (card == null) {
            String customerID;
            Customer customer = new Customer(name, address, phoneNumber);
            customerID = customer.getCustomerID();
            CreditCard newCard = new CreditCard(customerID, cardNumber, expiration);
            if (cardList.insertCard(newCard)) {
                if (customer.insertCard(newCard)) {
                    customerList.insertCustomer(customer);
                    return customer;
                } else {
                    cardList.removeCard(cardNumber);
                    return null;
                }
            } else {
                return null;
            }
        }
        return null;
    }

    /**
     * @param customerID
     * @return
     */
    public int removeCustomer(String customerID) {
        Customer customer = customerList.search(customerID);
        if (!(customer == null)) {
            for (Iterator iterator = customer.getCustomerCard(); iterator.hasNext(); ) {
                CreditCard creditCard = (CreditCard) iterator.next();
                cardList.removeCard(creditCard.getCardNumber());
            }
            customerList.removeCustomer(customerID);
            return CUSTOMER_REMOVED;
        }
        return CUSTOMER_NOT_FOUND;
    }

    /**
     * @param customerID
     * @param cardNumber
     * @param expiration
     * @return
     */
    public int addCreditCard(String customerID, String cardNumber,
                             String expiration) {
        Customer customer = customerList.search(customerID);
        if (!(customer == null)) {
            for (Iterator iterator = customer.getCustomerCard(); iterator.hasNext(); ) {
                CreditCard creditCard = (CreditCard) iterator.next();
                if (creditCard.getCardNumber().equals(cardNumber)) {
                    return DUPLICATE_CARD;

                }

            }
            CreditCard newCard = new CreditCard(customerID, cardNumber, expiration);
            if (cardList.insertCard(newCard)) {
                if (customer.insertCard(newCard)) {

                    return CARD_ADDED;
                } else {
                    cardList.removeCard(cardNumber);
                    return 0;
                }
            } else {
                return 0;
            }
        } else {
            return CUSTOMER_NOT_FOUND;
        }

    }

    /**
     * @param cardNumber
     * @return
     */
    public int removeCard(String cardNumber) {
        CreditCard newCard = cardList.search(cardNumber);
        if (!(newCard == null)) {
            String customerID = newCard.getCustomerID();
            Customer customer = customerList.search(customerID);
            if (!(customer == null)) {
                int cardNumResult = customer.cardCount();
                if (cardNumResult < 2) {
                    return ONLY_CARD;

                } else {
                    customer.removeCard(newCard);
                    cardList.removeCard(cardNumber);
                    return CARD_REMOVED;
                }


            } else {
                return 0;
            }


        } else {
            return CARD_NOT_FOUND;
        }

    }

    public Iterator getCustomers() {
        return customerList.getCustomers();
    }

    public int addShow(String clientID, String showName, Calendar startDate,
                       Calendar endDate, double regularPrice) {
        Client client = clientList.search(clientID);
        if (client != null) {
            if (showList.isDatesAvailable(startDate, endDate)) {
                Show show = new Show(showName, clientID, startDate, endDate, regularPrice);
                if (showList.insertShow(show)) {
                    return SHOW_ADDED;
                } else {
                    return 0;
                }

            } else {
                return DATE_NOT_OPEN;
            }

        } else {
            return CLIENT_NOT_FOUND;
        }
    }


    public Iterator getShows() {
        return showList.getShows();

    }

    /**
     * Serializes the Theater object
     *
     * @return true iff the data could be saved
     */
    public static boolean save() {
        try {
            FileOutputStream file = new FileOutputStream("TheaterData");
            ObjectOutputStream output = new ObjectOutputStream(file);
            output.writeObject(theater);
            output.writeObject(CreateIdServer.instance());
            return true;
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return false;
        }
    }


    /**
     * Retrieves a deserialized version of the theater from disk
     *
     * @return a Library object
     */
    public static Theater retrieve() {
        try {
            FileInputStream file = new FileInputStream("TheaterData");
            ObjectInputStream input = new ObjectInputStream(file);
            input.readObject();
            CreateIdServer.retrieve(input);
            return theater;
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return null;
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
            return null;
        }
    }


    /**
     * sells regular tickets
     *
     * @param customerID
     * @param cardNumber
     * @param date
     * @param quantity
     * @return
     */
    public int sellRegTickets(String customerID, String cardNumber, Calendar date, int quantity) {

        Customer customer = customerList.search(customerID);

        if (customer != null) {
            CreditCard card = customer.searchCard(cardNumber);

            if (card != null) {
                Show show = showList.checkShowByDate(date);

                if (show != null) {

                    String showName = show.getShowName();
                    double price = show.getRegularTicketPrice();
                    Ticket ticket = new RegularTicket(quantity, price, date);
                    this.ticketList.insertTicket(ticket);
                    customer.addTickets(ticket);
                    System.out.println("The total is: " + ticket.getTotal());
                    String clientID = show.getClientID();
                    Client client = clientList.search(clientID);
                    double clientMoney = ticket.getTotal() / 2;
                    System.out.println("The client monney: " + clientMoney);
                    client.addToBalance(clientMoney);
                    return SUCCEED;

                } else {
                    return SHOW_NOT_FOUND;

                }

            } else {
                return CARD_NOT_FOUND;
            }

        } else {
            return CUSTOMER_NOT_FOUND;
        }


    }


    /**
     * sells student advanced tickets
     *
     * @param customerID
     * @param cardNumber
     * @param date
     * @param quantity
     * @return
     */
    public int sellAdvTickets(String customerID, String cardNumber, Calendar date, int quantity) {
        Calendar currentDate = Calendar.getInstance();
        if (currentDate.equals(date)) {
            Customer customer = customerList.search(customerID);
            if (customer != null) {
                CreditCard card = customer.searchCard(cardNumber);
                if (card != null) {
                    Show show = showList.checkShowByDate(date);
                    if (show != null) {
                        String showName = show.getShowName();
                        double price = show.getAdvancedTicketPrice();
                        Ticket ticket = new AdvancedTicket(quantity, price, date);
                        this.ticketList.insertTicket(ticket);
                        customer.addTickets(ticket);
                        String clientID = show.getClientID();
                        Client client = clientList.search(clientID);
                        double clientMoney = ticket.getTotal() / 2;
                        client.addToBalance(clientMoney);
                        return SUCCEED;

                    } else {
                        return SHOW_NOT_FOUND;

                    }

                } else {
                    return CARD_NOT_FOUND;
                }

            } else {
                return CUSTOMER_NOT_FOUND;
            }


        } else {
            return INVALID_DATE;
        }


    }

    /**
     * sells regular tickets
     *
     * @param customerID
     * @param cardNumber
     * @param date
     * @param quantity
     * @return
     */
    public int sellAdvStudTickets(String customerID, String cardNumber, Calendar date, int quantity) {
        Calendar currentDate = Calendar.getInstance();
        if (currentDate.equals(date)) {
            Customer customer = customerList.search(customerID);
            if (customer != null) {
                CreditCard card = customer.searchCard(cardNumber);
                if (card != null) {
                    Show show = showList.checkShowByDate(date);
                    if (show != null) {
                        String showName = show.getShowName();
                        double price = show.getAdvancedTicketPrice();
                        Ticket ticket = new AdvancedTicket(quantity, price, date);
                        this.ticketList.insertTicket(ticket);
                        customer.addTickets(ticket);
                        String clientID = show.getClientID();
                        Client client = clientList.search(clientID);
                        double clientMoney = ticket.getTotal() / 2;
                        client.addToBalance(clientMoney);
                        return SUCCEED;

                    } else {
                        return SHOW_NOT_FOUND;

                    }

                } else {
                    return CARD_NOT_FOUND;
                }

            } else {
                return CUSTOMER_NOT_FOUND;
            }


        } else {
            return INVALID_DATE;
        }


    }


    public int payForClient(String clientID, double amountToPaid) {
        Client client = clientList.search(clientID);
        if (client != null) {
            double balance = client.getBalance();
            if (balance >= amountToPaid) {
                client.withdrawMoney(amountToPaid);
                return SUCCEED;

            } else {
                return AMOUNT_EXCEEDS;
            }

        } else {
            return CLIENT_NOT_FOUND;
        }
    }


    public Iterator getTickets(Calendar date) {
        return ticketList.getTicketsForDay(date);
    }


    /**
     * Writes the object to the output stream
     *
     * @param output <code>ObjectOutputStream output</code>
     *               the stream to be written to
     */
    private void writeObject(ObjectOutputStream output) {
        try {
            output.defaultWriteObject();
            output.writeObject(theater);
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    /**
     * Reads the object from a given stream
     *
     * @param input the stream to be read
     */
    private void readObject(ObjectInputStream input) {
        try {
            input.defaultReadObject();
            if (theater == null) {
                theater = (Theater) input.readObject();
            } else {
                input.readObject();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
