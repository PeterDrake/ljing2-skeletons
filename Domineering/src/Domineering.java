/** A domino-placing game invented by Goran Andersson. */
public class Domineering {

	/**
	 * Draws the current state of the game, including instructions to the user.
	 */
	public static void draw(boolean[][] board, boolean verticalToPlay) {
		// TODO You have to write this
	}

	/**
	 * Returns true if the game is over, i.e., the current player has no legal
	 * move.
	 */
	public static boolean gameOver(boolean[][] board, boolean verticalToPlay) {
		// TODO You have to write this
		return false;
	}

	/**
	 * Plays a move as specified by the user's mouse click. Returns true if
	 * vertical is to play next, false otherwise. If an illegal move is
	 * attempted, this method has no effect and returns the value passed in for
	 * verticalToPlay.
	 */
	public static boolean handleMouseClick(boolean[][] board, boolean verticalToPlay) {
		return false;
		// TODO You have to write this
	}

	/**
	 * Returns true if playing at x, y is legal for the current player. Assumes
	 * that x, y is on the board but verifies that the other half of the domino
	 * is on the board.
	 */
	public static boolean isLegal(int x, int y, boolean[][] board, boolean verticalToPlay) {
		// TODO You have to write this
		return false;
	}

	/** Plays the game. */
	public static void main(String[] args) {
		// TODO You have to write this
	}

	/**
	 * Returns the opposite value for verticalToPlay. For example,
	 * opposite(true) is false.
	 */
	public static boolean opposite(boolean verticalToPlay) {
		// TODO You have to write this
		return false;
	}

	/**
	 * Places a domino at x, y with the specified orientation. Assumes the
	 * placement is legal.
	 */
	public static void placeDomino(int x, int y, boolean[][] board, boolean verticalToPlay) {
		// TODO You have to write this
	}

}
