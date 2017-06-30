import java.io.Serializable;
import java.util.Calendar;

/**
 * This class creates a Show for a client holding a show name, clientID, and
 * start and end date for the show. creation
 * 
 * @author Matt Carlson, Jamison Czech, Slava Makharovich, Prashant Shrestha
 */

public class Show implements Serializable{

	private String showName;
	private String clientID;
	private Calendar startDate;
	private Calendar endDate;
	
	
	/**
	 * Creates a new Show
	 * @param showName
	 *
	 * @param clientID
	 *
	 * @param startDate
	 * 
	 * @param endDate
	 */
	public Show(String showName, String clientID, Calendar startDate,
			Calendar endDate) {
		this.showName = showName;
		this.clientID = clientID;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	
	

	/**
	 * Getter for the name of the show
	 * 
	 * @return a String of the show name
	 */
	public String getShowName() {
		return showName;
	}

	/**
	 * Getter for client ID for the show
	 * 
	 * @return a String of the client ID
	 */
	public String getClientID() {
		return clientID;
	}

	/**
	 * Getter for the start date of a show
	 * 
	 * @return a calendar of the start date
	 */
	public Calendar getStartDate() {
		return startDate;
	}

	/**
	 * Getter for the end date of a show
	 * 
	 * @return a calendar of the end date
	 */
	public Calendar getEndDate() {
		return endDate;
	}

	/**
	 * String representing a Show
	 * 
	 * @return
	 *   a string representing a Show
	 */
	@Override
	public String toString() {
		return "Show: " + getShowName() + ", Client ID: " + getClientID() + ", Start Date: "
				+ getStartDate() + ", End Date " + getEndDate();
	}
}
