/** A six-sided die. */
public class Die {

	/** The face of the die currently on top. */
	private int topFace;

	/** A new die has the 1 on top. */
	public Die() {
		topFace = 1;
	}

	/** Returns the die's top face. */
	public int getTopFace() {
		return topFace;
	}

	/** Rolls the die. */
	public void roll() {
		topFace = StdRandom.uniform(1, 7);
	}

}
