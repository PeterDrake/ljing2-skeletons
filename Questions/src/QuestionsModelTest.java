import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class QuestionsModelTest {

	private QuestionsModel model;

	@Before
	public void setUp() throws Exception {
		model = new QuestionsModel();
	}

	@Test
	public void hasDefaultInitialTree() {
		assertEquals(
				"Does it have a motor?\n  Does it store information?\n    a hard drive\n    a car\n  a giraffe\n",
				model.toString());
	}

	@Test
	public void getsRoot() {
		assertEquals("Does it have a motor?", model.getRoot().getKey());
	}

	@Test
	public void getsCurrentNode() {
		assertSame(model.getRoot(), model.getCurrentNode());
	}

	@Test
	public void descends() {
		model.descend(true);
		assertEquals("Does it store information?", model.getCurrentNode()
				.getKey());
		model.descend(false);
		assertEquals("a car", model.getCurrentNode().getKey());
	}

	@Test
	public void detectsLeaf() {
		model.descend(true);
		assertFalse(model.atLeaf());
		model.descend(false);
		assertTrue(model.atLeaf());
	}

	@Test
	public void resets() {
		model.descend(true);
		model.descend(false);
		model.reset();
		assertSame(model.getRoot(), model.getCurrentNode());
	}

	@Test
	public void getsQuestion() {
		model.descend(true);
		assertEquals("Does it store information? (y/n)", model.getQuestion());
		model.descend(false);
		assertEquals("Is it a car? (y/n)", model.getQuestion());
	}

	@Test
	public void getsLearningQuestion() {
		model.descend(false);
		assertEquals(
				"What question would distinguish a fox (y) from a giraffe (n)?",
				model.getLearningQuestion("a fox"));
	}

	@Test
	public void learns() {
		model.descend(false);
		model.learn("a lion", "Is it carnivorous?");
		assertEquals(
				"Does it have a motor?\n  Does it store information?\n    a hard drive\n    a car\n  Is it carnivorous?\n    a lion\n    a giraffe\n",
				model.toString());
	}

}
