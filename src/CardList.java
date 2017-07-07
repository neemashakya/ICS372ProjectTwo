import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


/**
 * This class creates a Show for a client holding a show name, clientID, and
 * start and end date for the show. creation
 *
 * @author Matt Carlson, Jamison Czech, Slava Makharovich, Prashant Shrestha
 */

public class CardList implements Serializable {


    private static CardList cardList;
    private List cards = new LinkedList();

    /*
     * Private constructor to create singleton
     */
    private CardList() {
    }

    /**
     * CardList singleton
     *
     * @return the CardList singleton object
     */
    public static CardList instance() {
        if (cardList == null) {
            return (cardList = new CardList());
        } else {
            return cardList;
        }
    }


    /**
     * Adds a CreditCard to the collection
     * @param creditCard
     * @return a boolean indicating successful addition to collection
     */
    public boolean insertCard(CreditCard creditCard) {
        cards.add(creditCard);
        return true;
    }

    /**
     * searches for a credit card in the collection
     *
     * @param cardNumber
     * @return a CreditCard if found or null if not found
     */
    public CreditCard search(String cardNumber) {
        if(cards.size() > 0) {
            for (Iterator iterator = cards.iterator(); iterator.hasNext(); ) {
                CreditCard creditCard = (CreditCard) iterator.next();
                if (creditCard.getCardNumber().equals(cardNumber)) {
                    return creditCard;
                }
            }
        }

        return null;
    }

    /**
     * Getter for collection of cards
     *
     * @return an iterator for the card collection
     */
    public Iterator getCards() {
        //ToDO finish getCardList
        return cards.iterator();
    }

    /**
     * removes a card with the given cardNumber from the collection
     *
     * @param cardNumber
     * @return true if CreditCard exists in the collection, or false otherwise
     */
    public boolean removeCard(String cardNumber) {
        CreditCard creditCard = search(cardNumber);
        if (creditCard == null) {
            return false;
        } else {
            return cards.remove(creditCard);
        }
    }

    /**
     * write objects for serialization
     *
     * @param output stream
     */
    private void writeObject(ObjectOutputStream output) {
        try {
            //output.defaultWriteObject();
            output.writeObject(cardList);
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    /**
     * read serialized object
     *
     * @param input stream
     */
    private void readObject(ObjectInputStream input) {
        try {
            if (cardList != null) {
                return;
            } else {
                //input.defaultReadObject();
                if (cardList == null) {
                    cardList = (CardList) input.readObject();
                } else {
                    input.readObject();
                }
            }
        } catch (IOException ioe) {
            System.out.println("in CardList readObject \n" + ioe);
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
    }


    /**
     * String of the card
     */
    @Override
    public String toString() {
        return cards.toString();
    }

}