import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MonsterTest {

	private Monster wolf;

	@Before
	public void setUp() throws Exception {
		wolf = new Monster("wolf", 2, "a ferocious, snarling wolf");
	}

	@Test
	public void testGetName() {
		assertEquals("wolf", wolf.getName());
	}

	@Test
	public void testGetDescription() {
		assertEquals("a ferocious, snarling wolf", wolf.getDescription());
	}

	@Test
	public void testGetArmor() {
		assertEquals(2, wolf.getArmor());
	}

}
