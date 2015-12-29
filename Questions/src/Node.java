/** Node in a binary tree. */
public class Node {

	// TODO You have to supply the instance variables
	
	/** Creates a leaf node. */
	public Node(String key) {
		// TODO You have to write this
	}

	public Node(String key, Node left, Node right) {
		// TODO You have to write this
	}

	/** Returns the key of this node. */
	public String getKey() {
		// TODO You have to write this
		return null;
	}

	/** Returns the left child of this node. */
	public Node getLeft() {
		// TODO You have to write this
		return null;
	}

	/** Returns the right child of this node. */
	public Node getRight() {
		// TODO You have to write this
		return null;
	}

	/**
	 * Returns the height of the subtree rooted at this node (depth of the
	 * deepest descendant, or 0 for a leaf).
	 */
	public int height() {
		// TODO You have to write this
		return -1;
	}

	/** Returns true if this node is a leaf. */
	public boolean isLeaf() {
		// TODO You have to write this
		return false;
	}

	/**
	 * Replaces the key of this node (a leaf) with question and gives it two
	 * children. The left child's key is correct. The right child's key is
	 * this node's old key.
	 */
	public void learn(String correct, String question) {
		// TODO You have to write this
	}

	/** Sets the key of this node. */
	public void setKey(String key) {
		// TODO You have to write this
	}

	/** Sets the left child of this node. */
	public void setLeft(Node left) {
		// TODO You have to write this
	}

	/** Sets the right child of this node. */
	public void setRight(Node right) {
		// TODO You have to write this
	}

	@Override
	public String toString() {
		return toString("");
	}

	/**
	 * Returns a String representing the subtree rooted at this node, in outline
	 * form.
	 * 
	 * @indent a String of spaces to be added to be beginning of each line.
	 *         Recursive calls pass in a longer indent String.
	 */
	public String toString(String indent) {
		// TODO You have to write this
		return null;
	}

}
