/** A paddle. */
public class Paddle {

	/**
	 * Half the height of a paddle. (This is needed more often than the full
	 * height.)
	 */
	public static final double HALF_HEIGHT = 0.05;

	/** Y coordinate of this paddle. */
	private double y;

	public Paddle(double y) {
		this.y = y;
	}

	/** Returns the y coordinate of this paddle. */
	public double getY() {
		return y;
	}

	/**
	 * Adjusts this paddle's position by dy. The paddle will not move beyond the
	 * top or bottom of the screen.
	 */
	public void move(double dy) {
		y += dy;
		if (y + HALF_HEIGHT > 1.0) {
			y = 1.0 - HALF_HEIGHT;
		}
		if (y < HALF_HEIGHT) {
			y = HALF_HEIGHT;
		}
	}

}
