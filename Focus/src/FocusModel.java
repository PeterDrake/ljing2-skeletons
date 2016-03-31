

/**
 * Logic for the Focus game. Where Deques are used in this game, the front is
 * down and the back is up. This is somewhat confusing, but helpful in FocusGui,
 * where piles being drawn from the bottom up can be iterated through.
 */
public class FocusModel {

	/** The black player. Equal to 0 for array indexing. */
	public static final int BLACK = 0;

	/**
	 * The board width. Always 8, but defined as a constant to avoid magic
	 * numbers in code.
	 */
	public static final int BOARD_WIDTH = 8;

	/** Reserves locations, indexed by color. */
	public static final Location[] RESERVES_LOCATIONS = { new Location(-1, -1),
			new Location(-2, -2) };

	/** The white player. Equal to 1 for array indexing. */
	public static final int WHITE = 1;

	/**
	 * Piles of pieces on each square. The entries for the missing corner
	 * squares are null.
	 */
	private Deque<Integer>[][] board;

	/** BLACK or WHITE. */
	private int currentPlayer;

	/** Reserves, indexed by player color. */
	private Deque<Integer>[] reserves;

	// The SuppressWarning tag is necessary because of the "unsafe" generic
	// cast.
	@SuppressWarnings("unchecked")
	public FocusModel() {
		// Set up the board
		board = new Deque[8][8];
		String setup = "??....???bbwwbb?.wwbbww..bbwwbb..wwbbww..bbwwbb.?wwbbww???....??";
		int i = 0;
		for (int r = 0; r < BOARD_WIDTH; r++) {
			for (int c = 0; c < BOARD_WIDTH; c++) {
				char s = setup.charAt(i);
				if (s != '?') {
					board[r][c] = new Deque<Integer>();
					if (s == 'b') {
						board[r][c].addFront(BLACK);
					} else if (s == 'w') {
						board[r][c].addFront(WHITE);
					}
				}
				i++;
			}
		}
		reserves = new Deque[] { new Deque<Integer>(), new Deque<Integer>() };
		// Black plays first
		currentPlayer = BLACK;
	}

	/** BLACK or WHITE. */
	public int getCurrentPlayer() {
		return currentPlayer;
	}

	/** Returns the pile of pieces at a particular location. */
	public Deque<Integer> getPile(Location location) {
		return board[location.getRow()][location.getColumn()];
	}

	/** Returns the number of pieces color has in reserve. */
	public int getReserves(int color) {
		return reserves[color].size();
	}

	/**
	 * Returns true if the game is over, i.e., the current player has no movable
	 * pieces (including reserves).
	 */
	public boolean isGameOver() {
		if (reserves[currentPlayer].size() > 0) {
			return false;
		}
		for (int r = 0; r < BOARD_WIDTH; r++) {
			for (int c = 0; c < BOARD_WIDTH; c++) {
				Deque<Integer> pile = board[r][c];
				if (pile == null || pile.isEmpty()) {
					continue;
				}
				int top = pile.removeBack();
				pile.addBack(top);
				if (top == currentPlayer) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Returns true if it is legal to move from source to destination. Assumes
	 * source is a legal source.
	 */
	public boolean isLegalMove(Location source, Location destination) {
		int d = source.getDistanceTo(destination);
		return (destination.getRow() >= 0) // Can't move to reserves
				&& (d > 0) // Can't move to same place
				// The rest checks that source has enough pieces
				&& (source == RESERVES_LOCATIONS[BLACK]
					|| source == RESERVES_LOCATIONS[WHITE]
					|| d <= getPile(source).size());
	}

	/**
	 * Returns true if the current player can move from source (which might be
	 * one of RESERVES_LOCATIONS).
	 */
	public boolean isLegalSource(Location source) {
		for (int color = BLACK; color <= WHITE; color++) {
			if (source == RESERVES_LOCATIONS[color]) {
				return reserves[color].size() > 0 && currentPlayer == color;
			}
		}
		Deque<Integer> pile = getPile(source);
		if (pile == null || pile.isEmpty()) {
			return false;
		}
		int top = pile.removeBack();
		pile.addBack(top);
		return top == currentPlayer;
	}

	/** Returns true if location is on the board. */
	public boolean isOnBoard(Location location) {
		return location.getRow() >= 0 && location.getRow() < BOARD_WIDTH
				&& location.getColumn() >= 0
				&& location.getColumn() < BOARD_WIDTH
				&& getPile(location) != null;
	}

	/**
	 * Moves pieces from source (which might be one of RESERVES_LOCATIONS) to
	 * destination and resolves any consequences.
	 */
	public void move(Location source, Location destination) {
//		System.out.println("Moving from " + source.getRow() + "," + source.getColumn() + " to "
//				+ destination.getRow() + "," + destination.getColumn());
//		System.out.println("Source: " + getPile(source));
//		System.out.println("Destination: " + getPile(destination));
		Deque<Integer> hand = new Deque<Integer>();
		int n = 1;
		Deque<Integer> sourcePile = null;
		if (source.getRow() >= 0) { // Square on board
			n = source.getDistanceTo(destination);
			sourcePile = getPile(source);
		} else { // Reserves
			if (source == RESERVES_LOCATIONS[BLACK]) {
				sourcePile = reserves[BLACK];
			} else {
				sourcePile = reserves[WHITE];
			}
		}
//		System.out.println("sourcePile: " + sourcePile);
		for (int i = 0; i < n; i++) {
			hand.addFront(sourcePile.removeBack());
		}
		// Deposit pieces from hand to destination
		Deque<Integer> d = getPile(destination);
		while (!hand.isEmpty()) {
			d.addBack(hand.removeFront());
		}
		// Check for captures and reserves due to overflow
		while (d.size() > 5) {
			int color = d.removeFront();
			if (color == getCurrentPlayer()) {
				reserves[currentPlayer].addBack(currentPlayer);
			}
		}
	}

	/** Toggles the current color to play between BLACK and WHITE. */
	public void toggleColorToPlay() {
		currentPlayer = 1 - currentPlayer;
	}

}
