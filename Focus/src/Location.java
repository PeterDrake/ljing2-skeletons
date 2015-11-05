/** A location (with row and column) on the board. */
public class Location {

	/**
	 * Greater than any actual distance on the board.
	 * 
	 * @see #getDistanceTo(Location)
	 */
	public static final int NOT_ON_SAME_LINE = Integer.MAX_VALUE;

	/** @see #Location(int, int) */
	private final int column;

	/** @see #Location(int, int) */
	private final int row;

	/** Row is zero-based from top, column zero-based from top. */
	public Location(int row, int column) {
		this.row = row;
		this.column = column;
	}

	/**
	 * Returns the column of this location.
	 * 
	 * @see #Location(int, int)
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * Returns the distance from this to that, or NOT_ON_SAME_LINE if this and
	 * that are not on the same horizontal or vertical line.
	 */
	public int getDistanceTo(Location that) {
		if (row != that.row && column != that.column) {
			return NOT_ON_SAME_LINE;
		}
		return Math.abs(row - that.row) + Math.abs(column - that.column);
	}

	/**
	 * Returns the row of this location.
	 * 
	 * @see #Location(int, int)
	 */
	public int getRow() {
		return row;
	}

}
