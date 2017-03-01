/** Generates, displays, and solves a maze. */
public class Maze {

	/** Index for east direction. */
	public static final int EAST = 1;

	/** Index for north direction. */
	public static final int NORTH = 0;

	/**
	 * x and y offsets for per direction. For example, the point to the east of
	 * x, y is x + OFFSETS[EAST][0], y + OFFSETS[EAST][1].
	 */
	public static final int[][] OFFSETS = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

	/** Index for south direction. */
	public static final int SOUTH = 2;

	/** Index for west direction. */
	public static final int WEST = 3;

	/**
	 * Modifies passage to contain a one-way passage from location a to location
	 * b. Assumes these two locations (arrays of two numbers) are adjacent.
	 * 
	 * @param passages
	 *            passages[x][y][direction] is true if there is a passage from
	 *            location x, y to its neighbor in direction. Directions are
	 *            specified by the constants NORTH, EAST, SOUTH, and WEST.
	 */
	public static void addPassage(boolean[][][] passages, int[] a, int[] b) {
		// TODO You have to write this
	}

	/**
	 * Returns a new array of pairs containing start followed by all of the
	 * elements in list.
	 */
	public static int[][] addToFront(int[] start, int[][] list) {
		// TODO You have to write this
		return null;
	}

	/**
	 * Returns a random one of the first n elements of list.
	 */
	public static int[] chooseRandomlyFrom(int[][] list, int n) {
		// TODO You have to write this
		return null;
	}

	/**
	 * Returns true if pair, assumed to be an array of two numbers, has the same
	 * numbers as one of the first n elements of list.
	 */
	public static boolean contains(int[] pair, int[][] list, int n) {
		// TODO You have to write this
		return false;
	}

	/**
	 * Graphically draws the maze.
	 * 
	 * @param passages
	 *            passages[x][y][direction] is true if there is a passage from
	 *            location x, y to its neighbor in direction. Directions are
	 *            specified by the constants NORTH, EAST, SOUTH, and WEST.
	 */
	public static void drawMaze(boolean[][][] passages) {
		StdDraw.clear(StdDraw.PINK);
		StdDraw.setPenColor(StdDraw.WHITE);
		int width = passages.length;
		StdDraw.setPenRadius(0.75 / width);
		// Draw passages
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < width; y++) {
				if (passages[x][y][NORTH] || (y + 1 < width && passages[x][y + 1][SOUTH])) {
					StdDraw.line(x, y, x, y + 1);
				}
				if (passages[x][y][EAST] || (x + 1 < width && passages[x + 1][y][WEST])) {
					StdDraw.line(x, y, x + 1, y);
				}
			}
		}
		// Draw entrance and exit
		StdDraw.line(0, 0, -1, 0);
		StdDraw.line(width - 1, width - 1, width, width - 1);
		StdDraw.show();
	}

	/**
	 * Graphically draws the solution.
	 */
	public static void drawSolution(int[][] path, int width) {
		StdDraw.setPenColor(); // Black by default
		StdDraw.setPenRadius();
		StdDraw.line(0, 0, -1, 0);
		StdDraw.line(width - 1, width - 1, width, width - 1);
		for (int i = 0; i < path.length - 1; i++) {
			StdDraw.line(path[i][0], path[i][1], path[i + 1][0], path[i + 1][1]);
		}
		StdDraw.show();
	}

	/**
	 * Checks if here's neighbor in direction (called there) is in unexplored.
	 * If so, adds a passage from here to there and returns there. If not,
	 * returns null.
	 * 
	 * @param passages
	 *            passages[x][y][direction] is true if there is a passage from
	 *            location x, y to its neighbor in direction. Directions are
	 *            specified by the constants NORTH, EAST, SOUTH, and WEST.
	 * @param unexplored
	 *            a list of locations that have not yet been reached.
	 * @param n
	 *            the number of valid elements in unexplored. Unexplored is
	 *            "resized" by changing this number.
	 * @param here
	 *            the location from which a neighbor is sought.
	 * @param direction
	 *            the direction to expand location, one of NORTH, SOUTH, EAST,
	 *            or WEST.
	 * @return the newly-explored location there if this succeeded, null
	 *         otherwise.
	 */
	public static int[] expandLocation(boolean[][][] passages, int[][] unexplored, int n, int[] here, int direction) {
		int[] there = new int[2];
		// Find the neighboring point
		there[0] = here[0] + OFFSETS[direction][0];
		there[1] = here[1] + OFFSETS[direction][1];
		// TODO You have to write the rest
		return null;
	}

	/**
	 * Chooses "here" to be either lastExploredLocation (if it is not null) or a
	 * random location in frontier. If possible, adds a passage from "here" to a
	 * location "there" in unexplored, then moves "there" from unexplored to
	 * frontier. If not, moves "here" from frontier to done. Returns "there", or
	 * null if no new location was explored.
	 * 
	 * @param passages
	 *            passages[x][y][direction] is true if there is a passage from
	 *            location x, y to its neighbor in direction. Directions are
	 *            specified by the constants NORTH, EAST, SOUTH, and WEST.
	 * @param done
	 *            a list of locations from which no new edges can be drawn to
	 *            locations in unexplored.
	 * @param frontier
	 *            a list of locations that have been reached by some edge but
	 *            are not yet in done.
	 * @param unexplored
	 *            a list of locations that have not yet been reached.
	 * @param counts
	 *            the number of valid elements in each of the three previous
	 *            arrays. The arrays are "resized" by changing these elements.
	 * @param lastExploredLocation
	 *            the last location that was explored or null.
	 * @return the newly explored location (or null if no new location was
	 *         explored).
	 */
	public static int[] expandMaze(boolean[][][] passages, int[][] done, int[][] frontier, int[][] unexplored,
			int[] counts, int[] lastExploredLocation) {
		int[] here;
		if (lastExploredLocation == null) {
			here = chooseRandomlyFrom(null, -1); // TODO Replace these arguments
		} else {
			here = lastExploredLocation;
		}
		// Choose a random direction
		int direction = StdRandom.uniform(4);
		for (int i = 0; i < 4; i++) {
			int[] there = expandLocation(null, null, -1, null, -1); // TODO Replace these arguments
			if (there != null) {
				// Move there from unexplored to frontier
				frontier[counts[1]] = there;
				counts[1]++;
				remove(null, null, -1); // TODO Replace these arguments
				counts[2]--;
				// We're done
				return there;
			}
			direction = (direction + 1) % 4;
		}
		// No valid neighbor was found. Move here from frontier to done.
		done[counts[0]] = here;
		counts[0]++;
		remove(null, null, -1); // TODO Replace these arguments
		counts[1]--;
		return null;
	}

	/** Draws and then solves a maze. */
	public static void main(String[] args) {
		StdDraw.enableDoubleBuffering();
		int width = 20;
		StdDraw.setXscale(-0.5, width - 0.5);
		StdDraw.setYscale(-0.5, width - 0.5);
		StdDraw.show();
		boolean[][][] passages = new boolean[width][width][4];
		// Initially, no locations are done
		int[][] done = new int[width * width][];
		// The frontier only contains {0, 0}
		int[][] frontier = new int[width * width][];
		frontier[0] = new int[] { 0, 0 };
		// All other locations are in unexplored
		int[][] unexplored = new int[width * width][];
		// Number of nodes done, on the frontier, and unexplored
		int[] counts = { 0, 1, width * width - 1 };
		int i = 0;
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < width; y++) {
				if (x != 0 || y != 0) {
					unexplored[i] = new int[] { x, y };
					i++;
				}
			}
		}
		// As long as there are unexplored locations, expand the maze
		int[] lastExploredLocation = null;
		while (counts[2] > 0) {
			lastExploredLocation = expandMaze(passages, done, frontier, unexplored, counts, lastExploredLocation);
			drawMaze(passages);
			StdDraw.show();
			StdDraw.pause(25);
		}
		// Solve the maze
		int[][] solution = solve(passages, new int[] { 0, 0 }, new int[] { width - 1, width - 1 });
		drawSolution(solution, width);
	}

	/**
	 * Modifies list so that pair is replaced with the (n - 1)th element of
	 * list. Assumes pair is an array of two numbers that appears somewhere in
	 * list. Thus, when this method is done, the first n - 1 element of list are
	 * the same as the first n elements of the old version, but with pair
	 * removed and with the order of elements potentially different.
	 */
	public static void remove(int[] pair, int[][] list, int n) {
		// TODO You have to write this
	}

	/**
	 * Returns a path (sequence of locations) leading from start to goal in
	 * passages or null if there is no such path.
	 * 
	 * @param passages
	 *            passages[x][y][direction] is true if there is a passage from
	 *            location x, y to its neighbor in direction. Directions are
	 *            specified by the constants NORTH, EAST, SOUTH, and WEST.
	 */
	public static int[][] solve(boolean[][][] passages, int[] start, int[] goal) {
		// Base case: we're already at the goal
		if (false) { // TODO Replace false
			return new int[][] { goal };
		}
		// Can we reach the goal from any of our neighbors?
		for (int d = 0; d < 4; d++) {
			if (passages[start[0]][start[1]][d]) {
				int[] next = { start[0] + OFFSETS[d][0], start[1] + OFFSETS[d][1] };
				int[][] restOfPath = null; // TODO Replace null
				if (restOfPath != null) {
					// TODO You have to write this line
				}
			}
		}
		// Nope -- we can't get there from here
		return null;
	}

}
