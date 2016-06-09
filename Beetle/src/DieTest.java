import static org.junit.Assert.*;

import org.junit.Test;

public class DieTest {

	@Test
	public void initialTopFaceIs1() {
		Die d = new Die();
		assertEquals(1, d.getTopFace());
	}

	@Test
	public void allNumbersAreEquallyLikely() {
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
	public void noExtraneousNumbersAppear() {
		Die d = new Die();
		int[] counts = new int[7];
		// Roll the die many times
		for (int i = 0; i < 600; i++) {
			d.roll();
			counts[d.getTopFace()]++;
		}
		// The 1 through 6 should account for all rolls
		int sum = 0;
		for (int i = 1; i <= 6; i++) {
			sum += counts[i];
		}
		assertEquals(sum, 600);
	}

	@Test
	public void multipleDiceCanShowDifferentTopFaces() {
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
