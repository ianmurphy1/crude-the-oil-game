import static org.junit.Assert.*;

import org.junit.Test;


/**
 * @authors Denis Murphy, Ian Murphy & Bill O'Keeffe
 *
 */
public class CounterTest
{

	@Test
	public void testCreateCounter() {
		Counter counter = new Counter(0,8);
		assertNotNull(counter);
		assertEquals(8, counter.getLimit());
		assertEquals(0, counter.getStartingPoint());
		assertEquals(0, counter.getCount());		
	}
	
	
	@Test
	public void testCounterReset() {
		Counter counterA = new Counter(0, 8);
		Counter counterB = new Counter(1, 4);
		
		counterA.setCount(5);
		counterA.increaseBy(3);
		assertEquals(0, counterA.getCount());
		assertEquals(true, counterA.isTurnedOver());
		
		
		counterA.setCount(6);
		counterA.increaseBy(3);
		assertEquals(0, counterA.getCount());
		
		counterB.setCount(3);
		counterB.increment();
		assertEquals(1, counterB.getCount());
	}
}
