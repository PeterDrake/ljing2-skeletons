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
	public void storesName() {
		assertEquals("a sword", sword.getName());
	}

	@Test
	public void storesDamage() {
		assertEquals(3, sword.getDamage());
	}

	@Test
	public void storesDescription() {
		assertEquals("a trusty steel shortsword", sword.getDescription());
	}

}
