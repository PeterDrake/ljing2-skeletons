import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BinarySearchTreeSetTest {

	private Set<Integer> set;
	
	@Before
	public void setUp() throws Exception {
		set = new BinarySearchTreeSet<Integer>();
	}

	@Test
	public void addsItems() {
		assertFalse(set.contains(8));
		set.add(8);
		assertTrue(set.contains(8));
		set.add(3);
		assertTrue(set.contains(3));
		assertTrue(set.contains(8));
	}

	@Test
	public void removesItems() {
		set.add(8);
		set.remove(8);
		assertFalse(set.contains(8));
	}

	@Test
	public void addsManyItems() {
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
	public void iterates() {
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
