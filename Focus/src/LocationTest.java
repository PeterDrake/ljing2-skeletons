import static org.junit.Assert.*;
import org.junit.Test;

public class LocationTest {

	@Test
	public void testGetDistanceTo() {
		assertEquals(3, new Location(2, 4).getDistanceTo(new Location(5, 4)));
		assertEquals(4, new Location(2, 4).getDistanceTo(new Location(2, 8)));
		assertEquals(Location.NOT_ON_SAME_LINE, new Location(2, 4).getDistanceTo(new Location(3, 5)));
	}

}
