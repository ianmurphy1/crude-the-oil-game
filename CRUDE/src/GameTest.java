import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class GameTest
{

	EconomyCard recovery, depression, rapidGrowth, prosperity, expansion, downturn, recession;
	
	ArrayList<EconomyCard> ecoCards;
	
	@Before
	public void setUp() {
		ecoCards = new ArrayList<EconomyCard>();

		recovery = new EconomyCard("Recovery", 3, 48, 24, 5, 3, 64,
				30, 30);
		depression = new EconomyCard("Depression", (-2), 32, 18, 4,
				2, 50, 24, 20);
		rapidGrowth = new EconomyCard("Rapid Growth", 6, 88, 50,
				14, 9, 132, 90, 65);
		prosperity = new EconomyCard("Prosperity", 7, 100, 60, 16,
				12, 160, 120, 75);
		expansion = new EconomyCard("Expansion", 4, 66, 32, 9, 5,
				96, 60, 50);
		downturn = new EconomyCard("Downturn", 2, 75, 45, 12, 8,
				120, 80, 60);
		recession = new EconomyCard("Recession", 0, 55, 30, 8, 4,
				80, 32, 45);
		ecoCards.add(recovery);
		ecoCards.add(depression);
		ecoCards.add(rapidGrowth);
		ecoCards.add(prosperity);
		ecoCards.add(expansion);
		ecoCards.add(downturn);
		ecoCards.add(recession);
	}

	@Test
	public void testNewsCardSelection() {
		Random randomiser = new Random();
		ArrayList<NewsCard> newsCards = new ArrayList<NewsCard>();
		NewsCard lessLikely;
		NewsCard moreLikely;
		NewsCard tempCard;

		NewsCard debtReductionTax = new NewsCard("Debt Reduction Taxes", 1,
				("Each player must pay the bank for each of his/her assets:- "
						+ "/n" + "Rig: 2million" + "/n" + "Well: 5million"
						+ "/n" + "Station: 15million" + "Refinery: 20million"));

		NewsCard globalWarming = new NewsCard("Global Warming Tax", 2,
				("Each player must pay the bank for each of his/her assets:- "
						+ "/n" + "Rig: 2million" + "/n" + "Well: 5million"
						+ "/n" + "Station: 15million" + "Refinery: 20million"));

		NewsCard salesTax = new NewsCard("Sales Taxes", 3,
				("Each player must pay the bank for each of his/her assets:- "
						+ "/n" + "Rig: 2million" + "/n" + "Well: 5million"
						+ "/n" + "Station: 15million" + "Refinery: 20million"));

		NewsCard longWinter = new NewsCard("Long Winter Costs", 4,
				("Each player must pay the bank for each of his/her assets:- "
						+ "/n" + "Rig: 2million" + "/n" + "Well: 5million"
						+ "/n" + "Station: 15million" + "Refinery: 20million"));

		NewsCard inspection = new NewsCard("Snap Inspection", 5,
				("Each player must pay the bank for each of his/her assets:- "
						+ "/n" + "Rig: 2million" + "/n" + "Well: 5million"
						+ "/n" + "Station: 15million" + "Refinery: 20million"));

		NewsCard uncertainty = new NewsCard("Market Uncertainty", 6,
				("Each player must pay the bank for each of his/her assets:- "
						+ "/n" + "Rig: 2million" + "/n" + "Well: 5million"
						+ "/n" + "Station: 15million" + "Refinery: 20million"));

		newsCards.add(debtReductionTax);
		newsCards.add(globalWarming);
		newsCards.add(salesTax);
		newsCards.add(longWinter);
		newsCards.add(inspection);
		newsCards.add(uncertainty);

		int pickA = randomiser.nextInt(newsCards.size());

		assertNotNull(pickA);
		lessLikely = newsCards.get(pickA);
		tempCard = lessLikely;
		assertNotNull(lessLikely);
		assertEquals(1, debtReductionTax.getNewsid());
		assertEquals(lessLikely.getNewsid(), tempCard.getNewsid());
		newsCards.remove(pickA);
		assertEquals(5, newsCards.size());

		int pickB = randomiser.nextInt(newsCards.size());
		moreLikely = newsCards.get(pickB);
		newsCards.add(tempCard);
		assertEquals(6, newsCards.size());
	}

	@Test
	public void testSizeOfEmptyArrayList() {
		ArrayList<Player> players = new ArrayList<Player>();

		assertEquals(0, players.size());
	}

	@Test
	public void testCounter() {
		Counter playerTurn = new Counter(0, 4);
		Player cPlayer;
		ArrayList<Player> thePlayers = new ArrayList<Player>();

		Player playerA = new Player("Ian", 0, 0, 0, 0);
		Player playerB = new Player("John", 1, 1, 1, 1);
		Player playerC = new Player("Joe", 1, 1, 1, 1);
		Player playerD = new Player("Jim", 1, 1, 1, 1);

		thePlayers.add(playerA);
		thePlayers.add(playerB);
		thePlayers.add(playerC);
		thePlayers.add(playerD);

		Collections.shuffle(thePlayers);

		cPlayer = thePlayers.get(playerTurn.getCount());
		assertNotNull(cPlayer);
	}

	/**
	 * Testing of the new version of selecting news cards.
	 */
	@Test
	public void testNewNewsSelector() {
		ArrayList<NewsCard> newsCards = new ArrayList<NewsCard>();

		NewsCard debtReductionTax = new NewsCard("Debt Reduction Taxes", 1,
				("Each player must pay the bank for each of his/her assets:- "
						+ "/n" + "Rig: 2million" + "/n" + "Well: 5million"
						+ "/n" + "Station: 15million" + "Refinery: 20million"));

		NewsCard globalWarming = new NewsCard("Global Warming Threat", 2,
				("Each player must pay the bank 25million for each "
						+ "refinery he owns"));

		NewsCard salesTax = new NewsCard(
				"Sales Taxes",
				3,
				("Each player must pay 15million for each Gas Station they own."));

		NewsCard longWinter = new NewsCard("Long Cold Winter Costs", 4,
				("Remove 5 barrels of Gas from the domestic market."));

		NewsCard inspection = new NewsCard(
				"Snap Inspection",
				5,
				("Each player must pay the bank 3million for every Oil barrel"
						+ "they own and 5million for every Gas barrel they own."));

		NewsCard uncertainty = new NewsCard("Market Uncertainty", 6,
				("Uncertainty causes the increase of Oil prices at home."
						+ "/n" + "The drop of gas on the consumer market."));

		// Adding news cards to an arraylist
		newsCards.add(debtReductionTax);
		newsCards.add(globalWarming);
		newsCards.add(salesTax);
		newsCards.add(longWinter);
		newsCards.add(inspection);
		newsCards.add(uncertainty);

		assertEquals(6, newsCards.size());

		NewsCard lessLikely = newsCards.get(0);
		// NewsCard tempCard = lessLikely;
		newsCards.remove(lessLikely);
		assertEquals(5, newsCards.size());
		assertEquals(1, lessLikely.newsid);
		// int pickB = randomiser.nextInt(newsCards.size());
		NewsCard moreLikely = newsCards.get(0);
		assertEquals(2, moreLikely.newsid);
		newsCards.remove(moreLikely);
		assertEquals(4, newsCards.size());

		moreLikely = lessLikely;
		assertEquals(1, moreLikely.newsid);
		newsCards.add(lessLikely);
		assertEquals(5, newsCards.size());
		lessLikely = newsCards.get(0);
		newsCards.remove(lessLikely);
		assertEquals(3, lessLikely.newsid);
	}

	@Test
	public void testHits() {
		int black = 3;

		String test = "";
		Player cPlayer = new Player("bob", 1, 200, 2, 2);
		EconomyBoard eBoard = new EconomyBoard(26, 16, 6, 16, 6);

		cPlayer.playerGrid[2][0] = new Refinery();
		cPlayer.playerGrid[0][3] = new Station();
		cPlayer.playerGrid[2][4] = new Refinery();		
		cPlayer.playerGrid[5][3] = new DrillRig();
		cPlayer.playerGrid[5][1] = new Refinery();
		cPlayer.playerGrid[2][5] = new Refinery();

		int i = 0;

		// check for doubles in the rows
		while (i < cPlayer.playerGrid.length) {
			if (cPlayer.playerGrid[i][black] != null) { // ignores null grid
														// spaces
				if (cPlayer.playerGrid[i][black].symbol.equals("R")) {
					if (black != i) {
						if (cPlayer.oilBarrels > 0) {
							cPlayer.oilBarrels -= 1;
							cPlayer.gasBarrels += 1;
						}
					}
				}

				else if (cPlayer.playerGrid[i][black].symbol.equals("S")) {
					if (black != i) {
						if (cPlayer.gasBarrels > 0) {
							cPlayer.gasBarrels -= 1;
							cPlayer.balance += eBoard.consumerMarketPrice;
							eBoard.consumerMarketPrice -= 1;
						}

					}
				} else if (cPlayer.playerGrid[i][black].symbol.equals("W")) {
					if (black != i) {

						cPlayer.oilBarrels += 2;
					}

				} else if ((cPlayer.playerGrid[i][black].symbol).equals("D")) {
					if (i != black) {
						test = "Action only on double hits for Oil Rigs.";
					} else {}
				}
			}
			i++;
		}
		assertEquals(1, cPlayer.getGasBarrels());
		assertEquals(226, cPlayer.getBalance());
		assertEquals("Action only on double hits for Oil Rigs.", test);
	}
	
	@Test
	public void newEconomyTypeTest() {
		
		EconomyCard cEcoCard = ecoCards.get(0);
		Counter ecoCounter = new Counter(0, 8);
		
		ecoCounter.setCount(7);
		ecoCounter.increment();
		
		int diceRoll = 1;
		
		
		if (cEcoCard.getName().equals("Depression")) {
			if ((diceRoll >= 1) && (diceRoll <= 4)) {
				cEcoCard = ecoCards.get(0);

			} else if ((diceRoll <= 6) && (diceRoll >= 5)) {
				cEcoCard = ecoCards.get(4);
			}			
		}

		else if (cEcoCard.getName().equals("Downturn")) {
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
		assertEquals("Downturn", cEcoCard.name);
		assertEquals(true, ecoCounter.isTurnedOver());
		ecoCounter.setTurnedOver(false);
		assertEquals(false, ecoCounter.isTurnedOver());
	}
	
	@Test
	public void testForceSale()  {
		
		ArrayList<Player> thePlayers = new ArrayList<Player>();

		Player playerA = new Player("Ian", 0, 0, 0, 0);
		Player playerB = new Player("John", 1, 1, 1, 1);
		
		thePlayers.add(playerA);
		thePlayers.add(playerB);
				
		playerA.balance = 450;
		playerA.oilBarrels = 5;
		playerA.gasBarrels = 3;
		
		playerB.balance = 20;
		playerB.oilBarrels = 3;
		playerB.gasBarrels = 5;
		
		for (Player player : thePlayers) {
			int sum = 0;
			sum += (player.oilBarrels * 3) + (player.gasBarrels * 5);
			boolean broke = true;
							
			while (broke) {
				if (player.balance - sum > 0) {
					player.balance -= sum;
					broke = false;
				} else {
					player.balance = 100;  //Change playerB's balance to test loop
				}							
			} 
		}
		
		assertEquals(420, playerA.balance);
		assertEquals(66, playerB.balance);		
	}
}
