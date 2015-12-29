/** Holds a collection of items with no duplication. */
public interface Set<T> extends Iterable<T> {

	/** Adds key to this set if it is not already present. */
	public void add(T key);

	/** Returns true if key is in this set. */
	public boolean contains(T key);

	/** Removes key from this set. */
	public void remove(T key);

}
