/** Graphic user interface for the Focus game. */
public class Focus {

	/** Color used to draw the squares. */
	public static final java.awt.Color VERY_LIGHT_BLUE = new java.awt.Color(191, 191, 255);

	/** Color used to draw the squares. */
	public static final java.awt.Color LIGHT_BLUE = new java.awt.Color(127, 127, 255);

	public static void main(String[] args) {
		new Focus().run();
	}

	/** The associated game instance. */
	private FocusModel model;

	public Focus() {
		model = new FocusModel();
	}

	/**
	 * Draws the state of the model, including instructions.
	 * 
	 * @param source
	 *            If non-null, drawing highlights all legal moves from source.
	 */
	public void draw(String instructions, Location source) {
		StdDraw.clear();
		for (int r = 0; r < FocusModel.BOARD_WIDTH; r++) {
			for (int c = 0; c < FocusModel.BOARD_WIDTH; c++) {
				Location destination = new Location(r, c);
				drawSquare(model.getPile(destination), 1.5 + c, 9.5 - r,
						source != null && model.isLegalMove(source, destination));
			}
		}
		StdDraw.setPenColor(LIGHT_BLUE);
		StdDraw.filledRectangle(2, 0.5, 2, 0.45);
		StdDraw.filledRectangle(8, 0.5, 2, 0.45);
		StdDraw.setPenColor();
		StdDraw.text(2, 0.5, "Black reserves: " + model.getReserves(FocusModel.BLACK));
		StdDraw.text(8, 0.5, "White reserves: " + model.getReserves(FocusModel.WHITE));
		StdDraw.text(5, 1.5, instructions);
		StdDraw.show();
	}

	/**
	 * Draws one square (and any pieces piled there) at coordinates x, y.
	 * 
	 * @param highlight
	 *            If true, highlights this square as a legal move.
	 */
	public void drawSquare(Deque<Integer> pile, double x, double y, boolean highlight) {
		if (pile != null) {
			if (highlight) {
				StdDraw.setPenColor(VERY_LIGHT_BLUE);
			} else {
				StdDraw.setPenColor(LIGHT_BLUE);
			}
			StdDraw.filledSquare(x, y, 0.45);
			x -= 0.2;
			y -= 0.2;
			for (int c : pile) {
				StdDraw.setPenColor(c == FocusModel.BLACK ? java.awt.Color.BLACK : java.awt.Color.WHITE);
				StdDraw.filledEllipse(x, y, 0.2, 0.1);
				StdDraw.setPenColor(c == FocusModel.BLACK ? java.awt.Color.WHITE : java.awt.Color.BLACK);
				StdDraw.ellipse(x, y, 0.2, 0.1);
				x += 0.1;
				y += 0.1;
			}
		}
	}

	/**
	 * Returns "Black" or "White" as appropriate.
	 */
	public String getCurrentPlayerName() {
		return model.getCurrentPlayer() == FocusModel.BLACK ? "Black" : "White";
	}

	/** Plays the game. */
	public void run() {
		StdDraw.setXscale(0, 10);
		StdDraw.setYscale(0, 10);
		StdDraw.text(5.0, 8.0, "Focus");
		StdDraw.text(5.0, 6.0, "Get your pieces on top of the piles.");
		StdDraw.text(5.0, 4.0, "The first player unable to move loses.");
		StdDraw.text(5.0, 2.0, "Click to continue.");
		StdDraw.show();
		waitForClick();
		while (!model.isGameOver()) {
			draw(getCurrentPlayerName()
					+ ", click on one of your piles or your reserves.", null);
			Location source;
			do {
				source = waitForClick();
			} while (source == null || !model.isLegalSource(source));
			draw(getCurrentPlayerName() + ", click on destination square.", source);
			Location destination;
			do {
				destination = waitForClick();
			} while (destination == null
					|| !model.isLegalMove(source, destination));
			model.move(source, destination);
			model.toggleColorToPlay();
		}
		model.toggleColorToPlay();
		draw(getCurrentPlayerName() + " wins.", null);
	}

	/**
	 * Waits for the user to click and returns the location where they clicked
	 * (which might be one of Game.RESERVES_LOCATIONS). For invalid locations,
	 * may return null.
	 */
	public Location waitForClick() {
		while (!StdDraw.mousePressed()) {
			// Wait for mouse press
		}
		double x = StdDraw.mouseX();
		double y = StdDraw.mouseY();
		while (StdDraw.mousePressed()) {
			// Wait for mouse release
		}
		// This catches some clicks on the background as if they were on one of
		// the reserve buttons, but handles valid clicks correctly
		if (y <= 2.0) {
			if (x < 5) {
				return FocusModel.RESERVES_LOCATIONS[FocusModel.BLACK];
			}
			return FocusModel.RESERVES_LOCATIONS[FocusModel.WHITE];
		}
		Location result = new Location((int) (10.0 - y), (int) (x - 1.0));
		if (model.isOnBoard(result)) {
			return result;
		}
		return null;
	}

}
