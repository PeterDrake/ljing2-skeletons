import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PokerDiceModelTest {

	private PokerDiceModel model;
	
	@Before
	public void setUp() throws Exception {
		model = new PokerDiceModel(3);
	}

	@Test
	public void diceAreNotNull() {
		assertNotNull(model.getHand(2));
	}
	
	@Test
	public void diceAreDifferentObjects() {
		assertNotSame(model.getHand(1), model.getHand(2));
	}

	@Test
	public void storesNumberOfPlayers() {
		assertEquals(3, model.getNumberOfPlayers());
		model = new PokerDiceModel(4);
		assertEquals(4, model.getNumberOfPlayers());
	}

	@Test
	public void advancesCurrentPlayer() {
		assertEquals(0, model.getCurrentPlayer());
		model.advanceCurrentPlayer();
		assertEquals(1, model.getCurrentPlayer());
	}

	@Test
	public void turnEndsAfterThreeRolls() {
		model.roll();
		model.roll();
		assertFalse(model.turnOver());
		model.roll();
		assertTrue(model.turnOver());
	}

	@Test
	public void turnEndsWhenAllDiceKept() {
		for (int i = 0; i < 5; i++) {
			model.getHand(0).setKept(i, true);
		}
		assertFalse(model.turnOver());
		model.roll();
		assertTrue(model.turnOver());
	}

	@Test
	public void turnIsNotOverForNewPlayer() {
		model.roll();
		model.roll();
		model.roll();
		assertTrue(model.turnOver());
		model.advanceCurrentPlayer();
		assertFalse(model.turnOver());
	}

	@Test
	public void gameEndsAfterAllTurns() {
		model.advanceCurrentPlayer();
		model.advanceCurrentPlayer();
		assertFalse(model.gameOver());
		model.advanceCurrentPlayer();
		assertTrue(model.gameOver());
	}

	@Test
	public void detectsWinner() {
		model.getHand(0).set(0, 0, 1, 4, 1);
		model.getHand(1).set(2, 2, 2, 3, 2);
		model.getHand(2).set(5, 4, 3, 2, 1);
		assertFalse(model.isWinner(0));
		assertTrue(model.isWinner(1));
		assertFalse(model.isWinner(2));
	}

	@Test
	public void resolvesTies() {
		model.getHand(0).set(2, 3, 2, 2, 2);
		model.getHand(1).set(2, 2, 2, 3, 2);
		model.getHand(2).set(5, 4, 3, 2, 1);
		assertTrue(model.isWinner(1));
		assertTrue(model.isWinner(1));
		assertFalse(model.isWinner(2));		
	}

}
