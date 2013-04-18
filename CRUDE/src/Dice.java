import java.util.Random;

/**
 * Creates a Dice class which randomly chooses a number between 1 and 6 and
 * returns the value.
 * 
 * @authors Denis Murphy, Ian Murphy & Bill O'Keeffe
 * 
 */
public class Dice
{
	final static int MAX = 6;
	int value;
	Random num = new Random();

	public Dice() {
		this.value = 1;
	}

	public int roll() {
		value = (num.nextInt(MAX) + 1);

		return value;
	}
}