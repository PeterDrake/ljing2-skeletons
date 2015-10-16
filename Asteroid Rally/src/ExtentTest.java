import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ExtentTest {

	public static final double DELTA = 0.001;
	
	private Extent extent;
	
	@Before
	public void setUp() throws Exception {
		extent = new Extent(0.5, 0.3, 0.2);
	}

	@Test
	public void testGetX() {
		assertEquals(0.5, extent.getX(), DELTA);
	}
	
	@Test
	public void testGetY() {
		assertEquals(0.3, extent.getY(), DELTA);
	}
	
	@Test
	public void testGetRadius() {
		assertEquals(0.2, extent.getRadius(), DELTA);
	}
	
	@Test
	public void testMove() {
		extent.move(0.1, -0.2);
		assertEquals(0.6, extent.getX(), DELTA);
		assertEquals(0.1, extent.getY(), DELTA);
		assertEquals(0.2, extent.getRadius(), DELTA);		
	}

	@Test
	public void testDistanceTo() {
		Extent a = new Extent(0.3, 0.0, 0.1);
		Extent b = new Extent(0.0, 0.4, 0.8);
		assertEquals(0.5, a.distanceTo(b), DELTA);
	}

	@Test
	public void testOverlaps() {
		assertTrue(new Extent(1, 0, 1.0).overlaps(new Extent(4, 0, 2.1)));
		assertFalse(new Extent(0, 1, 1.0).overlaps(new Extent(0, 4, 1.9)));
		assertTrue(new Extent(0, 0, 1.0).overlaps(new Extent(-1, -1, 1.0)));
	}

}
