import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class HandTest {

	private Hand hand;

	@Before
	public void setUp() throws Exception {
		hand = new Hand();
	}

	@Test
	public void allRollsAreEquallyLikely() {
		// counts[i][j] is the number of times die i has come up with value j
		int[][] counts = new int[5][6];
		// Roll the whole hand many times
		for (int i = 0; i < 10000; i++) {
			hand.roll();
			for (int j = 0; j < 5; j++) {
				counts[j][hand.get(j)]++;
			}
		}
		// Each number should be about equally likely for each die
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 6; j++) {
				assertTrue(counts[i][j] > 0.75 * (10000 / 6));
				assertTrue(counts[i][j] < 1.25 * (10000 / 6));
			}
		}
	}
	
	@Test
	public void storesKeptFlags() {
		assertFalse(hand.isKept(1));
		hand.setKept(1, true);
		assertTrue(hand.isKept(1));
		assertFalse(hand.isKept(0));
		hand.setKept(1, false);
		assertFalse(hand.isKept(1));
	}
	
	@Test
	public void detectsAllKept() {
		for (int i = 1; i < 5; i++) {
			hand.setKept(i, true);
		}
		assertFalse(hand.allKept());
		hand.setKept(0, true);
		assertTrue(hand.allKept());
	}

	@Test
	public void unkeptDiceAreRerolled() {
		// Mark dice 0 and 3 for keeping
		hand.setKept(0, true);
		hand.setKept(3, true);
		// counts[i][j] is the number of times die i has come up with value j
		int[][] counts = new int[5][6];
		// Roll the hand many times
		for (int i = 0; i < 10000; i++) {
			hand.roll();
			for (int j = 0; j < 5; j++) {
				counts[j][hand.get(j)]++;
			}
		}
		// The kept dice should stay at 0.
		for (int i : new int[] {0, 3}) {
			assertEquals(10000, counts[i][0]);
		}		
		// Each number should be about equally likely for the rerolled dice
		for (int i : new int[] {1, 2, 4}) {
			for (int j = 0; j < 6; j++) {
				assertTrue(counts[i][j] > 0.75 * (10000 / 6));
				assertTrue(counts[i][j] < 1.25 * (10000 / 6));
			}
		}
	}

	@Test
	public void scoresFiveOfAKind() {
		hand.set(2, 3, 2, 2, 2);
		assertEquals(-1, hand.fiveOfAKindScore());
		hand.set(4, 4, 4, 4, 4);
		assertEquals(740000, hand.fiveOfAKindScore());
	}

	@Test
	public void scoresFourOfAKind() {
		// TODO You have to write this
		fail("You have to write this test");
	}

	@Test
	public void scoresFullHouse() {
		// TODO You have to write this
		fail("You have to write this test");
	}

	@Test
	public void scoresStraight() {
		// TODO You have to write this
		fail("You have to write this test");
	}

	@Test
	public void scoresThreeOfAKind() {
		// TODO You have to write this
		fail("You have to write this test");
	}

	@Test
	public void scoresTwoPair() {
		// TODO You have to write this
		fail("You have to write this test");
	}

	@Test
	public void scoresOnePair() {
		// TODO You have to write this
		fail("You have to write this test");
	}

	@Test
	public void scoresHighCard() {
		// TODO You have to write this
		fail("You have to write this test");
	}

	@Test
	public void scoresGenerally() {
		hand.set(2, 2, 2, 2, 2);
		assertEquals(720000, hand.getScore());
		hand.set(4, 0, 0, 0, 4);
		assertEquals(504000, hand.getScore());
		hand.set(3, 2, 3, 3, 5);
		assertEquals(335200, hand.getScore());
		hand.set(2, 1, 4, 5, 0);
		assertEquals(54210, hand.getScore());		
	}

}