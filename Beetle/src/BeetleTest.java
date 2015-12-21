import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class BeetleTest {

	private Beetle beetle;
	
	@Before
	public void setUp() throws Exception {
		beetle = new Beetle();
	}

	@Test
	public void testAddBody() {
		assertFalse(beetle.hasBody());
		assertTrue(beetle.addBody());
		assertTrue(beetle.hasBody());
		// Trying to add a second body should fail
		assertFalse(beetle.addBody());
	}

	@Test
	public void testAddHead() {
		assertFalse(beetle.hasHead());
		// Can't add a head without a body
		assertFalse(beetle.addHead());
		beetle.addBody();
		assertTrue(beetle.addHead());
		assertTrue(beetle.hasHead());
		// Trying to add a second head should fail
		assertFalse(beetle.addHead());
	}

	@Test
	public void testAddTail() {
		assertFalse(beetle.hasTail());
		// Can't add a tail without a body
		assertFalse(beetle.addTail());
		beetle.addBody();
		assertTrue(beetle.addTail());
		assertTrue(beetle.hasTail());
		// Trying to add a second tail should fail
		assertFalse(beetle.addTail());
	}

	@Test
	public void testAddFeeler() {
		assertEquals(0, beetle.getFeelers());
		// Can't add feelers without a head
		assertFalse(beetle.addFeeler());
		beetle.addBody();
		beetle.addHead();
		assertTrue(beetle.addFeeler());
		assertEquals(1, beetle.getFeelers());
		assertTrue(beetle.addFeeler());
		assertEquals(2, beetle.getFeelers());
		// Trying to add a third feeler should fail
		assertFalse(beetle.addFeeler());
	}

	@Test
	public void testAddEye() {
		assertEquals(0, beetle.getEyes());
		// Can't add feelers without a head
		assertFalse(beetle.addEye());
		beetle.addBody();
		beetle.addHead();
		assertTrue(beetle.addEye());
		assertEquals(1, beetle.getEyes());
		assertTrue(beetle.addEye());
		assertEquals(2, beetle.getEyes());
		// Trying to add a third eye should fail
		assertFalse(beetle.addEye());
	}

	@Test
	public void testAddLeg() {
		assertEquals(0, beetle.getLegs());
		// Can't add feelers without a head
		assertFalse(beetle.addLeg());
		beetle.addBody();
		beetle.addHead();
		for (int i = 1; i <= 6; i++) {
			assertTrue(beetle.addLeg());
			assertEquals(i, beetle.getLegs());
		}
		// Trying to add a seventh leg should fail
		assertFalse(beetle.addLeg());		
	}
	
	@Test
	public void testIsComplete() {
		beetle.addBody();
		beetle.addHead();
		beetle.addTail();
		for (int i = 0; i < 6; i++) {
			beetle.addLeg();
		}
		beetle.addFeeler();
		beetle.addFeeler();
		beetle.addEye();
		assertFalse(beetle.isComplete());
		beetle.addEye();
		assertTrue(beetle.isComplete());
	}

}
