/** A treasure. */
public class Treasure {

	/** A description of this treasure. */
	private String description;

	/** This treasure's name. */
	private String name;

	/** The value of this treasure. */
	private int value;

	/**
	 * @param name
	 *            This treasure's name.
	 * @param value
	 *            The value of this treasure.
	 * @param description
	 *            A description of this treasure.
	 */
	public Treasure(String name, int value, String description) {
		this.name = name;
		this.value = value;
		this.description = description;
	}

	/** Returns a description of this treasure. */
	public String getDescription() {
		return description;
	}

	/** Returns this treasure's name. */
	public String getName() {
		return name;
	}

	/** Returns the value of this treasure. */
	public int getValue() {
		return value;
	}

}
