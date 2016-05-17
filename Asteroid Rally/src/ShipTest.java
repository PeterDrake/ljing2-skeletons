import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ShipTest {

	public static final double DELTA = 0.001;

	private Ship ship;

	@Before
	public void setUp() throws Exception {
		ship = new Ship(0.5, 0.5, 0.0);
	}

	@Test
	public void testDrift() {
		ship.accelerate(0.1);
		Extent e = ship.getExtent();
		assertEquals(0.5, e.getX(), DELTA);
		assertEquals(0.5, e.getY(), DELTA);
		assertEquals(0.025, e.getRadius(), DELTA);
		ship.drift();
		assertEquals(0.6, e.getX(), DELTA);
		assertEquals(0.5, e.getY(), DELTA);
		// Merely changing the direction the ship is facing should not change
		// the direction it is drifting
		ship.rotate(Math.PI / 2);
		ship.drift();
		assertEquals(0.7, e.getX(), DELTA);
		assertEquals(0.5, e.getY(), DELTA);
	}

	@Test
	public void testWrapAround() {
		ship.rotate(Math.PI * 1.0 / 2); // Point north
		ship.accelerate(0.1);
		Extent e = ship.getExtent();
		for (int i = 0; i < 6; i++) {
			ship.drift();
		}
		assertEquals(0.5, e.getX(), DELTA);
		assertEquals(0.1, e.getY(), DELTA);
	}

	@Test
	public void testAngularDrift() {
		ship.rotate(Math.atan(3.0 / 4) + Math.PI);
		ship.accelerate(0.5);
		Extent e = ship.getExtent();
		ship.drift();
		assertEquals(0.1, e.getX(), DELTA);
		assertEquals(0.2, e.getY(), DELTA);
		ship.drift();
		assertEquals(0.7, e.getX(), DELTA);
		assertEquals(0.9, e.getY(), DELTA);
	}

}
