/** GUI for the Questions game. */
public class Questions {

	/** Radius of circles drawn for nodes. */
	public static final double CIRCLE_RADIUS = 0.01;

	public static void main(String[] args) {
		new Questions().run();
	}

	/** Game model associated with this GUI. */
	private QuestionsModel model;

	/** Question or instructions for the user. */
	private String label;

	/** The text currently being entered by the user. */
	private String textBeingEntered;

	public Questions() {
		model = new QuestionsModel();
		label = "";
		textBeingEntered = "";
	}

	/** Draws the state of the model. */
	public void draw() {
		StdDraw.clear();
		StdDraw.text(0.5, 0.9, label);
		StdDraw.text(0.5, 0.8, textBeingEntered);
		double yDecrement = 0.5 / (model.getRoot().height() + 1);
		drawSubtree(model.getRoot(), 0.5 - yDecrement / 2, yDecrement, 0.0, 1.0);
		StdDraw.show();
	}

	/**
	 * Draws the subtree rooted at node in a rectangle with horizontal
	 * boundaries left and right and vertical boundaries 0 and y.
	 * 
	 * @param y
	 *            y coordinate at which to draw node.
	 * @param yDecrement
	 *            amount to decrease y coordinate for each level of the tree.
	 * @param left
	 *            left boundary of drawing space for this subtree.
	 * @param right
	 *            right boundary of drawing space for this subtree.
	 */
	public void drawSubtree(Node node, double y, double yDecrement, double left, double right) {
		// TODO You have to write this
	}

	/**
	 * Reads a String from the user, displaying it as they type and allowing
	 * backspaces.
	 */
	public String readString() {
		while (true) {
			draw();
			char pressed = waitForKey();
			if (pressed == '\n') {
				String result = textBeingEntered;
				textBeingEntered = "";
				return result;
			} else if (pressed == '\b') {
				textBeingEntered = textBeingEntered.substring(0, textBeingEntered.length() - 1);
			} else {
				textBeingEntered += pressed;
			}
		}
	}

	/** Plays games until the user chooses to quit. */
	public void run() {
		StdDraw.enableDoubleBuffering();
		while (true) {
			// Descend through the tree to a leaf
			model.reset();
			while (!model.atLeaf()) {
				label = model.getQuestion();
				draw();
				model.descend(waitForKey() == 'y');
			}
			// Now node is a leaf; handle end of game
			label = model.getQuestion();
			draw();
			if (waitForKey() == 'y') {
				label = "I win! Press space to play again.";
			} else {
				label = "I give up! What was it?";
				String correct = readString();
				label = model.getLearningQuestion(correct);
				String question = readString();
				model.learn(correct, question);
				label = "Press space to play again.";
			}
			draw();
			while (waitForKey() != ' ') {
				// Wait for a space
			}
		}
	}

	/** Waits for the user to press a key, then returns that character. */
	public char waitForKey() {
		while (!StdDraw.hasNextKeyTyped()) {
			// Wait for keypress
		}
		return StdDraw.nextKeyTyped();
	}

}
