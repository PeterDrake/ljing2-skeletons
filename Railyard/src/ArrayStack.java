/** An array-based stack. */
public class ArrayStack implements Branch {

	/** Items in this stack. Only the first size elements count. */
	private int[] data;

	/** Number of items in this stack. */
	private int size;

	public ArrayStack(int capacity) {
		data = new int[capacity];
	}

	@Override
	public void add(int item) {
		data[size] = item;
		size++;
	}

	@Override
	public int get(int index) {
		return data[index];
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public boolean isFull() {
		return size == data.length;
	}

	@Override
	public int remove() {
		size--;
		return data[size];
	}

	@Override
	public int size() {
		return size;
	}

}
