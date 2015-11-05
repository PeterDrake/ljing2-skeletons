/**
 * Model of the game of Hex, which was developed independently by Piet Hein and
 * by John Nash.
 */
public class HexModel {

	/**
	 * The first player, trying to connect the north and south edges of the
	 * board.
	 */
	public static final int BLACK = 0;

	/** Used to wire the nodes to their neighbors. */
	public static final int[][] OFFSETS = { { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 } };

	/** Color of unoccupied hexes. */
	public static final int VACANT = 2;

	/**
	 * The second player, trying to connect the west and east edges of the
	 * board.
	 */
	public static final int WHITE = 1;

	/** The color to play next, either BLACK or WHITE. */
	private int currentPlayer;

	/** Sentinel node off the east side of the board. */
	private final HexNode east;

	/** The grid of nodes. */
	private final HexNode[][] grid;

	/** Sentinel node off the north side of the board. */
	private final HexNode north;

	/** Sentinel node off the south side of the board. */
	private final HexNode south;

	/** Sentinel node off the west side of the board. */
	private final HexNode west;

	/**
	 * @param size
	 *            the width of the board.
	 */
	public HexModel(int size) {
		currentPlayer = BLACK;
		grid = new HexNode[size][size];
		for (int r = 0; r < grid.length; r++) {
			for (int c = 0; c < grid.length; c++) {
				grid[r][c] = new HexNode();
			}
		}
		east = new HexNode(WHITE);
		north = new HexNode(BLACK);
		south = new HexNode(BLACK);
		west = new HexNode(WHITE);
		wireNodes();
	}

	/**
	 * Returns BLACK if black has won, WHITE if white has won, and VACANT
	 * otherwise.
	 */
	public int findWinner() {
		if (search(north, south, new java.util.ArrayList<HexNode>())) {
			return BLACK;
		}
		if (search(west, east, new java.util.ArrayList<HexNode>())) {
			return WHITE;
		}
		return VACANT;
	}

	/** Returns the color of the node at r, c. */
	public int getColor(int r, int c) {
		return grid[r][c].getColor();
	}

	/** Returns the color to play next. */
	public int getCurrentPlayer() {
		return currentPlayer;
	}

	/** Returns the east sentinel. */
	public HexNode getEast() {
		return east;
	}

	/**
	 * Returns the number of neighbors of node (including sentinels). For
	 * testing.
	 */
	public int getNeighborCount(HexNode node) {
		return node.getNeighbors().size();
	}

	/** Returns the node at r, c. */
	public HexNode getNode(int r, int c) {
		return grid[r][c];
	}

	/** Returns the north sentinel. */
	public HexNode getNorth() {
		return north;
	}

	/** Returns the south sentinel. */
	public HexNode getSouth() {
		return south;
	}

	/** Returns the west sentinel. */
	public HexNode getWest() {
		return west;
	}

	/** Returns the width (in hexes) of the board. */
	public int getWidth() {
		return grid.length;
	}

	/** Returns the opposite color (BLACK and WHITE being opposites). */
	public int opposite(int color) {
		return 1 - color;
	}

	/** Plays a move at r, c. */
	public void playAt(int r, int c) {
		setColor(r, c, currentPlayer);
		currentPlayer = opposite(currentPlayer);
	}

	/**
	 * Returns true if there is a path from node to destination, involving only
	 * nodes of the same color as destination, not including any listed in
	 * visited.
	 */
	public boolean search(HexNode node, HexNode destination, java.util.ArrayList<HexNode> visited) {
		if (node == destination) {
			return true;
		}
		if ((node.getColor() != destination.getColor()) || visited.contains(node)) {
			return false;
		}
		visited.add(node);
		java.util.ArrayList<HexNode> neighbors = node.getNeighbors();
		for (int i = 0; i < neighbors.size(); i++) {
			if (search(neighbors.get(i), destination, visited)) {
				return true;
			}
		}
		return false;
	}

	/** Sets the color of hex r, c. */
	public void setColor(int r, int c, int color) {
		grid[r][c].setColor(color);
	}

	@Override
	public String toString() {
		String result = " ";
		for (int c = 0; c < grid.length; c++) {
			result += c + " ";
		}
		result += "\n";
		for (int r = 0; r < grid.length; r++) {
			for (int i = 0; i < r; i++) {
				result += " ";
			}
			result += r + " ";
			for (int c = 0; c < grid.length; c++) {
				if (grid[r][c].getColor() == BLACK) {
					result += "#";
				} else if (grid[r][c].getColor() == WHITE) {
					result += "O";
				} else {
					result += ".";
				}
				result += " ";
			}
			result += r + "\n";
		}
		result += "  ";
		for (int r = 0; r < grid.length; r++) {
			result += " ";
		}
		for (int c = 0; c < grid.length; c++) {
			result += c + " ";
		}
		return result + "\n";
	}

	/** Connects the nodes to their neighbors (including sentinels). */
	public void wireNodes() {
		// Wire nodes within grid
		for (int r = 0; r < grid.length; r++) {
			for (int c = 0; c < grid.length; c++) {
				for (int i = 0; i < OFFSETS.length; i++) {
					int row = r + OFFSETS[i][0];
					int column = c + OFFSETS[i][1];
					if ((row >= 0) && (row < grid.length) && (column >= 0) && (column < grid.length)) {
						grid[r][c].addNeighbor(grid[row][column]);
					}
				}
			}
		}
		// Wire sentinels
		for (int i = 0; i < grid.length; i++) {
			east.addNeighbor(grid[i][grid.length - 1]);
			grid[i][grid.length - 1].addNeighbor(east);
			north.addNeighbor(grid[0][i]);
			grid[0][i].addNeighbor(north);
			south.addNeighbor(grid[grid.length - 1][i]);
			grid[grid.length - 1][i].addNeighbor(south);
			west.addNeighbor(grid[i][0]);
			grid[i][0].addNeighbor(west);
		}
	}

}
