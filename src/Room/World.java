package Room;

public class World {

	public static Room buildWorld() {
		
		//Locations
		Room house = new Room("You are in a old, one roomed, house. There is a door in the south of the room.", "House");
		Room farm = new Room("You are on a Farm covered in debris and dead plants. South is the House."+
								" A sign labeled Town points east.", "Farm");
		Room town = new Room ("You are in a small town with a bunch of leaves on all over. West leads back to the Farm. To the north a building with produce outside has a sign label \"Frank's Fesh Food\". "
				+ "East is a large building labled \"Townhall\". There is another sign pointing south labled Beach.", "Town");
		Room market = new Room("You are in a local grocery market. South is the exit door back into Town.", "Market");
		Room beach = new Room("You are on a tiny, cold beach. To the east you see a Baitshop built on an dock."+
				" A sign to the north is labeled Town.", "Beach");
		Room baitshop = new Room("You are in a Baitshop on the dock. To the west is the door you came in through.", "BaitShop");
		Room townhall = new Room("You are in the Townhall. A door on the west wall leads back to Town," +
						"there are stairs that lead upstairs.", "Townhall");
		Room office = new Room("You in a messy Office. There are stairs that lead downstairs.", "Office");
		
		//House directions & Items
		house.addExit(farm, 's');
		
		Item pen = new Item("pen", "a fancy pen.");
		house.setItem("pen", pen);
		
		Rake rake = new Rake("rake", "it's a rake!");//bait(item) used at baitshop teleports & unlocks market
		house.setItem("rake", rake);
		
		//Farm directions & items
		farm.addExit(house, 'n');
		farm.addExit(town, 'e');
		
		Item gloves = new Item("gloves", "a pair of new gardening gloves.");
		Item hat = new Item("hat", "a hat.");
		Item water = new Item("water", "a water bottle.");
		farm.setItem("gloves", gloves);
		farm.setItem("water", water);
		farm.setItem("hat", hat);
		
		
		//Town directions & items
		town.addExit(farm, 'w');
		town.addExit(market, 'n');
		town.addExit(townhall,'e');
		town.addExit(beach, 's');
		
		Item key = new Item("key", "an old rusty key.");
		town.setItem("key", key);
		
		//Market directions & items, unlocks when worms is used in baitshop
		market.addExit(town, 's');
		market.setLock(true);
		Seeds carrotseeds = new Seeds("carrotseeds", "it's carrot seeds.");
		market.setItem("carrotseeds", carrotseeds);
		
		//Townhall directions
		townhall.addExit(town, 'w');
		townhall.addExit(office, 'u');
		
		//Office directions & locks
		office.addExit(townhall, 'd');
		office.setLock(true);
		
		//Beach directions
		beach.addExit(town, 'n');
		beach.addExit(baitshop, 'e');
		
		//Baitshop directions
		baitshop.addExit(beach, 'w');
		
		//Room Key (will be removed from game)
		Key test = new Key("test", "test key for the locked office");
		market.setItem("test", test);
		
		//Inheritance Items (will be removed from game)
		Safe safe = new Safe("safe","a small wooden box");
		town.setItem("safe", safe);
		Combination shovel = new Combination("shovel", "a new shiny shovel with numbers on it.");
		beach.setItem("shovel", shovel);
		
		return house;
	}
	
	
}
