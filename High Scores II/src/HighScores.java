/** Maintains a list of high scores. */
public class HighScores {

	/**
	 * Examines the results of iterating through a set of scores using each of
	 * several set implementations.
	 */
	public static void iterationTest() {
		@SuppressWarnings("unchecked")
		Set<Integer>[] sets = (Set<Integer>[]) new Set[] { new SortedArraySet<Integer>(),
				new BinarySearchTreeSet<Integer>(), new JcfTreeSet<Integer>(), new JcfHashSet<Integer>() };
		for (Integer score : new In("scores.txt").readAllInts()) {
			for (Set<Integer> set : sets) {
				set.add(score);
			}
		}
		for (Set<Integer> set : sets) {
			for (Integer score : set) {
				StdOut.print(score + " ");
			}
			StdOut.println();
		}
	}

	/** Interactively maintains a set of high scores. */
	public static void main(String[] args) {
		Set<Integer> scores = new SortedArraySet<Integer>();
		int score = 0;
		do {
			StdOut.print(
					"Enter a positive number to add a score, a negative number to remove the opposite score, or 0 to quit: ");
			score = StdIn.readInt();
			if (score > 0) {
				scores.add(score);
			} else if (score < 0) {
				scores.remove(-score);
			}
			StdOut.print("Scores: ");
			for (int s : scores) {
				StdOut.print(s + " ");
			}
			StdOut.println();
		} while (score != 0);
	}

	/** Compares the speed of various set implementations. */
	public static void speedTest() {
		@SuppressWarnings("unchecked")
		Set<Integer>[] sets = (Set<Integer>[]) new Set[] { new SortedArraySet<Integer>(),
				new BinarySearchTreeSet<Integer>(), new JcfTreeSet<Integer>(), new JcfHashSet<Integer>() };
		Integer[] numbers = new Integer[100000];
		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = StdRandom.uniform(Integer.MAX_VALUE);
		}
		for (Set<Integer> set : sets) {
			long before = System.nanoTime();
			for (Integer n : numbers) {
				set.add(n);
			}
			long after = System.nanoTime();
			StdOut.println("Elapsed time: " + ((after - before) / 1000000000.0) + " seconds");
		}
	}

}
