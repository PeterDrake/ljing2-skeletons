import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ArrayQueueTest {

	private ArrayQueue queue;
	
	@Before
	public void setUp() throws Exception {
		queue = new ArrayQueue(5);
	}

	@Test
	public void grows() {
		assertEquals(0, queue.size());
		queue.add(23);
		assertEquals(1, queue.size());
		queue.add(42);
		assertEquals(2, queue.size());
	}

	@Test
	public void detectsEmptiness() {
		assertTrue(queue.isEmpty());
		queue.add(17);
		assertFalse(queue.isEmpty());
		queue.add(88);
		queue.remove();
		assertFalse(queue.isEmpty());
		queue.remove();
		assertTrue(queue.isEmpty());
	}
	
	@Test
	public void removesFifo() {
		queue.add(1);
		queue.add(2);
		assertEquals(1, queue.remove());
		assertEquals(2, queue.remove());
	}

	@Test
	public void getsItemAtSpecifiedIndex() {
		queue.add(5);
		queue.add(38);
		assertEquals(5, queue.get(0));
		assertEquals(38, queue.get(1));
	}

	@Test
	public void detectsFullness() {
		queue.add(1);
		queue.add(1);
		queue.add(1);
		queue.add(1);
		assertFalse(queue.isFull());
		queue.add(1);
		assertTrue(queue.isFull());
	}

}
