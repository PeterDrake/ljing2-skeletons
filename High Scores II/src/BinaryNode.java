/** Node in a binary tree. */
public class BinaryNode<T> {

	/** Item stored at this node. */
	private T item;
	
	/** Left child. */
	private BinaryNode<T> left;
	
	/** Right child. */
	private BinaryNode<T> right;

	/** Creates a new node with the specified left child, item, and right child. */
	public BinaryNode(BinaryNode<T> left, T item, BinaryNode<T> right) {
		this.left = left;
		this.item = item;
		this.right = right;
	}
	
	/** Creates a new leaf node. */
	public BinaryNode(T item) {
		this.item = item;
	}

	/** Returns the item stored in this node. */
	public T getItem() {
		return item;
	}

	/** Returns the left child of this node. */
	public BinaryNode<T> getLeft() {
		return left;
	}

	/** Returns the right child of this node. */
	public BinaryNode<T> getRight() {
		return right;
	}

}
