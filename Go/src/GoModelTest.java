import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GoModelTest {

	private GoModel model;
	
	@Before
	public void setUp() throws Exception {
		model = new GoModel(5);
	}

	@Test
	public void testPlaySimple() {
		assertTrue(model.play(2, 3));
		assertEquals(StdDraw.BLACK, model.get(2, 3));
		assertTrue(model.play(3, 0));
		assertEquals(StdDraw.WHITE, model.get(3, 0));
	}

	@Test
	public void testPlayOffBoard() {
		assertFalse(model.play(5, 2));
	}

	@Test
	public void testPlayOccupied() {
		assertTrue(model.play(2, 3));
		assertFalse(model.play(2, 3));
		assertEquals(StdDraw.BLACK, model.get(2,  3));
	}

	@Test
	public void testSimpleCapture() {
		for (int[] move : new int[][] {{1, 1}, {1, 2}, {2, 0}, {2, 3}, {3, 1}, {3, 2}, {2, 2}}) {
			model.play(move[0], move[1]);
		}
		assertTrue(model.play(2, 1));
		assertNull(model.get(2, 2));		
	}

	@Test
	public void testSuicide() {
		for (int[] move : new int[][] {{3, 0}, {4, 1}, {3, 1}, {3, 2}, {4, 2}}) {
			model.play(move[0], move[1]);
		}
		String before =
				".....\n" +
				".....\n" +
				".....\n" +
				"##O..\n" +
				".O#..\n";
		assertEquals(before, model.toString());
		assertFalse(model.play(4, 0));
		assertEquals(before, model.toString());	
	}

	@Test
	public void testComplexCapture() {
		for (int[] move : new int[][] {{1, 1}, {2, 1}, {1, 2}, {2, 2}, {2, 0}, {3, 0}, {2, 3}, {3, 2}, {2, 4}, {3, 3}, {3, 4}, {4, 0}, {4, 1}, {4, 2}, {4, 4}, {4, 3}}) {
			model.play(move[0], move[1]);
		}
		String before =
				".....\n" +
				".##..\n" +
				"#OO##\n" +
				"O.OO#\n" +
				"O#OO#\n";
		assertEquals(before, model.toString());
		assertTrue(model.play(3, 1));
		String after =
				".....\n" +
				".##..\n" +
				"#..##\n" +
				".#..#\n" +
				".#..#\n";
		assertEquals(after, model.toString());
	}
	
	@Test
	public void testSimpleKo() {
		testSimpleCapture();
		assertFalse(model.play(2, 2));
	}

	@Test
	public void testComplexKo() {
		for (int[] move : new int[][] {{0, 2}, {0, 1}, {0, 3}, {1, 0}, {1, 1}, {1, 3}, {1, 4}, {2, 4}, {3, 1}, {3, 0}, {4, 2}, {4, 1}}) {
			model.play(move[0], move[1]);
		}
		String before =
				".O##.\n" +
				"O#.O#\n" +
				"....O\n" +
				"O#...\n" +
				".O#..\n";
		assertEquals(before, model.toString());
		assertTrue(model.play(0, 0));
		assertTrue(model.play(0, 4));
		assertTrue(model.play(4, 0));
		assertTrue(model.play(0, 1));
		assertTrue(model.play(1, 4));
		String justBefore =
				".O##.\n" +
				"O#.O#\n" +
				"....O\n" +
				"O#...\n" +
				"#.#..\n";
		assertEquals(justBefore, model.toString());
		assertFalse(model.play(4, 1));
	}

	@Test
	public void testGameOver() {
		assertFalse(model.gameOver());
		model.pass();
		assertEquals(StdDraw.WHITE, model.getCurrentPlayer());
		assertFalse(model.gameOver());
		model.pass();
		assertEquals(StdDraw.BLACK, model.getCurrentPlayer());
		assertTrue(model.gameOver());
	}
	
	@Test
	public void testUndo() {
		for (int[] move : new int[][] {{0, 0}, {0, 1}, {0, 2}}) {
			model.play(move[0], move[1]);
		}
		model.pass();
		model.undo();
		String back1 =
				"#O#..\n" +
				".....\n" +
				".....\n" +
				".....\n" +
				".....\n";
		assertEquals(back1, model.toString());
		assertEquals(StdDraw.WHITE, model.getCurrentPlayer());
		// Since a pass undone, adding one more shouldn't end the game
		model.pass();
		assertFalse(model.gameOver());
		model.undo();
		model.undo();
		String back2 =
				"#O...\n" +
				".....\n" +
				".....\n" +
				".....\n" +
				".....\n";
		assertEquals(back2, model.toString());
		assertEquals(StdDraw.BLACK, model.getCurrentPlayer());
		model.undo();
		String back3 =
				"#....\n" +
				".....\n" +
				".....\n" +
				".....\n" +
				".....\n";
		assertEquals(back3, model.toString());
		assertEquals(StdDraw.WHITE, model.getCurrentPlayer());
		model.undo();
		String back4 =
				".....\n" +
				".....\n" +
				".....\n" +
				".....\n" +
				".....\n";
		assertEquals(back4, model.toString());
		assertEquals(StdDraw.BLACK, model.getCurrentPlayer());
		// One more undo should have no effect, but not crash
		model.undo();
		assertEquals(back4, model.toString());
	}
	
	@Test
	public void testScore() {
		for (int[] move : new int[][] {{0, 1}, {1, 2}, {0, 3}, {2, 1}, {1, 1}, {2, 3}, {1, 3}, {3, 1}, {2, 0}, {3, 3}, {2, 4}, {4, 1}, {3, 4}, {4, 3}}) {
			model.play(move[0], move[1]);
		}
		String board =
				".#.#.\n" +
				".#O#.\n" +
				"#O.O#\n" +
				".O.O#\n" +
				".O.O.\n";
		assertEquals(board, model.toString());
		// Black is ahead by 1 on the board, but komi is 7.5
		assertEquals(6.5, model.score(), 0.001);
	}
	
	@Test
	public void testConsecutivePasses() {
		model.pass();
		model.play(0, 0);
		model.pass();
		assertFalse(model.gameOver());
	}

}
