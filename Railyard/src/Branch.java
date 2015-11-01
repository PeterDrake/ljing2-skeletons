/** A branch in the railyard, containing a sequence of cars (integers). */
public interface Branch {

	/**
	 * Adds car to one end of this branch. Which end can vary by implementation.
	 * Assumes that this branch is not full.
	 */
	public void add(int car);

	/**
	 * Returns the indexth car from this branch, Which end the indexing starts
	 * at can vary by implementation. Assumes this branch contains at least
	 * (index + 1) cars.
	 */
	public int get(int index);

	/** Returns true if this branch holds no cars. */
	public boolean isEmpty();

	/** Returns true if this branch does not have room for any more cars. */
	public boolean isFull();

	/**
	 * Removes and returns a car from one end of this branch. Which end can vary
	 * by implementation. Assumes that this branch is not empty.
	 */
	public int remove();

	/** Returns the number of cars currently on this branch. */
	public int size();

}
