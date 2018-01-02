import java.awt.Color;

/** Beetle game. */
public class BeetleGame {

	/** Array of colors to use when drawing beetles. */
	public static final Color[] COLORS = { StdDraw.RED, StdDraw.GREEN, StdDraw.BLUE, StdDraw.BLACK };

	public static void main(String[] args) {
		BeetleGame game = new BeetleGame();
		game.run();
	}

	/** Beetles for the different players. */
	private Beetle[] beetles;

	/** Number of the current player (starting from 0). */
	private int currentPlayer;

	/** Die for rolling. */
	private Die die;

	public BeetleGame() {
		beetles = new Beetle[4];
		for (int i = 0; i < beetles.length; i++) {
			beetles[i] = new Beetle();
		}
		currentPlayer = 0;
		die = new Die();
	}

	/** Draws the current state of the game. */
	public void draw(String message) {
		StdDraw.clear();
		for (int i = 0; i < beetles.length; i++) {
			StdDraw.setPenColor(COLORS[i]);
			double x = (i + 0.5) / beetles.length;
			drawBeetle(beetles[i], x, 0.75);
			// Square indicates current player
			if (i == currentPlayer) {
				StdDraw.filledSquare(x, 0.5, 0.05);
			}
		}
		StdDraw.square(0.5, 0.3, 0.05);
		StdDraw.text(0.5, 0.3, "" + die.getTopFace());
		StdDraw.text(0.5, 0.1, message);
		StdDraw.show();
	}

	/** Draws b at x, y. */
	public void drawBeetle(Beetle b, double x, double y) {
		if (b.hasBody()) {
			StdDraw.filledEllipse(x, y, 0.05, 0.1);
		}
		if (b.hasHead()) {
			StdDraw.filledCircle(x, y + 0.1, 0.05);
		}
		if (b.hasTail()) {
			StdDraw.filledPolygon(new double[] { x - 0.05, x + 0.05, x }, new double[] { y, y, y - 0.15 });
		}
		if (b.getEyes() >= 1) {
			StdDraw.filledCircle(x - 0.01, y + 0.15, 0.01);
		}
		if (b.getEyes() >= 2) {
			StdDraw.filledCircle(x + 0.01, y + 0.15, 0.01);
		}
		StdDraw.setPenRadius(0.01);
		if (b.getFeelers() >= 1) {
			StdDraw.line(x, y + 0.1, x - 0.05, y + 0.15);
		}
		if (b.getFeelers() >= 2) {
			StdDraw.line(x, y + 0.1, x + 0.05, y + 0.15);
		}
		StdDraw.setPenRadius(0.02);
		if (b.getLegs() >= 1) {
			StdDraw.line(x, y + 0.02, x - 0.1, y + 0.1);
		}
		if (b.getLegs() >= 2) {
			StdDraw.line(x, y, x - 0.1, y);
		}
		if (b.getLegs() >= 3) {
			StdDraw.line(x, y - 0.02, x - 0.1, y - 0.1);
		}
		if (b.getLegs() >= 4) {
			StdDraw.line(x, y + 0.02, x + 0.1, y + 0.1);
		}
		if (b.getLegs() >= 5) {
			StdDraw.line(x, y, x + 0.1, y);
		}
		if (b.getLegs() >= 6) {
			StdDraw.line(x, y - 0.02, x + 0.1, y - 0.1);
		}
		StdDraw.setPenRadius();
	}

	/** Plays the game. */
	public void run() {
		StdDraw.enableDoubleBuffering();
		draw("Click to roll.");
		while (winner() == -1) {
			takeTurn();
		}
		draw(new String[] { "Red", "Green", "Blue", "Black" }[winner()] + " wins!");
	}

	/** Takes one player's turn. */
	public void takeTurn() {
		while (!StdDraw.isMousePressed()) {
			// Wait for mouse to be pressed
		}
		while (StdDraw.isMousePressed()) {
			// Wait for mouse to be released
		}
		die.roll();
		int d = die.getTopFace();
		boolean partAdded = false;
		if (d == 1) {
			partAdded = beetles[currentPlayer].addBody();
		} else if (d == 2) {
			partAdded = beetles[currentPlayer].addHead();
		} else if (d == 3) {
			partAdded = beetles[currentPlayer].addLeg();
		} else if (d == 4) {
			partAdded = beetles[currentPlayer].addEye();
		} else if (d == 5) {
			partAdded = beetles[currentPlayer].addFeeler();
		} else {
			partAdded = beetles[currentPlayer].addTail();
		}
		if (!partAdded) {
			currentPlayer = (currentPlayer + 1) % beetles.length;
		}
		draw("Click to roll.");
	}

	/**
	 * Returns the index (0-3) of the player who won, or -1 if nobody has won
	 * yet.
	 */
	public int winner() {
		for (int i = 0; i < beetles.length; i++) {
			if (beetles[i].isComplete()) {
				return i;
			}
		}
		return -1;
	}

}
