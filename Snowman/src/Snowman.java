/** A word game. */
public class Snowman {

	/** 
	 * Returns true if known contains no underscores.
	 * 
	 * @param known What the player has already learned (letters or underscores).
	 */
	public static boolean complete(char[] known) {
		// TODO You have to write this
		return false;
	}

	/**
	 * Draws the state of the game.
	 * 
	 * @param guesses Number of guesses the player has left.
	 * @param known What the player has already learned (letters or underscores).
	 * @param word The correct word.
	 */
	public static void draw(int guesses, char[] known, String word) {
		StdDraw.clear();
		// TODO Your drawing code goes here
		String s = "" + known[0];
		for (int i = 1; i < known.length; i++) {
			s += " " + known[i];
		}
		StdDraw.text(0.5, 0.2, s);
		if (guesses == 0) {
			StdDraw.text(0.5, 0.1, "The word was '" + word + "'. Press any key to play again.");
		} else if (complete(known)) {
			StdDraw.text(0.5, 0.1, "You win! Press any key to play again.");
		} else {
			StdDraw.text(0.5, 0.1, guesses + " guesses left. Type a letter.");
		}
		StdDraw.show(0);
	}

	/**
	 * Returns true if letter occurs at least once in word. The corresponding
	 * elements of known are set to letter.
	 * 
	 * @param letter The letter the player has just guessed.
	 * @param known What the player has already learned (letters or underscores).
	 * @param word The correct word.
	 */
	public static boolean found(char letter, String word, char[] known) {
		// TODO You have to write this
		return false;
	}

	/** Runs the game repeatedly. */
	public static void main(String[] args) {
		String[] dictionary = new In("enable1.txt").readAllLines();
		while (true) {
			String word = randomWord(dictionary);
			int guesses = 6;
			char[] known = new char[word.length()];
			for (int i = 0; i < known.length; i++) {
				known[i] = '_';
			}
			while (guesses > 0 && !complete(known)) {
				draw(guesses, known, word);
				while (!StdDraw.hasNextKeyTyped()) {
					// Wait for keypress
				}
				char letter = StdDraw.nextKeyTyped();
				if (!found(letter, word, known)) {
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
		// TODO You have to write this
		return null;
	}

}
