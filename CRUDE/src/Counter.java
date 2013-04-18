/**
 * Counter class that keeps track of a counter that can be reset.
 * Both the starting point and the limit is to be defined by the user
 * at creation of the object.
 * 
 * The point of reset happens when count goes equal to or above the 
 * limit that was set. 
 * 
 * @authors Denis Murphy, Ian Murphy & Bill O'Keeffe
 *
 */
public class Counter {
	
	private int limit;
	private int startingPoint;
	private int count;
	private boolean turnedOver;
	
	/**
	 * Constructor for objects of class Counter. 
	 * 
	 * @param limit The limit the counter goes to, exclusive.
	 * @param startingPoint Number the counter is to be rest to.
	 */
	public Counter(int startingPoint, int limit) {
		this.limit = limit;
		this.startingPoint = startingPoint;
		this.count = startingPoint;
	    this.turnedOver = false;
	}
	
	/**
	 * Accessor that returns the limit that the counter has been set to.
	 * 
	 * @return The limit
	 */
	public int getLimit() {
		return limit;
	}
	
	/**
	 * Setter that allows the limit of the counter to be set
	 * 
	 * @param limit The limit to set.
	 */
	public void setLimit(int limit) {
		this.limit = limit;
	}
	
	/**
	 * Getter that returns the starting point that a counter has been at,
	 * 
	 * @return The starting point of the counter.
	 */
	public int getStartingPoint() {
		return startingPoint;
	}
	
	/**
	 * Accessor method that allows the starting point of a counter to be
	 * changed.
	 * 
	 * @param startingPoint Value the starting point should be set at.
	 */
	public void setStartingPoint(int startingPoint) {
		this.startingPoint = startingPoint;
	}
	
	
	/**
	 * Method that returns the value that the counter is currently at.
	 * 
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * Method that allows the count to be set a new value.
	 * 
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}	
	
	/**
	 * @return the turnedOver
	 */
	public boolean isTurnedOver() {
		return turnedOver;
	}

	/**
	 * @param turnedOver the turnedOver to set
	 */
	public void setTurnedOver(boolean turnedOver) {
		this.turnedOver = turnedOver;
	}

	/**
	 * Method that increments the counter by 1.
	 */
	public void increment() {
		if ((count + 1) < limit) {
			count = (count + 1) % limit;
		} 
		else if ((count + 1) >= limit) {
			count = startingPoint;
			turnedOver = true;
		}		
	}
	
	/**
	 * Method that increases the counter by the amount set but resets
	 * to the counters starting point once it reaches the limit.
	 * 
	 * @param amount The amount to increase the counter by.
	 */
	public void increaseBy(int amount) {
		if ((count + amount) < limit) {
			count = (count + amount) % limit;
		} 
		else if ((count + amount) >= limit) {
			count = startingPoint;
			turnedOver = true;
		}		
	}
}
