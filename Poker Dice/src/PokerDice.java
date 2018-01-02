/** Poker Dice game. */
public class PokerDice {

	/** Used for background. */
	public static final java.awt.Color DARK_GREEN = new java.awt.Color(0, 95, 0);

	public static void main(String[] args) {
		new PokerDice().run();
	}

	/** Logical model of the game. */
	private PokerDiceModel model;

	public PokerDice() {
		model = new PokerDiceModel(selectNumberOfPlayers());
	}

	/** Draws the current state of the game, including instructions. */
	public void draw(String instructions) {
		StdDraw.clear(DARK_GREEN);
		StdDraw.setFont();
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.text(2.5, 5.0, instructions);
		for (int i = 0; i < model.getNumberOfPlayers(); i++) {
			drawHand(i);
		}
		StdDraw.show();
	}

	/**
	 * Draws a die at x, y, with face n showing.
	 *
	 * @param n
	 *            0-5, with 0 representing a 9 and 5 an ace.
	 */
	public void drawDie(double x, double y, int n) {
		StdDraw.setFont(new java.awt.Font("Serif", java.awt.Font.PLAIN, 36));
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.filledSquare(x, y, 0.4);
		if (n == 0) {
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.text(x, y, "9");
		} else if (n == 1) {
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.text(x, y, "10");
		} else if (n == 2) {
			StdDraw.setPenColor(DARK_GREEN);
			StdDraw.text(x, y, "J");
		} else if (n == 3) {
			StdDraw.setPenColor(StdDraw.BLUE);
			StdDraw.text(x, y, "Q");
		} else if (n == 4) {
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.text(x, y, "K");
		} else {
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.text(x, y, "A");
		}
	}

	/**
	 * Draws the ith player's hand, plus "Roll" or "Stay" (if the game is not
	 * over) or "Winner" (if it is and this player won).
	 */
	public void drawHand(int i) {
		Hand h = model.getHand(i);
		for (int j = 0; j < 5; j++) {
			if (i <= model.getCurrentPlayer()) {
				if (i == model.getCurrentPlayer() && h.isKept(j)) {
					StdDraw.setPenColor(StdDraw.GREEN);
					StdDraw.filledSquare(j, i, 0.45);
				}
				drawDie(j, i, h.get(j));
			} else {
				StdDraw.setPenColor(StdDraw.WHITE);
				StdDraw.filledSquare(j, i, 0.4);
			}
		}
		StdDraw.setFont();
		StdDraw.setPenColor(StdDraw.WHITE);
		if (model.gameOver()) {
			if (model.isWinner(i)) {
				StdDraw.text(5, i, "Winner");
			}
		} else if (i == model.getCurrentPlayer()) {
			if (h.allKept()) {
				StdDraw.text(5, i, "Stay");
			} else {
				StdDraw.text(5, i, "Roll");
			}
		}
	}

	/**
	 * Responds to a mouse click, which a player can use to toggle kept dice or
	 * roll/stay.
	 */
	public void handleMouseClick() {
		waitForMouseClick();
		int x = (int) Math.round(StdDraw.mouseX());
		Hand h = model.getHand(model.getCurrentPlayer());
		if (x < 5) {
			h.setKept(x, !h.isKept(x));
		} else {
			model.roll();
		}
	}

	/** Plays the game repeatedly. */
	public void run() {
		StdDraw.enableDoubleBuffering();
		StdDraw.setXscale(-0.5, 5.5);
		StdDraw.setYscale(-0.5, 5.5);
		while (true) {
			while (!model.gameOver()) {
				model.roll();
				while (!model.turnOver()) {
					draw("Select dice to keep or roll/stay.");
					handleMouseClick();
				}
				model.advanceCurrentPlayer();
			}
			draw("Click to play again");
			waitForMouseClick();
			model = new PokerDiceModel(model.getNumberOfPlayers());
		}
	}

	/** Gets the number of players from the user. */
	public int selectNumberOfPlayers() {
		StdDraw.clear(DARK_GREEN);
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.text(0.5, 0.75, "Select number of players (2-5).");
		StdDraw.text(0.5, 0.55, "Hands are:");
		StdDraw.text(0.5, 0.45, "5 of a kind");
		StdDraw.text(0.5, 0.4, "4 of a kind");
		StdDraw.text(0.5, 0.35, "Full house");
		StdDraw.text(0.5, 0.3, "Straight");
		StdDraw.text(0.5, 0.25, "3 of a kind");
		StdDraw.text(0.5, 0.2, "2 pair");
		StdDraw.text(0.5, 0.15, "1 pair");
		StdDraw.text(0.5, 0.1, "High card");
		while (true) {
			if (StdDraw.hasNextKeyTyped()) {
				int n = StdDraw.nextKeyTyped() - '0';
				if (n >= 2 && n <= 5) {
					return n;
				}
			}
		}
	}

	/** Waits until the mouse has been pressed and released. */
	public void waitForMouseClick() {
		while (!StdDraw.isMousePressed()) {
			// Wait for mouse to be pressed
		}
		while (StdDraw.isMousePressed()) {
			// Wait for mouse to be released
		}
	}

}
