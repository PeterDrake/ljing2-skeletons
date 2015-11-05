import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class HexModelTest {

	private HexModel model;
	
	public void setUpProblem(String... diagram) {
		for (int r = 0; r < diagram.length; r++) {
			int c = 0;
			for (char color : diagram[r].toCharArray()) {
				if ((color != ' ') && (color != '\n')) {
					if (color == '#') {
						model.setColor(r, c, HexModel.BLACK);
					} else if (color == 'O') {
						model.setColor(r, c, HexModel.WHITE);
					}
					c++;
				}
			}
		}
	}

	@Before
	public void setUp() throws Exception {
		model = new HexModel(4);
	}

	@Test
	public void testGetWidth() {
		assertEquals(4, model.getWidth());
		model = new HexModel(10);
		assertEquals(10, model.getWidth());
	}

	@Test
	public void testToString() {
		assertEquals(" 0 1 2 3 \n0 . . . . 0\n 1 . . . . 1\n  2 . . . . 2\n   3 . . . . 3\n      0 1 2 3 \n", model.toString());
	}

	@Test
	public void testSetColor() {
		model.setColor(2, 3, HexModel.BLACK);
		assertEquals(" 0 1 2 3 \n0 . . . . 0\n 1 . . . . 1\n  2 . . . # 2\n   3 . . . . 3\n      0 1 2 3 \n", model.toString());
	}

	@Test
	public void testSetAndGetColor() {
		assertEquals(HexModel.VACANT, model.getColor(3, 2));
		model.setColor(3, 2, HexModel.WHITE);
		assertEquals(HexModel.WHITE, model.getColor(3, 2));
		model.setColor(3, 2, HexModel.VACANT);
		assertEquals(HexModel.VACANT, model.getColor(3, 2));
	}

	@Test
	public void testNeighborWiringInCenter() {
		assertEquals(6, model.getNeighborCount(model.getNode(1, 1)));
		assertTrue(model.getNode(1, 1).isNeighborOf(model.getNode(0, 1)));
		assertTrue(model.getNode(1, 1).isNeighborOf(model.getNode(0, 2)));
		assertTrue(model.getNode(1, 1).isNeighborOf(model.getNode(1, 0)));
		assertTrue(model.getNode(1, 1).isNeighborOf(model.getNode(1, 2)));
		assertTrue(model.getNode(1, 1).isNeighborOf(model.getNode(2, 0)));
		assertTrue(model.getNode(1, 1).isNeighborOf(model.getNode(2, 1)));
	}

	@Test
	public void testNeighborWiringAtEdge() {
		assertEquals(4, model.getNeighborCount(model.getNode(3, 3)));
		assertTrue(model.getNode(3, 3).isNeighborOf(model.getNode(3, 2)));
		assertTrue(model.getNode(3, 3).isNeighborOf(model.getNode(2, 3)));
		assertTrue(model.getNode(3, 3).isNeighborOf(model.getEast()));
		assertTrue(model.getNode(3, 3).isNeighborOf(model.getSouth()));
	}
	
	@Test
	public void testSentinelsAreDifferent() {
		assertNotSame(model.getNorth(), model.getSouth());
		assertNotSame(model.getWest(), model.getEast());
	}
	
	@Test
	public void testFindWinner() {
		setUpProblem("# . . .",
					  "# # # #",
					   "O O O .",
					    ". . # O");
		assertEquals(HexModel.VACANT, model.findWinner());
		model.setColor(2, 3, HexModel.BLACK);
		assertEquals(HexModel.BLACK, model.findWinner());
		model.setColor(2, 3, HexModel.WHITE);
		assertEquals(HexModel.WHITE, model.findWinner());
	}

	@Test
	public void testGetCurrentPlayer() {
		assertEquals(HexModel.BLACK, model.getCurrentPlayer());
		model.playAt(1, 2);
		assertEquals(HexModel.WHITE, model.getCurrentPlayer());
		model.playAt(2, 0);
		assertEquals(HexModel.BLACK, model.getCurrentPlayer());
	}

}
