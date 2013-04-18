/**
 * Creates the Assets(buildings) superclass required for game play.
 * 
 * @authors Denis Murphy, Ian Murphy & Bill O'Keeffe
 * 
 */
public class Asset
{
	String buildingName;
	String symbol;

	/**
	 * Constructor for objects of class Asset
	 * 
	 * @param BuildingName
	 *            The building name
	 * 
	 * @param symbol
	 *            The symbol to be used to represent the building types.
	 * 
	 */
	public Asset(String buildingName, String symbol) {
		this.buildingName = buildingName;
		this.symbol = symbol;
	}

	/**
	 * Setter change the building name.
	 * 
	 * @param buildingName
	 *            What the building should be set to.
	 */
	public void setBuidingName(String buildingName) {
		this.buildingName = buildingName;
	}

	/**
	 * Method that returns the name of a building .
	 * 
	 * @return The name of the building.
	 * 
	 */
	public String getBuildingName() {
		return buildingName;
	}

	/**
	 * Setter change the synbol type.
	 * 
	 * @param symbol
	 *            What the symbol should be set to.
	 */
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	/**
	 * Method that returns the symbol that represents a building .
	 * 
	 * @return The symbol that represents the building.
	 * 
	 */
	public String getSymbol() {
		return symbol;
	}

	/**
	 * * Method that returns all the symbol info in string form.
	 * 
	 * @return The symbol of the asset.
	 */
	@Override
	public String toString() {
		return symbol;
	}

}
