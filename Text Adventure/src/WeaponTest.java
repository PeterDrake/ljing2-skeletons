import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class WeaponTest {

	private Weapon sword;

	@Before
	public void setUp() throws Exception {
		sword = new Weapon("a sword", 3, "a trusty steel shortsword");
	}

	@Test
	public void testGetName() {
		assertEquals("a sword", sword.getName());
	}

	@Test
	public void testGetDescription() {
		assertEquals("a trusty steel shortsword", sword.getDescription());
	}

	@Test
	public void testGetDamage() {
		assertEquals(3, sword.getDamage());
	}

}
