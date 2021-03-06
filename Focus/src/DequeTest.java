import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;

public class DequeTest {

	private Deque<Integer> deque;
	
	@Before
	public void setUp() throws Exception {
		deque = new Deque<Integer>();
	}

	@Test
	public void detectsEmptiness() {
		assertTrue(deque.isEmpty());
		deque.addFront(3);
		assertFalse(deque.isEmpty());
		deque.removeFront();
		assertTrue(deque.isEmpty());
	}
	
	@Test
	public void storesSize() {
		assertEquals(0, deque.size());
		deque.addFront(3);
		assertEquals(1, deque.size());
		deque.addFront(7);
		assertEquals(2, deque.size());
		deque.removeFront();
		assertEquals(1, deque.size());
		deque.removeFront();
		assertEquals(0, deque.size());
	}

	@Test
	public void addsToBack() {
		assertEquals("<>", deque.toString());
		deque.addBack(3);
		deque.addBack(7);
		deque.addBack(4);
		assertEquals("<3, 7, 4>", deque.toString());
	}

	@Test
	public void addsToFront() {
		assertEquals("<>", deque.toString());
		deque.addFront(3);
		deque.addFront(7);
		deque.addFront(4);
		assertEquals("<4, 7, 3>", deque.toString());
	}

	@Test
	public void removesFromBack() {
		deque.addBack(3);
		deque.addBack(7);
		deque.addBack(4);
		assertEquals(new Integer(4), deque.removeBack());
		assertEquals("<3, 7>", deque.toString());
		assertEquals(new Integer(7), deque.removeBack());
		assertEquals("<3>", deque.toString());
		assertEquals(new Integer(3), deque.removeBack());
		assertEquals("<>", deque.toString());
	}

	@Test
	public void removesFromFront() {
		deque.addBack(3);
		deque.addBack(7);
		deque.addBack(4);
		assertEquals(new Integer(3), deque.removeFront());
		assertEquals("<7, 4>", deque.toString());
		assertEquals(new Integer(7), deque.removeFront());
		assertEquals("<4>", deque.toString());
		assertEquals(new Integer(4), deque.removeFront());
		assertEquals("<>", deque.toString());
	}
	
	@Test
	public void handlesMultipleAdditionsAndRemovals() {
		deque.addBack(3);
		assertEquals(new Integer(3), deque.removeBack());
		deque.addFront(7);
		assertEquals(new Integer(7), deque.removeBack());
		assertTrue(deque.isEmpty());
	}

	@Test
	public void iterates() {
		deque.addBack(3);
		deque.addBack(7);
		deque.addBack(4);
		Iterator<Integer> iterator = deque.iterator();
		assertTrue(iterator.hasNext());
		assertEquals(new Integer(3), iterator.next());
		assertTrue(iterator.hasNext());
		assertEquals(new Integer(7), iterator.next());
		assertTrue(iterator.hasNext());
		assertEquals(new Integer(4), iterator.next());
		assertFalse(iterator.hasNext());
		// The deque should not have been modified by the iteration
		assertEquals("<3, 7, 4>", deque.toString());
	}

	@Test
	public void isGeneric() {
		Deque<String> d = new Deque<String>();
		d.addBack("a");
		d.addBack("b");
		d.addBack("c");
		assertEquals("<a, b, c>", d.toString());
	}

	@Test
	public void implementsIterable() {
		// This test will not compile if Deque does not implement Iterable
		@SuppressWarnings("unused") // Avoid a compiler warning on next line
		Iterable<String> iter = new Deque<String>();
	}

}
