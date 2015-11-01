/** An array-based queue. */
public class ArrayQueue implements Branch {

	/** Items in this queue. Only some of them count. */
	private int[] data;

	/** Index of the front item. */
	private int front;

	/** Number of items in this queue. */
	private int size;

	public ArrayQueue(int capacity) {
		data = new int[capacity];
	}

	@Override
	public void add(int item) {
		data[(front + size) % data.length] = item;
		size++;
	}

	@Override
	public int get(int index) {
		return data[(front + index) % data.length];
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
		int result = data[front];
		front = (front + 1) % data.length;
		size--;
		return result;
	}

	@Override
	public int size() {
		return size;
	}

}
