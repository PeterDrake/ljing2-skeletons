import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FlagTest {

	public static final double DELTA = 0.001;
	
	private Flag flag;
	
	@Before
	public void setUp() throws Exception {
		flag = new Flag(0.4, 0.6);
	}

	@Test
	public void storesExtent() {
		assertEquals(0.6, flag.getExtent().getY(), DELTA);
		assertEquals(0.01, flag.getExtent().getRadius(), DELTA);
	}

	@Test
	public void storesHitByShip() {
		assertFalse(flag.hasBeenHitByShip1());
		assertFalse(flag.hasBeenHitByShip2());
		flag.setHitByShip2();
		assertFalse(flag.hasBeenHitByShip1());
		assertTrue(flag.hasBeenHitByShip2());
		flag.setHitByShip1();
		assertTrue(flag.hasBeenHitByShip1());
		assertTrue(flag.hasBeenHitByShip2());
	}

}
