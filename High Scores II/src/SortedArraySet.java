import java.util.Iterator;

/** Sorted array implementation of a map. */
public class SortedArraySet<T extends Comparable<T>> implements Set<T> {

	/** Array of elements. */
	private T[] data;
	
	/** Number of elements in this set. */
	private int size;
	
	@SuppressWarnings("unchecked")
	public SortedArraySet() {
		data = (T[])new Comparable[1];
	}
	
	/** Returns the index of key in data. If there is none, returns -1. */
	public int find(T key) {
		int lo = 0;
		int hi = size;
		while (lo < hi) {
			int mid = lo + (hi - lo) / 2;
			int c = key.compareTo(data[mid]);
			if (c < 0) {
				hi = mid;
			} else if (c == 0) {
				return mid;
			} else {
				lo = mid + 1;
			}
		}
		return -1;		
	}

	@Override
	public boolean contains(T key) {
		return find(key) != -1;
	}

	@SuppressWarnings("unchecked")
	public void resize() {
		T[] longer = (T[])(new Comparable[data.length * 2]);
		for (int i = 0; i < size; i++) {
			longer[i] = data[i];
		}
		data = longer;
	}

	@Override
	public void add(T key) {
		int i = find(key);
		if (i == -1) {
			if (size == data.length) {
				resize();
			}
			int j = size;
			while (j > 0 && key.compareTo(data[j - 1]) < 0) {
				data[j] = data[j - 1];
				j--;
			}
			data[j] = key;
			size++;
			return;
		}
	}

	@Override
	public void remove(T key) {
		int i = find(key);
		if (i != -1) {
			for (int j = i; j < size - 1; j++) {
				data[j] = data[j + 1];
			}
			size--;
		}
	}

	@Override
	public Iterator<T> iterator() {
		return new SortedArraySetIterator<T>(data, size);
	}

}
