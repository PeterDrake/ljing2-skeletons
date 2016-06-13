import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RailyardModelTest {

	private RailyardModel model;
	
	@Before
	public void setUp() throws Exception {
		model = new RailyardModel();
	}

	@Test
	public void togglesPulling() {
		assertTrue(model.isPulling());
		model.flipSwitch();
		assertFalse(model.isPulling());
		model.flipSwitch();
		assertTrue(model.isPulling());
	}

	@Test
	public void detectsSolution() {
		Branch mainLine = model.getMainLine();
		// Just manufacture the cars we need and line them up
		mainLine.add(5);
		mainLine.add(4);
		mainLine.add(3);
		mainLine.add(2);
		mainLine.add(1);
		assertFalse(model.isSolved());
		mainLine.add(8);
		assertFalse(model.isSolved());
		mainLine.remove();
		mainLine.add(0);
		assertTrue(model.isSolved());
	}

	@Test
	public void pullsToMainLine() {
		// Clear out all of the branches
		for (int i = 0; i < 4; i++) {
			Branch b = model.getBranch(i);
			while (!b.isEmpty()) {
				b.remove();
			}
		}
		// Manufacture some specific cars
		Branch b0 = model.getBranch(0);
		Branch b3 = model.getBranch(3);
		b0.add(0);
		b0.add(1);
		b3.add(2);
		b3.add(3);
		// Pull to the main line
		model.move(0);
		model.move(3);
		model.move(0);
		model.move(3);
		Branch mainLine = model.getMainLine();
		assertEquals(4, mainLine.size());
		assertEquals(1, mainLine.get(0));
		assertEquals(2, mainLine.get(1));
		assertEquals(0, mainLine.get(2));
		assertEquals(3, mainLine.get(3));
	}
	
	@Test
	public void pushesToBranches() {
		// Clear out all of the branches
		for (int i = 0; i < 4; i++) {
			Branch b = model.getBranch(i);
			while (!b.isEmpty()) {
				b.remove();
			}
		}
		// Manufacture some specific cars
		Branch b0 = model.getBranch(0);
		Branch b3 = model.getBranch(3);
		b0.add(0);
		b0.add(1);
		b3.add(2);
		b3.add(3);
		// Pull to the main line
		model.move(0);
		model.move(3);
		model.move(0);
		model.move(3);
		// Push to the branches
		model.flipSwitch();
		model.move(0);
		model.move(0);
		model.move(3);
		model.move(3);
		assertEquals(2, b0.size());
		assertEquals(3, b0.get(0));
		assertEquals(0, b0.get(1));
		assertEquals(2, b3.size());
		assertEquals(2, b3.get(0));
		assertEquals(1, b3.get(1));		
	}

}
