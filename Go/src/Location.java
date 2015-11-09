/** A location (with row and column) on the board. */
public class Location {

	/** @see #Location(int, int) */
	private final int column;

	/** @see #Location(int, int) */
	private final int row;

	/** Row is zero-based from top, column zero-based from top. */
	public Location(int row, int column) {
		this.row = row;
		this.column = column;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		if (column != other.column)
			return false;
		if (row != other.row)
			return false;
		return true;
	}

	/**
	 * Returns the column of this location.
	 * 
	 * @see #Location(int, int)
	 */
	public int getColumn() {
		return column;
	}

	/** Returns an array of the four neighboring locations. */
	public Location[] getNeighbors() {
		return new Location[] {new Location(row - 1, column),
				new Location(row + 1, column),
				new Location(row, column - 1),
				new Location(row, column + 1)};
	}

	/**
	 * Returns the row of this location.
	 * 
	 * @see #Location(int, int)
	 */
	public int getRow() {
		return row;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + column;
		result = prime * result + row;
		return result;
	}

}
