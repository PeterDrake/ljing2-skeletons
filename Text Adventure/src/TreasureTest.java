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
	public void storesName() {
		assertEquals("diamond", diamond.getName());
	}

	@Test
	public void storesValue() {
		assertEquals(10, diamond.getValue());
	}

	@Test
	public void storesDescription() {
		assertEquals("a huge, glittering diamond", diamond.getDescription());
	}

}
