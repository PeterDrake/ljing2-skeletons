/** A hand of five dice. Die values are in the range [0,5]. */
public class Hand {

	/** Number showing on each die. */
	private int[] dice;

	/** kept[i] is true if die i should be kept (rather than rerolled). */
	private boolean[] kept;

	public Hand() {
		dice = new int[5];
		kept = new boolean[5];
	}

	/** Returns true if all dice in this hand are marked for keeping. */
	public boolean allKept() {
		for (boolean b : kept) {
			if (!b) {
				return false;
			}
		}
		return true;
	}

	/** Clears which dice are marked for keeping. */
	public void clearKept() {
		for (int i = 0; i < kept.length; i++) {
			kept[i] = false;
		}
	}

	/**
	 * Returns an array where element i is the number of dice showing number i.
	 */
	public int[] counts() {
		int[] result = new int[6];
		for (int i = 0; i < dice.length; i++) {
			result[dice[i]]++;
		}
		return result;
	}

	/**
	 * If all dice match, returns a number of the form 7a0000, where a is the
	 * number appearing five times. Otherwise returns -1.
	 */
	public int fiveOfAKindScore() {
		for (int i = 1; i < dice.length; i++) {
			if (dice[i] != dice[0]) {
				return -1;
			}
		}
		return 700000 + dice[0] * 10000;
	}

	/**
	 * If four dice match, returns a number of the form 6ab000, where a is the
	 * number appearing four times and b is the number appearing once. Otherwise
	 * returns -1.
	 * 
	 * Assumes this hand is not five of a kind.
	 */
	public int fourOfAKindScore() {
		int[] counts = counts();
		int result = 600000;
		boolean fourFound = false;
		for (int i = 0; i < counts.length; i++) {
			if (counts[i] == 1) {
				result += i * 1000;
			} else if (counts[i] == 4) {
				result += i * 10000;
				fourFound = true;
			}
		}
		if (fourFound) {
			return result;
		}
		return -1;
	}

	/**
	 * If this hand is a full house (three of one number and two of another),
	 * returns 5ab000, where a is the number appearing three times and b is the
	 * number appearing two times. Otherwise returns -1.
	 * 
	 * Assumes this hand is not five of a kind.
	 */
	public int fullHouseScore() {
		// TODO You have to write this
		return -1;
	}

	/** Returns the value of the ith die in this hand. */
	public int get(int i) {
		return dice[i];
	}

	/**
	 * Returns the score for this hand. The digit in the 100000s place indicates
	 * the type of hand (7 for five of a kind, 6 for four of a kind, 5 for a
	 * full house, 4 for a straight, 3 for three of a kind, 2 for two pair, and
	 * 1 for 1 pair). Other digits indicate the cards involved to break ties.
	 * See the test for examples.
	 */
	public int getScore() {
		int result = 0;
		result = fiveOfAKindScore();
		if (result < 0) {
			result = fourOfAKindScore();
		}
		if (result < 0) {
			result = fullHouseScore();
		}
		if (result < 0) {
			result = straightScore();
		}
		if (result < 0) {
			result = threeOfAKindScore();
		}
		if (result < 0) {
			result = twoPairScore();
		}
		if (result < 0) {
			result = onePairScore();
		}
		if (result < 0) {
			result = highCardScore();
		}
		return result;
	}

	/**
	 * Returns a five-digit number whose digits are the numbers in this hand in
	 * decreasing order.
	 * 
	 * Assumes this hand is not something better than high card (e.g., one
	 * pair).
	 */
	public int highCardScore() {
		// TODO You have to write this
		return -1;
	}

	/**
	 * Returns true if the ith die is marked for keeping (as opposed to
	 * rerolling).
	 */
	public boolean isKept(int i) {
		return kept[i];
	}

	/**
	 * If this hand contains a pair of matching numbers, returns 2abcd0, where a
	 * is the paired number and b, c, and d are the single numbers in decreasing
	 * order. Otherwise returns -1.
	 * 
	 * Assumes this hand is not something better than one pair (e.g., two pair).
	 */
	public int onePairScore() {
		// TODO You have to write this
		return -1;
	}

	/** Rolls all of the dice except those marked for keeping. */
	public void roll() {
		for (int i = 0; i < dice.length; i++) {
			if (!kept[i]) {
				dice[i] = StdRandom.uniform(6);
			}
		}
	}

	/** Sets the dice to specific values. Used for testing. */
	public void set(int a, int b, int c, int d, int e) {
		dice[0] = a;
		dice[1] = b;
		dice[2] = c;
		dice[3] = d;
		dice[4] = e;
	}

	/** Mark whether the ith die should be kept (as opposed to rerolled). */
	public void setKept(int i, boolean keep) {
		kept[i] = keep;
	}

	/**
	 * If this hand is a straight (a sequence of five consecutive numbers),
	 * returns 4a0000, where a is the highest number in the sequence. Otherwise
	 * returns -1;
	 */
	public int straightScore() {
		// TODO You have to write this
		return -1;
	}

	/**
	 * If this hand contains three matching numbers, returns 3abc00, where a is
	 * the number appearing three times, b is the highest single number, and c
	 * is the other single number. Otherwise returns -1.
	 * 
	 * Assumes this hand is not something better than three of a kind (e.g., a
	 * full house).
	 */
	public int threeOfAKindScore() {
		// TODO You have to write this
		return -1;
	}

	/**
	 * If this hand contains two pairs of matching numbers, returns 2abc00,
	 * where a is the higher paired number, b is the lower paired number, and c
	 * is the single number. Otherwise returns -1.
	 * 
	 * Assumes this hand is not something better than two pair (e.g., three of a
	 * kind).
	 */
	public int twoPairScore() {
		// TODO You have to write this
		return -1;
	}

}
