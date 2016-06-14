import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GoModelTestHighLevel {

	private GoModel model;

	@Before
	public void setUp() throws Exception {
		model = new GoModel(9);
	}

	@Test
	public void playsCompleteGame() {
		for (int[] moves : new int[][] { { 4, 4 }, { 5, 3 }, { 4, 3 }, { 5, 4 }, { 5, 5 }, { 6, 5 }, { 5, 6 }, { 6, 6 },
				{ 5, 2 }, { 2, 6 }, { 2, 4 }, { 4, 7 }, { 5, 7 }, { 1, 5 }, { 1, 4 }, { 6, 2 }, { 6, 3 }, { 6, 4 },
				{ 7, 3 }, { 7, 2 }, { 5, 1 }, { 6, 7 }, { 4, 8 }, { 3, 8 }, { 3, 7 }, { 5, 8 }, { 4, 6 }, { 4, 8 },
				{ 2, 8 }, { 6, 8 }, { 3, 6 }, { 2, 7 }, { 7, 1 }, { 6, 1 }, { 6, 0 }, { 7, 4 }, { 8, 3 }, { 0, 4 },
				{ 0, 3 }, { 0, 5 }, { 1, 2 }, { 3, 5 }, { 4, 5 }, { 3, 4 }, { 3, 3 }, { 2, 5 }, { 8, 4 }, { 8, 5 },
				{ 8, 2 }, { 1, 8 } }) {
			model.play(moves[0], moves[1]);
		}
		model.pass();
		model.pass();
		String end =
				"...#OO...\n" +
				"..#.#O..O\n" +
				"....#OOO.\n" +
				"...#OO##O\n" +
				"...####OO\n" +
				".##OO###O\n" +
				"#..#OOOOO\n" +
				".#.#O....\n" +
				"..###O...\n";
		assertEquals(end, model.toString());
		assertEquals(46, model.score(StdDraw.BLACK));
		assertEquals(35, model.score(StdDraw.WHITE));
		for (int i = 0; i < 10; i++) {
			model.undo();
		}
		String earlier =
				"...#OO...\n" +
				"..#.#O...\n" +
				"....#.OO#\n" +
				".....O##O\n" +
				"...##.#OO\n" +
				".##OO###O\n" +
				"#OO#OOOOO\n" +
				".#O#O....\n" +
				"...#.....\n";
		assertEquals(earlier, model.toString());
	}

}
