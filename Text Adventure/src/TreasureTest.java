import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TreasureTest {

	private Treasure diamond;

	@Before
	public void setUp() throws Exception {
		diamond = new Treasure("diamond", 10, "a huge, glittering diamond");
	}

	@Test
	public void testGetName() {
		assertEquals("diamond", diamond.getName());
	}

	@Test
	public void testGetDescription() {
		assertEquals("a huge, glittering diamond", diamond.getDescription());
	}

	@Test
	public void testGetValue() {
		assertEquals(10, diamond.getValue());
	}

}
