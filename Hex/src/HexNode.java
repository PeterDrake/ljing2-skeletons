public class HexNode {

	/**
	 * Color of this node (one of the int constants BLACK, WHITE, or VACANT
	 * defined in HexModel).
	 */
	private int color;

	/** List of neighbors of this node. */
	private java.util.ArrayList<HexNode> neighbors;

	/** Makes this node unoccupied, i.e., color '.'. */
	public HexNode() {
		this(HexModel.VACANT);
	}

	/** Makes this node the specified color. */
	public HexNode(int color) {
		this.color = color;
		neighbors = new java.util.ArrayList<HexNode>();
	}

	/** Adds that as a neighbor of this node. */
	public void addNeighbor(HexNode that) {
		neighbors.add(that);
	}

	/**
	 * Returns the color of this node (one of the int constants BLACK, WHITE, or
	 * VACANT defined in HexModel).
	 */
	public int getColor() {
		return color;
	}

	/** Returns the list of neighbors of this node. */
	public java.util.ArrayList<HexNode> getNeighbors() {
		return neighbors;
	}

	/** Returns true if that node is a neighbor of this one. */
	public boolean isNeighborOf(HexNode that) {
		return neighbors.contains(that);
	}

	/**
	 * Sets the color of this node (one of the int constants BLACK, WHITE, or
	 * VACANT defined in HexModel).
	 */
	public void setColor(int color) {
		this.color = color;
	}

}
