import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Tester class for the Player Class
 * 
 * @authors Denis Murphy, Ian Murphy & Bill O'Keeffe
 *
 */
public class PlayerTest
{	
	@Test
	public void testCreatePlayer() {
		Player player = new Player("bob", 1, 200, 2, 2);
		assertNotNull(player);
		assertEquals(2, player.getOilBarrels());
		assertEquals(2, player.getGasBarrels());
		assertEquals(200, player.getBalance());
		assertEquals(1, player.getPlayerNumber());
	}
	
	@Test
	public void testAccessorsAndMutators() {
		Player player = new Player("bob", 1, 200, 2, 2);
		assertEquals(2, player.getOilBarrels());
		assertEquals(2, player.getGasBarrels());
		assertEquals(200, player.getBalance());	
		player.setOilBarrels(10);
		assertEquals(10, player.getOilBarrels());
		player.setOilBarrels(-1);
		assertEquals(10, player.getOilBarrels());
		player.setGasBarrels(10);
		assertEquals(10, player.getGasBarrels());
		player.setGasBarrels(-1);
		assertEquals(10, player.getGasBarrels());
		player.setBalance(1000);
		assertEquals(1000, player.getBalance());
		player.setBalance(-120);
		assertEquals(1000, player.getBalance());
	}

}
