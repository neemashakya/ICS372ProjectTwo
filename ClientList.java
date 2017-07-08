import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * The collection class for Client objects
 *
 * @author Matt Carlson, Jamison Czech, Slava Makharovich, Prashant Shrestha
 */

public class ClientList implements Serializable {


    private static ClientList clientList;
    private List clients = new LinkedList();

    /*
     * Private constructor to create singleton
     */
    private ClientList() {
    }

    /**
     * ClientList singleton
     * @return
     *   the ClientList singleton object
     */
    public static ClientList instance() {
        return clientList == null ? (clientList = new ClientList()) : clientList;
    }

   
    /**
     * Returns an iteration for all
     * of the clients.
     */
    public Iterator getClients() {
        Iterator result = clients.iterator();
        if (clients != null && !clients.isEmpty()) {
            System.out.println("The Clients are: ");
            while (result.hasNext()) {
                System.out.println(result.next());
            }
        }


        return result;
    }

    /**
     * Adds a Client to the collection
     * @param client
     *   <CODE>Client client</CODE>
     * @return
     *   a boolean indicating successful addition to collection
     */
    public boolean insertClient(Client client) {
        clients.add(client);
        return true;
    }

    /**
     * searches for a client in the collection
     * @param clientID
     *   <CODE>String ClientID</CODE>
     * @return client
     */
    public Client search(String clientID) {
        for (Iterator iterator = clients.iterator(); iterator.hasNext(); ) {
            Client client = (Client) iterator.next();
            if (client.getClientID().equals(clientID)) {
                return client;
            }
        }
        return null;
    }


    /**
     * Removes a client with the given clientID from the collection.
     * @param clientID
     *   string clientID
     * @return true
     *   If Client exists in the collection,
     *   or false otherwise.
     */
    public boolean removeClient(String clientID) {
        Client client = search(clientID);
        
        if (client == null) {
            return false;
        } else {
            return clients.remove(client);
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
            output.writeObject(clientList);

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
            if (clientList != null) {
                return;
            } else {
                input.defaultReadObject();
                if (clientList == null) {
                    clientList = (ClientList) input.readObject();
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
     * String of the client
     */
    @Override
    public String toString() {
        return clients.toString();
    }

}
