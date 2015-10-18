import java.awt.Color;

/** Logical model of the game. */
public class Connect4Model {

	/** Number of columns on the board. */
	public static final int COLUMNS = 7;

	/** Number of rows on the board. */
	public static final int ROWS = 6;

	/** The board. */
	private Color[][] board;

	/** The current player. */
	private Color currentPlayer;

	public Connect4Model() {
		// TODO You have to write this
	}

	/**
	 * Returns black's best move.
	 * 
	 * @param maxDepth
	 *            Maximum search depth, e.g., 1 to consider moves but not
	 *            replies.
	 */
	public int bestMoveForBlack(int maxDepth) {
		// TODO You have to write this
		return -1;
	}

	/**
	 * Returns the color at row r, column c.
	 */
	public Color colorAt(int r, int c) {
		return board[r][c];
	}

	/**
	 * Drops a piece of color in column. Assumes the move is legal.
	 */
	public void drop(Color color, int column) {
		// TODO You have to write this
	}

	/** Returns the current player. */
	public Color getCurrentPlayer() {
		return currentPlayer;
	}

	/** Returns true if the board is full. */
	public boolean isFull() {
		// TODO You have to write this
		return false;
	}

	/** Returns true if column is neither off the edge of the board nor full. */
	public boolean isLegal(int column) {
		// TODO You have to write this
		return false;
	}

	/**
	 * Returns the value of the board with black to move: 1 if black can force a
	 * win, -1 if black cannot avoid a loss, 0 otherwise.
	 * 
	 * @param maxDepth
	 *            Maximum search depth, e.g., 1 to consider moves but not
	 *            replies.
	 * @param depth
	 *            Current search depth, e.g., 0 if this is the root position.
	 */
	public int max(int maxDepth, int depth) {
		Color winner = winner();
		if (winner == StdDraw.BLACK) {
			return 1;
		} else if (winner == StdDraw.WHITE) {
			return -1;
		} else if (isFull() || (depth == maxDepth)) {
			return 0;
		} else {
			int bestResult = -2;
			for (int c = 0; c < COLUMNS; c++) {
				if (isLegal(c)) {
					drop(StdDraw.BLACK, c);
					int result = min(maxDepth, depth + 1);
					undo(c);
					if (result >= bestResult) {
						bestResult = result;
					}
				}
			}
			return bestResult;
		}
	}

	/**
	 * Returns the value of the board with white to move: 1 if white cannot
	 * avoid a loss, -1 if white can force a win, 0 otherwise.
	 * 
	 * @param maxDepth
	 *            Maximum search depth, e.g., 1 to consider moves but not
	 *            replies.
	 * @param depth
	 *            Current search depth, e.g., 0 if this is the root position.
	 */
	public int min(int maxDepth, int depth) {
		// TODO You have to write this
		return -1;
	}

	/**
	 * Toggles the current player between StdDraw.BLACK and StdDraw.WHITE..
	 */
	public void toggleCurrentPlayer() {
		// TODO You have to write this
	}

	/**
	 * Removes the top piece from column. Assumes column is not empty.
	 */
	public void undo(int column) {
		// TODO You have to write this
	}

	/**
	 * Returns the color of the player with four in a row starting at r, c and
	 * advancing rOffset, cOffset each step. For example, winAt(2, 3, 1, 0)
	 * examines locations 2, 3; 3, 3; 4, 3; and 5, 3. Returns StdDraw.GRAY if no
	 * player has four in a row here, or if there aren't four spaces starting
	 * here.
	 */
	public Color winAt(int r, int c, int rOffset, int cOffset) {
		Color result = board[r][c];
		for (int i = 1; i < 4; i++) {
			r += rOffset;
			c += cOffset;
			if ((r >= ROWS) || (c >= COLUMNS) || (c < 0)
					|| (board[r][c] != result)) {
				return StdDraw.GRAY;
			}
		}
		return result;
	}

	/**
	 * Returns StdDraw.BLACK if black has won, StdDraw.WHITE if white has won,
	 * or StdDraw.GRAY if neither player has won.
	 */
	public Color winner() {
		// TODO You have to write this
		return null;
	}

}
