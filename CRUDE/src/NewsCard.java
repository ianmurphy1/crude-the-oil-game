/**
 * This is the News Card class that stores and maintains the info of the News
 * Card
 * 
 * @authors Denis Murphy, Ian Murphy & Bill O'Keeffe
 * 
 */

public class NewsCard
{

	String name;
	String description;
	int newsid;

	/**
	 * A constructor for object of class News Card
	 * 
	 * @param name
	 *            The News Card name
	 * @param newsid
	 *            The News Card ID
	 * @param description
	 *            The News Card Description
	 */
	public NewsCard(String name, int newsid, String description) {
		this.name = name;
		this.description = description;
		this.newsid = newsid;

	}

	/**
	 * A getter that returns the news Cards ID
	 * 
	 * @return the news id
	 */
	public int getNewsid() {
		return newsid;
	}

	/**
	 * Method to return the cards id.
	 * 
	 * @param id
	 *            the news id to set
	 */
	public void setNewsid(int newsid) {
		this.newsid = newsid;
	}

	/**
	 * A getter that returns the description of the News Card
	 * 
	 * @return The description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * A setter that sets the news Card Description
	 * 
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * A getter that returns the name of a News card
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * A setter that sets the name of the News Card
	 * 
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * A method that returns a string version of object news card
	 * 
	 * @return The card variables in string form.
	 */
	@Override
	public String toString() {
		return (" News Card name: " + name + "/n" + " News card Description: "
				+ description + "/n" + " News card id: " + newsid);
	}
}
