import java.awt.event.KeyEvent;

/** Pong game with graphic user interface. */
public class Pong {

	public static void main(String[] args) {
		new Pong().run();
	}

	/** Logical model of the game. */
	private PongModel model;

	public Pong() {
		model = new PongModel();
	}

	/** Draws the current state of the game, with a text message. */
	public void draw(String message) {
		StdDraw.clear(StdDraw.BLACK);
		StdDraw.setPenColor(StdDraw.WHITE);
		Ball b = model.getBall();
		StdDraw.filledCircle(b.getX(), b.getY(), 0.01);
		for (int i = 0; i < 2; i++) {
			Paddle p = model.getPaddles()[i];
			StdDraw.line(i, p.getY() - Paddle.HALF_HEIGHT, i, p.getY()
					+ Paddle.HALF_HEIGHT);
			StdDraw.text(i, 0.95, model.getScores()[i] + "");
		}
		StdDraw.text(0.5, 0.25, message);
		System.out.println("About to show [" + message + "]");
		StdDraw.show();
		StdDraw.pause(25);
	}

	/**
	 * Checks for keys being pressed and returns an array of the paddle
	 * adjustments (left at index 0, right at index 1).
	 */
	public double[] getPaddleChanges() {
		double[] result = new double[2];
		if (StdDraw.isKeyPressed(KeyEvent.VK_A)) {
			result[0] += PongModel.PADDLE_SPEED;
		}
		if (StdDraw.isKeyPressed(KeyEvent.VK_Z)) {
			result[0] -= PongModel.PADDLE_SPEED;
		}
		if (StdDraw.isKeyPressed(KeyEvent.VK_K)) {
			result[1] += PongModel.PADDLE_SPEED;
		}
		if (StdDraw.isKeyPressed(KeyEvent.VK_M)) {
			result[1] -= PongModel.PADDLE_SPEED;
		}
		return result;
	}

	/** Plays the game. */
	public void run() {
		StdDraw.enableDoubleBuffering();
		// Add a little padding so that paddles and scores, drawn right at y =
		// 0.0 or 1.0, are visible
		StdDraw.setScale(-0.05, 1.05);
		draw("Left player use A/Z. Right player use K/M.");
		StdDraw.pause(2000);
		while (!model.gameOver()) {
			draw("");
			model.advance(getPaddleChanges());
		}
		if (model.getScores()[0] == 5) {
			draw("Left player wins!");
		} else {
			draw("Right player wins!");
		}
	}

}
