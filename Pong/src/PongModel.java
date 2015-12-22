/** Model of the Pong game. */
public class PongModel {

	/** Speed at which paddles move. */
	public static final double PADDLE_SPEED = 0.01;

	/** The ball starts randomly at one of these angles. */
	public static final double[] START_ANGLES = { Math.PI * 0.1, Math.PI * 0.2, Math.PI * 0.8, Math.PI * 0.9,
			Math.PI * 1.1, Math.PI * 1.2, Math.PI * 1.8, Math.PI * 1.9 };

	/** The ball. */
	private Ball ball;

	/** Paddles for the left (index 0) and right (index 1) players. */
	private Paddle[] paddles;

	/** Scores for the left (index 0) and right (index 1) players. */
	private int[] scores;

	public PongModel() {
		resetBall();
		paddles = new Paddle[] { new Paddle(0.5), new Paddle(0.5) };
		scores = new int[] { 0, 0 };
	}

	/**
	 * Advances the state of the game.
	 * 
	 * @param paddleAdjustments
	 *            Amounts by which to adjust the positions of the left and right
	 *            paddles.
	 */
	public void advance(double[] paddleAdjustments) {
		paddles[0].move(paddleAdjustments[0]);
		paddles[1].move(paddleAdjustments[1]);
		ball.move(paddles);
		if (ball.getX() > 1.0) {
			scores[0]++;
			resetBall();
		}
		if (ball.getX() < 0.0) {
			scores[1]++;
			resetBall();
		}
	}

	/**
	 * Returns true if the game is over, i.e., one player has scored at least
	 * five points.
	 */
	public boolean gameOver() {
		return scores[0] >= 5 || scores[1] >= 5;
	}

	/** Returns the ball. */
	public Ball getBall() {
		return ball;
	}

	/** Returns the two paddles (left at index 0, right at index 1). */
	public Paddle[] getPaddles() {
		return paddles;
	}

	/** Returns the two scores (left at index 0, right and index 1). */
	public int[] getScores() {
		return scores;
	}

	/** Puts the ball at the center, moving in a random direction. */
	public void resetBall() {
		resetBall(START_ANGLES[StdRandom.uniform(START_ANGLES.length)]);
	}

	/** Puts the ball at the center, moving at the specified angle. */
	public void resetBall(double angle) {
		ball = new Ball(0.5, 0.5, PADDLE_SPEED * Math.cos(angle), PADDLE_SPEED * Math.sin(angle));
	}

}
