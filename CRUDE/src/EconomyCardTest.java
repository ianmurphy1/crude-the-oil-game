/**
 * A tester class that tests the objects and Accessor of the Economy Card Class
 * 
 *  @authors Denis Murphy, Ian Murphy & Bill O'Keeffe
 */


import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class EconomyCardTest
{

	EconomyCard downturn, recovery, recession;
	

	@Before
	public void setUp() throws Exception {
		downturn = new EconomyCard("downturn", 2, 75, 45, 12, 8, 120, 80, 60);
		recovery = new EconomyCard("recovery", 3, 48, 24, 5, 3, 64, 30, 30);
		recession = new EconomyCard("recession", 0, 55, 30, 8, 4, 80, 32, 45);
	}

	@Test
	public void testConstructor() {
		assertEquals("recovery", recovery.getName());
		assertEquals(3, recovery.getDemandChange());
		assertEquals(48, recovery.getStationPurPrice());
		assertEquals(24, recovery.getStationLiqPrice());
		assertEquals(5, recovery.getRigPurPrice());
		assertEquals(3, recovery.getRigLiqPrice());
		assertEquals(64, recovery.getRefinePurPrice());
		assertEquals(30, recovery.getRefineLiqPrice());
		assertEquals(30, recovery.getWellLiqPrice());
		
		assertEquals("downturn", downturn.getName());
		assertEquals(2, downturn.getDemandChange());
		assertEquals(75, downturn.getStationPurPrice());
		assertEquals(45, downturn.getStationLiqPrice());
		assertEquals(12, downturn.getRigPurPrice());
		assertEquals(8, downturn.getRigLiqPrice());
		assertEquals(120, downturn.getRefinePurPrice());
		assertEquals(80,downturn.getRefineLiqPrice());
		assertEquals(60, downturn.getWellLiqPrice());
	}
	
	@Test
	public void testRecoveryMutatorAndAccessor()
	  {
	    assertEquals("recovery", recovery.getName());
	    recovery.setName("downturn");
	    assertEquals("downturn", recovery.getName());
	  }
	
	@Test
	 public void testDemandChangeMutatorAndAccessor()
	  {
	    assertEquals(3, recovery.getDemandChange());
	    recovery.setDemandChange(2);
	    assertEquals(2, recovery.getDemandChange());
	  }
	
	@Test
	 public void testStationPurPriceMutatorAndAccessor()
	  {
	    assertEquals(48, recovery.getStationPurPrice());
	    recovery.setStationPurPrice(75);
	    assertEquals(75, recovery.getStationPurPrice());
	  }
	 
	@Test
	 public void testStationLiqPriceMutatorAndAccessor()
	  {
	    assertEquals(24, recovery.getStationLiqPrice());
	    recovery.setStationLiqPrice(45);
	    assertEquals(45, recovery.getStationLiqPrice());
	  }
	 
	@Test
	 public void testRigPurPriceMutatorAndAccessor()
	  {
	    assertEquals(5, recovery.getRigPurPrice());
	    recovery.setRigPurPrice(12);
	    assertEquals(12, recovery.getRigPurPrice());
	  }
	 
	@Test
	 public void testRigLiqPriceMutatorAndAccessor()
	  {
	    assertEquals(3, recovery.getRigLiqPrice());
	    recovery.setRigLiqPrice(8);
	    assertEquals(8, recovery.getRigLiqPrice());
	  }
	
	@Test
	 public void testRefinePurPriceMutatorAndAccessor()
	  {
	    assertEquals(64, recovery.getRefinePurPrice());
	    recovery.setRefinePurPrice(120);
	    assertEquals(120, recovery.getRefinePurPrice());
	  }
	 
	@Test
	 public void testRefineLiqPriceMutatorAndAccessor()
	  {
	    assertEquals(30, recovery.getRefineLiqPrice());
	    recovery.setRefineLiqPrice(80);
	    assertEquals(80, recovery.getRefineLiqPrice());
	  }
	 
	@Test
	 public void testWellLiqPriceMutatorAndAccessor()
	  {
	    assertEquals(30, recovery.getWellLiqPrice());
	    recovery.setWellLiqPrice(60);
	    assertEquals(60, recovery.getWellLiqPrice());
	  }
	 
	 
}	

	 
	
	
	


