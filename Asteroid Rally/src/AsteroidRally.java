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
		StdDraw.show(10);
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
		StdDraw.filledCircle(e.getX(), e.getY(), e.getRadius());
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
		titleScreen();
		while (true) {
			model = new AsteroidRallyModel();
			while (!model.gameOver()) {
				draw();
				handleKeyPresses();
				model.advance();
			}
			draw();
			StdDraw.setPenColor(StdDraw.WHITE);
			StdDraw.text(0.5, 0.75, "Game over");
			StdDraw.text(0.5, 0.25, "Press space to play again");
			StdDraw.show(0);
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
		StdDraw.text(0.5, 0.25, "Maneuver with A/W/D or J/I/L");
		StdDraw.text(0.5, 0.2, "to avoid rocks and hit all colored flags.");
		StdDraw.show(2000);
	}

}
