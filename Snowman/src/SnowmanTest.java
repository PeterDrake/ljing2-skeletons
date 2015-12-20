import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;

public class SnowmanTest {

	@Test
	public void testComplete() {
		// complete() should only return true if the char[] passed to it
		// contains no underscores
		assertFalse(Snowman.complete("_oobar".toCharArray()));
		assertFalse(Snowman.complete("foo_ar".toCharArray()));
		assertFalse(Snowman.complete("fooba_".toCharArray()));
		assertTrue(Snowman.complete("foobar".toCharArray()));
	}

	@Test
	public void testFound() {
		char[] known = "______".toCharArray();
		assertFalse(Snowman.found('e', "foobar", known));
		assertTrue(Arrays.equals("______".toCharArray(), known));
		// When the letter is present, found() should return true
		assertTrue(Snowman.found('o', "foobar", known));
		// found() should also modify the char[] that was passed in
		assertTrue(Arrays.equals("_oo___".toCharArray(), known));
	}

	@Test
	public void testRandomWord() {
		// Make a tiny dictionary
		String[] dictionary = { "foo", "bar", "quux" };
		int[] counts = new int[dictionary.length];
		// Many times, choose a random word from this dictionary
		for (int i = 0; i < 5000; i++) {
			String word = Snowman.randomWord(dictionary);
			for (int j = 0; j < dictionary.length; j++) {
				// Update a counter for each word when it appears
				if (dictionary[j].equals(word)) {
					counts[j]++;
				}
			}
		}
		// All words in the dictionary should appear with similar frequency
		for (int i = 0; i < counts.length; i++) {
			assertTrue(counts[i] > (5000.0 / dictionary.length) * 0.5);
			assertTrue(counts[i] < (5000.0 / dictionary.length) * 1.5);
		}
	}

}
