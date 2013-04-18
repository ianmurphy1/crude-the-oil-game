import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * A tester class that tests the objects and accessors of the News card
 * Class
 * 
 * @authors Denis Murphy, Ian Murphy & Bill O'Keeffe
 * 
 */

public class NewsCardTest
{

	NewsCard aggressiveTaxes, stateTaxes, globalWarming;

	@Before
	public void setUp() throws Exception {
		aggressiveTaxes = new NewsCard("Aggressive Taxes", 1, "pay Bank 25m");
		stateTaxes = new NewsCard("state Taxes ", 2, "pay Bank");
		globalWarming = new NewsCard("global Warming", 3, "pay Bank 25m");
	}

	@Test
	public void testConstructor() {
		assertEquals("Aggressive Taxes", aggressiveTaxes.getName());
		assertEquals(1, aggressiveTaxes.getNewsid());
		assertEquals("pay Bank", stateTaxes.getDescription());
		assertEquals("state Taxes ", stateTaxes.getName());
		assertEquals(2, stateTaxes.getNewsid());
		assertEquals("pay Bank", stateTaxes.getDescription());
		assertEquals("global Warming", globalWarming.getName());
		assertEquals(3, globalWarming.getNewsid());
		assertEquals("pay Bank 25m", globalWarming.getDescription());
	}

	@Test
	public void testNameMutatorAndAccessor() {
		assertEquals("Aggressive Taxes", aggressiveTaxes.getName());
		aggressiveTaxes.setName("stateTaxes");
		assertEquals("stateTaxes", aggressiveTaxes.getName());
	}

	@Test
	public void testDescriptionMutatorAndAccessor() {
		assertEquals("pay Bank 25m", aggressiveTaxes.getDescription());
		aggressiveTaxes.setDescription("pay bank 5m");
		assertEquals("pay bank 5m", aggressiveTaxes.getDescription());
	}

	@Test
	public void testNewsidMutatorAndAccessor() {
		assertEquals(1, aggressiveTaxes.getNewsid());
		aggressiveTaxes.setNewsid(2);
		assertEquals(2, aggressiveTaxes.getNewsid());
	}

}