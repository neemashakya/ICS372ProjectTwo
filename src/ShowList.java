import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * The collection class for Show objects
 * 
 * @author Matt Carlson, Jamison Czech, Slava Makharovich, Prashant Shrestha
 */

public class ShowList implements Serializable {

	private static ShowList showList;
	private List shows = new LinkedList();

	/*
	 * Private constructor to create singleton
	 */
	private ShowList() {
	}

	/**
	 * ShowList singleton
	 * @return the ShowList singleton object
	 */
	public static ShowList instance() {
		if (showList == null) {
			return (showList = new ShowList());
		} 
		else {
			return showList;
		}
	}
	

	/**
	 * Adds a Show to the collection
	 * @param newShow
	 *   Show newSHow
	 * @return
	 *   a boolean indicating successful addition to collection
	 */
	public boolean insertShow(Show newShow) {
		shows.add(newShow);
		return true;
	}
	
	/**
	 * searches for a show in the collection
	 * @param showName
	 *   String showName
	 * @return a show if found or null if not found
	 */
	public Show search(String showName) {
	    for (Iterator iterator = shows.iterator(); iterator.hasNext(); ) {
	      Show show = (Show) iterator.next();
	      if (show.getShowName().equals(showName)) {
	        return show;
	      }
	    }
	    return null;
	  }


    /**
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public boolean isDatesAvailable(Calendar startDate, Calendar endDate){
    	Date start;
    	Date end;
    	Iterator iter=this.getShows();
    	while(iter.hasNext()) {
    		Show show=(Show) iter.next();
    		start=(Date) show.setStartDate(startDate);
    		end=(Date) show.setStartDate(endDate);
    		if((show.isWithinRange(start)) || (show.isWithinRange(end))) {
    			return false;
    			
    		}
    		
    	}


        return true;
    }


    /**
     * Returns an iteration for all
     * of the customers and their cards.
     */
    public Iterator getShows() {
        //ToDO fix show output to screen
        Iterator result = shows.iterator();
        if (shows != null && !shows.isEmpty()) {
            System.out.println("The Shows are: ");
            while (result.hasNext()) {
                System.out.println(result.next());
            }
        }


        return result;
    }

	/**
	 * removes a show with the given showName from the collection
	 * @param showName
	 * String showName
	 * @return true if Show exists in the collection, or false otherwise
	 */
	public boolean removeShow(String showName) {

		Show showSearch = search(showName);
		if (showSearch == null) {
			return false;
		}
		else {
			return shows.remove(showName);
		}
	}
	
	/**
	 * write objects for serialization
	 * @param output stream
	 */
	private void writeObject(ObjectOutputStream output) {
	    try {
	      output.defaultWriteObject();
	      output.writeObject(showList);
	    } 
	    catch(IOException ioe) {
	      System.out.println(ioe);
	    }
	  }
	
	  /**
	   * read serialized object
	   * @param input stream
	   */
	private void readObject(ObjectInputStream input) {
		try {
			if (showList != null) {
				return;
			} 
			else {
				input.defaultReadObject();
				if (showList == null) {
					showList = (ShowList) input.readObject();
				} 
				else {
					input.readObject();
				}
			}
		} 
		catch(IOException ioe) {
			System.out.println("in ShowList readObject \n" + ioe);
		} 
		catch(ClassNotFoundException cnfe) {
				cnfe.printStackTrace();
		}
	}
	/**
	 * check if there is show in the certain day
	 * @param date
	 * @return
	 */
	public Show checkShowByDate(Calendar date) {
		Iterator iter=this.getShows();
		while(iter.hasNext()) {
			Show show=(Show)iter.next();
			Date showDate= (Date) show.endStartDate(date);
			if(show.isWithinRange(showDate)) {
				return show;
			}
		}
		return null;
	}
	
	/**
	 * String of the show
	 */
	@Override
	public String toString() {
		return shows.toString();
	}

}
