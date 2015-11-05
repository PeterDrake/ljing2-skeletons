import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class FocusModelTest {

	private FocusModel model;
	
	@Before
	public void setUp() throws Exception {
		model = new FocusModel();
	}

	/** Test some piles in the initial setup. */
	@Test
	public void testGetPile() {
		Deque<Integer> pile = model.getPile(new Location(1, 6));
		assertEquals(1, pile.size());
		assertEquals(new Integer(FocusModel.BLACK), pile.removeFront());
		pile = model.getPile(new Location(3, 3));
		assertEquals(1, pile.size());
		assertEquals(new Integer(FocusModel.WHITE), pile.removeFront());
		// This square is off the board, so has a null pile
		assertNull(model.getPile(new Location(0, 1)));
	}
	
	@Test
	public void testIsOnBoard() {
		assertTrue(model.isOnBoard(new Location(4, 5)));
		assertFalse(model.isOnBoard(new Location(4, 8)));
		assertFalse(model.isOnBoard(new Location(-1, 5)));
		assertFalse(model.isOnBoard(new Location(6, 7)));
	}

	@Test
	public void testIsLegalSource() {
		assertFalse(model.isLegalSource(new Location(0, 1)));
		Location there = new Location(3, 0);
		Deque<Integer> pile = model.getPile(there);
		assertFalse(model.isLegalSource(there));
		pile.addBack(FocusModel.WHITE);
		pile.addBack(FocusModel.WHITE);
		assertFalse(model.isLegalSource(there));
		pile.addBack(FocusModel.BLACK);
		assertTrue(model.isLegalSource(there));		
	}

	@Test
	public void testIsLegalMove() {
		Location source = new Location(2, 3);
		model.getPile(source).addBack(FocusModel.WHITE);
		model.getPile(source).addBack(FocusModel.BLACK);
		// Normal move
		assertTrue(model.isLegalMove(source, new Location(2, 6)));
		// Short move
		assertTrue(model.isLegalMove(source, new Location(2, 6)));
		// Illegal move too far
		assertFalse(model.isLegalMove(source, new Location(6, 3)));		
		// Illegal diagonal move
		assertFalse(model.isLegalMove(source, new Location(3, 4)));
	}

	@Test
	public void testMove() {
		Location source = new Location(2, 2);
		model.getPile(source).addBack(FocusModel.WHITE);
		model.getPile(source).addBack(FocusModel.WHITE);
		model.getPile(source).addBack(FocusModel.BLACK);
		model.getPile(source).addBack(FocusModel.BLACK);
		Location destination = new Location(6, 2);
		model.move(source, destination);
		assertEquals(1, model.getPile(source).size());
		assertEquals(new Integer(FocusModel.WHITE), model.getPile(source).removeBack());
		assertEquals(5, model.getPile(destination).size());
		assertEquals(new Integer(FocusModel.BLACK), model.getPile(destination).removeBack());
		assertEquals(new Integer(FocusModel.BLACK), model.getPile(destination).removeBack());
		assertEquals(new Integer(FocusModel.WHITE), model.getPile(destination).removeBack());
		assertEquals(new Integer(FocusModel.WHITE), model.getPile(destination).removeBack());
		assertEquals(new Integer(FocusModel.WHITE), model.getPile(destination).removeBack());
	}

	@Test
	public void testToggleColorToPlay() {
		assertEquals(FocusModel.BLACK, model.getCurrentPlayer());
		model.toggleColorToPlay();
		assertEquals(FocusModel.WHITE, model.getCurrentPlayer());
		model.toggleColorToPlay();
		assertEquals(FocusModel.BLACK, model.getCurrentPlayer());
	}

	@Test
	public void testCapturesAndReserves() {
		Location source = new Location(4, 2);
		Deque<Integer> s = model.getPile(source);
		s.addBack(FocusModel.BLACK);
		s.addBack(FocusModel.WHITE);
		s.addBack(FocusModel.WHITE);
		s.addBack(FocusModel.BLACK);
		Location destination = new Location(4, 5);
		Deque<Integer> d = model.getPile(destination);
		d.addBack(FocusModel.BLACK);
		d.addBack(FocusModel.WHITE);
		d.addBack(FocusModel.WHITE);
		d.addBack(FocusModel.BLACK);
		assertEquals(0, model.getReserves(FocusModel.BLACK));
		model.move(source, destination);
		assertEquals(5, d.size());
		assertEquals(1, model.getReserves(FocusModel.BLACK));
		assertEquals(0, model.getReserves(FocusModel.WHITE));
	}

	@Test
	public void testReservesAsLegalSource() {
		assertFalse(model.isLegalSource(FocusModel.RESERVES_LOCATIONS[FocusModel.BLACK]));
		testCapturesAndReserves();
		// Make another move to flip the color to play back to black
		model.move(new Location(2, 1), new Location(1, 1));
		assertTrue(model.isLegalSource(FocusModel.RESERVES_LOCATIONS[FocusModel.BLACK]));
		assertFalse(model.isLegalSource(FocusModel.RESERVES_LOCATIONS[FocusModel.WHITE]));
	}

	@Test
	public void testMoveFromReserves() {
		Location d = new Location(2, 6);
		testCapturesAndReserves();
		// Make another move to flip the color to play back to black
		model.move(new Location(2, 1), new Location(1, 1));
		assertTrue(model.isLegalMove(FocusModel.RESERVES_LOCATIONS[FocusModel.BLACK], d));
		model.move(FocusModel.RESERVES_LOCATIONS[FocusModel.BLACK], d);
		assertEquals(2, model.getPile(d).size());
		assertEquals(new Integer(FocusModel.BLACK), model.getPile(d).removeBack());
	}

	@Test
	public void testIsGameOver() {
		assertFalse(model.isGameOver());
		testCapturesAndReserves();
		// Make another move to flip the color to play back to black
		model.move(new Location(2, 1), new Location(1, 1));
		// Add white pieces everywhere
		for (int r = 0; r < FocusModel.BOARD_WIDTH; r++) {
			for (int c = 0; c < FocusModel.BOARD_WIDTH; c++) {
				Location location = new Location(r, c);
				if (model.isOnBoard(location)) {
					model.getPile(location).addBack(FocusModel.WHITE);
				}
			}
		}
		// Black still has a reserve
		assertFalse(model.isGameOver());
		// Play that reserve, then cover it up with a white piece
		model.move(FocusModel.RESERVES_LOCATIONS[FocusModel.BLACK], new Location(6, 1));
		model.move(new Location(6, 2), new Location(6, 1));
		assertTrue(model.isGameOver());
	}

}
