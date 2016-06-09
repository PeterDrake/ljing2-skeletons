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
	public void storesName() {
		assertEquals("wolf", wolf.getName());
	}

	@Test
	public void storesArmor() {
		assertEquals(2, wolf.getArmor());
	}

	@Test
	public void storesDescription() {
		assertEquals("a ferocious, snarling wolf", wolf.getDescription());
	}

}
