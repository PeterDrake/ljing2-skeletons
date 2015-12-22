/** A ball. */
public class Ball {

	/** Velocity on x axis. */
	private double dx;

	/** Velocity on y axis. */
	private double dy;

	/** Position on x axis. */
	private double x;

	/** Position on y axis. */
	private double y;

	/**
	 * @param x
	 *            X position.
	 * @param y
	 *            Y position.
	 * @param dx
	 *            X velocity.
	 * @param dy
	 *            Y velocity.
	 */
	public Ball(double x, double y, double dx, double dy) {
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
	}

	/** Returns this ball's x coordinate. */
	public double getX() {
		return x;
	}

	/** Returns this ball's y coordinate. */
	public double getY() {
		return y;
	}

	/**
	 * Updates this ball's position for the next time step. The ball will bounce
	 * off the floor or ceiling, but not the left or right sides of the screen
	 * (unless it hits a paddle).
	 * 
	 * @param paddles
	 *            paddles[0] is assumed to be at x-coordinate 0.0, paddles[1] at
	 *            x-coordinate 1.0.
	 */
	public void move(Paddle[] paddles) {
		x += dx;
		y += dy;
		// Ball should bounce off floor ...
		if (y > 1.0) {
			y = 2.0 - y;
			dy = -dy;
		}
		// ... and off ceiling ...
		if (y < 0.0) {
			y = -y;
			dy = -dy;
		}
		// ... and off left paddle ...
		if (x < 0.0 && y <= (paddles[0].getY() + Paddle.HALF_HEIGHT) && y >= (paddles[0].getY() - Paddle.HALF_HEIGHT)) {
			x = -x;
			dx = -dx;
		}
		// ... and off right paddle.
		if (x > 1.0 && y <= (paddles[1].getY() + Paddle.HALF_HEIGHT) && y >= (paddles[1].getY() - Paddle.HALF_HEIGHT)) {
			x = 2.0 - x;
			dx = -dx;
		}
	}

}
