import java.util.ArrayList;
import java.util.Collections;

/**
 * Game class where game is created and run from.
 * 
 * @author Denis Murphy, Ian Murphy & Bill O'Keeffe
 * 
 */
/**
 * @author Ian
 *
 */
public class Game
{
	// Game Constants
	static final int MAX_PLAYERS = 4;
	static final int START_MONEY = 200;
	static final int START_CON_PRICE = 26;
	static final int START_GAS_PRICE = 16;
	static final int START_OIL_PRICE = 6;	
	static final int START_BARRELS = 2;
	static final int E_COUNT_START = 0;
	static final int E_COUNT_LIMIT = 8;
	static final int PT_COUNT_START = 0; //Using it to grab the player at index
	static final int PT_COUNT_LIMIT = 4; //in the counter.
	static final int PC_COUNT_START = 1;
	static final int PC_COUNT_LIMIT = (4 + 1); // '+ 1' due to counter rolling
												// over once a value equals the
												// limit

	// Game Variables	
	int nmrWells;

	Dice redDie;
	Dice blackDie;
	Dice greenDie;

	ArrayList<Player> thePlayers;
	// Player that keeps track of the current player.
	Player cPlayer;
	// Player object used to store a player who claims victory
	Player chancer;

	ArrayList<EconomyCard> ecoCards;
	EconomyCard recovery;
	EconomyCard depression;
	EconomyCard rapidGrowth;
	EconomyCard prosperity;
	EconomyCard expansion;
	EconomyCard downturn;
	EconomyCard recession;
	// Card that keeps track of the current economic state
	EconomyCard cEcoCard;

	ArrayList<NewsCard> newsCards;
	NewsCard debtReductionTax;
	NewsCard globalWarming;
	NewsCard salesTax;
	NewsCard longWinter;
	NewsCard inspection;
	NewsCard uncertainty;
	// Cards that keep track of the news cards that can be drawn
	NewsCard lessLikely;
	NewsCard moreLikely;

	Counter newsCount;
	Counter playerTurn;
	Counter phaseCount;
	// Keeps track of the counter responsible for the changing of the economy
	Counter ecoCounter;

	EconomyBoard eBoard;
	boolean boughtf;
	boolean boughtd;

   /**
	* Constructor of objects of class game.
	* 
	*/
	public Game() {
		this.thePlayers = new ArrayList<Player>();
		this.ecoCards = new ArrayList<EconomyCard>();
		this.newsCards = new ArrayList<NewsCard>();
		this.boughtd = false;
		this.boughtf = false;
	}

	/**
	 * Main method, starts the game off by calling the run method.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Game app = new Game();
		app.createGameObjects();
		app.run();
	}

	/**
	 * Method that creates all objects needed within the game.
	 */
	public void createGameObjects() {
		// Create player turn counter
		playerTurn = new Counter(PT_COUNT_START, PT_COUNT_LIMIT);

		// Create phase counter
		phaseCount = new Counter(PC_COUNT_START, PC_COUNT_LIMIT);

		// Sets number of wells		
		nmrWells = 0;

		// Create Dice for the game
		redDie = new Dice();
		blackDie = new Dice();
		greenDie = new Dice();

		// Create the economy board
		eBoard = new EconomyBoard(START_CON_PRICE, START_GAS_PRICE,
				START_OIL_PRICE, START_GAS_PRICE, START_OIL_PRICE);

		// Create counter for economy board
		ecoCounter = new Counter(E_COUNT_START, E_COUNT_LIMIT);

		// Create all Economy cards
		recovery = new EconomyCard("Recovery", 3, 48, 24, 5, 3, 64, 30, 30);
		depression = new EconomyCard("Depression", (-2), 32, 18, 4, 2, 50, 24,
				20);
		rapidGrowth = new EconomyCard("Rapid Growth", 6, 88, 50, 14, 9, 132,
				90, 65);
		prosperity = new EconomyCard("Prosperity", 7, 100, 60, 16, 12, 160,
				120, 75);
		expansion = new EconomyCard("Expansion", 4, 66, 32, 9, 5, 96, 60, 50);
		downturn = new EconomyCard("Downturn", 2, 75, 45, 12, 8, 120, 80, 60);
		recession = new EconomyCard("Recession", 0, 55, 30, 8, 4, 80, 32, 45);
		// Store Economy Cards
		ecoCards.add(recovery);
		ecoCards.add(depression);
		ecoCards.add(rapidGrowth);
		ecoCards.add(prosperity);
		ecoCards.add(expansion);
		ecoCards.add(downturn);
		ecoCards.add(recession);
		// Sets the current economy card to 'Recovery'
		cEcoCard = ecoCards.get(0);

		// Create all news cards.
		// Not sure if implementing yet!
		debtReductionTax = new NewsCard("Debt Reduction Taxes", 1,
				("Each player must pay the bank for each of his/her assets:- "
						+ "/n" + "Rig: 2million" + "/n" + "Well: 5million"
						+ "/n" + "Station: 15million" + "Refinery: 20million"));

		globalWarming = new NewsCard("Global Warming Threat", 2,
				("Each player must pay the bank 25million for each "
						+ "refinery he owns"));

		salesTax = new NewsCard(
				"Sales Taxes",
				3,
				("Each player must pay 15million for each Gas Station they own."));

		longWinter = new NewsCard("Long Cold Winter Costs", 4,
				("Remove 5 barrels of Gas from the domestic market."));

		inspection = new NewsCard(
				"Snap Inspection",
				5,
				("Each player must pay the bank 3million for every Oil barrel"
						+ "they own and 5million for every Gas barrel they own."));

		uncertainty = new NewsCard("Market Uncertainty", 6,
				("Uncertainty causes the increase of Oil prices at home."
						+ "/n" + "The drop of gas on the consumer market."));

		// Adding news cards to an arraylist
		newsCards.add(debtReductionTax);
		newsCards.add(globalWarming);
		newsCards.add(salesTax);
		newsCards.add(longWinter);
		newsCards.add(inspection);
		newsCards.add(uncertainty);

		// Choosing 2 random cards for the starting less and more
		// likely news events.
		Collections.shuffle(newsCards);
		lessLikely = newsCards.get(0);
		newsCards.remove(lessLikely);
		moreLikely = newsCards.get(0);
		newsCards.remove(moreLikely);
	}

	/**
	 * Method used to kick-start the game by calling mainMenu() 
	 * where players can be created + edited and the game can 
	 * be started.
	 * 
	 */
	private void run() {
		StdOut.println("Crude: The Oil Game");
		StdOut.println();
		int option = mainMenu();
	
		while (option != 0) {
			switch (option) {
			// Set Up Menu
			case 1:
				int adminOption = adminMenu();
	
				switch (adminOption) {
				case 1:
					addPlayer();
					break;
				case 2:
					listPlayers();
					break;
				case 3:
					editPlayer();
					break;
				case 4:
					removePlayer();
					break;
				}
				break;
	
			case 2:
				int playerOption = playerMenu();
				switch (playerOption) {
				case 1:
					chooseGo();
					break;
				case 2:
					specialRound();
					break;
				}
				break;
			}
			option = mainMenu();
		}
		StdOut.println("Exiting...bye.");
	}

	/**
	 * Method that returns an int to the run() method
	 * that calls other functions to interact with the game.
	 * 
	 * @return Player's selection.
	 */
	private int mainMenu() {
		StdOut.println("-------------------");
		StdOut.println("MAIN MENU");
		StdOut.println("-------------------");
		StdOut.println("1 - Admin Menu");
		StdOut.println("2 - Player Menu");
		StdOut.println("-------------------");
		StdOut.println("0 - Exit");
	
		int option = StdIn.readInt();
		StdIn.readLine();
	
		return option;
	}

	/**
	 * Method that display a menu that displays the admin menu 
	 * and returns the option that they choose.
	 * 
	 * @return The admin's choice
	 */
	private int adminMenu() {
		StdOut.println("-------------------");
		StdOut.println("ADMIN MENU");
		StdOut.println("-------------------");
		StdOut.println("1 - Create Players");
		StdOut.println("2 - List Players");
		StdOut.println("3 - Edit Player (Name Only)");
		StdOut.println("4 - Remove Player");
		StdOut.println("-------------------");
		StdOut.println("0 - Back To Main Menu");
	
		int adminOption = StdIn.readInt();
		StdIn.readLine();
	
		return adminOption;
	}

	/**
	 * Method that allows an admin to add new player into an array so that the
	 * game can begin. Once in create players, it doesn't leave until
	 * all players are created.
	 * 
	 */
	private void addPlayer() {
		if (thePlayers.size() > 0) { // No list if no players in list
			listPlayers();
		}
		if (thePlayers.size() < 4) {
			StdOut.println("Enter name of new player: ");
			String playerName = StdIn.readLine();
			if (thePlayers.size() > 0) {
				for (Player player : thePlayers) {
					if (player.getPlayerName().equals(playerName)) {
						StdOut.println("Duplicated Name!");
						StdOut.println("Please Enter new name:");
						playerName = StdIn.readLine();
					}
				}
			}
	
			int playerNumber = (thePlayers.size() + 1);
	
			Player newPlayer = new Player(playerName, playerNumber,
					START_MONEY, START_BARRELS, START_BARRELS);
	
			thePlayers.add(newPlayer);
			if (thePlayers.size() <= 3) {
				addPlayer(); // Stay in player creation till 4 players are made.
			} else {
				StdOut.println("All players created!!");
			}
		} else {
			StdOut.println("No more than 4 players allowed!!");
		}
	}

	/**
	 * editPlayer() A method that allows access to the player ArrayList were if
	 * a player player exists the player name in the ArrayList can be edited
	 * 
	 */
	private void editPlayer() {
	
		listPlayers();
		StdOut.print("Select Player index number to edit: ");
		int index = StdIn.readInt();
		StdIn.readLine();
	
		StdOut.print("Enter the Player's new name: ");
		String newPlayerName = StdIn.readLine();
		if (thePlayers.size() > 0) {
			for (Player player : thePlayers) {
				if (player.getPlayerName().equals(newPlayerName)) {
					StdOut.println("Duplicated Name!");
					StdOut.println("Please Enter new name:");
					newPlayerName = StdIn.readLine();
				}
			}
		}
	
		Player player = thePlayers.get(index);
		player.setPlayerName(newPlayerName);
	
	}

	/**
	 * Method used to display the players that are currently in the 
	 * array list.
	 */
	private void listPlayers() {
		StdOut.println("-----------");
		StdOut.println("PLAYER LIST");
		StdOut.println("-----------");
	
		for (int i = 0; i < thePlayers.size(); i += 1) {
			StdOut.println("Name: " + thePlayers.get(i).getPlayerName());
			StdOut.println("Index: " + i);
			StdOut.println("Player Number: "
					+ thePlayers.get(i).getPlayerNumber());
			StdOut.println("------------------");
			StdOut.println();
		}
	}

	/*
	 * A method that removes the player from Player ArrayList. If this is done
	 * the addPlayer() method is called to make it more user friendly.
	 */
	private void removePlayer() {
		listPlayers();
		StdOut.print("Choose Player To Remove: ");
		int index = StdIn.readInt();
		StdIn.readLine();
	
		thePlayers.remove(index);
		StdOut.print("New player needs to added!!");
		addPlayer();
	}

	/**
	 * Method to display a menu to a player and to return his/her choice
	 * 
	 * @return Player's choice
	 */
	private int playerMenu() {
		StdOut.println("-------------------");
		StdOut.println("PLAYER MENU");
		StdOut.println("-------------------");
		StdOut.println("1 - Radomise Starting Player");
		StdOut.println("2 - Start Game");
		StdOut.println("-------------------");
		StdOut.println("0 - Back to Main Menu");
	
		int playerOption = StdIn.readInt();
		StdIn.readLine();
	
		return playerOption;
	}

	/**
	 * Method that picks who the starting player is if players aren't
	 * satisfied to go with the order in which players were inserted.
	 */
	private void chooseGo() {
		Collections.shuffle(thePlayers);
		cPlayer = thePlayers.get(0);
		StdOut.println(cPlayer.playerName + " Goes 1st");
		StdOut.println("Good Luck Every One");
	}

	/**
	 * Method that gets called at the start of the game and allows the 
	 * player to buy and place assets inside the grid.
	 */
	private void runSpecialAsset() {
	
		int assetOption = specialAssetMenu();
	
		while (assetOption != 0) {
			switch (assetOption) {
			case 1:
				buyOilRig();
				break;
			case 2:
				buyRefinery();
				break;
			case 3:
				buyStation();
				break;
			}
			assetOption = specialAssetMenu();
		}
		StdOut.println();
	}

	/**
	 * Method that prints a menu and allows the player to choose
	 * which building he/she wants to purchase before the game 
	 * begins proper.
	 * 
	 * @return The users choice
	 */
	private int specialAssetMenu() {
		StdOut.println("-------------------");
		StdOut.println("ASSET MENU");
		StdOut.println("-------------------");
		StdOut.println("1 - Buy an Oil Rig");
		StdOut.println("2 - Buy a Refinery");
		StdOut.println("3 - Buy a Station");
		StdOut.println("-------------------");
		StdOut.println("0 - Finish Your Turn");
	
		int specialAssetOption = StdIn.readInt();
		StdIn.readLine();
	
		return specialAssetOption;
	}

	/**
	 * Start's the special round where players get to buy and 
	 * place assets.
	 * 
	 */
	private void specialRound() {
		// Setting player number for use later.
		int num = 1;
	
		for (Player player : thePlayers) {
			player.setPlayerNumber(num);
			num += 1;
		}
	    
		//Grabbing first player in list, only used when players not shuffled
		if (cPlayer == null) {
			cPlayer = thePlayers.get(0);
		}
		
		while (!playerTurn.isTurnedOver()) {
			StdOut.println("----------------------");
			StdOut.println("SPECIAL PURCHASE PHASE");
			StdOut.println("----------------------");
			StdOut.println(cPlayer.getPlayerName() + "'s "
					+ "go to buy and place asset's onto board");
			StdOut.println();
			cPlayer.printGrid();
	
			StdOut.println("Current Prices are: ");
			cEcoCard.printPrices();
			StdOut.println();
			StdOut.println("What assets would you like to buy?");
			StdOut.println();
			runSpecialAsset();
	
			StdOut.println("Are you sure you want to end your turn (y/n)?");
			String response = StdIn.readString();
			StdIn.readLine();
	
			if (response.equals("y")) {
				StdOut.println("Thank you " + cPlayer.getPlayerName());
				StdOut.println("Here is what you're current grid looks like: ");
				StdOut.println();
				cPlayer.printGrid();
				cPlayer.printInfo();
				playerTurn.increment();
				cPlayer = thePlayers.get(playerTurn.getCount());
			} else if (response.equals("n")) {
				runSpecialAsset();
			}
		}		
		
		marketRun();
	}

	/**
	 * Method that signifies the end of the market phase and waits
	 * for input from a user to kick start the production phase of 
	 * the player's turn.
	 */
	private void runProduction() {
		int prodOption = prodMenu();
	
		while (prodOption != 0) {
			switch (prodOption) {
			case 1:
				productionPhase();
				break;
			}
			prodOption = prodMenu();
		}
		StdOut.println();
	}

	/**
	 * Menu that's linked to the runProduction() method, the value
	 * returned here determines whether the production menu starts.
	 * 
	 * @return Player's input.
	 */
	private int prodMenu() {
		StdOut.println("Phase: " + phaseCount.getCount() + "For: "
				+ cPlayer.playerName);
		StdOut.println();
		StdOut.println("----------------------");
		StdOut.println("PRODUCTION PHASE MENU");
		StdOut.println("----------------------");
		StdOut.println("1 - Begin Production Phase " + cPlayer.playerName);
		StdOut.println("----------------------");
		StdOut.println("0 - End Turn");
		StdOut.println("----------------------");
		StdOut.println();
	
		int prodOption = StdIn.readInt();
		StdIn.readLine();
	
		return prodOption;
	}

	/**
	 * Method that carries out the start of the production phase.
	 * 
	 */
	private void productionPhase() {

		int red = 0;
		int black = 0;

		StdOut.println(cPlayer.balance);
		StdOut.println("Phase: " + phaseCount.getCount() + " For: "
				+ cPlayer.playerName);
		StdOut.println();
		// START Roll Dice
		StdOut.println("Enter any key to roll the dice " + cPlayer.playerName);
		String response = StdIn.readString();
		StdIn.readLine();
		if (!response.equals("")) {
			StdOut.println("Rolling: ");
			red = redDie.roll();
			black = blackDie.roll();
		} else {
			StdOut.println("Ensure valid key is entered!");
			productionPhase();
		}

		StdOut.println("Red Die was: " + red);
		StdOut.println("Black Die was: " + black);
		StdOut.println();
		StdOut.println("Press any key to continue: ");
		response = StdIn.readString();
		StdIn.readLine();
		if (!response.equals("")) {
		} else {
			response = StdIn.readString();
			StdIn.readLine();
		}
		StdOut.println();
		// END Roll Dice

		// START Checking for doubles
		StdOut.println("Now Checking for news events!");
		if (red == black) {
			checkNews(red, black);
		}
		// END

		// START Checking difference and moving counter
		int diff = calcDifference(red, black);
		ecoCounter.increaseBy(diff);

		if (!ecoCounter.isTurnedOver()) {
			StdOut.println("Now Checking for Hits on Rows and Doubles!");
			checkRowHits(red);
			StdOut.println("Now Checking for Hits on Columns!");
			checkColHits(black);

		} else {
			// What to do if ecoCounter rolls over... I think
			int green = greenDie.roll();
			newEconomyType(green);

			if (cEcoCard.demandChange > 0) {
				eBoard.consumerMarketPrice += (cEcoCard.demandChange * 2);
			} else if (cEcoCard.demandChange < 0) {
				eBoard.consumerMarketPrice += (cEcoCard.demandChange * 2);
			}
			// END Checking difference and moving counter
			StdOut.println("Now Checking for Hits on Rows and Doubles!");
			checkRowHits(red);
			StdOut.println("Enter any key to continue to column check!");
			StdOut.println("Now Checking for Hits on Columns!");
			checkColHits(black);
		}
		phaseCount.increment();

		runAsset(red, black);
	}

	/**
	 * Method to change the economy card, the green die is rolled and the value
	 * it returns is passed into the method and then the economy is changed
	 * based on what the current economy card specifies it should be.
	 * 
	 * @param diceRoll
	 *            Value to be searched for.
	 */
	private void newEconomyType(int diceRoll) {
		if (cEcoCard.getName().equals("Depression")) {
			if ((diceRoll >= 1) && (diceRoll <= 4)) {
				cEcoCard = ecoCards.get(0);
	
			} else if ((diceRoll <= 6) && (diceRoll >= 5)) {
				cEcoCard = ecoCards.get(4);
			}			
		} else if (cEcoCard.getName().equals("Downturn")) {
			if ((diceRoll >= 2) && (diceRoll <= 5)) {
				cEcoCard = ecoCards.get(6);
	
			} else if (diceRoll == 1) {
				cEcoCard = ecoCards.get(3);
			} else if (diceRoll == 6) {
				cEcoCard = ecoCards.get(1);
			}
		} else if (cEcoCard.getName().equals("Recovery")) {
			if ((diceRoll >= 2) && (diceRoll <= 5)) {
				cEcoCard = ecoCards.get(4);
	
			} else if (diceRoll == 1) {
				cEcoCard = ecoCards.get(5);
			} else if (diceRoll == 6) {
				cEcoCard = ecoCards.get(2);
			}
		} else if (cEcoCard.getName().equals("Recession")) {
			if ((diceRoll >= 1) && (diceRoll <= 3)) {
				cEcoCard = ecoCards.get(1);
			} else if ((diceRoll >= 4) && (diceRoll <= 6)) {
				cEcoCard = ecoCards.get(0);
			}		
		} else if (cEcoCard.getName().equals("Rapid Growth")) {
			if ((diceRoll >= 2) && (diceRoll <= 5)) {
				cEcoCard = ecoCards.get(3);
			} else if (diceRoll == 1) {
				cEcoCard = ecoCards.get(4);
			} else if (diceRoll == 6) {
				cEcoCard = ecoCards.get(5);
			}
		} else if (cEcoCard.getName().equals("Prosperity")) {
			if ((diceRoll >= 2) && (diceRoll <= 5)) {
				cEcoCard = ecoCards.get(5);
			} else if (diceRoll == 1) {
				cEcoCard = ecoCards.get(2);
			} else if (diceRoll == 6) {
				cEcoCard = ecoCards.get(6);
			}	
		} else if (cEcoCard.getName().equals("Expansion")) {
			if ((diceRoll >= 2) && (diceRoll <= 5)) {
				cEcoCard = ecoCards.get(2);
			} else if (diceRoll == 1) {
				cEcoCard = ecoCards.get(0);
			} else if (diceRoll == 6) {
				cEcoCard = ecoCards.get(3);
			}			
		}
		
		ecoCounter.setTurnedOver(false);
	
		StdOut.println("New Economy is: " + cEcoCard.getName());
		StdOut.println("New prices are: ");
		cEcoCard.printPrices();
		StdOut.println("Well Price: " + eBoard.wellPrice);
	}

	/**
	 * Method that calculates and returns the difference between the two dice.
	 * 
	 * @param redVal
	 *            Value of red die
	 * @param blackVal
	 *            Value of black die
	 * @return The difference between the two or '0'.
	 */
	private int calcDifference(int redVal, int blackVal) {
		int value = 0;
	
		if (redVal > blackVal) {
			value = redVal - blackVal;
		} else if (blackVal > redVal) {
			value = blackVal - redVal;
		}
		return value;
	}

	/**
	 * Method that takes the values of the dice rolled and checks if they are
	 * doubles or not. If they are doubles then a new method is called based on
	 * whether the double's are 1's, 3's or 6's.
	 * 
	 * @param red
	 *            Red Die roll value
	 * @param black
	 *            Red Die roll value
	 */
	private void checkNews(int red, int black) {
		if (((red == 1) && (black == 1)) || ((red == 3) && (black == 3))) {
			doMoreLikely();
		} else if ((red == 6) && (black == 6)) {
			doLessLikely();
		}
	}

	/**
	 * Method that takes in the value of the black die and checks to see if
	 * there are any hits in the Player's grid. If the grid slot contains a
	 * building the correct action for that building is carried out. Doubles
	 * checked in this method!
	 * 
	 * @param red
	 *            The value of the red die roll.
	 */
	private void checkRowHits(int red) {
		int i = 0;
		int row = (red - 1);
		// check for doubles in the rows
		while (i < cPlayer.playerGrid.length) {
			if (cPlayer.playerGrid[row][i] != null) {
				if ((cPlayer.playerGrid[row][i].symbol).equals("R")) {
					if (row != i) {
						if (cPlayer.oilBarrels > 0) {
							StdOut.println("Hit on Refinery");
							StdOut.println("Do you want to convert an oil barrel to gas.");
							StdOut.println("Return any key to convert.");
							String response = StdIn.readString();
							StdIn.readLine();
							if (!response.equals("")) {
								cPlayer.oilBarrels -= 1;
								cPlayer.gasBarrels += 1;
							}
						} else {
							StdOut.println("No Barrels to convert!");
						}
					} else if (row == i) {
						if (cPlayer.oilBarrels >= 2) {
							StdOut.println("Double hit on Refinery!");
							StdOut.println("Do you want to convert 2 oil barrels to gas.");
							StdOut.println("Return any key to convert.");
							String response = StdIn.readString();
							StdIn.readLine();
							if (!response.equals("")) {
								cPlayer.oilBarrels -= 2;
								cPlayer.gasBarrels += 2;
							}
						} else {
							StdOut.println("Not enough barrels to convert!");
						}
					}
				} else if ((cPlayer.playerGrid[row][i].symbol).equals("S")) {
					if (row != i) {
						if (cPlayer.gasBarrels > 0) {
							StdOut.println("Hit on Station");
							StdOut.println("Do you want to sell gas to the consumer market?");
							StdOut.println("Current consumer market price: "
									+ eBoard.consumerMarketPrice);
							StdOut.println("Return any key to sell.");
							String response = StdIn.readString();
							StdIn.readLine();
							if (!response.equals("")) {
								cPlayer.gasBarrels -= 1;
								cPlayer.balance += eBoard.consumerMarketPrice;
								eBoard.consumerMarketPrice -= 1;
							}
						} else {
							StdOut.println("Not enough barrels to sell!");
						}
					} else if (row == i) {
						if (cPlayer.gasBarrels >= 2) {
							StdOut.println("Double Hit on Station");
							StdOut.println("Do you want to sell 2 gas barrels to the consumer market?");
							StdOut.println("Current consumer market price: "
									+ eBoard.consumerMarketPrice);
							StdOut.println("Return any key to sell 2 barrels.");
							String response = StdIn.readString();
							StdIn.readLine();
							if (!response.equals("")) {
								cPlayer.gasBarrels -= 2;
								eBoard.consumerMarketPrice -= (2 * 2);
								cPlayer.balance += eBoard.consumerMarketPrice;
							}
						} else {
							StdOut.println("Not enough barrels to sell!");
						}
					}
				} else if ((cPlayer.playerGrid[row][i].symbol).equals("W")) {
					if (row != i) {
						StdOut.println("Hit on Well");
						StdOut.println("You get 2 oil barrels!");
						cPlayer.oilBarrels += 2;
					} else {
						StdOut.println("Hit on Well");
						StdOut.println("You get 4 oil barrels!");
						cPlayer.oilBarrels += 4;
					}
				} else if ((cPlayer.playerGrid[row][i].symbol).equals("D")) {
					if (row == i) {
						if (cPlayer.balance >= eBoard.wellPrice) {
							StdOut.println("Double Hit on Well");
							StdOut.println("Convert Drilling Rig to a well for "
									+ eBoard.wellPrice);
							StdOut.println("Yes (y) or No (n)");
							String response = StdIn.readString();
							StdIn.readLine();
							if (response.equals("y")) {
								cPlayer.playerGrid[row][i] = new Well();
								cPlayer.balance -= eBoard.wellPrice;
								eBoard.wellPrice += (nmrWells * 5);
								nmrWells += 1;
							}
						} else {
							StdOut.println("You don't have enough money to convert!");
						}
					} else {
						StdOut.println("Action only on double hits.");
					}
				}
			}
			i++;
		}
	}

	/**
	 * Method that takes in the value of the black die and checks to see if
	 * there are any hits in the Player's grid. If the grid slot contains a
	 * building the correct action for that building is carried out. Doubles not
	 * checked in this method!
	 * 
	 * @param black
	 *            The value from the black die roll.
	 */
	private void checkColHits(int black) {
		int i = 0;
		int col = (black - 1);
	
		while (i < cPlayer.playerGrid.length) {
			if (cPlayer.playerGrid[i][col] != null) {
				if ((cPlayer.playerGrid[i][col].symbol).equals("R")) {
					if (col != i) {
						if (cPlayer.oilBarrels > 0) {
							StdOut.println("Hit on Refinery");
							StdOut.println("Do you want to convert an oil barrel to gas.");
							StdOut.println("Return any key to convert.");
							String response = StdIn.readString();
							StdIn.readLine();
							if (!response.equals("")) {
								cPlayer.oilBarrels -= 1;
								cPlayer.gasBarrels += 1;
							}
						} else {
							StdOut.println("No Barrels to convert!");
						}
					}
				} else if ((cPlayer.playerGrid[i][col].symbol).equals("S")) {
					if (col != i) {
						if (cPlayer.gasBarrels > 0) {
							StdOut.println("Hit on Station");
							StdOut.println("Do you want to sell gas to the consumer market?");
							StdOut.println("Current consumer market price: "
									+ eBoard.consumerMarketPrice);
							StdOut.println("Return any key to sell.");
							String response = StdIn.readString();
							StdIn.readLine();
							if (!response.equals("")) {
								cPlayer.gasBarrels -= 1;
								cPlayer.balance += eBoard.consumerMarketPrice;
								eBoard.consumerMarketPrice -= 1;
							}
						} else {
							StdOut.println("Not enough barrels to sell!");
						}
					}
				} else if ((cPlayer.playerGrid[i][col].symbol).equals("W")) {
					if (col != i) {
						StdOut.println("Hit on Well");
						StdOut.println("You get 2 oil barrels!");
						cPlayer.oilBarrels += 2;
					}
				} else if ((cPlayer.playerGrid[i][col].symbol).equals("D")) {
					if (col != i) {
						StdOut.println("Action only on double hits.");
					}
				}
			}
			i++;
		}
		
	}

	/**
	 * Method that checks what the news id of the more likely news event is and
	 * carries out it's instructions. On completion, it then replaces more
	 * likely card with the current less likely event and then selects a new
	 * less likely card from the array list.
	 * 
	 */
	private void doMoreLikely() {
		if (moreLikely.newsid == 1) {
			for (Player player : thePlayers) {
				int sum = 0;
				sum += (player.nmrOilRigs * 2) + (player.nmrOilWells * 5)
						+ (player.nmrStations * 15)
						+ (player.nmrRefineries * 20);
				boolean broke = true;
				while (broke) {
					if (player.balance - sum > 0) {
						player.balance -= sum;
						broke = false;
					} else {
						StdOut.println("You haven't enough money to complete this action!");
						StdOut.println("You need to sell Assets to pay your debts.");
						forceSell(player);
					}
				}
			}
		} else if (moreLikely.newsid == 2) {
			for (Player player : thePlayers) {
				int sum = 0;
				sum += (player.nmrRefineries * 25);
				boolean broke = true;

				while (broke) {
					if (player.balance - sum > 0) {
						player.balance -= sum;
						broke = false;
					} else {
						StdOut.println("You haven't enough money to complete this action!");
						StdOut.println("You need to sell Assets to pay your debts.");
						forceSell(player);
					}
				}
			}
		} else if (moreLikely.newsid == 3) {
			for (Player player : thePlayers) {
				int sum = 0;
				sum += (player.nmrStations * 15);
				boolean broke = true;

				while (broke) {
					if (player.balance - sum > 0) {
						player.balance -= sum;
						broke = false;
					} else {
						StdOut.println("You haven't enough money to complete this action!");
						StdOut.println("You need to sell Assets to pay your debts.");
						forceSell(player);
					}
				}
			}
		} else if (moreLikely.newsid == 4) {
			eBoard.domGasPrice += (10); // 5 Barrels off the board increases
										// price
		} else if (moreLikely.newsid == 5) {
			for (Player player : thePlayers) {
				int sum = 0;
				sum += (player.oilBarrels * 3) + (player.gasBarrels * 5);
				boolean broke = true;

				while (broke) {
					if (player.balance - sum > 0) {
						player.balance -= sum;
						broke = false;
					} else {
						StdOut.println("You haven't enough money to complete this action!");
						StdOut.println("You need to sell Assets to pay your debts.");
						forceSell(player);
					}
				}
			}
		} else if (moreLikely.newsid == 6) {
			eBoard.forOilPrice -= 5;
			eBoard.domOilPrice += 5;
			eBoard.consumerMarketPrice -= 5;
		}
	}

	/**
	 * Method that checks what the news id of the less likely news event is and
	 * carries out it's instructions. On completion, it adds the less likely
	 * card back into the deck and grabs the next available card to be the new
	 * less likely news card.
	 */
	private void doLessLikely() {
		if (lessLikely.newsid == 1) {
			for (Player player : thePlayers) {
				int sum = 0;
				sum += (player.nmrOilRigs * 2) + (player.nmrOilWells * 5)
						+ (player.nmrStations * 15)
						+ (player.nmrRefineries * 20);
				boolean broke = true;
				while (broke) {
					if (player.balance - sum > 0) {
						player.balance -= sum;
						broke = false;
					} else {
						StdOut.println("You haven't enough money to complete this action!");
						StdOut.println("You need to sell Assets to pay your debts.");
						forceSell(player);
					}
				}
			}
		} else if (lessLikely.newsid == 2) {
			for (Player player : thePlayers) {
				int sum = 0;
				sum += (player.nmrRefineries * 25);
				boolean broke = true;

				while (broke) {
					if (player.balance - sum > 0) {
						player.balance -= sum;
						broke = false;
					} else {
						StdOut.println("You haven't enough money to complete this action!");
						StdOut.println("You need to sell Assets to pay your debts.");
						forceSell(player);
					}
				}
			}
		} else if (lessLikely.newsid == 3) {
			for (Player player : thePlayers) {
				int sum = 0;
				sum += (player.nmrStations * 15);
				boolean broke = true;

				while (broke) {
					if (player.balance - sum > 0) {
						player.balance -= sum;
						broke = false;
					} else {
						StdOut.println("You haven't enough money to complete this action!");
						StdOut.println("You need to sell Assets to pay your debts.");
						forceSell(player);
					}
				}
			}
		} else if (lessLikely.newsid == 4) {
			eBoard.domGasPrice += (10); // 5 Barrels off the board increases
										// price
		} else if (lessLikely.newsid == 5) {
			for (Player player : thePlayers) {
				int sum = 0;
				sum += (player.oilBarrels * 3) + (player.gasBarrels * 5);
				boolean broke = true;

				while (broke) {
					if (player.balance - sum > 0) {
						player.balance -= sum;
						broke = false;
					} else {
						StdOut.println("You haven't enough money to complete this action!");
						StdOut.println("You need to sell Assets to pay your debts.");
						forceSell(player);
					}
				}
			}
		} else if (lessLikely.newsid == 6) {
			eBoard.forOilPrice -= 5;
			eBoard.domOilPrice += 5;
			eBoard.consumerMarketPrice -= 5;
		}
		StdOut.println();
		StdOut.println("-----------------------");
		StdOut.println("LESS LIKELY NEWS EVENT");
		StdOut.println("-----------------------");
		StdOut.println("------Description------");
		StdOut.println(lessLikely.description);
		StdOut.println("-----------------------");

		// Doing println to check changes occur
		// StdOut.println("Old Less Likely ID: " + lessLikely.newsid);
		// StdOut.println("Old More Likely ID: " + moreLikely.newsid);

		newsCards.add(lessLikely); // Puts less likely back into list
		lessLikely = newsCards.get(0); // Gets the next card in the list

		// StdOut.println("Less likely should have now be a new card");
		// StdOut.println("More Likely ID shouldn't have changed: " +
		// moreLikely.newsid);
		// StdOut.println("New Less Likely ID: " + lessLikely.newsid);
	}

	/**
	 * Method that gets called during the news event phase in the
	 * case of a player not having enough money to complete the action that
	 * the news card calls for.
	 * 
	 * @param player The player who has to sell the assets.
	 */
	private void forceSell(Player player) {
		int row = 0;
		int col = 0;
		StdOut.println();
		StdOut.println("Welcome to force sell menu " + player.playerName);
		cPlayer.printGrid();
		StdOut.println("Your current balance is: " + player.balance);
		cEcoCard.printLiqValues();
	
		StdOut.println();
		StdOut.println("Enter your row selection: ");
		row = StdIn.readInt();
		StdIn.readLine();
		StdOut.println("Enter your column selection: ");
		col = StdIn.readInt();
		StdIn.readLine();
	
		if ((player.playerGrid[row][col].symbol).equals("R")) {
			player.balance += cEcoCard.refineLiqPrice;
			player.playerGrid[row][col] = null;
		} else if ((player.playerGrid[row][col].symbol).equals("S")) {
			player.balance += cEcoCard.stationLiqPrice;
			player.playerGrid[row][col] = null;
		} else if ((cPlayer.playerGrid[row][col].symbol).equals("D")) {
			player.balance += cEcoCard.rigLiqPrice;
			player.playerGrid[row][col] = null;
		} else if ((cPlayer.playerGrid[row][col].symbol).equals("W")) {
			player.balance += cEcoCard.wellLiqPrice;
			player.playerGrid[row][col] = null;
		}
		StdOut.println("Your new balance is: " + player.balance);
	
		StdOut.println("Back to production phase");
	}

	/**
	 * Method that's responsible for handling the users choice 
	 * of what building they want to buy in the asset phase and
	 * also launches the method that will allow the player to 
	 * sell the assets he/she has on the corresponding row and
	 * column represented by the dice.
	 * 
	 * @param black The Value of the black die roll (col)
	 * @param red The value of the red die roll (row)
	 * 
	 */
	private void runAsset(int red, int black) {
	
		int assetOption = assetMenu();
	
		while (assetOption != 0) {
			switch (assetOption) {
			case 1:
				buyOilRig();
				break;
			case 2:
				buyRefinery();
				break;
			case 3:
				buyStation();
				break;
			case 4:
				sellAssets(red, black);
				break;
			}
			assetOption = assetMenu();
		}
		StdOut.println();
		phaseCount.increment();
		winCheck();
	}

	/**
	 * Method that prints the menu choices and returns the value 
	 * to the assetRun() method so that the chosen option can be 
	 * carried out
	 * 
	 * @return The players choice.
	 */
	private int assetMenu() {
		StdOut.println("---------------------");
		StdOut.println("ASSET MENU");
		StdOut.println("---------------------");
		StdOut.println("---------Buy---------");
		StdOut.println("1 - Buy an Oil Rig");
		StdOut.println("2 - Buy a Refinery");
		StdOut.println("3 - Buy a Station");
		StdOut.println("--------Sell---------");
		StdOut.println("4 - Sell assets");
		StdOut.println("---------------------");
		StdOut.println("0 - Finish This Phase");
		StdOut.println("---------------------");
	
		int assetOption = StdIn.readInt();
		StdIn.readLine();
	
		return assetOption;
	}

	/**
	 * Method where the player buys and places their Gas Station
	 * 
	 */
	private void buyStation() {
		// 1st Checking to see if player has enough money.
		if (cPlayer.balance > cEcoCard.stationPurPrice) {
			cPlayer.printGrid();
			StdOut.println("Where would you like to place your Gas Station?");
			StdOut.println("Choose Row: ");
			int row = StdIn.readInt();
			StdIn.readLine();
			// Checking if the inputted value is between 1 and 6 inclusive
			if ((row >= 1) && (row <= 6)) {
				row -= 1; // Index is one less than is shown to player
			} else {
				StdOut.println("Not a valid index. Must be between 1 and 6");
				StdOut.println("Restarting asset placement!");
				buyStation();
			}
	
			StdOut.println("Choose Row: ");
			int col = StdIn.readInt();
			StdIn.readLine();
			// Checking if the inputted value is between 1 and 6 inclusive
			if ((col >= 1) && (col <= 6)) {
				col -= 1; // Index is one less than is shown to player
			} else {
				StdOut.println("Not a valid index. Must be between 1 and 6");
				StdOut.println("Restarting asset placement!");
				buyStation(); // If the index chosen is wrong, process starts
								// over
			}
	
			// Now checking if area where asset is to be created is empty.
			if (cPlayer.playerGrid[row][col] == null) {
				cPlayer.playerGrid[row][col] = new Station();
			} else {
				StdOut.println("Space already contains a building!");
				buyStation(); // If space is taken then it starts again.
			}
	
			// After buying set new balance
			cPlayer.balance -= cEcoCard.stationPurPrice;
			cPlayer.nmrStations += 1;
			cPlayer.printGrid();
		} else {
			StdOut.println("You can't afford to buy Gas Stations!");
			StdOut.println("Station Price is: " + cEcoCard.stationPurPrice);
			StdOut.println("Your balance is: " + cPlayer.balance);
		}
	}

	/**
	 * Method where the player buys and places their refinery.
	 * 
	 */
	private void buyRefinery() {
		// 1st Checking to see if player has enough money.
		if (cPlayer.balance > cEcoCard.refinePurPrice) {
			cPlayer.printGrid();
			StdOut.println("Where would you like to place your Gas Refinery?");
			StdOut.println("Choose Row: ");
			int row = StdIn.readInt();
			StdIn.readLine();
			// Checking if the inputted value is between 1 and 6 inclusive
			if ((row >= 1) && (row <= 6)) {
				row -= 1; // Index is one less than is shown to player
			} else {
				StdOut.println("Not a valid index. Must be between 1 and 6");
				StdOut.println("Restarting asset placement!");
				buyRefinery();
			}
	
			StdOut.println("Choose Row: ");
			int col = StdIn.readInt();
			StdIn.readLine();
			// Checking if the inputted value is between 1 and 6 inclusive
			if ((col >= 1) && (col <= 6)) {
				col -= 1; // Index is one less than is shown to player
			} else {
				StdOut.println("Not a valid index. Must be between 1 and 6");
				StdOut.println("Restarting asset placement!");
				buyRefinery(); // If the index chosen is wrong, process starts
								// over
			}
	
			// Now checking if area where asset is to be created is empty.
			if (cPlayer.playerGrid[row][col] == null) {
				cPlayer.playerGrid[row][col] = new Refinery();
			} else {
				StdOut.println("Space already contains a building!");
				buyRefinery(); // If space is taken then it starts again.
			}
	
			// After buying set new balance
			cPlayer.balance -= cEcoCard.refinePurPrice;
			cPlayer.nmrRefineries += 1;
			cPlayer.printGrid();
		} else {
			// What happens if not enough money.
			StdOut.println("You can't afford to buy Refineries!");
			StdOut.println("Refinery Price is: " + cEcoCard.refinePurPrice);
			StdOut.println("Your balance is: " + cPlayer.balance);
		}
	}

	/**
	 * Method that allows the buying of Oil Rigs and it's placement
	 * 
	 */
	private void buyOilRig() {
		// 1st Checking to see if player has enough money.
		if (cPlayer.balance > cEcoCard.rigPurPrice) {
			cPlayer.printGrid();
			StdOut.println("Where would you like to place your Gas Oil Rig?");
			StdOut.println("Choose Row: ");
			int row = StdIn.readInt();
			StdIn.readLine();
			// Checking if the inputted value is between 1 and 6 inclusive
			if ((row >= 1) && (row <= 6)) {
				row -= 1; // Index is one less than is shown to player
			} else {
				StdOut.println("Not a valid index. Must be between 1 and 6");
				StdOut.println("Restarting asset placement!");
				buyOilRig();
			}
	
			StdOut.println("Choose Column: ");
			int col = StdIn.readInt();
			StdIn.readLine();
			// Checking if the inputted value is between 1 and 6 inclusive
			if ((col >= 1) && (col <= 6)) {
				col -= 1; // Index is one less than is shown to player
			} else {
				StdOut.println("Not a valid index. Must be between 1 and 6");
				StdOut.println("Restarting asset placement!");
				buyRefinery(); // If the index chosen is wrong, process starts								
			}
	
			// Now checking if area where asset is to be created is empty.
			if (cPlayer.playerGrid[row][col] == null) {
				cPlayer.playerGrid[row][col] = new DrillRig();
			} else {
				StdOut.println("Space already contains a building!");
				buyRefinery(); // If space is taken then it starts again.
			}
			// After buying set new balance
			cPlayer.balance -= cEcoCard.rigPurPrice;
			cPlayer.nmrOilRigs += 1;
			cPlayer.printGrid();
		} else {
			// What happens if not enough money.
			StdOut.println("You can't afford to buy Oil Rig!");
			StdOut.println("Refinery Price is: " + cEcoCard.rigPurPrice);
			StdOut.println("Your balance is: " + cPlayer.balance);
		}
	}

	/**
	 * Method where players are allowed to sell assets that they own that
	 * are contains within cells that can be represented by the dice rolls
	 * that occured earlier.
	 * 
	 * @param black The value of the black dice, representing the column index
	 * @param red The value of the black dice, representing the row index
	 * 
	 */
	private void sellAssets(int red, int black) {
		int row = 0;
		int col = 0;
		StdOut.println("Welcome to sell menu " + cPlayer.playerName);
		StdOut.println("You can only sell what's on the rows and columns you've hit with the dice!");
		cPlayer.printGrid();
		cEcoCard.printLiqValues();
		StdOut.println("Row to choose from: " + red);
		StdOut.println("Column to choose from: " + black);
		StdOut.println();
		StdOut.println("Enter your row selection: ");
		row = StdIn.readInt();
		StdIn.readLine();
		StdOut.println("Enter your column selection: ");
		col = StdIn.readInt();
		StdIn.readLine();
		if ((row == red) || (col == black)) {
			StdOut.println("Are you sure you want to sell a(n) "
					+ cPlayer.playerGrid[row][col].buildingName);
			StdOut.println("(y/n)");
			String response = StdIn.readString();
			StdIn.readLine();
			if (response.equals("y")) {
				if ((cPlayer.playerGrid[row][col].symbol).equals("R")) {
					cPlayer.balance += cEcoCard.refineLiqPrice;
					cPlayer.playerGrid[row][col] = null;
				} else if ((cPlayer.playerGrid[row][col].symbol).equals("S")) {
					cPlayer.balance += cEcoCard.stationLiqPrice;
					cPlayer.playerGrid[row][col] = null;
				} else if ((cPlayer.playerGrid[row][col].symbol).equals("D")) {
					cPlayer.balance += cEcoCard.rigLiqPrice;
					cPlayer.playerGrid[row][col] = null;
				} else if ((cPlayer.playerGrid[row][col].symbol).equals("W")) {
					cPlayer.balance += cEcoCard.wellLiqPrice;
					cPlayer.playerGrid[row][col] = null;
				}
			}
		} else {
			StdOut.println("One or more index is wrong!");
			StdOut.println("You can only sell what's on the rows and columns you've hit with the dice!");
			StdOut.println("Restarting sale!");
			sellAssets(red, black);
		}
		StdOut.println("Back to asset menu");
	}

	/**
	 * Method that runs the market phase, this phase allows players to buy and
	 * sell gas and oil barrels on both the domestic and foreign markets.
	 * 
	 */
	private void marketRun() {

		int marketOption = marketMenu(); // Market Phase

		while (marketOption != 0) {
			switch (marketOption) {

			case 1:
				if (!boughtf) {
					int domMarketOption = domMarketMenu();

					switch (domMarketOption) {
					case 1:
						buyDomGas();
						break;
					case 2:
						buyDomOil();
						break;
					}
					break;
				} else {
					StdOut.println("You've already bought from the Foreign Market.");
					StdOut.println("You can only buy from one market per turn.");
				}
			case 2:
				if (!boughtd) {
					int forMarketOption = forMarketMenu();
					switch (forMarketOption) {
					case 1:
						buyForGas();
						break;
					case 2:
						buyForOil();
						break;
					}
					break;
				} else {
					StdOut.println("You've already bought from the Foreign Market.");
					StdOut.println("You can only buy from one market per turn.");
				}
			}
			marketOption = marketMenu();
		}
		phaseCount.increment();
		runProduction();
	}

	/**
	 * Method that displays the main market menu and returns the user's 
	 * option to the marketRun() method.
	 * 
	 * @return The user's choice.
	 */
	private int marketMenu() {
	
		StdOut.println();
		StdOut.println("Phase: " + phaseCount.getCount());
		StdOut.println("Do you want to buy any commodities "
				+ cPlayer.playerName);
		StdOut.println();
		StdOut.println("-------------------");
		StdOut.println("MARKET PHASE");
		StdOut.println("-------------------");
		StdOut.println("1 - Buy From Domestic");
		StdOut.println("2 - Buy From Foreign");
		StdOut.println("-------------------");
		StdOut.println("0 - Finish Your Turn");
	
		int marketOption = StdIn.readInt();
		StdIn.readLine();
	
		return marketOption;
	}

	/**
	 * Method to print the menu for the gas market
	 * 
	 * @return The players choice
	 */
	private int forMarketMenu() {
		StdOut.println();
		StdOut.println("-------------------");
		StdOut.println("FOREIGN MARKET MENU");
		StdOut.println("-------------------");
		StdOut.println("1 - Buy Gas");
		StdOut.println("2 - Buy Oil");
		StdOut.println("-------------------");
		StdOut.println("0 - Finish Your Turn");
	
		int forMarketOption = StdIn.readInt();
		StdIn.readLine();
	
		return forMarketOption;
	}

	/**
	 * Method that allows the player to buy from the domestic
	 * gas market
	 */
	private void buyDomGas() {
		if (cPlayer.balance >= eBoard.domGasPrice) {
			StdOut.println();
			StdOut.println("Are you sure you want to buy from the Domestic Market?");
			StdOut.println("The current price of Gas here is: "
					+ eBoard.domGasPrice);
			StdOut.println("The current foreign market price is: "
					+ eBoard.forGasPrice);
			StdOut.println("Your balance is: " + cPlayer.balance);
			String response = StdIn.readString();
			StdIn.readLine();
			if (response.equals("y")) {
				StdOut.println("How many barrels of Gas do you want?");
				StdOut.println("Amount: ");
				int amount = StdIn.readInt();
				StdIn.readLine();
				int price = eBoard.forOilPrice;
				int cost = 0;

				for (int i = 0; i < amount; i += 1) {
					cost += price;
					price += 2;
				}
				
				if ((cPlayer.balance - cost) >= 0) {
					eBoard.domGasPrice += (amount * 2);
					cPlayer.balance -= cost;
					cPlayer.gasBarrels += amount;
				} else {
					StdOut.println("You can't afford that many barrels!");
					StdOut.println("Restarting...");
					buyDomGas();
				}
				StdOut.println("You now have " + cPlayer.gasBarrels
						+ " barrels.");
				StdOut.println("New balance is: " + cPlayer.balance);
				boughtd = true;
			}
		} else {
			StdOut.println(cPlayer.playerName + " you can't afford any Gas!");
		}
	
	}

	/**
	 *  Method to print the menu so the player can choose which
	 *  oil market he/she buys from.
	 * 
	 * @return The players choice
	 */
	private int domMarketMenu() {
		StdOut.println();
		StdOut.println("---------------------");
		StdOut.println("DOMESTIC MARKET MENU");
		StdOut.println("---------------------");
		StdOut.println("1 - Buy Gas");
		StdOut.println("2 - Buy Oil");
		StdOut.println("-------------------");
		StdOut.println("0 - Finish Your Turn");
	
		int domMarketOption = StdIn.readInt();
		StdIn.readLine();
	
		return domMarketOption;
	}

	/**
	 * Method called when player wants to purchase a barrel from the domestic
	 * oil market
	 */
	private void buyDomOil() {
		if (cPlayer.balance >= eBoard.domOilPrice) {
			StdOut.println();
			StdOut.println("Are you sure you want to buy from the Domestic Market?");
			StdOut.println("The current price of Oil here is: "
					+ eBoard.domOilPrice);
			StdOut.println("The current foreign market price is: "
					+ eBoard.forOilPrice);
			StdOut.println("Your balance is: " + cPlayer.balance);
			String response = StdIn.readString();
			StdIn.readLine();
			if (response.equals("y")) {
				StdOut.println("How many barrels of Oil do you want?");
				StdOut.println("Amount: ");
				int amount = StdIn.readInt();
				StdIn.readLine();
				
				int price = eBoard.domOilPrice;
				int cost = 0;

				for (int i = 0; i < amount; i += 1) {
					cost += price;
					price += 1;					
				}
				
				if ((cPlayer.balance - cost) >= 0) {
					eBoard.domOilPrice += amount;
					cPlayer.balance -= cost;
					cPlayer.oilBarrels += amount;

				} else {
					StdOut.println("You can't afford that many barrels");
					StdOut.println("Restarting...");
					buyDomOil();
				}
			}
			StdOut.println("You now have " + cPlayer.oilBarrels + " barrels.");
			StdOut.println("New balance is: " + cPlayer.balance);
			boughtd = true;
		} else {
			StdOut.println(cPlayer.playerName + " you can't afford any oil!");
		}
	}

	/**
	 * Method that allows the purchase of gas barrels from the foreign 
	 * market.
	 */
	private void buyForGas() {
		if (cPlayer.balance >= eBoard.forGasPrice) {
			StdOut.println();
			StdOut.println("Are you sure you want to buy from the Foreign Market?");
			StdOut.println("The current price of Gas here is: "
					+ eBoard.forGasPrice);
			StdOut.println("The current domestic market price is: "
					+ eBoard.domGasPrice);
			StdOut.println("Your balance is: " + cPlayer.balance);
			String response = StdIn.readString();
			StdIn.readLine();
			if (response.equals("y")) {
				StdOut.println("How many barrels of Gas do you want?");
				StdOut.println("Amount: ");
				int amount = StdIn.readInt();
				StdIn.readLine();
				
				int price = eBoard.forGasPrice;
				int cost = 0;

				for (int i = 0; i < amount; i += 1) {
					cost += price;
					price += 2;
				}
				
				if ((cPlayer.balance - cost) >= 0) {
					eBoard.forGasPrice += (amount * 2);
					cPlayer.balance -= cost;
					cPlayer.gasBarrels += amount;
					
				} else {
					StdOut.println("You can't afford that many barrels");
					StdOut.println("Restarting...");
					buyDomGas();
				}
	
				StdOut.println("You now have " + cPlayer.gasBarrels
						     + " barrels.");
				StdOut.println("New balance is: " + cPlayer.balance);
				boughtf = true;
			}
		} else {
			StdOut.println(cPlayer.playerName + " you can't afford any Gas!");
		}
	}

	/**
	 * Method where player can purchase from the foreign oil market
	 */
	private void buyForOil() {
		if (cPlayer.balance >= eBoard.forOilPrice) {
			StdOut.println();
			StdOut.println("Are you sure you want to buy from the Foreign Market?");
			StdOut.println("The current price of Oil here is: "
					+ eBoard.forOilPrice);
			StdOut.println("The current domestic market price is: "
					+ eBoard.domOilPrice);
			StdOut.println("Your balance is: " + cPlayer.balance);
			String response = StdIn.readString();
			StdIn.readLine();
			if (response.equals("y")) {
				StdOut.println("How many barrels of Oil do you want?");
				StdOut.println("Amount: ");
				int amount = StdIn.readInt();
				StdIn.readLine();
				int price = eBoard.forOilPrice;
				int cost = 0;

				for (int i = 0; i < amount; i += 1) {
					cost += price;
					price += 1;
				}
				
				if ((cPlayer.balance - cost) >= 0) {
					eBoard.forOilPrice += amount;
					cPlayer.balance -= cost;
					cPlayer.oilBarrels += amount;

				} else {
					StdOut.println("You can't afford that many barrels");
					StdOut.println("Restarting...");
					buyDomGas();
				}

				StdOut.println("You now have " + cPlayer.oilBarrels
						+ " barrels.");
				StdOut.println("New balance is: " + cPlayer.balance);
				boughtf = true;
			}
		} else {
			StdOut.println(cPlayer.playerName + " you can't afford any oil!");
		}
	}

	/**
	 * Method that will perform the check to see if a player has the 
	 * 750million required to win a game, if the player does it will launch a
	 * new round of phases and once all players bar the player claiming
	 * victory has completed each phase it will declare a winner.
	 * If player doesn't have 750million or chooses not to declare
	 * the next player in line is allowed to start a turn.
	 * 
	 */
	private void winCheck() {
		if (thePlayers.size() == 4) {
			StdOut.println();
			StdOut.println("Phase: " + phaseCount.getCount());
			StdOut.println();
			StdOut.println("Do you want to check and see if you can win?"
					+ cPlayer.playerName);
			StdOut.println("Yes will check to see if you more than 750million in cash"
					+ " and everyone but you will get another run through the" 
					+ " phases if you have 750million or more");
			StdOut.println("Enter 'y' or 'n'...");
			String response = StdIn.readString();
			StdIn.readLine();
			if ((response.equals("y")) && (cPlayer.balance >= 750)) {
				playerTurn.setCount(PT_COUNT_START); 
				int num = (cPlayer.playerNumber - 1);
				chancer = thePlayers.get(num);
				thePlayers.remove(num);
				cPlayer = thePlayers.get(0);
				runProduction();
			} else if (response.equals("n")) {
				phaseCount.increment();
				playerTurn.increment();
				cPlayer = thePlayers.get(playerTurn.getCount());
				boughtf = false;
				boughtd = false;
				marketRun();
			} else {
				StdOut.println("Response needs to be YES or NO!!");
				winCheck();
			}
		} else if ((thePlayers.size() < 4) && (playerTurn.isTurnedOver())){
			thePlayers.add(chancer);
	        
			//Index should be 1 as ecoCards don't get shuffled around
			EconomyCard dep = ecoCards.get(1);
			
	        int sum = 0;
	        
	        //Selling all players' assets at depression card prices
			for (Player player : thePlayers) {
				
				sum = (dep.refineLiqPrice * player.nmrRefineries) 
					+ (dep.rigLiqPrice * player.nmrOilRigs) 
				    + (dep.stationLiqPrice * player.nmrStations) 
				    + (dep.wellLiqPrice * player.nmrOilWells);
				
				player.balance += sum;
			}
			
			//Sorting through players
			//Winner put into card on his own
			Player victor = thePlayers.get(0);
			for (Player player : thePlayers) {
				if (player.getBalance() > victor.getBalance()) {
					victor = player;
				}
			}
			StdOut.println(victor.playerName + " WON");
			StdOut.println("He Drank Everyone's Milkshake!");
			StdOut.println("HE DRANK IT UP!");
			
			StdOut.println("Press any key to end...");
			String response = StdIn.readString();
			StdIn.readLine();
			if (!response.equals("")) {
				System.exit(0);		
			} else {
				System.exit(0);
			}
		} else {
			playerTurn.increment();
			marketRun();			
		}
		
		
	}
}
//END CLASS