/** A word game. */
public class Snowman {

	/**
	 * Returns true if word contains letter.
	 */
	public static boolean contains(String word, char letter) {
		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) == letter) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Draws the state of the game.
	 * 
	 * @param guesses
	 *            Number of guesses the player has left.
	 * @param known
	 *            What the player has already learned (letters or underscores).
	 * @param word
	 *            The correct word.
	 */
	public static void draw(int guesses, char[] known, String word) {
		StdDraw.clear();
		StdDraw.line(0.1, 0.3, 0.9, 0.3); // Ground
		if (guesses < 6) { // Base
			StdDraw.circle(0.5, 0.45, 0.15);
		}
		if (guesses < 5) { // Torso
			StdDraw.circle(0.5, 0.7, 0.1);
		}
		if (guesses < 4) { // Head
			StdDraw.circle(0.5, 0.85, 0.05);
		}
		if (guesses < 3) { // Left eye
			StdDraw.filledRectangle(0.48, 0.87, 0.01, 0.01);
		}
		if (guesses < 2) { // Right eye
			StdDraw.filledRectangle(0.52, 0.87, 0.01, 0.01);
		}
		if (guesses < 1) { // Nose
			StdDraw.setPenColor(java.awt.Color.ORANGE);
			StdDraw.filledPolygon(new double[] { 0.5, 0.5, 0.54 }, new double[] { 0.83, 0.84, 0.835 });
			StdDraw.setPenColor();
		}
		String s = "" + known[0];
		for (int i = 1; i < known.length; i++) {
			s += " " + known[i];
		}
		StdDraw.text(0.5, 0.2, s);
		if (guesses == 0) {
			StdDraw.text(0.5, 0.1, "The word was '" + word + "'. Press any key to play again.");
		} else if (isComplete(known)) {
			StdDraw.text(0.5, 0.1, "You win! Press any key to play again.");
		} else {
			StdDraw.text(0.5, 0.1, guesses + " guesses left. Type a letter.");
		}
		StdDraw.show();
	}

	/**
	 * Modified known, filling in all instances of letter found in word. For
	 * example, if known is {'a', '_', '_', 'l', '_'}, word is "apple", and
	 * letter is 'p', known becomes {'a', 'p', 'p', 'l', '_'}.
	 */
	public static void fillIn(char[] known, String word, char letter) {
		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) == letter) {
				known[i] = letter;
			}
		}
	}

	/**
	 * Returns true if known contains no underscores.
	 * 
	 * @param known
	 *            What the player has already learned (letters or underscores).
	 */
	public static boolean isComplete(char[] known) {
		for (int i = 0; i < known.length; i++) {
			if (known[i] == '_') {
				return false;
			}
		}
		return true;
	}

	/** Runs the game repeatedly. */
	public static void main(String[] args) {
		StdDraw.enableDoubleBuffering();
		String[] dictionary = new In("enable1.txt").readAllLines();
		while (true) {
			String word = randomWord(dictionary);
			int guesses = 6;
			char[] known = new char[word.length()];
			for (int i = 0; i < known.length; i++) {
				known[i] = '_';
			}
			while (guesses > 0 && !isComplete(known)) {
				draw(guesses, known, word);
				while (!StdDraw.hasNextKeyTyped()) {
					// Wait for keypress
				}
				char letter = StdDraw.nextKeyTyped();
				if (contains(word, letter)) {
					fillIn(known, word, letter);
				} else {
					guesses--;
				}
			}
			draw(guesses, known, word);
			while (!StdDraw.hasNextKeyTyped()) {
				// Wait for keypress
			}
			StdDraw.nextKeyTyped(); // Use up the key typed
		}
	}

	/** Returns a random word from dictionary. */
	public static String randomWord(String[] dictionary) {
		return dictionary[StdRandom.uniform(dictionary.length)];
	}

}
