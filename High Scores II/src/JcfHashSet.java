import java.util.HashSet;
import java.util.Iterator;

/** Set implementation using HashSet from the Java Collections Framework. */
public class JcfHashSet<T> implements Set<T> {

	/** All work is delegated to this object. */
	private HashSet<T> set;

	public JcfHashSet() {
		set = new HashSet<T>();
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
