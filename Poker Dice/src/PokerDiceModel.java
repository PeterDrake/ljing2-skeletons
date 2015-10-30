/** Logical model of the Poker Dice game. */
public class PokerDiceModel {

	/** Index of the current player. */
	private int currentPlayer;

	/** Hands of the players. */
	private Hand[] hands;

	/** Number of rolls made so far by the current player. */
	private int rolls;

	public PokerDiceModel(int numberOfPlayers) {
		hands = new Hand[numberOfPlayers];
		for (int i = 0; i < hands.length; i++) {
			hands[i] = new Hand();
		}
	}

	/** Advances to the next player and resets the number of rolls made. */
	public void advanceCurrentPlayer() {
		currentPlayer++;
		rolls = 0;
	}

	/** Returns true if the game is over. */
	public boolean gameOver() {
		return currentPlayer >= hands.length;
	}

	/** Returns the index of the current player. */
	public int getCurrentPlayer() {
		return currentPlayer;
	}

	/** Returns the ith player's hand. */
	public Hand getHand(int i) {
		return hands[i];
	}

	/** Returns the number of players. */
	public int getNumberOfPlayers() {
		return hands.length;
	}

	/** Returns true if hand i is (or is tied for) best. */
	public boolean isWinner(int i) {
		int best = -1;
		for (Hand h : hands) {
			int score = h.getScore();
			if (score > best) {
				best = score;
			}
		}
		return hands[i].getScore() == best;
	}

	/**
	 * Rolls the current player's unkept dice. If all dice were kept,
	 * immediately turns the number of rolls up to 3, ending the turn.
	 */
	public void roll() {
		hands[currentPlayer].roll();
		if (hands[currentPlayer].allKept()) {
			rolls = 3;
		} else {
			rolls++;
		}
	}

	/**
	 * Returns true if the turn is over, i.e., the current player has made 3
	 * rolls.
	 */
	public boolean turnOver() {
		return rolls >= 3;
	}

}
