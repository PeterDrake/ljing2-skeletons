import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AsteroidRallyModelTest {

	public static final double DELTA = 0.001;
	
	private AsteroidRallyModel model;
	
	@Before
	public void setUp() throws Exception {
		model = new AsteroidRallyModel();
	}

	@Test
	public void testConstructor() {
		assertEquals(0.25, model.getShip1().getExtent().getX(), DELTA);
		assertEquals(0.5, model.getShip1().getExtent().getY(), DELTA);
		assertEquals(0.75, model.getShip2().getExtent().getX(), DELTA);
		assertEquals(0.5, model.getShip2().getExtent().getY(), DELTA);
		assertNotNull(model.getFlags()[4]);
		assertNotNull(model.getAsteroids()[9]);
	}

	@Test
	public void testIsConflictingAsteroidPosition() {
		Extent[] asteroids = model.getAsteroids();
		asteroids[0] = new Extent(0.3, 0.5, 0.05);
		assertTrue(model.isConflictingAsteroidPosition(0)); // Overlaps ship 1
		asteroids[0] = new Extent(0.5, 0.25, 0.05);
		asteroids[1] = new Extent(0.5, 0.75, 0.05);
		assertFalse(model.isConflictingAsteroidPosition(1));
		asteroids[2] = new Extent(0.5, 0.7, 0.05);
		assertTrue(model.isConflictingAsteroidPosition(2)); // Overlaps asteroid 1		
	}

	@Test
	public void testIsConflictingFlagPosition() {
		Extent[] asteroids = model.getAsteroids();
		// Put all the asteroids on top of each other
		for (int i = 0; i < asteroids.length; i++) {
			asteroids[i] = new Extent(0.5, 0.5, 0.05);
		}
		Flag[] flags = model.getFlags();
		flags[0] = new Flag(0.24, 0.51);
		assertTrue(model.isConflictingFlagPosition(0)); // Overlaps ship 1
		flags[0] = new Flag(0.53, 0.48);
		assertTrue(model.isConflictingFlagPosition(0)); // Overlaps asteroids		
		flags[0] = new Flag(0.5, 0.25);
		flags[1] = new Flag(0.5, 0.75);
		assertFalse(model.isConflictingFlagPosition(1));
		flags[2] = new Flag(0.5, 0.26);
		assertTrue(model.isConflictingFlagPosition(2)); // Overlaps flag 1		
	}

	@Test
	public void testAdvance() {
		model.getShip2().accelerate(0.2);
		Flag[] flags = model.getFlags();
		for (int i = 0; i < flags.length; i++) {
			flags[i] = new Flag(0.5, 0.5);
		}		
		flags[2] = new Flag(0.75, 0.7);
		model.advance();
		for (Flag f : flags) {
			assertFalse(f.hasBeenHitByShip1());
		}
		assertTrue(flags[2].hasBeenHitByShip2());
		assertFalse(flags[3].hasBeenHitByShip2());
	}

	@Test
	public void testGameOverByExplosion() {
		assertEquals(0, model.winner());
		model.getAsteroids()[0] = new Extent(0.25, 0.5, 0.05);
		assertEquals(2, model.winner());
	}

	@Test
	public void testGameOverByScore() {
		for (Flag f : model.getFlags()) {
			f.setHitByShip1();
		}
		assertEquals(1, model.winner());
	}

}
