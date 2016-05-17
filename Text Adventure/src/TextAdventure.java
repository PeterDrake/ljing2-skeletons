/** Text adventure game. */
public class TextAdventure {

	public static void main(String[] args) {
		new TextAdventure().run();
	}

	/** Damage done by the best weapon the player has picked up. */
	private int bestWeaponDamage;

	/** The room where the player currently is. */
	private Room currentRoom;

	/** Total value of all treasures taken by the player. */
	private int score;

	public TextAdventure() {
		// Create rooms
		Room entrance = new Room("entrance",
				"a cramped natural passage, filled with dripping stalactites");
		Room hall = new Room("hall", "a vast hall with a vaulted stone ceiling");
		Room armory = new Room("armory", "an abandoned armory");
		Room lair = new Room("lair",
				"a large cavern, steaming with volcanic heat");
		// Create connections
		entrance.addNeighbor("north", hall);
		hall.addNeighbor("south", entrance);
		hall.addNeighbor("west", armory);
		armory.addNeighbor("east", hall);
		hall.addNeighbor("east", lair);
		lair.addNeighbor("west", hall);
		// Create and install monsters
		hall.setMonster(new Monster("wolf", 2, "a ferocious, snarling wolf"));
		lair.setMonster(new Monster("dragon", 4, "a fire-breathing dragon"));
		// Create and install treasures
		hall.setTreasure(new Treasure("diamond", 10,
				"a huge, glittering diamond"));
		lair.setTreasure(new Treasure("chalice", 90,
				"a golden chalice encrusted with all manner of gemstones"));
		// Create and install weapons
		armory.setWeapon(new Weapon("axe", 5, "a mighty battle axe"));
		// The player starts in the entrance, armed with a sword
		currentRoom = entrance;
		bestWeaponDamage = 3;
	}

	/**
	 * Attacks the specified monster and prints the result. If the monster is
	 * present, this either kills it (if the player's weapon is good enough) or
	 * results in the player's death (and the end of the game).
	 */
	public void attack(String name) {
		Monster monster = currentRoom.getMonster();
		if (monster != null && monster.getName().equals(name)) {
			if (bestWeaponDamage > monster.getArmor()) {
				StdOut.println("You strike it dead!");
				currentRoom.setMonster(null);
			} else {
				StdOut.println("Your blow bounces off harmlessly.");
				StdOut.println("The " + monster.getName() + " eats your head!");
				StdOut.println();
				StdOut.println("GAME OVER");
				System.exit(0);
			}
		} else {
			StdOut.println("There is no " + name + " here.");
		}
	}

	/** Moves in the specified direction, if possible. */
	public void go(String direction) {
		Room destination = currentRoom.getNeighbor(direction);
		if (destination == null) {
			StdOut.println("You can't go that way from here.");
		} else {
			currentRoom = destination;
		}
	}

	/** Handles a command read from standard input. */
	public void handleCommand(String line) {
		String[] words = line.split(" ");
		if (currentRoom.getMonster() != null
				&& !(words[0].equals("attack") || words[0].equals("look"))) {
			StdOut.println("You can't do that with unfriendlies about.");
			listCommands();
		} else if (words[0].equals("attack")) {
			attack(words[1]);
		} else if (words[0].equals("go")) {
			go(words[1]);
		} else if (words[0].equals("look")) {
			look();
		} else if (words[0].equals("take")) {
			take(words[1]);
		} else {
			StdOut.println("I don't understand that.");
			listCommands();
		}
	}

	/** Prints examples of possible commands as a hint to the player. */
	public void listCommands() {
		StdOut.println("Examples of commands:");
		StdOut.println("  attack wolf");
		StdOut.println("  go north");
		StdOut.println("  look");
		StdOut.println("  take diamond");
	}

	/** Prints a description of the current room and its contents. */
	public void look() {
		StdOut.println("You are in " + currentRoom.getDescription() + ".");
		if (currentRoom.getMonster() != null) {
			StdOut.println("There is "
					+ currentRoom.getMonster().getDescription() + " here.");
		}
		if (currentRoom.getWeapon() != null) {
			StdOut.println("There is "
					+ currentRoom.getWeapon().getDescription() + " here.");
		}
		if (currentRoom.getTreasure() != null) {
			StdOut.println("There is "
					+ currentRoom.getTreasure().getDescription() + " here.");
		}
		StdOut.println("Exits: " + currentRoom.listExits());
	}

	/** Runs the game. */
	public void run() {
		listCommands();
		StdOut.println();
		while (true) {
			StdOut.println("You are in the " + currentRoom.getName() + ".");
			StdOut.print("> ");
			handleCommand(StdIn.readLine());
			StdOut.println();
		}
	}

	/** Attempts to pick up the specified treasure or weapon. */
	public void take(String name) {
		Treasure treasure = currentRoom.getTreasure();
		Weapon weapon = currentRoom.getWeapon();
		if (treasure != null && treasure.getName().equals(name)) {
			currentRoom.setTreasure(null);
			score += treasure.getValue();
			StdOut.println("Your score is now " + score + " out of 100.");
			if (score == 100) {
				StdOut.println();
				StdOut.println("YOU WIN!");
				System.exit(0);
			}
		} else if (weapon != null && weapon.getName().equals(name)) {
			currentRoom.setWeapon(null);
			if (weapon.getDamage() > bestWeaponDamage) {
				bestWeaponDamage = weapon.getDamage();
				StdOut.println("You'll be a more effective fighter with this!");
			}
		} else {
			StdOut.println("There is no " + name + " here.");
		}
	}

}
