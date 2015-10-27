import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class HighScoreListTest {

	private HighScoreList list;
	
	@Before
	public void setUp() throws Exception {
		list = new HighScoreList(10);
	}

	@Test
	public void testAddScore() {
		list.addScore(23);
		list.addScore(42);
		list.addScore(18);
		assertEquals(23, list.get(0));
		assertEquals(42, list.get(1));
		assertEquals(18, list.get(2));
	}

	@Test
	public void testToString() {
		assertEquals("[]", list.toString());
		list.addScore(23);
		assertEquals("[23]", list.toString());
		list.addScore(42);
		assertEquals("[23, 42]", list.toString());
		list.addScore(18);
		assertEquals("[23, 42, 18]", list.toString());
	}

	@Test
	public void testInsertionSortEasy() {
		list.addScore(2);
		list.addScore(5);
		list.addScore(1);
		list.addScore(4);
		list.addScore(3);
		list.addScore(6);
		list.insertionSort();
		assertEquals("[6, 5, 4, 3, 2, 1]", list.toString());
	}

	@Test
	public void testInsertionSortHard() {
		list = new HighScoreList(1000);
		for (int i = 0; i < 1000; i++) {
			list.addScore(StdRandom.uniform(Integer.MAX_VALUE));
		}
		list.insertionSort();
		for (int i = 1; i < 1000; i++) {
			assertTrue(list.get(i - 1) >= list.get(i));
		}
	}

	@Test
	public void testMergeSortEasy() {
		list.addScore(2);
		list.addScore(5);
		list.addScore(1);
		list.addScore(4);
		list.addScore(3);
		list.addScore(6);
		list.mergeSort();
		assertEquals("[6, 5, 4, 3, 2, 1]", list.toString());
	}
	
	@Test
	public void testMergeSortHard() {
		list = new HighScoreList(1000);
		for (int i = 0; i < 1000; i++) {
			list.addScore(StdRandom.uniform(Integer.MAX_VALUE));
		}
		list.mergeSort();
		for (int i = 1; i < 1000; i++) {
			assertTrue(list.get(i - 1) >= list.get(i));
		}
	}

}
