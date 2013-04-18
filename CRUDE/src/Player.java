/**
 * Class that contains info on a player.
 * 
 * @authors Denis Murphy, Ian Murphy & Bill O'Keeffe
 * 
 */

public class Player
{

	static final int ROWS = 6;
	static final int COLS = 6;
	static final int START_ASSETS = 0;

	String playerName;
	int playerNumber;
	int balance;
	int oilBarrels;
	int gasBarrels;
	int nmrRefineries;
	int nmrOilWells;
	int nmrOilRigs;
	int nmrStations;
	Asset[][] playerGrid;

	/**
	 * Constructor for objects of class player.
	 * 
	 * @param playerName
	 *            The player's name.
	 * @param playerNumber
	 *            The player's number.
	 * @param balance
	 *            The balance a player is to start off with.
	 * @param oilBarrels
	 *            Amount of oil barrels to start with.
	 * @param gasBarrels
	 *            Amount of petrol barrels to start with.
	 */
	public Player(String playerName, int playerNumber, int balance,
			int oilBarrels, int gasBarrels) {
		this.playerName = playerName;
		this.playerNumber = playerNumber;
		this.balance = balance;
		this.oilBarrels = oilBarrels;
		this.gasBarrels = gasBarrels;
		this.nmrOilRigs = START_ASSETS;
		this.nmrStations = START_ASSETS;
		this.nmrRefineries = START_ASSETS;
		this.playerGrid = new Asset[ROWS][COLS];
		this.nmrOilWells = START_ASSETS;
	}

	/**
	 * Setter to change the name of a player.
	 * 
	 * @param playerName
	 *            What the player should be set to.
	 */
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	/**
	 * Method that returns the name of a player.
	 * 
	 * @return The name of the player.
	 */
	public String getPlayerName() {
		return playerName;
	}

	/**
	 * A method that can be used to set the player's number.
	 * 
	 * @param playerNumber
	 *            the playerNumber to set
	 */
	public void setPlayerNumber(int playerNumber) {
		this.playerNumber = playerNumber;
	}

	/**
	 * Getter that returns the player's number.
	 * 
	 * @return The player's number.
	 */
	public int getPlayerNumber() {
		return playerNumber;
	}

	/**
	 * Setter to change the current balance of a player.
	 * 
	 * @param balance
	 *            The balance to set
	 */
	public void setBalance(int balance) {
		if (balance >= 0) {
			this.balance = balance;
		}
	}

	/**
	 * Getter that returns the current balance a player has.
	 * 
	 * @return The balance a player currently has.
	 */
	public int getBalance() {
		return balance;
	}

	/**
	 * Setter to change the amount of oil barrels that a player currently has.
	 * 
	 * @param oilBarrels
	 *            The oil barrels to set.
	 */
	public void setOilBarrels(int oilBarrels) {
		if (oilBarrels >= 0) {
			this.oilBarrels = oilBarrels;
		}
	}

	/**
	 * Method that returns the amount of oil barrels a player currently has.
	 * 
	 * @return The amount of oil barrels a player has.
	 */
	public int getOilBarrels() {
		return oilBarrels;
	}

	/**
	 * Setter to change the amount of petrol barrels that a user has.
	 * 
	 * @param gasBarrels
	 *            The new amount of barrels to be set.
	 */
	public void setGasBarrels(int gasBarrels) {
		if (gasBarrels >= 0) {
			this.gasBarrels = gasBarrels;
		}
	}

	/**
	 * Method that returns the amount of barrels a player has in his/her
	 * inventory.
	 * 
	 * @return The amount of petrol barrels a player has.
	 */
	public int getGasBarrels() {
		return gasBarrels;
	}

	/**
	 * Setter to change the player grid.
	 * 
	 * @param playerGrid
	 *            the playerGrid to set
	 */
	public void setPlayerGrid(Asset[][] playerGrid) {
		this.playerGrid = playerGrid;
	}

	/**
	 * Returns the player grid
	 * 
	 * @return The Players Grid
	 */
	public Asset[][] getPlayerGrid() {
		return playerGrid;
	}

	/**
	 * Method that prints out the player's grid. Each asset the player holds is
	 * represented on the grid as a single letter.
	 * 
	 */
	public void printGrid() {
		StdOut.println("___|__1__|__2__|__3__|__4__|__5__|__6__|");
		for (int i = 0; i < playerGrid.length; i += 1) {

			StdOut.print("_" + (i + 1) + "_|__");

			for (int j = 0; j < playerGrid[i].length; j += 1) {
				if (playerGrid[i][j] == null) {
					if (j == playerGrid[j].length - 1) {
						StdOut.print("0__|");
					} else {
						StdOut.print("0");
					}
				} else {
					if (j == playerGrid[j].length - 1) {
						StdOut.print(playerGrid[i][j] + "__|");
					} else {
						StdOut.print(playerGrid[i][j]);
					}
				}

				if (j < playerGrid[i].length - 1) {
					StdOut.print("__|__");
				}
			}
			StdOut.println();
		}
		StdOut.println();
	}

	/**
	 * Method that returns all the players info in string form.
	 * 
	 * @return Object info contained within a string.
	 */
	@Override
	public String toString() {
		return ("Player Name: " + playerName + "/n" + "Balance: " + balance
				+ "million" + "/n" + "Oil Barrels: " + oilBarrels + "/n"
				+ "Gas Barrels: " + gasBarrels + "/n");
	}

	/**
	 * Method that prints an inventory of the player's info.
	 * 
	 */
	public void printInfo() {
		StdOut.println();
		StdOut.println("--------------------");
		StdOut.println("INVENTORY");
		StdOut.println("--------------------");
		StdOut.println("Balance: " + playerName);
		StdOut.println("Oil Barrels: " + oilBarrels);
		StdOut.println("Gas Barrels: " + gasBarrels);
		StdOut.println("Number of Oil Rigs: " + nmrOilRigs);
		StdOut.println("Number of Gas Stations: " + nmrStations);
		StdOut.println("Number of Refineries: " + nmrRefineries);
		StdOut.println("Number of Oil Wells: " + nmrOilWells);
		StdOut.println();
	}

	/**
	 * @return the oilRigs
	 */
	public int getOilRigs() {
		return nmrOilRigs;
	}

	/**
	 * @param oilRigs
	 *            the oilRigs to set
	 */
	public void setOilRigs(int oilRigs) {
		this.nmrOilRigs = oilRigs;
	}

	/**
	 * @return the nmrStations
	 */
	public int getNmrStations() {
		return nmrStations;
	}

	/**
	 * @param nmrStations
	 *            the nmrStations to set
	 */
	public void setNmrStations(int nmrStations) {
		this.nmrStations = nmrStations;
	}

	/**
	 * @return the nmrRefineries
	 */
	public int getNmrRefineries() {
		return nmrRefineries;
	}

	/**
	 * @param nmrRefineries
	 *            the nmrRefineries to set
	 */
	public void setNmrRefineries(int nmrRefineries) {
		this.nmrRefineries = nmrRefineries;
	}
}
