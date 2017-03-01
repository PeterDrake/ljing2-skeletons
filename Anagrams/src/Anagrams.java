/** Word-unscrambling game. */
public class Anagrams {

	/** Returns true if word is in dictionary. */
	public static boolean contains(String word, String[] dictionary) {
		for (int i = 0; i < dictionary.length; i++) {
			if (word.equals(dictionary[i])) {
				return true;
			}
		}
		return false;
	}

	/** Returns a copy of a. */
	public static char[] copy(char[] a) {
		char[] result = new char[a.length];
		for (int i = 0; i < result.length; i++) {
			result[i] = a[i];
		}
		return result;
	}

	/** Draws the current state of the game. */
	public static void draw(String correct, String scrambled, String guess, String[] dictionary) {
		StdDraw.clear();
		StdDraw.text(0.5, 0.75, scrambled);
		StdDraw.text(0.5, 0.5, guess);
		if (guess.length() == scrambled.length()) {
			if (isCorrect(guess, correct, dictionary)) {
				StdDraw.text(0.5, 0.25, "Correct! Hit any key to continue.");
			} else {
				StdDraw.text(0.5, 0.25, "Incorrect. The word was " + correct
						+ ". Hit any key to continue.");
			}
		} else {
			StdDraw.text(0.5, 0.25, "Type to unscramble the word.");
		}
		StdDraw.show();
	}

	/**
	 * Handles a key press, returning the new, longer guess. Also handles
	 * backspaces.
	 */
	public static String handleKeyPress(String guess) {
		while (!StdDraw.hasNextKeyTyped()) {
			// Wait for a key to be typed
		}
		char c = StdDraw.nextKeyTyped();
		if (c == '\b' && guess.length() > 0) {
			return guess.substring(0, guess.length() - 1);
		}
		return guess + c;
	}

	/** Returns true if guess is an anagram of correct and is in the dictionary. */
	public static boolean isCorrect(String guess, String correct,
			String[] dictionary) {
		// Verify that guess is a valid anagram
		char[] g = guess.toCharArray();
		sort(g);
		char[] c = correct.toCharArray();
		sort(c);
		for (int i = 0; i < g.length; i++) {
			if (g[i] != c[i]) {
				return false;
			}
		}
		// Check the dictionary
		return contains(guess, dictionary);
	}

	/** Plays the game. */
	public static void main(String[] args) {
		StdDraw.enableDoubleBuffering();
		String[] dictionary = new In("enable1.txt").readAllLines();
		while (true) {
			String correct = randomWord(dictionary);
			char[] scrambled = correct.toCharArray();
			scramble(scrambled);
			String guess = "";
			draw(correct, new String(scrambled), guess, dictionary);
			while (guess.length() < correct.length()) {
				guess = handleKeyPress(guess);
				draw(correct, new String(scrambled), guess, dictionary);
			}
			handleKeyPress(guess);
		}
	}

	/** Returns a random word from dictionary. */
	public static String randomWord(String[] dictionary) {
		return dictionary[StdRandom.uniform(dictionary.length)];
	}

	/** Randomly scrambles word. */
	public static void scramble(char[] word) {
		for (int i = word.length - 1; i > 0; i--) {
			int j = StdRandom.uniform(i + 1);
			char temp = word[i];
			word[i] = word[j];
			word[j] = temp;
		}
	}

	/** Sorts the characters in a in increasing order. */
	public static void sort(char[] a) {
		for (int i = 1; i < a.length; i++) {
			for (int j = i; j > 0 && a[j - 1] > a[j]; j--) {
				char temp = a[j - 1];
				a[j - 1] = a[j];
				a[j] = temp;
			}
		}
	}

}
