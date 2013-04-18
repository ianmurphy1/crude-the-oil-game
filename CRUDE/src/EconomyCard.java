/**
 * This is the Economy Card class that stores and maintains the prices that
 * assets are to be bought and sold for.
 * 
 * @authors Denis Murphy, Ian Murphy & Bill O'Keeffe
 * 
 */
public class EconomyCard
{

	String name;
	int demandChange;
	int stationPurPrice;
	int stationLiqPrice;
	int rigPurPrice;
	int rigLiqPrice;
	int refinePurPrice;
	int refineLiqPrice;
	int wellLiqPrice;

	/**
	 * Constructor for objects of type economy card.
	 * 
	 * 
	 * @param name
	 *            The name of the Economy Card
	 * @param demandChange
	 *            The Demand Change value on the Economy Card
	 * @param stationPurPrice
	 *            The Gas Station purchase price listed on the Economy Card
	 * @param stationLiqPrice
	 *            The Gas Station liquidation purchase price listed on the
	 *            Economy Card
	 * @param rigPurPrice
	 *            The Drilling Rig purchase price listed on the Economy Card
	 * @param rigLiqPrice
	 *            The Drilling Rig liquidation purchase price listed on the
	 *            Economy Card
	 * @param refinePurPrice
	 *            The Refinery purchase price listed on the Economy Card
	 * @param refineLiqPrice
	 *            The Refinery purchase price listed on the Economy Card
	 * @param wellLiqPrice
	 *            The oil Well liquidation purchase price listed on the Economy
	 *            Card
	 */
	public EconomyCard(String name, int demandChange, int stationPurPrice,
			int stationLiqPrice, int rigPurPrice, int rigLiqPrice,
			int refinePurPrice, int refineLiqPrice, int wellLiqPrice)

	{
		this.name = name;
		this.demandChange = demandChange;
		this.stationPurPrice = stationPurPrice;
		this.stationLiqPrice = stationLiqPrice;
		this.rigPurPrice = rigPurPrice;
		this.rigLiqPrice = rigLiqPrice;
		this.refinePurPrice = refinePurPrice;
		this.refineLiqPrice = refineLiqPrice;
		this.wellLiqPrice = wellLiqPrice;
	}

	/**
	 * A method that returns the name of the current Economy Card
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * A Setter that sets the name of the economy card
	 * 
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * A method that returns the current economic demand change
	 * 
	 * @return the demandChange
	 */
	public int getDemandChange() {
		return demandChange;
	}

	/**
	 * A setter that sets the new economic demand change
	 * 
	 * @param demandChange
	 *            the demandChange to set
	 */
	public void setDemandChange(int demandChange) {
		this.demandChange = demandChange;
	}

	/**
	 * A method that returns the current gas station purchase price
	 * 
	 * @return the stationPurPrice
	 */
	public int getStationPurPrice() {
		return stationPurPrice;
	}

	/**
	 * A setter that sets the new gas station purchase price
	 * 
	 * @param stationPurPrice
	 *            the stationPurPrice to set
	 */
	public void setStationPurPrice(int stationPurPrice) {
		if (stationPurPrice >= 0) {
			this.stationPurPrice = stationPurPrice;
		}

	}

	/**
	 * A method that returns the current gas station liquidation price
	 * 
	 * @return the stationLiqPrice
	 */
	public int getStationLiqPrice() {
		return stationLiqPrice;
	}

	/**
	 * A setter that sets the gas station liquidation price
	 * 
	 * @param stationLiqPrice
	 *            the stationLiqPrice to set
	 */
	public void setStationLiqPrice(int stationLiqPrice) {
		if (stationLiqPrice >= 0)
			this.stationLiqPrice = stationLiqPrice;
	}

	/**
	 * A method that returns the current purchase price of an oil rig
	 * 
	 * @return the rigPurPrice
	 */
	public int getRigPurPrice() {
		return rigPurPrice;
	}

	/**
	 * A setter that sets the new purchase price of the oil rig
	 * 
	 * @param rigPurPrice
	 *            the rigPurPrice to set
	 */
	public void setRigPurPrice(int rigPurPrice) {
		if (rigPurPrice >= 0)
			this.rigPurPrice = rigPurPrice;
	}

	/**
	 * A method that returns the current oil rig liquidation price
	 * 
	 * @return the rigLiqPrice
	 */
	public int getRigLiqPrice() {
		return rigLiqPrice;
	}

	/**
	 * A setter that sets the new liquidation price of the oil rig
	 * 
	 * @param rigLiqPrice
	 *            the rigLiqPrice to set
	 */
	public void setRigLiqPrice(int rigLiqPrice) {
		if (rigLiqPrice >= 0) {
			this.rigLiqPrice = rigLiqPrice;
		}
	}

	/**
	 * A method that returns the current oil Refinery purchase price
	 * 
	 * @return the refinePurPrice
	 */
	public int getRefinePurPrice() {
		return refinePurPrice;
	}

	/**
	 * A setter that sets the new oil refinery purchase price
	 * 
	 * @param refinePurPrice
	 *            the refinePurPrice to set
	 */
	public void setRefinePurPrice(int refinePurPrice) {
		if (refinePurPrice >= 0) {
			this.refinePurPrice = refinePurPrice;
		}
	}

	/**
	 * A method that returns the current oil Refinery liquidation price
	 * 
	 * @return the refineLiqPrice
	 */
	public int getRefineLiqPrice() {
		return refineLiqPrice;
	}

	/**
	 * A Setter to set the new liquidation price for the oil refinery
	 * 
	 * @param refineLiqPrice
	 *            the refineLiqPrice to set
	 */
	public void setRefineLiqPrice(int refineLiqPrice) {
		if (refineLiqPrice >= 0) {
			this.refineLiqPrice = refineLiqPrice;
		}
	}

	/**
	 * A method that returns the oil well liquidation price
	 * 
	 * @return the wellLiqPrice
	 */
	public int getWellLiqPrice() {

		return wellLiqPrice;
	}

	/**
	 * A setter that sets the new oil well liquidation price
	 * 
	 * @param wellLiqPrice
	 *            the wellLiqPrice to set
	 */
	public void setWellLiqPrice(int wellLiqPrice) {
		if (wellLiqPrice >= 0) {
			this.wellLiqPrice = wellLiqPrice;
		}
	}

	/**
	 * Method that prints all the prices to the screen.
	 * 
	 */
	public void printPrices() {
		StdOut.println();
		StdOut.println("----------------");
		StdOut.println("PRICE LIST");
		StdOut.println("----------------");
		StdOut.println("Station Price: " + stationPurPrice);
		StdOut.println("Drill Rig Price: " + rigPurPrice);
		StdOut.println("Refinery Price: " + refinePurPrice);
	}

	/**
	 * Method that prints the current liquidation values of the assets.
	 * 
	 */
	public void printLiqValues() {
		StdOut.println();
		StdOut.println("----------------");
		StdOut.println("LIQUIDATION VALUE LIST");
		StdOut.println("----------------");
		StdOut.println("Station Liquidation Amount: " + stationLiqPrice);
		StdOut.println("Drill Rig Liquidation Amount: " + rigLiqPrice);
		StdOut.println("Refineery Liquidation Amount: " + refineLiqPrice);
		StdOut.println("Well Liquidation Amount: " + wellLiqPrice);
	}

	/*
	 * A method that returns the string version of the Economy card
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "EconomyCard [name=" + name + ", demandChange=" + demandChange
				+ ", stationPurPrice=" + stationPurPrice + ", stationLiqPrice="
				+ stationLiqPrice + ", rigPurPrice=" + rigPurPrice
				+ ", rigLiqPrice=" + rigLiqPrice + ", refinePurPrice="
				+ refinePurPrice + ", refineLiqPrice=" + refineLiqPrice
				+ ", wellLiqPrice=" + wellLiqPrice + "]";
	}
}
