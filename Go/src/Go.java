import java.awt.Color;

/** Graphic user interface for the Go game. */
public class Go {

	public static void main(String[] args) {
		new Go().run();
	}

	/** Logical model of the game. */
	private GoModel model;

	public Go() {
		model = new GoModel(selectBoardSize());
	}

	/** Draws the current state of the game. */
	public void draw() {
		StdDraw.clear(StdDraw.GRAY);
		int w = model.getBoardWidth();
		// Draw board
		StdDraw.setPenColor(StdDraw.ORANGE);
		StdDraw.filledRectangle((w / 2.0) - 0.5, (w / 2.0) - 0.5, w / 2.0, w / 2.0);
		// Draw lines
		StdDraw.setPenColor();
		for (int i = 0; i < w; i++) {
			StdDraw.line(0, i, w - 1, i);
			StdDraw.line(i, 0, i, w - 1);
		}
		// Draw stones
		for (int r = 0; r < w; r++) {
			for (int c = 0; c < w; c++) {
				Color color = model.get(r, c);
				if (color != null) {
					StdDraw.setPenColor(color);
					StdDraw.filledCircle(c, w - 1 - r, 0.5);
				}
			}
		}
		// Draw ownership squares
		for (int r = 0; r < w; r++) {
			for (int c = 0; c < w; c++) {
				Color color = model.owner(r, c);
				if (color != null) {
					StdDraw.setPenColor(color);
					StdDraw.filledSquare(c, w - 1 - r, 0.1);
				}
			}
		}
		// Draw instructions
		StdDraw.setPenColor(StdDraw.YELLOW);
		if (model.gameOver()) {
			StdDraw.text((w / 2.0) - 0.5, w + 0.25, "Game over.");
		} else if (model.getCurrentPlayer() == StdDraw.BLACK) {
			StdDraw.text((w / 2.0) - 0.5, w + 0.25, "Black's turn. P to pass, U to undo, or click on a point.");
		} else {
			StdDraw.text((w / 2.0) - 0.5, w + 0.25, "White's turn. P to pass, U to undo, or click on a point.");
		}
		// Draw score
		int black = model.score(StdDraw.BLACK);
		int white = model.score(StdDraw.WHITE);
		StdDraw.text((w / 2.0) - 0.5, w - 0.25,
				"Black: " + black + "    White: " + white + "+7.5 = " + (white + 7.5));
		StdDraw.show();
	}

	/** Handles a mouse click or keyboard command. */
	public void handleCommand() {
		if (StdDraw.hasNextKeyTyped()) {
			char c = StdDraw.nextKeyTyped();
			if (c == 'p') {
				model.pass();
			} else if (c == 'u') {
				model.undo();
			}
		}
		if (StdDraw.mousePressed()) {
			while (StdDraw.mousePressed()) {
				// Wait for mouse release
			}
			int x = (int) Math.round(StdDraw.mouseX());
			int y = (int) Math.round(StdDraw.mouseY());
			model.play(model.getBoardWidth() - 1 - y, x);
		}
	}

	/** Plays the game. */
	public void run() {
		StdDraw.enableDoubleBuffering();
		showRules();
		StdDraw.setScale(-1.5, model.getBoardWidth() + 0.5);
		while (!model.gameOver()) {
			draw();
			handleCommand();
		}
		draw();
	}

	/** Interactively gets the board size from the user. */
	public int selectBoardSize() {
		StdDraw.clear();
		StdDraw.text(0.5, 0.75, "Go");
		StdDraw.text(0.5, 0.5, "Click to select board width");
		StdDraw.text(0.2, 0.25, "5");
		StdDraw.text(0.4, 0.25, "9");
		StdDraw.text(0.6, 0.25, "13");
		StdDraw.text(0.8, 0.25, "19");
		StdDraw.show();
		while (!StdDraw.mousePressed()) {
			// Wait for mouse press
		}
		while (StdDraw.mousePressed()) {
			// Wait for mouse release
		}
		double x = StdDraw.mouseX();
		if (x < 0.3) {
			return 5;
		} else if (x < 0.5) {
			return 9;
		} else if (x < 0.7) {
			return 13;
		} else {
			return 19;
		}
	}

	/** Displays very concise rules. */
	public void showRules() {
		StdDraw.clear();
		StdDraw.text(0.5, 0.9, "CONCISE RULES OF GO:");
		StdDraw.text(0.5, 0.8, "Take turns occupying intersections.");
		StdDraw.text(0.5, 0.7, "Occupy or surround more intersections to win.");
		StdDraw.text(0.5, 0.6, "Tightly surround opponent's pieces to capture them.");
		StdDraw.text(0.5, 0.5, "Self-capture is illegal.");
		StdDraw.text(0.5, 0.4, "It is illegal to repeat a board state with same color to play.");
		StdDraw.text(0.5, 0.3, "The game ends after two consecutive passes.");
		StdDraw.text(0.5, 0.2, "White gets 7.5 extra points to make up for playing second.");
		StdDraw.text(0.5, 0.1, "CLICK TO CONTINUE");
		StdDraw.show();
		while (!StdDraw.mousePressed()) {
			// Wait for mouse press
		}
		while (StdDraw.mousePressed()) {
			// Wait for mouse release
		}
	}

}
