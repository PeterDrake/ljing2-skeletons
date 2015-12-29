import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/** Binary search tree implementation of a map. */
public class BinarySearchTreeSet<T extends Comparable<T>> implements Set<T> {

	/** Root node of the binary search tree. */
	private BinaryNode<T> root;

	@Override
	public void add(T key) {
		root = add(key, root);
	}

	/**
	 * Returns the tree made by adding key to the subtree rooted at node.
	 */
	public BinaryNode<T> add(T key, BinaryNode<T> node) {
		if (node == null) {
			return new BinaryNode<T>(key);
		}
		int c = key.compareTo(node.getItem());
		if (c < 0) {
			return new BinaryNode<T>(add(key, node.getLeft()), node.getItem(), node.getRight());
		}
		if (c == 0) {
			return node;
		}
		return new BinaryNode<T>(node.getLeft(), node.getItem(), add(key, node.getRight()));
	}

	@Override
	public boolean contains(T key) {
		BinaryNode<T> node = root;
		while (node != null) {
			int c = key.compareTo(node.getItem());
			if (c < 0) {
				node = node.getLeft();
			} else if (c == 0) {
				return true;
			} else {
				node = node.getRight();
			}
		}
		return false;
	}

	/** Returns the leftmost key in the subtree rooted at node. */
	public T findLeftmost(BinaryNode<T> node) {
		if (node.getLeft() == null) {
			return node.getItem();
		}
		return findLeftmost(node.getLeft());
	}

	@Override
	public Iterator<T> iterator() {
		List<T> list = new ArrayList<T>();
		traverse(root, list);
		return list.iterator();
	}

	@Override
	public void remove(T key) {
		root = remove(key, root);
	}

	/** Returns the tree left after removing key from the subtree rooted at node. */
	public BinaryNode<T> remove(T key, BinaryNode<T> node) {
		if (node == null) {
			return null;
		}
		int c = key.compareTo(node.getItem());
		if (c < 0) {
			return new BinaryNode<T>(remove(key, node.getLeft()), node.getItem(), node.getRight());
		}
		if (c == 0) { // Key found; remove node
			if (node.getLeft() == null) {
				return node.getRight();
			}
			if (node.getRight() == null) {
				return node.getLeft();
			}
			T leftmost = findLeftmost(node.getRight());
			return new BinaryNode<T>(node.getLeft(), leftmost, remove(leftmost, node.getRight()));
		}
		return new BinaryNode<T>(node.getLeft(), node.getItem(), remove(key, node.getRight()));
	}

	/** Traverses the subtree rooted at node inorder, adding keys to list as it goes. */
	public void traverse(BinaryNode<T> node, List<T> list) {
		if (node == null) {
			return;
		}
		traverse(node.getLeft(), list);
		list.add(node.getItem());
		traverse(node.getRight(), list);
	}

}
