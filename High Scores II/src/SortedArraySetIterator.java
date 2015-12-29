import java.util.Iterator;

/** Used to iterate through a SortedArraySet. */
public class SortedArraySetIterator<T> implements Iterator<T> {

	/** Data array from the set. */
	private T[] data;
	
	/** Size of the set. */
	private int size;
	
	/** Index (in data) of the next item to be returned. */
	private int index;
	
	public SortedArraySetIterator(T[] data, int size) {
		this.data = data;
		this.size = size;
	}

	@Override
	public boolean hasNext() {
		return index < size;
	}

	@Override
	public T next() {
		T result = data[index];
		index++;
		return result;
	}

}
