import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class Connect4ModelTest {

	private Connect4Model model;
	
	@Before
	public void setUp() {
		model = new Connect4Model();
	}

	@Test
	public void testCurrentPlayer() {
		assertEquals(StdDraw.BLACK, model.getCurrentPlayer());
		model.toggleCurrentPlayer();
		assertEquals(StdDraw.WHITE, model.getCurrentPlayer());
	}

	@Test
	public void testDrop() {
		model.drop(StdDraw.BLACK, 2);
		assertEquals(StdDraw.BLACK, model.colorAt(0, 2));
		model.drop(StdDraw.WHITE, 2);
		assertEquals(StdDraw.BLACK, model.colorAt(0, 2));
		assertEquals(StdDraw.WHITE, model.colorAt(1, 2));
		model.drop(StdDraw.BLACK, 2);
		assertEquals(StdDraw.BLACK, model.colorAt(0, 2));
		assertEquals(StdDraw.WHITE, model.colorAt(1, 2));
		assertEquals(StdDraw.BLACK, model.colorAt(2, 2));
	}

	@Test
	public void testLegal() {
		assertFalse(model.isLegal(-1));		
		assertFalse(model.isLegal(7));		
		assertTrue(model.isLegal(6));		
		model.drop(StdDraw.BLACK, 2);
		model.drop(StdDraw.WHITE, 2);
		model.drop(StdDraw.BLACK, 2);
		model.drop(StdDraw.WHITE, 2);
		model.drop(StdDraw.BLACK, 2);
		assertTrue(model.isLegal(2));
		model.drop(StdDraw.WHITE, 2);
		// Column 2 is now full
		assertFalse(model.isLegal(2));
	}

	@Test
	public void testFull() {
		for (int r = 0; r < Connect4Model.ROWS; r++) {
			for (int c = 0; c < Connect4Model.COLUMNS; c++) {
				assertFalse(model.isFull());
				model.drop(StdDraw.BLACK, c);
			}
		}
		assertTrue(model.isFull());
	}

	@Test
	public void testHorizontalWinner() {
		model.drop(StdDraw.BLACK, 2);
		model.drop(StdDraw.BLACK, 3);
		model.drop(StdDraw.BLACK, 5);
		model.drop(StdDraw.BLACK, 6);
		assertEquals(StdDraw.GRAY, model.winner());
		model.drop(StdDraw.BLACK, 4);
		assertEquals(StdDraw.BLACK, model.winner());
	}
	
	@Test
	public void testVerticalWinner() {
		for (int i = 0; i < 4; i++) {
			model.drop(StdDraw.WHITE, 6);
		}
		assertEquals(StdDraw.WHITE, model.winner());
	}
	
	@Test
	public void testDiagonalWinner1() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < i; j++) {
				model.drop(StdDraw.WHITE, i);
			}
			model.drop(StdDraw.BLACK, i);
		}
		assertEquals(StdDraw.BLACK, model.winner());
	}

	@Test
	public void testDiagonalWinner2() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < i; j++) {
				model.drop(StdDraw.BLACK, 5 - i);
			}
			model.drop(StdDraw.WHITE, 5 - i);
		}
		assertEquals(StdDraw.WHITE, model.winner());
	}
	
	@Test
	public void testUndo() {
		model.drop(StdDraw.BLACK, 2);
		model.drop(StdDraw.WHITE, 2);
		model.undo(2);
		assertEquals(StdDraw.BLACK, model.colorAt(0, 2));
		assertEquals(StdDraw.GRAY, model.colorAt(1, 2));
	}

	@Test
	public void testMax0() {
		for (int i = 0; i < 3; i++) {
			model.drop(StdDraw.BLACK, i);
			model.drop(StdDraw.WHITE, Connect4Model.COLUMNS - 1 - i);
		}
		assertEquals(0, model.max(0, 0));
		model.drop(StdDraw.BLACK, 3);
		assertEquals(1, model.max(0, 0));
		model.undo(3);
		model.drop(StdDraw.WHITE, 3);
		assertEquals(-1, model.max(0, 0));
	}
	
	@Test
	public void testMax1() {
		// Black cannot win in one move
		assertEquals(0, model.max(1, 0));
		model.drop(StdDraw.BLACK, 0);
		model.drop(StdDraw.BLACK, 0);
		model.drop(StdDraw.BLACK, 0);
		// Now black can win in one move
		assertEquals(1, model.max(1, 0));
	}

	@Test
	public void testMax2() {
		for (int i = 0; i < 3; i++) {
			model.drop(StdDraw.WHITE, i + 2);
		}
		// Black cannot prevent white from winning
		assertEquals(-1, model.max(2, 0));
		model.drop(StdDraw.BLACK, 0);
		model.drop(StdDraw.BLACK, 0);
		model.drop(StdDraw.BLACK, 0);
		// Now black can win in one move
		assertEquals(1, model.max(2, 0));
	}

	@Test
	public void testMin3() {
		for (int i = 0; i < 2; i++) {
			model.drop(StdDraw.WHITE, i + 2);
		}
		// White can win in three moves
		assertEquals(-1, model.min(3, 0));
	}
	
	@Test
	public void testBestMoveForBlack() {
		for (int i = 0; i < 3; i++) {
			model.drop(StdDraw.WHITE, 5);
		}
		// Black has to block
		assertEquals(5, model.bestMoveForBlack(3));
	}

	@Test
	public void testDeepSearch() {
		model.drop(StdDraw.WHITE, 0);
		model.drop(StdDraw.BLACK, 1);
		model.drop(StdDraw.WHITE, 2);
		model.drop(StdDraw.BLACK, 5);
		model.drop(StdDraw.BLACK, 6);
		model.drop(StdDraw.BLACK, 0);
		model.drop(StdDraw.BLACK, 1);
		model.drop(StdDraw.BLACK, 2);
		// Black 3 or 4 force a win on black's next move
		// Similarly, black 3 forces white to block at 3
		int move = model.bestMoveForBlack(3);
		assertTrue(move == 3 || move == 4);
		// This sequence can't be seen in a shallower search
		move = model.bestMoveForBlack(1);
		assertFalse(move == 3 || move == 4);
		move = model.bestMoveForBlack(2);
		assertFalse(move == 3 || move == 4);
	}

}
