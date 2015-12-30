import java.awt.Color;
import java.util.Set;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Stack;

/** Logical model of the Go game. */
public class GoModel {

	/** Color at each point on the board: BLACK, WHITE, or null. */
	private Color[][] board;

	/** The current player, BLACK or WHITE. */
	private Color currentPlayer;

	/** Number of consecutive passes. */
	private int passes;

	/** Stack of previous game states for undoing. */
	private Stack<GoModel> previousStates;

	public GoModel(int width) {
		board = new Color[width][width];
		currentPlayer = StdDraw.BLACK;
		previousStates = new Stack<>();
	}

	/**
	 * Returns true if either point is of targetColor or there is a path through
	 * only intersections of color from point to an intersection of targetColor.
	 * 
	 * @param visited
	 *            Intersections already explored in this search.
	 */
	public boolean canReach(Location point, Color color, Color targetColor, Set<Location> visited) {
		if (visited.contains(point)) {
			return false;
		}
		if (get(point) == targetColor) {
			return true;
		}
		if (get(point) != color) {
			return false;
		}
		visited.add(point);
		for (Location neighbor : point.getNeighbors()) {
			if (canReach(neighbor, color, targetColor, visited)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Tries to capture the chain of stones including point. Has no effect if
	 * this chain has at least one liberty.
	 */
	public void capture(Location point) {
		if (isOnBoard(point)) {
			Set<Location> visited = new HashSet<>();
			if (canReach(point, get(point), null, visited)) {
				for (Location p : visited) {
					board[p.getRow()][p.getColumn()] = null;
				}
			}
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GoModel other = (GoModel) obj;
		if (!Arrays.deepEquals(board, other.board))
			return false;
		if (currentPlayer == null) {
			if (other.currentPlayer != null)
				return false;
		} else if (!currentPlayer.equals(other.currentPlayer))
			return false;
		if (passes != other.passes)
			return false;
		return true;
	}

	/**
	 * Returns true if the game is over, i.e., there have been two consecutive
	 * passes.
	 */
	public boolean gameOver() {
		return passes == 2;
	}

	/**
	 * Returns the color (BLACK, WHITE, or null) on the board at row, column.
	 * Assumes these coordinates are valid.
	 */
	public Color get(int row, int column) {
		return board[row][column];
	}

	/**
	 * Returns the color (BLACK, WHITE, or null) on the board at point. Assumes
	 * these coordinates are valid.
	 */
	public Color get(Location point) {
		return board[point.getRow()][point.getColumn()];
	}

	/**
	 * Returns the width of the board.
	 */
	public int getBoardWidth() {
		return board.length;
	}

	/** Returns the current player color (BLACK or WHITE). */
	public Color getCurrentPlayer() {
		return currentPlayer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(board);
		result = prime * result + ((currentPlayer == null) ? 0 : currentPlayer.hashCode());
		result = prime * result + passes;
		return result;
	}

	/** Returns true if point is on the board. */
	public boolean isOnBoard(Location point) {
		return isValidCoordinate(point.getRow()) && isValidCoordinate(point.getColumn());
	}

	/**
	 * Returns true if i is a valid row or column coordinate given the size of
	 * the board.
	 */
	public boolean isValidCoordinate(int i) {
		return i >= 0 && i < board.length;
	}

	/**
	 * Returns the owner (BLACK, WHITE, or null) of the point at row, column.
	 */
	public Color owner(int row, int column) {
		Location point = new Location(row, column);
		if (get(point) == StdDraw.BLACK || (canReach(point, null, StdDraw.BLACK, new HashSet<Location>())
				&& !canReach(point, null, StdDraw.WHITE, new HashSet<Location>()))) {
			return StdDraw.BLACK;
		}
		if (get(point) == StdDraw.WHITE || (canReach(point, null, StdDraw.WHITE, new HashSet<Location>())
				&& !canReach(point, null, StdDraw.BLACK, new HashSet<Location>()))) {
			return StdDraw.WHITE;
		}
		return null;
	}

	/**
	 * Plays a pass move (which is always legal) and toggles the current player.
	 */
	public void pass() {
		saveCopy();
		passes++;
		toggleCurrentPlayer();
	}

	/**
	 * Attempts to play a stone for the current color at row, column. If the
	 * move is legal, toggles the current player and returns true. If the move
	 * is not legal, has no effect and returns false. Illegal moves include
	 * those where row and column are not on the board, occupied points, ko
	 * violations, and suicide.
	 */
	public boolean play(int row, int column) {
		Location point = new Location(row, column);
		// Check for off-board point
		if (!isOnBoard(point)) {
			return false;
		}
		// Check for occupied point
		if (board[row][column] != null) {
			return false;
		}
		// Play the move
		saveCopy();
		board[row][column] = currentPlayer;
		passes = 0;
		// Check for captures
		for (Location n : point.getNeighbors()) {
			if (isOnBoard(n) && get(n) != currentPlayer) {
				capture(n);
			}
		}
		// Check for suicide
		capture(point);
		if (get(point) == null) {
			undo();
			return false;
		}
		// Toggle current player color
		toggleCurrentPlayer();
		// Check for ko
		for (GoModel previous : previousStates) {
			if (this.equals(previous)) {
				undo();
				return false;
			}
		}
		return true;
	}

	/** Saves a copy of the current board state on a stack for undoing. */
	public void saveCopy() {
		GoModel copy = new GoModel(board.length);
		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board.length; c++) {
				copy.board[r][c] = board[r][c];
			}
		}
		copy.currentPlayer = currentPlayer;
		copy.passes = passes;
		previousStates.push(copy);
	}

	/**
	 * Returns the number of points occupied or surrounded by player. Does not
	 * into account the 7.5 bonus points given to white.
	 */
	public int score(Color color) {
		int result = 0;
		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board.length; c++) {
				if (owner(r, c) == color) {
					result++;
				}
			}
		}
		return result;
	}

	/** Switches the current player between BLACK and WHITE. */
	public void toggleCurrentPlayer() {
		if (currentPlayer == StdDraw.BLACK) {
			currentPlayer = StdDraw.WHITE;
		} else {
			currentPlayer = StdDraw.BLACK;
		}
	}

	@Override
	public String toString() {
		String result = "";
		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board.length; c++) {
				if (board[r][c] == StdDraw.BLACK) {
					result += "#";
				} else if (board[r][c] == StdDraw.WHITE) {
					result += "O";
				} else {
					result += ".";
				}
			}
			result += "\n";
		}
		return result;
	}

	/** Undoes the last move. Has no effect if it is the beginning of the game. */
	public void undo() {
		if (!previousStates.isEmpty()) {
			GoModel previous = previousStates.peek();
			board = previous.board;
			passes = previous.passes;
			currentPlayer = previous.currentPlayer;
		}
	}

}
