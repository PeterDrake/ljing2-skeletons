import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ArrayStackTest {

	private ArrayStack stack;
	
	@Before
	public void setUp() throws Exception {
		stack = new ArrayStack(5);
	}

	@Test
	public void grows() {
		assertEquals(0, stack.size());
		stack.add(23);
		assertEquals(1, stack.size());
		stack.add(42);
		assertEquals(2, stack.size());
	}

	@Test
	public void detectsEmptiness() {
		assertTrue(stack.isEmpty());
		stack.add(17);
		assertFalse(stack.isEmpty());
		stack.add(88);
		stack.remove();
		assertFalse(stack.isEmpty());
		stack.remove();
		assertTrue(stack.isEmpty());
	}
	
	@Test
	public void removesLifo() {
		stack.add(1);
		stack.add(2);
		assertEquals(2, stack.remove());
		assertEquals(1, stack.remove());
	}

	@Test
	public void getsItemAtSpecifiedIndex() {
		stack.add(5);
		stack.add(38);
		assertEquals(5, stack.get(0));
		assertEquals(38, stack.get(1));
	}

	@Test
	public void detectsFullness() {
		stack.add(1);
		stack.add(1);
		stack.add(1);
		stack.add(1);
		assertFalse(stack.isFull());
		stack.add(1);
		assertTrue(stack.isFull());
	}

}
