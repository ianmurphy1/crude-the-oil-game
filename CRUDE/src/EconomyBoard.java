/**
 * 
 * Class that sets up and is used to control aspects of the economy board of the
 * game
 * 
 * @authors Denis Murphy, Ian Murphy & Bill O'Keeffe
 * 
 */
public class EconomyBoard
{

	static final int START_WELL_PRICE = 5;
	
	int consumerMarketPrice;
	int domGasPrice;
	int domOilPrice;
	int forGasPrice;
	int forOilPrice;
	int wellPrice;
	/**
	 * 
	 * Constructor for objects of class Economy Board.
	 * 
	 * @param consumerMarketPrice
	 *            The amount that gas can be sold on the consumer market for
	 * @param domGasPrice
	 *            The price of gas on the domestic market.
	 * @param domOilPrice
	 *            The price of oil on the domestic market.
	 * @param forGasPrice
	 *            The price of gas on the foreign market.
	 * @param forOilPrice
	 *            The price of oil on the foreign market.
	 */
	public EconomyBoard(int consumerMarketPrice, int domGasPrice,
			int domOilPrice, int forGasPrice, int forOilPrice) {

		this.consumerMarketPrice = consumerMarketPrice;
		this.domGasPrice = domGasPrice;
		this.domOilPrice = domOilPrice;
		this.forGasPrice = forGasPrice;
		this.forOilPrice = forOilPrice;
		this.wellPrice   = START_WELL_PRICE;
	}

	/**
	 * Setter to change the price that a player can sell petrol on the consumer
	 * market for.
	 * 
	 * @param consumerMarketPrice
	 *            The new selling price of petrol.
	 */
	public void setConsumerMarketPrice(int consumerMarketPrice) {

		if ((consumerMarketPrice >= 0) && ((consumerMarketPrice % 2) == 0)) {
			this.consumerMarketPrice = consumerMarketPrice;
		}
	}

	/**
	 * Getter to return the current price that petrol can be sold on the market
	 * for.
	 * 
	 * @return Sale price of petrol.
	 */
	public int getConsumerMarketPrice() {
		return consumerMarketPrice;
	}

	/**
	 * Setter that be called to set a new price for the price of gas on the
	 * domestic market.
	 * 
	 * @param domGasPrice
	 *            The new price of oil.
	 */
	public void setDomGasPrice(int domGasPrice) {
		if ((domGasPrice >= 0) && ((domGasPrice % 2) == 0)) {
			this.domGasPrice = domGasPrice;
		}
	}

	/**
	 * Method to return the current price of petrol to buy on the domestic
	 * market.
	 * 
	 * @return The price of petrol on domestic market.
	 */
	public int getDomGasPrice() {
		return domGasPrice;
	}

	/**
	 * Setter to set the current price of oil on the domestic market.
	 * 
	 * @param domOilPrice
	 *            The new price of oil barrels.
	 */
	public void setDomOilPrice(int domOilPrice) {
		this.domOilPrice = domOilPrice;
	}

	/**
	 * Method to return the price that oil can be bought for on the domestic
	 * market.
	 * 
	 * @return The price of oil on the domestic market.
	 */
	public int getDomOilPrice() {
		return domOilPrice;
	}

	/**
	 * Getter that returns the current price of petrol on the foreign market.
	 * 
	 * @return Foreign market petrol price.
	 */
	public int getForGasPrice() {
		return forGasPrice;
	}

	/**
	 * Method to set the current price of oil on the foreign market.
	 * 
	 * @param forOilPrice
	 */
	public void setForOilPrice(int forOilPrice) {
		this.forOilPrice = forOilPrice;
	}

	/**
	 * Method to return the current price of oil on the foreign market
	 * 
	 * @return The Current Price of Oil
	 */
	public int getForOilPrice() {
		return forOilPrice;
	}

	/**
	 * Lists all information contained in the EconomyBoard Class and returns it
	 * in String form.
	 * 
	 * @return Object info as a string
	 */
	@Override
	public String toString() {
		return ("Consumer Market Price: " + consumerMarketPrice + "/n"
				+ "Domestic Petrol Price : " + domGasPrice + "/n"
				+ "Domestic Oil Price: " + domOilPrice + "/n"
				+ "Foreign Petrol Price: " + forGasPrice + "/n"
				+ "Foreign Oil Price: " + forOilPrice + "/n");
	}
}
