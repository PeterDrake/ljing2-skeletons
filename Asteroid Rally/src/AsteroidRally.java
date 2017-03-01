/** Asteroid rally game. */
public class AsteroidRally {

	public static void main(String[] args) {
		new AsteroidRally().run();
	}

	/** Logical model of the game. */
	private AsteroidRallyModel model;

	/** Draws the state of the game. */
	public void draw() {
		StdDraw.clear(StdDraw.BLACK);
		// Draw ship
		drawShip(model.getShip1());
		drawShip(model.getShip2());
		// Draw asteroids
		StdDraw.setPenColor(StdDraw.GRAY);
		for (Extent a : model.getAsteroids()) {
			StdDraw.filledCircle(a.getX(), a.getY(), a.getRadius());
		}
		// Draw flags
		for (Flag f : model.getFlags()) {
			drawFlag(f);
		}
		// Show the results
		StdDraw.show();
		StdDraw.pause(10);
	}

	/** Draws a flag. */
	public void drawFlag(Flag f) {
		StdDraw.setPenColor(StdDraw.YELLOW);
		Extent e = f.getExtent();
		if (f.hasBeenHitByShip1()) {
			if (f.hasBeenHitByShip2()) {
				return; // Don't draw flags both ships have hit
			}
			StdDraw.setPenColor(StdDraw.BLUE);
		} else if (f.hasBeenHitByShip2()) {
			StdDraw.setPenColor(StdDraw.RED);
		}
		StdDraw.filledSquare(e.getX(), e.getY(), e.getRadius());
	}

	/** Draws a ship. */
	public void drawShip(Ship s) {
		Extent e = s.getExtent();
		double radius = e.getRadius();
		double angle = s.getAngle();
		double[] xs = { e.getX() + radius * Math.cos(angle),
				e.getX() + radius * Math.cos(angle + Math.PI * 4.0 / 5),
				e.getX() + radius * Math.cos(angle + Math.PI * 6.0 / 5) };
		double[] ys = { e.getY() + radius * Math.sin(angle),
				e.getY() + radius * Math.sin(angle + Math.PI * 4.0 / 5),
				e.getY() + radius * Math.sin(angle + Math.PI * 6.0 / 5) };
		if (s == model.getShip1()) {
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.polygon(xs, ys);
		} else {
			StdDraw.setPenColor(StdDraw.BLUE);
			StdDraw.filledPolygon(xs, ys);
		}
	}

	/** Handles key presses. */
	public void handleKeyPresses() {
		Ship ship1 = model.getShip1();
		if (StdDraw.isKeyPressed(java.awt.event.KeyEvent.VK_A)) {
			ship1.rotate(0.05);
		}
		if (StdDraw.isKeyPressed(java.awt.event.KeyEvent.VK_D)) {
			ship1.rotate(-0.05);
		}
		if (StdDraw.isKeyPressed(java.awt.event.KeyEvent.VK_W)) {
			ship1.accelerate(0.0001);
		}
		Ship ship2 = model.getShip2();
		if (StdDraw.isKeyPressed(java.awt.event.KeyEvent.VK_J)) {
			ship2.rotate(0.05);
		}
		if (StdDraw.isKeyPressed(java.awt.event.KeyEvent.VK_L)) {
			ship2.rotate(-0.05);
		}
		if (StdDraw.isKeyPressed(java.awt.event.KeyEvent.VK_I)) {
			ship2.accelerate(0.0001);
		}
	}

	/** Runs the game. */
	public void run() {
		StdDraw.enableDoubleBuffering();
		titleScreen();
		while (true) {
			model = new AsteroidRallyModel();
			int winner = model.winner();
			while (winner == 0) {
				draw();
				handleKeyPresses();
				model.advance();
				winner = model.winner();
			}
			draw();
			StdDraw.setPenColor(StdDraw.WHITE);
			String w = "Red";
			if (winner == 2) {
				w = "Blue";
			}
			StdDraw.text(0.5, 0.75, w + " player wins!");
			StdDraw.text(0.5, 0.25, "Press space to play again.");
			StdDraw.show();
			while (!StdDraw.isKeyPressed(java.awt.event.KeyEvent.VK_SPACE)) {
				// Wait for spacebar
			}
		}
	}

	/** Displays the game title and instructions. */
	public void titleScreen() {
		StdDraw.clear(StdDraw.BLACK);
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.text(0.5, 0.75, "Asteroid Rally");
		StdDraw.text(0.5, 0.4, "Red player: maneuver with A/W/D.");
		StdDraw.text(0.5, 0.3, "Blue player: manevuer with J/I/L.");
		StdDraw.text(0.5, 0.2, "Avoid rocks, hit all square flags first.");
		StdDraw.text(0.5, 0.1, "Press space to start.");
		StdDraw.show();
		while (!StdDraw.isKeyPressed(java.awt.event.KeyEvent.VK_SPACE)) {
			// Wait for spacebar
		}
	}

}
