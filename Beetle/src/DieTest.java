import static org.junit.Assert.*;

import org.junit.Test;

public class DieTest {

	@Test
	public void testInitialTopFace() {
		// A new die should have the 1 on top
		Die d = new Die();
		assertEquals(1, d.getTopFace());
	}

	@Test
	public void testRoll() {
		Die d = new Die();
		int[] counts = new int[7];
		// Roll the die many times
		for (int i = 0; i < 600; i++) {
			d.roll();
			counts[d.getTopFace()]++;
		}
		// Each number should be about equally likely
		for (int i = 1; i <= 6; i++) {
			assertTrue(counts[i] > 50);
			assertTrue(counts[i] < 150);
		}
	}

	@Test
	public void testMultipleDice() {
		Die d1 = new Die();
		Die d2 = new Die();
		int counter = 0;
		for (int i = 0; i < 100; i++) {
			d1.roll();
			d2.roll();
			if (d1.getTopFace() == d2.getTopFace()) {
				counter++;
			}
		}
		assertTrue(counter > 0);
		assertTrue(counter < 50);
	}

}
