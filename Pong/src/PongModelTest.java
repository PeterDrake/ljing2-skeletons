import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PongModelTest {

	public static final double DELTA = 0.001;

	private PongModel model;

	@Before
	public void setUp() throws Exception {
		model = new PongModel();
	}

	@Test
	public void testConstructor() {
		Ball b = model.getBall();
		assertEquals(0.5, b.getX(), DELTA);
		assertEquals(0.5, b.getY(), DELTA);
		Paddle[] paddles = model.getPaddles();
		assertEquals(0.5, paddles[0].getY(), DELTA);
		assertEquals(0.5, paddles[1].getY(), DELTA);
		int[] scores = model.getScores();
		assertEquals(0, scores[0]);
		assertEquals(0, scores[1]);
	}

	@Test
	public void testAdvance() {
		model.resetBall(Math.PI * 0.1);
		model.advance(new double[] { PongModel.PADDLE_SPEED, -PongModel.PADDLE_SPEED });
		Ball b = model.getBall();
		assertEquals(0.51, b.getX(), DELTA);
		assertEquals(0.504, b.getY(), DELTA);
		Paddle[] paddles = model.getPaddles();
		assertEquals(0.5 + PongModel.PADDLE_SPEED, paddles[0].getY(), DELTA);
		assertEquals(0.5 - PongModel.PADDLE_SPEED, paddles[1].getY(), DELTA);
		int[] scores = model.getScores();
		assertEquals(0, scores[0]);
		assertEquals(0, scores[1]);
	}

	@Test
	public void testScore() {
		for (int i = 0; i < 101; i++) {
			model.advance(new double[] { 0, 0 });
		}
		// By now one player should have scored a point
		int[] scores = model.getScores();
		assertEquals(1, scores[0] + scores[1]);
	}

	@Test
	public void testGameOver() {
		assertFalse(model.gameOver());
		int[] scores = model.getScores();
		// Here the test takes advantage of the fact that, while we can't access
		// the private instance variable scores, we can get the array to which
		// it points and alter that
		scores[0] = 4;
		scores[1] = 3;
		assertFalse(model.gameOver());
		scores[1] = 5;
		assertTrue(model.gameOver());
	}

}
