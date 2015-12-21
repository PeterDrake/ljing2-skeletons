/** Beetle, with many body parts, for the Beetle game. */
public class Beetle {

	/** True if this beetle has a body. */
	private boolean body;

	/** Number of eyes this beetle has. */
	private int eyes;

	/** Number of feelers this beetle has. */
	private int feelers;

	/** True if this beetle has a head. */
	private boolean head;

	/** Number of legs this beetle has. */
	private int legs;

	/** True if this beetle has a tail. */
	private boolean tail;

	public Beetle() {
		body = false;
		head = false;
		tail = false;
		eyes = 0;
		feelers = 0;
		legs = 0;
	}

	/** Tries to add a body to this beetle. Returns true if this succeeds. */
	public boolean addBody() {
		if (!body) {
			body = true;
			return true;
		}
		return false;
	}

	/** Tries to add an eye to this beetle. Returns true if this succeeds. */
	public boolean addEye() {
		if (head && eyes < 2) {
			eyes++;
			return true;
		}
		return false;
	}

	/** Tries to add a feeler to this beetle. Returns true if this succeeds. */
	public boolean addFeeler() {
		if (head && feelers < 2) {
			feelers++;
			return true;
		}
		return false;
	}

	/** Tries to add a head to this beetle. Returns true if this succeeds. */
	public boolean addHead() {
		if (body && !head) {
			head = true;
			return true;
		}
		return false;
	}

	/** Tries to add a leg to this beetle. Returns true if this succeeds. */
	public boolean addLeg() {
		if (head && legs < 6) {
			legs++;
			return true;
		}
		return false;
	}

	/** Tries to add a tail to this beetle. Returns true if this succeeds. */
	public boolean addTail() {
		if (body && !tail) {
			tail = true;
			return true;
		}
		return false;
	}

	/** Returns the number of eyes this beetle has. */
	public int getEyes() {
		return eyes;
	}

	/** Returns the number of feelers this beetle has. */
	public int getFeelers() {
		return feelers;
	}

	/** Returns the number of legs this beetle has. */
	public int getLegs() {
		return legs;
	}

	/** Returns true if this beetle has a body. */
	public boolean hasBody() {
		return body;
	}

	/** Returns true if this beetle has a head. */
	public boolean hasHead() {
		return head;
	}

	/** Returns true if this beetle has a tail. */
	public boolean hasTail() {
		return tail;
	}

	/** Returns true if this beetle is complete. */
	public boolean isComplete() {
		return body && head && tail && eyes == 2 && feelers == 2 && legs == 6;
	}

}
