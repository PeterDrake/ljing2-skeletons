import java.util.Iterator;
import java.util.TreeSet;

/** Set implementation using TreeSet from the Java Collections Framework. */
public class JcfTreeSet<T> implements Set<T> {

	/** All work is delegated to this object. */
	private TreeSet<T> set;

	public JcfTreeSet() {
		set = new TreeSet<T>();
	}

	@Override
	public void add(T key) {
		set.add(key);
	}

	@Override
	public boolean contains(T key) {
		return set.contains(key);
	}

	@Override
	public Iterator<T> iterator() {
		return set.iterator();
	}

	@Override
	public void remove(T key) {
		set.remove(key);
	}

}
