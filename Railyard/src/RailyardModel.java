/** Logical model of the Railyard puzzle. */
public class RailyardModel {

	/** Branches where cars start. */
	private Branch[] branches;

	/** Main track where player assembles the solution. */
	private Branch mainLine;

	/**
	 * True if cars are being pulled to the main line rather than pushed to the
	 * branches.
	 */
	private boolean pulling;

	public RailyardModel() {
		mainLine = new ArrayStack(6);
		branches = new Branch[4];
		branches[0] = new ArrayStack(6);
		branches[1] = new ArrayStack(6);
		branches[2] = new ArrayQueue(6);
		branches[3] = new ArrayQueue(6);
		pulling = true;
		placeRandomCars();
	}

	/**
	 * Toggles mode between pulling cars to the main line and pushing them to
	 * the branches.
	 */
	public void flipSwitch() {
		pulling = !pulling;
	}

	/** Returns the ith branch. */
	public Branch getBranch(int i) {
		return branches[i];
	}

	/** Returns the main line. */
	public Branch getMainLine() {
		return mainLine;
	}

	/**
	 * Returns true cars are currently being pulled to the main line rather than
	 * being pushed to the branches.
	 */
	public boolean isPulling() {
		return pulling;
	}

	/** Returns true if the puzzle is solved. */
	public boolean isSolved() {
		if (mainLine.size() != 6) {
			return false;
		}
		for (int i = 0; i < 6; i++) {
			if (mainLine.get(i) != 5 - i) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Moves one car between the main line and branch i. The direction of
	 * movement depends on the push/pull switch.
	 */
	public void move(int i) {
		Branch here;
		Branch there;
		if (pulling) {
			here = branches[i];
			there = mainLine;
		} else {
			here = mainLine;
			there = branches[i];
		}
		if (!(here.isEmpty() || there.isFull())) {
			there.add(here.remove());
		}
	}

	/**
	 * Distribute 15 cars randomly among the branches, with no branch
	 * overflowing.
	 */
	public void placeRandomCars() {
		for (int i = 0; i < 15; i++) {
			int b = StdRandom.uniform(4);
			if (branches[b].isFull()) {
				continue;
			}
			branches[b].add(i);
		}
	}

}
