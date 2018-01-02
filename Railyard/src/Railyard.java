/** Railyard puzzle. */
public class Railyard {

	/** Background color. */
	public static final java.awt.Color TAN = new java.awt.Color(192, 192, 128);

	public static void main(String[] args) {
		new Railyard().run();
	}

	/** Logical model of the puzzle. */
	private RailyardModel model;

	public Railyard() {
		model = new RailyardModel();
	}

	/** Draws the state of the puzzle. */
	public void draw(String instructions) {
		StdDraw.clear(TAN);
		StdDraw.setPenColor();
		// Draw instructions
		StdDraw.text(0.5, 0.9, instructions);
		// Draw switch
		drawCar(0.7, 0.6, switchSymbol());
		// Draw tracks
		StdDraw.line(0.6, 0.5, 0.9, 0.5); // Main line (right side)
		StdDraw.line(0.1, 0.6, 0.4, 0.6); // Spur 0
		StdDraw.line(0.1, 0.5, 0.4, 0.5); // Spur 1
		StdDraw.line(0.1, 0.4, 0.4, 0.4); // Top of loop 2
		StdDraw.line(0.1, 0.3, 0.4, 0.3); // Bottom of loop 2
		StdDraw.line(0.1, 0.3, 0.1, 0.4); // Back of loop 2
		StdDraw.line(0.1, 0.2, 0.4, 0.2); // Top of loop 3
		StdDraw.line(0.1, 0.1, 0.4, 0.1); // Bottom of loop 3
		StdDraw.line(0.1, 0.1, 0.1, 0.2); // Back of loop 3
		StdDraw.line(0.4, 0.6, 0.6, 0.5); // Link from spur 0
		StdDraw.line(0.4, 0.5, 0.6, 0.5); // Link from spur 1
		if (model.isPulling()) {
			StdDraw.line(0.4, 0.4, 0.6, 0.5); // Link to top of loop 2
			StdDraw.line(0.4, 0.2, 0.6, 0.5); // Link to top of loop 3
		} else {
			StdDraw.line(0.4, 0.3, 0.6, 0.5); // Link to bottom of loop 2
			StdDraw.line(0.4, 0.1, 0.6, 0.5); // Link to bottom of loop 3
		}
		// Draw cars
		drawBranchCars(model.getBranch(0), 0.6);
		drawBranchCars(model.getBranch(1), 0.5);
		drawBranchCars(model.getBranch(2), 0.3);
		drawBranchCars(model.getBranch(3), 0.1);
		drawMainLineCars();
		StdDraw.show();
	}

	/** Draws the cars on a given branch. */
	public void drawBranchCars(Branch branch, double y) {
		for (int i = 0; i < branch.size(); i++) {
			drawCar(0.1 + 0.05 * i, y, branch.get(i) + "");
		}
	}

	/** Draws a car at x, y, with label as the text. */
	public void drawCar(double x, double y, String label) {
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.filledSquare(x, y, 0.02);
		StdDraw.setPenColor();
		StdDraw.square(x, y, 0.02);
		StdDraw.text(x, y, label);
	}

	/** Draws the cars on the main line. */
	public void drawMainLineCars() {
		Branch line = model.getMainLine();
		for (int i = 0; i < line.size(); i++) {
			drawCar(0.9 - 0.05 * i, 0.5, line.get(i) + "");
		}
	}

	/** Resolves a mouse click. */
	public void handleMouseClick() {
		while (!StdDraw.isMousePressed()) {
			// Wait for mouse to be pressed
		}
		while (StdDraw.isMousePressed()) {
			// Wait for mouse to be released
		}
		double x = StdDraw.mouseX();
		double y = StdDraw.mouseY();
		if (x > 0.65 && x < 0.75 && y > 0.55 && y < 0.65) {
			model.flipSwitch();
		} else if (x < 0.4 && y < 0.65) {
			int i;
			if (y > 0.55) {
				i = 0;
			} else if (y > 0.45) {
				i = 1;
			} else if (y > 0.25) {
				i = 2;
			} else {
				i = 3;
			}
			model.move(i);
		}
	}

	/** Runs the puzzle. */
	public void run() {
		StdDraw.enableDoubleBuffering();
		while (!model.isSolved()) {
			draw("Click on " + switchSymbol() + " or a branch at left to build 012345 at right.");
			handleMouseClick();
		}
		draw("You win!");
	}

	/**
	 * Returns the symbols to be displayed for the switch (">" for pulling, "<"
	 * for pushing).
	 */
	public String switchSymbol() {
		if (model.isPulling()) {
			return ">";
		}
		return "<";
	}

}
