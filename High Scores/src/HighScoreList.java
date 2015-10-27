/** Maintains a list of high scores. */
public class HighScoreList {

	public static void main(String[] args) {
		HighScoreList list = new HighScoreList(5);
		StdOut.println("Enter 5 scores, separated by spaces or newlines:");
		for (int i = 0; i < 5; i++) {
			list.addScore(StdIn.readInt());
		}
		StdOut.println("Before sorting: " + list);
		list.insertionSort();
		StdOut.println("After sorting: " + list);
	}

	/** Stored scores. Only the first size elements count. */
	private int[] scores;

	/** Number of elements in size that count. */
	private int size;

	/**
	 * @param capacity
	 *            Maximum number of scores this list can hold.
	 */
	public HighScoreList(int capacity) {
		scores = new int[capacity];
	}

	/**
	 * Adds score to this list. Does not sort the list.
	 */
	public void addScore(int score) {
		scores[size] = score;
		size++;
	}

	/** Returns the ith (zero-based) score in this list. */
	public int get(int i) {
		return scores[i];
	}

	/** Sorts this list in decreasing order. */
	public void insertionSort() {
		for (int i = 1; i < size; i++) {
			for (int j = i; j > 0; j--) {
				if (scores[j - 1] < scores[j]) {
					int temp = scores[j];
					scores[j] = scores[j - 1];
					scores[j - 1] = temp;
				} else {
					break;
				}
			}
		}
	}

	/**
	 * Finishes sorting the region of this list from lo (inclusive) to hi
	 * (exclusive). Assumes that each of the regions lo through mid and mid
	 * through hi (with the second index exclusive in each case) is already sorted.
	 * @return
	 */
	public int[] merge(int lo, int hi, int mid) {
		int[] merged = new int[scores.length];
		int a = lo; // Index into sorted left side
		int b = mid; // Index into sorted right side
		for (int i = lo; i < hi; i++) {
			if (b >= hi) {
				merged[i] = scores[a];
				a++;
			} else if (a >= mid || scores[b] > scores[a]) {
				merged[i] = scores[b];
				b++;
			} else {
				merged[i] = scores[a];
				a++;
			}
		}
		return merged;
	}

	/** Sorts this list in decreasing order. */
	public void mergeSort() {
		mergeSort(0, size);
	}

	/**
	 * Sorts the region of this list from lo (inclusive) to hi (exclusive) in
	 * decreasing order.
	 */
	public void mergeSort(int lo, int hi) {
		if (hi <= lo + 1) { // No elements to sort
			return;
		}
		int mid = lo + (hi - lo) / 2;
		// Sort both halves
		mergeSort(lo, mid);
		mergeSort(mid, hi);
		// Merge the two sorted halves
		int[] merged = merge(lo, hi, mid);
		// Copy elements from merged back into scores
		for (int i = lo; i < hi; i++) {
			scores[i] = merged[i];
		}
	}

	@Override
	public String toString() {
		String result = "[";
		if (size > 0) {
			result += scores[0];
			for (int i = 1; i < size; i++) {
				result += ", " + scores[i];
			}
		}
		return result + "]";
	}

}
