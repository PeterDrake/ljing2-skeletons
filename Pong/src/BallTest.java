import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BallTest {

	public static final double DELTA = 0.001;

	private Ball ball;
	
	private Paddle[] paddles;
	
	@Before
	public void setUp() throws Exception {
		ball = new Ball(0.3, 0.4, 0.1, -0.2);
		paddles = new Paddle[] {new Paddle(0.7), new Paddle(0.4)};
	}

	@Test
	public void storesInitialX() {
		assertEquals(0.3, ball.getX(), DELTA);
	}

	@Test
	public void storesInitialY() {
		assertEquals(0.4, ball.getY(), DELTA);
	}

	@Test
	public void moves() {
		ball.move(paddles);
		assertEquals(0.4, ball.getX(), DELTA);
		assertEquals(0.2, ball.getY(), DELTA);		
	}

	@Test
	public void bouncesOffFloor() {
		ball = new Ball(0.5, 0.2, 0.1, -0.3);
		ball.move(paddles);
		assertEquals(0.6, ball.getX(), DELTA);
		assertEquals(0.1, ball.getY(), DELTA);		
		ball.move(paddles);
		assertEquals(0.7, ball.getX(), DELTA);
		assertEquals(0.4, ball.getY(), DELTA);				
	}

	@Test
	public void bouncesOffCeiling() {
		ball = new Ball(0.5, 1.0 - 0.2, 0.1, 0.3);
		ball.move(paddles);
		assertEquals(0.6, ball.getX(), DELTA);
		assertEquals(1.0 - 0.1, ball.getY(), DELTA);		
		ball.move(paddles);
		assertEquals(0.7, ball.getX(), DELTA);
		assertEquals(1.0 - 0.4, ball.getY(), DELTA);		
	}

	@Test
	public void movesOffRightSide() {
		ball = new Ball(0.9, 0.9, 0.2, 0.0);
		ball.move(paddles);
		assertEquals(1.1, ball.getX(), DELTA);
	}
	
	@Test
	public void movesOffLeftSide() {
		ball = new Ball(0.1, 0.9, -0.2, 0.0);
		ball.move(paddles);
		assertEquals(-0.1, ball.getX(), DELTA);
	}
	
	@Test
	public void bouncesOffRightPaddle() {
		ball = new Ball(0.9, 0.95, 0.2, -0.1);
		paddles[1] = new Paddle(1.0 - (2.0 * Paddle.HALF_HEIGHT));
		ball.move(paddles);
		assertEquals(0.9, ball.getX(), DELTA);
		assertEquals(0.85, ball.getY(), DELTA);
		ball.move(paddles);
		assertEquals(0.7, ball.getX(), DELTA);
		assertEquals(0.75, ball.getY(), DELTA);
	}

	@Test
	public void bouncesOffLeftPaddle() {
		ball = new Ball(0.1, 0.7 - (0.9 * Paddle.HALF_HEIGHT), -0.3, 0.0);
		ball.move(paddles);
		assertEquals(0.2, ball.getX(), DELTA);
		ball.move(paddles);
		assertEquals(0.5, ball.getX(), DELTA);
	}

}
