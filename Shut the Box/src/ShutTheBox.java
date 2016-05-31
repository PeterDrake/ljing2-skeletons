public class ShutTheBox {

	public static void main(String[] args) {
		StdOut.println("Shut the Box");
		StdOut.println();
		StdOut.println("This game involves a box with nine numbered levers, all initially open:");
		StdOut.println();
		StdOut.println("123456789");
		StdOut.println();
		StdOut.println("Your goal is to close all of them, leaving the game in this state:");
		StdOut.println();
		StdOut.println("---------");
		StdOut.println();
		StdOut.println("On your turn, you roll two dice.");
		StdOut.println("You may close any combination of open levers adding up to the sum on the dice.");
		StdOut.println();
		StdOut.println();
		boolean[] closed = new boolean[10];
		for (int i = 1; i <= 9; i++) {
			StdOut.print(i);
		}
		StdOut.println();
		int score = 45;
		while (true) {
			int roll = StdRandom.uniform(1, 7);
			if (closed[7] && closed[8] && closed[9]) {
				StdOut.println("7, 8, and 9 are closed, so you only roll one die.");
			} else {
				roll += StdRandom.uniform(1, 7);
			}
			StdOut.println("You rolled " + roll + ".");
			StdOut.print("How many levers will you close? ");
			int count = StdIn.readInt();
			if (count == 0) {
				StdOut.println("Game over. Your final score is " + score + ".");
				return;
			}
			StdOut.println("Enter the numbers of the levers you want to close.");
			int total = 0;
			for (int i = 0; i < count; i++) {
				int n = StdIn.readInt();
				if (closed[n]) {
					StdOut.println("That lever is already closed. You forfeit the game.");
					return;
				}
				closed[n] = true;
				score -= n;
				total += n;
			}
			if (roll != total) {
				StdOut.println("Those numbers don't add up to " + roll + ". You forfeit the game.");
				return;
			}
			for (int i = 1; i <= 9; i++) {
				if (closed[i]) {
					StdOut.print("-");
				} else {
					StdOut.print(i);
				}			
			}
			StdOut.println();
			if (score == 0) {
				StdOut.println("You've shut the box -- you win!");
				return;
			}
		}
	}

}
