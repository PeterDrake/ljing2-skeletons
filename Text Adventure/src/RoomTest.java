import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RoomTest {

	private Room hall;

	@Before
	public void setUp() throws Exception {
		hall = new Room("hall", "a vast hall with a vaulted stone ceiling");
	}

	@Test
	public void storesName() {
		assertEquals("hall", hall.getName());
	}

	@Test
	public void storesDescription() {
		assertEquals("a vast hall with a vaulted stone ceiling",
				hall.getDescription());
	}

	@Test
	public void addsNeighbor() {
		Room entrance = new Room("entrance",
				"a cramped natural passage, filled with dripping stalactites");
		hall.addNeighbor("south", entrance);
		assertSame(entrance, hall.getNeighbor("south"));
	}

	@Test
	public void listsExits() {
		// We're adding null neighbors because it doesn't matter what they are,
		// just that there are exits in those directions
		hall.addNeighbor("south", null);
		hall.addNeighbor("west", null);
		hall.addNeighbor("east", null);
		assertEquals("[south, west, east]", hall.listExits());
	}

	@Test
	public void treasureIsInitiallyNull() {
		assertNull(hall.getTreasure());		
	}

	@Test
	public void setsTreasure() {
		Treasure diamond = new Treasure("diamond", 10,
				"a huge, glittering diamond");
		hall.setTreasure(diamond);
		assertSame(diamond, hall.getTreasure());
	}

	@Test
	public void monsterIsInitiallyNull() {
		assertNull(hall.getMonster());
	}

	@Test
	public void setsMonster() {
		Monster wolf = new Monster("wolf", 2, "a ferocious, snarling wolf");
		hall.setMonster(wolf);
		assertSame(wolf, hall.getMonster());
	}

	@Test
	public void weaponIsInitiallyNull() {
		assertNull(hall.getWeapon());
	}

	@Test
	public void setsWeapon() {
		Weapon axe = new Weapon("axe", 5, "a mighty battle axe");
		hall.setWeapon(axe);
		assertSame(axe, hall.getWeapon());
	}
	
}
