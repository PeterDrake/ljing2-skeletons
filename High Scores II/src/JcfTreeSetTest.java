import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class JcfTreeSetTest {

	private Set<Integer> set;
	
	@Before
	public void setUp() throws Exception {
		set = new JcfTreeSet<Integer>();
	}

	@Test
	public void testAdd() {
		assertFalse(set.contains(8));
		set.add(8);
		assertTrue(set.contains(8));
		set.add(3);
		assertTrue(set.contains(3));
		assertTrue(set.contains(8));
	}

	@Test
	public void testRemove() {
		set.add(8);
		set.remove(8);
		assertFalse(set.contains(8));
	}

	@Test
	public void testManyPuts() {
		int[] numbers = new int[1000];
		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = StdRandom.uniform(Integer.MAX_VALUE);
		}
		for (int n : numbers) {
			set.add(n);
		}
		for (int n : numbers) {
			assertTrue(set.contains(n));
		}
	}

	@Test
	public void testIteration() {
		int[] numbers = {8, 6, 7, 5, 3, 0, 9};
		for (int n : numbers) {
			set.add(n);
		}
		java.util.Arrays.sort(numbers);
		int i = 0;
		for (int n : set) {
			assertEquals(numbers[i], n);
			i++;
		}
		assertEquals(numbers.length, i);
	}

}
