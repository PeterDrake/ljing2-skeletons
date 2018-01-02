/** The three-in-a-row game for two human players. */
public class TicTacToe {

	/**
	 * Draws the state of board, including instructions or a game over message.
	 */
	public static void draw(char[][] board, char player) {
		StdDraw.clear();
		StdDraw.line(0.5, -0.5, 0.5, 2.5);
		StdDraw.line(1.5, -0.5, 1.5, 2.5);
		StdDraw.line(-0.5, 0.5, 2.5, 0.5);
		StdDraw.line(-0.5, 1.5, 2.5, 1.5);
		for (int x = 0; x < board.length; x++) {
			for (int y = 0; y < board[x].length; y++) {
				StdDraw.text(x, y, board[x][y] + "");
			}
		}
		if (winner(board) != '-') {
			StdDraw.text(1.0, 0.75, winner(board) + " wins!");
		} else if (isFull(board)) {
			StdDraw.text(1.0, 0.75, "Tie game.");
		} else {
			StdDraw.text(1.0, 0.75, player + " to play. Click in a square. Three in a row wins.");
		}
		StdDraw.show();
	}

	/** Returns true if the game is over. */
	public static boolean gameOver(char[][] board) {
		return (winner(board) != '-') || isFull(board);
	}

	/**
	 * Handles a mouse click, placing player's mark in the square on which the
	 * user clicks.
	 */
	public static void handleMouseClick(char[][] board, char player) {
		while (!StdDraw.isMousePressed()) {
			// Wait for mouse press
		}
		double x = Math.round(StdDraw.mouseX());
		double y = Math.round(StdDraw.mouseY());
		while (StdDraw.isMousePressed()) {
			// Wait for mouse release
		}
		int a = (int) x;
		int b = (int) y;
		if (board[a][b] == ' ') {
			board[a][b] = player;
		}
	}

	/** Returns true if board is full. */
	public static boolean isFull(char[][] board) {
		for (int x = 0; x < board.length; x++) {
			for (int y = 0; y < board[x].length; y++) {
				if (board[x][y] == ' ') {
					return false;
				}
			}
		}
		return true;
	}

	/** Plays the game. */
	public static void main(String[] args) {
		StdDraw.enableDoubleBuffering();
		StdDraw.setScale(-0.5, 2.5);
		char[][] board = new char[3][3];
		for (int x = 0; x < board.length; x++) {
			for (int y = 0; y < board[x].length; y++) {
				board[x][y] = ' ';
			}
		}
		char currentPlayer = 'X';
		draw(board, currentPlayer);
		while (!gameOver(board)) {
			handleMouseClick(board, currentPlayer);
			currentPlayer = opposite(currentPlayer);
			draw(board, currentPlayer);
		}
	}

	/** Returns the opposite player's character. */
	public static char opposite(char player) {
		if (player == 'X') {
			return 'O';
		} else {
			return 'X';
		}
	}

	/**
	 * Returns the character ('X' or 'O') of the winning player, or '-' if there
	 * is no winner.
	 */
	public static char winner(char[][] board) {
		int[][][] lines = { { { 0, 0 }, { 0, 1 }, { 0, 2 } },
							{ { 1, 0 }, { 1, 1 }, { 1, 2 } },
							{ { 2, 0 }, { 2, 1 }, { 2, 2 } },
							{ { 0, 0 }, { 1, 0 }, { 2, 0 } },
							{ { 0, 1 }, { 1, 1 }, { 2, 1 } },
							{ { 0, 2 }, { 1, 2 }, { 2, 2 } },
							{ { 0, 0 }, { 1, 1 }, { 2, 2 } },
							{ { 0, 2 }, { 1, 1 }, { 2, 0 } } };
		for (int i = 0; i < lines.length; i++) {
			int[][] line = lines[i];
			char a = board[line[0][0]][line[0][1]];
			char b = board[line[1][0]][line[1][1]];
			char c = board[line[2][0]][line[2][1]];
			if (a != ' ' && a == b && b == c) {
				return a;
			}
		}
		return '-';
	}

}
