import static org.junit.Assert.*;

import org.junit.Test;

public class AnagramsTest {

	@Test
	public void containsAcceptsWordInDictionary() {
		String[] dictionary = {"cat", "dog"};
		assertTrue(Anagrams.contains("cat", dictionary));
	}
	
	@Test
	public void containsRejectsWordNotInDictionary() {
		String[] dictionary = {"cat", "dog"};
		assertFalse(Anagrams.contains("snake", dictionary));		
	}

	@Test
	public void sortAlphabetizes() {
		char[] letters = "scrambled".toCharArray();
		Anagrams.sort(letters);
		assertArrayEquals("abcdelmrs".toCharArray(), letters);
	}
	
	@Test
	public void randomWordReturnsAWordFromDictionary() {
		String[] dictionary = { "cat", "dog", "bird" };
		String word = Anagrams.randomWord(dictionary);
		assertTrue(word.equals("cat") || word.equals("dog") || word.equals("bird"));
	}

	@Test
	public void allWordsInDictionaryAreEquallyLikely() {
		// Make a tiny dictionary
		String[] dictionary = { "cat", "dog", "bird" };
		int[] counts = new int[dictionary.length];
		// Many times, choose a random word from this dictionary
		for (int i = 0; i < 5000; i++) {
			String word = Anagrams.randomWord(dictionary);
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

	@Test
	public void resultOfCopyHasSameCharacters() {
		char[] a = "abcde".toCharArray();
		char[] b = Anagrams.copy(a);
		assertArrayEquals(a, b);
	}

	@Test
	public void resultOfCopyIsNotSameObjectAsOriginal() {
		char[] a = "abcde".toCharArray();
		char[] b = Anagrams.copy(a);
		assertNotSame(a, b);
	}

	@Test
	public void scrambleMakesAllPermutationsEquallyLikely() {
		// This test is inspired by experiments 1.1.36 and 1.1.37 in Sedgewick
		// and Wayne, Algorithms, 4th edition. It tests whether, over many
		// scrambles, each letter is equally likely to end up in each position. It
		// may fail once in a great while, but it generally passes for a correct
		// algorithm and fails for common incorrect algorithms.
		char[] sorted = "abcde".toCharArray();
		int[][] counts = new int[sorted.length][sorted.length];
		for (int trial = 0; trial < 5000; trial++) {
			boolean[] seen = new boolean[sorted.length];
			char[] scrambled = Anagrams.copy(sorted);
			Anagrams.scramble(scrambled);
			for (int i = 0; i < sorted.length; i++) {
				for (int j = 0; j < scrambled.length; j++) {
					if (sorted[i] == scrambled[j]) {
						counts[i][j]++;
						assertFalse(seen[i]);
						seen[i] = true;
					}
				}
			}
		}
		for (int i = 0; i < sorted.length; i++) {
			for (int j = 0; j < sorted.length; j++) {
				assertTrue(counts[i][j] > (5000.0 / sorted.length) * 0.5);
				assertTrue(counts[i][j] < (5000.0 / sorted.length) * 1.5);
			}
		}
	}

	@Test
	public void isCorrectAcceptsCorrectAnswer() {
		String[] dictionary = {"act", "banana", "bonobo", "cat"};
		assertTrue(Anagrams.isCorrect("banana", "banana", dictionary));
	}

	@Test
	public void isCorrectRejectsWordNotUsingSameLetters() {
		String[] dictionary = {"act", "banana", "bonobo", "cat"};
		assertFalse(Anagrams.isCorrect("bonobo", "banana", dictionary));
	}
	
	@Test
	public void isCorrectAcceptsAlternateSolution() {
		String[] dictionary = {"act", "banana", "bonobo", "cat"};
		assertTrue(Anagrams.isCorrect("act", "cat", dictionary));		
	}
	
	@Test
	public void isCorrectRejectsWordNotInDictionary() {
		String[] dictionary = {"act", "banana", "bonobo", "cat"};
		assertFalse(Anagrams.isCorrect("cta", "cat", dictionary));				
	}

}
