import static org.junit.Assert.*;
import org.junit.Test;

public class SnowmanTest {

	@Test
	public void containsAcceptsPresentLetter() {
		assertTrue(Snowman.contains("snowman", 'o'));
	}
	
	@Test
	public void containsRejectsAbsentLetter() {
		assertFalse(Snowman.contains("snowman", 'e'));
	}
		
	@Test
	public void isCompleteAcceptsCompleteWord() {
		assertTrue(Snowman.isComplete("snowman".toCharArray()));
	}

	@Test
	public void isCompleteRejectsArrayWithUnderscoreAtBeginning() {
		assertFalse(Snowman.isComplete("_nowman".toCharArray()));
	}
	
	@Test
	public void isCompleteRejectsArrayWithUnderscoreInMiddle() {
		assertFalse(Snowman.isComplete("sno_man".toCharArray()));
	}
	
	@Test
	public void isCompleteRejectsArrayWithUnderscoreAtEnd() {
		assertFalse(Snowman.isComplete("snowma_".toCharArray()));
	}
	
	@Test
	public void fillInFillsInAllCopiesOfPresentLetter() {
		char[] known = "s______".toCharArray();
		Snowman.fillIn(known, "snowman", 'n');
		assertArrayEquals("sn____n".toCharArray(), known);
	}

	@Test
	public void randomWordReturnsAWordFromDictionary() {
		String[] dictionary = { "cat", "dog", "bird" };
		String word = Snowman.randomWord(dictionary);
		assertTrue(word.equals("cat") || word.equals("dog") || word.equals("bird"));
	}

	@Test
	public void allWordsInDictionaryAreEquallyLikely() {
		// Make a tiny dictionary
		String[] dictionary = { "cat", "dog", "bird" };
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
