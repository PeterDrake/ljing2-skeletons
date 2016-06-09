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
	public void initiallyThereIsNoBody() {
		assertFalse(beetle.hasBody());		
	}
	
	@Test
	public void canAddBodyIfThereIsNone() {
		assertTrue(beetle.addBody());
		assertTrue(beetle.hasBody());		
	}

	@Test
	public void cannotAddSecondBody() {
		beetle.addBody();
		assertFalse(beetle.addBody());
	}

	@Test
	public void initiallyThereAreNoOtherParts() {
		assertFalse(beetle.hasHead());
		assertFalse(beetle.hasTail());
		assertEquals(0, beetle.getFeelers());
		assertEquals(0, beetle.getEyes());
		assertEquals(0, beetle.getLegs());
	}

	@Test
	public void cannotAddHeadWithoutBody() {
		assertFalse(beetle.addHead());
	}

	@Test
	public void canAddHeadWithBody() {
		beetle.addBody();
		assertTrue(beetle.addHead());
		assertTrue(beetle.hasHead());
	}
	
	@Test
	public void cannotAddSecondHead() {
		beetle.addBody();
		beetle.addHead();
		assertFalse(beetle.addHead());		
	}
	
	@Test
	public void cannotAddTailWithoutBody() {
		assertFalse(beetle.addTail());
	}

	@Test
	public void canAddTailWithBody() {
		beetle.addBody();
		assertTrue(beetle.addTail());
		assertTrue(beetle.hasTail());
	}
	
	@Test
	public void cannotAddSecondTail() {
		beetle.addBody();
		beetle.addTail();
		assertFalse(beetle.addTail());		
	}

	@Test
	public void cannotAddFeelerWithoutHead() {
		assertFalse(beetle.addFeeler());
	}

	@Test
	public void canAddFeelerWithHead() {
		beetle.addBody();
		beetle.addHead();
		assertTrue(beetle.addFeeler());
		assertEquals(1, beetle.getFeelers());
	}
	
	@Test
	public void canAddSecondFeeler() {
		beetle.addBody();
		beetle.addHead();
		beetle.addFeeler();
		assertTrue(beetle.addFeeler());
		assertEquals(2, beetle.getFeelers());
	}

	@Test
	public void cannotAddThirdFeeler() {
		beetle.addBody();
		beetle.addHead();
		beetle.addFeeler();
		beetle.addFeeler();
		assertFalse(beetle.addFeeler());
	}

	@Test
	public void cannotAddEyeWithoutHead() {
		assertFalse(beetle.addEye());
	}

	@Test
	public void canAddEyeWithHead() {
		beetle.addBody();
		beetle.addHead();
		assertTrue(beetle.addEye());
		assertEquals(1, beetle.getEyes());
	}
	
	@Test
	public void canAddSecondEye() {
		beetle.addBody();
		beetle.addHead();
		beetle.addEye();
		assertTrue(beetle.addEye());
		assertEquals(2, beetle.getEyes());
	}

	@Test
	public void cannotAddThirdEye() {
		beetle.addBody();
		beetle.addHead();
		beetle.addEye();
		beetle.addEye();
		assertFalse(beetle.addEye());
	}

	@Test
	public void cannotAddLegWithoutHead() {
		assertFalse(beetle.addLeg());
	}

	@Test
	public void canAddLegWithHead() {
		beetle.addBody();
		beetle.addHead();
		assertTrue(beetle.addLeg());
		assertEquals(1, beetle.getLegs());
	}
	
	@Test
	public void canAddAdditionalLegs() {
		beetle.addBody();
		beetle.addHead();
		for (int i = 0; i < 6; i++) {
			assertTrue(beetle.addLeg());
		}
		assertEquals(6, beetle.getLegs());
	}

	@Test
	public void cannotAddSeventhLeg() {
		beetle.addBody();
		beetle.addHead();
		for (int i = 0; i < 6; i++) {
			beetle.addLeg();
		}
		assertFalse(beetle.addLeg());
	}
	
	@Test
	public void detectsIncompleteBeetle() {
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
	}
	
	@Test
	public void detectsCompleteBeetle() {
		beetle.addBody();
		beetle.addHead();
		beetle.addTail();
		for (int i = 0; i < 6; i++) {
			beetle.addLeg();
		}
		beetle.addFeeler();
		beetle.addFeeler();
		beetle.addEye();
		beetle.addEye();
		assertTrue(beetle.isComplete());
	}

}
