import static org.junit.Assert.*;

import org.junit.Test;


/**
 * @authors Denis Murphy, Ian Murphy & Bill O'Keeffe
 *
 */
public class DiceTest
{

	@Test
	public void test() {
		Dice redDie = new Dice();
		int blue = redDie.roll();
		assertNotNull(blue);
	}

}
