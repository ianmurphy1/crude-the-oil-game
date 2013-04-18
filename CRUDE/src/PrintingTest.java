/**
 * Class just to test the printing out of the player grid.
 * 
 * @authors Denis Murphy, Ian Murphy & Bill O'Keeffe
 * 
 */
public class PrintingTest
{
	static Asset[][] playerGrid = new Asset[6][6];

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// printGrid();
		playerGrid[2][0] = new Refinery();
		playerGrid[0][0] = new Station();
		playerGrid[2][4] = new Refinery();
		playerGrid[3][1] = new Refinery();
		playerGrid[5][1] = new Refinery();
		playerGrid[2][5] = new Refinery();
		tryAgain();
	}

	public static void printGrid() {

		StdOut.print("|  1  |  2  |  3  |  4  |  5  |  6  |");
		for (int row = 0; row < playerGrid.length; row++) {
			StdOut.print("__" + row + "_|");
			for (int col = 0; col < playerGrid[row].length; col++) {
				if (playerGrid[row][col] != null) {
					StdOut.println("  " + playerGrid[row][col]);
				} else {
					StdOut.println("  " + "| 0 |");
				}
			}
		}
	}

	public static void tryAgain() {
		StdOut.println("___|__1__|__2__|__3__|__4__|__5__|__6__|");
		for (int i = 0; i < playerGrid.length; i++) {

			StdOut.print("_" + (i + 1) + "_|__");

			for (int j = 0; j < playerGrid[i].length; j++) {
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
	}
}
