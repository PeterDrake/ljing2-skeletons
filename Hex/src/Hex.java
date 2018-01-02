/** Graphic user interface for the Hex game. */
public class Hex {

	/** Background color for the board. */
	public static final java.awt.Color DARK_GREEN = new java.awt.Color(0, 95, 0);

	/** Edit this to change the board size. */
	public static void main(String[] args) {
		new Hex(9).run();
	}

	/** The game model. */
	private HexModel model;

	/** Radius of a hexagon, in pixels. */
	private double radius;

	/** X coordinates of hex centers. */
	private double[][] xs;

	/** Y coordinates of hex centers. */
	private double[][] ys;

	public Hex(int width) {
		model = new HexModel(width);
		radius = 1.0 / (width * 3);
		xs = new double[width][width];
		ys = new double[width][width];
		double r = radius; // To shorten formulae
		double a = r * Math.cos(Math.PI / 6);
		double b = r * Math.sin(Math.PI / 6);
		for (int row = 0; row < width; row++) {
			for (int column = 0; column < width; column++) {
				xs[row][column] = a * (3 + row + (2 * column));
				ys[row][column] = 1.0 - ((r + b) * (row + (model.getWidth() / 2.0)));
			}
		}
	}

	/** Returns the Euclidean distance between x1, y1 and x2, y2. */
	public double distance(double x1, double y1, double x2, double y2) {
		double dx = x1 - x2;
		double dy = y1 - y2;
		return Math.sqrt(dx * dx + dy * dy);
	}

	/** Draws the current state of the model. */
	public void draw() {
		StdDraw.clear(DARK_GREEN);
		int width = model.getWidth();
		for (int r = 0; r < width; r++) {
			for (int c = 0; c < width; c++) {
				java.awt.Color color;
				if (model.getColor(r, c) == HexModel.BLACK) {
					color = StdDraw.BLACK;
				} else if (model.getColor(r, c) == HexModel.WHITE) {
					color = StdDraw.WHITE;
				} else {
					color = StdDraw.GRAY;
				}
				drawHex(color, r, c);
			}
		}
		StdDraw.setPenColor(StdDraw.WHITE);
		int winner = model.findWinner();
		if (winner == HexModel.BLACK) {
			StdDraw.text(0.5, 0.95, "Black wins!");
		} else if (winner == HexModel.WHITE) {
			StdDraw.text(0.5, 0.95, "White wins!");
		} else if (model.getCurrentPlayer() == HexModel.BLACK) {
			StdDraw.text(0.5, 0.95, "Black to choose a hex. Try to connect top to bottom.");
		} else {
			StdDraw.text(0.5, 0.95, "White to choose a hex. Try to connect left side to right side.");
		}
		StdDraw.show();
	}

	/** Draws a particular hex. */
	public void drawHex(java.awt.Color color, int row, int column) {
		double r = radius; // To shorten formulae
		double a = r * Math.cos(Math.PI / 6);
		double b = r * Math.sin(Math.PI / 6);
		double x = xs[row][column];
		double y = ys[row][column];
		// Make hexes slightly smaller to leave borders between them
		a *= 0.9;
		b *= 0.9;
		r *= 0.9;
		double[] cornerXs = { x, x - a, x - a, x, x + a, x + a };
		double[] cornerYs = { y + r, y + b, y - b, y - r, y - b, y + b };
		StdDraw.setPenColor(color);
		StdDraw.filledPolygon(cornerXs, cornerYs);
	}

	/**
	 * Plays the game.
	 */
	public void run() {
		StdDraw.enableDoubleBuffering();
		while (model.findWinner() == HexModel.VACANT) {
			draw();
			while (!StdDraw.isMousePressed()) {
				// Wait for mouse click
			}
			// Figure out which hex the user clicked on
			double x = StdDraw.mouseX();
			double y = StdDraw.mouseY();
			double shortest = Double.POSITIVE_INFINITY;
			int bestR = -1;
			int bestC = -1;
			for (int r = 0; r < model.getWidth(); r++) {
				for (int c = 0; c < model.getWidth(); c++) {
					double d = distance(x, y, xs[r][c], ys[r][c]);
					if (d < shortest) {
						shortest = d;
						bestR = r;
						bestC = c;
					}
				}
			}
			// Play the move (if legal)
			if (model.getColor(bestR, bestC) == HexModel.VACANT) {
				model.playAt(bestR, bestC);
			}
			while (StdDraw.isMousePressed()) {
				// Wait for mouse release
			}
		}
		draw();
	}

}
