/** The game also known as The Captain's Mistress. */
public class Connect4 {

	public static void main(String[] args) {
		new Connect4().run();
	}
	
	/** Logical model of the game. */
	private Connect4Model model;
	
	public Connect4() {
		model = new Connect4Model();
	}
	
	/** Draws the state of the game. */
	public void draw() {
		StdDraw.clear(StdDraw.BLUE);
		for (int r = 0; r < Connect4Model.ROWS; r++) {
			for (int c = 0; c < Connect4Model.COLUMNS; c++) {
				StdDraw.setPenColor(model.colorAt(r, c));
				StdDraw.filledCircle((1.0 / (Connect4Model.COLUMNS + 2)) * (c + 1.5),
						(1.0 / (Connect4Model.ROWS + 2)) * (r + 2.5), 0.05);
			}
		}
		StdDraw.setPenColor(StdDraw.WHITE);
		if (model.winner() == StdDraw.BLACK) {
			StdDraw.text(0.5, 0.1, "Black wins!");
		} else if (model.winner() == StdDraw.WHITE) {
			StdDraw.text(0.5, 0.1, "White wins!");
		} else if (model.isFull()) {
			StdDraw.text(0.5, 0.1, "Draw.");
		} else if (model.getCurrentPlayer() == StdDraw.BLACK) {
			StdDraw.text(0.5, 0.1, "Black to play.");
		} else {
			StdDraw.text(0.5, 0.1, "White to play.");
		}
		StdDraw.show(0);
	}

	/** Plays the game. */
	public void run() {
		while ((model.winner() == StdDraw.GRAY) && !model.isFull()) {
			draw();
			if (model.getCurrentPlayer() == StdDraw.BLACK) { // Computer turn
				// The second argument to bestMoveForBlack() can be increased
				// for a stronger, slower opponent
				model.drop(StdDraw.BLACK, model.bestMoveForBlack(5));
				model.toggleCurrentPlayer();
			} else { // Human turn
				while (!StdDraw.mousePressed()) {
					// Wait for mouse press
				}
				int column = (int) (Math.round((StdDraw.mouseX() / (1.0 / (Connect4Model.COLUMNS + 2))) - 1.5));
				if (model.isLegal(column)) {
					model.drop(model.getCurrentPlayer(), column);
					model.toggleCurrentPlayer();
				}
				while (StdDraw.mousePressed()) {
					// Wait for mouse release
				}
			}
		}
		draw();	
	}

}
