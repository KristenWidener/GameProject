package project;

import java.util.HashMap;

public class World {
	public static HashMap<String, Room> currentWorldMap = new HashMap<String, Room>();//stores the Room objects
	
	public static Room buildWorld() {
		
		//Locations
		Room house = new Room("house");
		Room farm = new Room("farm");
		Room town = new Room ("town");
		Room market = new Room("market");
		Room beach = new Room("beach");
		Room baitshop = new Room("baitshop");
		Room townhall = new Room("townhall");
		Room office = new Room("office");
		
		//House directions & Items
		house.addExit(farm, 's');
		
		Item pen = new Item("pen", "a fancy pen.");
		house.setItem("pen", pen);
		
		Rake rake = new Rake("rake", "it's a rake!");//bait(item) used at baitshop teleports & unlocks market
		house.setItem("rake", rake);
		
		Item $3 = new Item("$3", "3 dollars!");
		house.setItem("$3", $3);
		
		Puppy puppy = new Puppy();
		house.setNPC("puppy", puppy);
		
		//Farm directions & items
		farm.addExit(house, 'n');
		farm.addExit(town, 'e');
		
		Item gloves = new Item("gloves", "a pair of new gardening gloves."); 
		Item hat = new Item("hat", "a hat.");//remove
		Item water = new Item("water", "a water bottle.");
		farm.setItem("gloves", gloves);
		farm.setItem("water", water);
		farm.setItem("hat", hat);//remove
		
		
		//Town directions & items
		town.addExit(farm, 'w');
		town.addExit(market, 'n');
		town.addExit(townhall,'e');
		town.addExit(beach, 's');
		
		//Item key = new Item("key", "an old rusty key.");
		//town.setItem("key", key);
		
		//Market directions & items, unlocks when worms is used in baitshop
		market.addExit(town, 's');
		market.setLock(true);
		//Seeds carrotseeds = new Seeds("carrotseeds", "it's carrot seeds.");
		//market.setItem("carrotseeds", carrotseeds);
		
		Sally sally = new Sally();
		market.setNPC("sally", sally);
		
		//Townhall directions
		townhall.addExit(town, 'w');
		townhall.addExit(office, 'u');
		
		//Item permit = new Item("permit", "you can now plant thing on your farm!");
		//office.setItem("permit", permit);
		
		//Office directions & locks
		office.addExit(townhall, 'd');
		office.setLock(true);
		Mayor mayor = new Mayor();
		office.setNPC("mayor", mayor);
		
		//Beach directions
		beach.addExit(town, 'n');
		beach.addExit(baitshop, 'e');
		
		//Baitshop directions
		baitshop.addExit(beach, 'w');
		
		Fisherman fisherman = new Fisherman();
		baitshop.setNPC("fisherman", fisherman);
		
		//Room Key (will be removed from game)
		Key key = new Key("key", "test key for the locked office");
		market.setItem("key", key);
		
		//Inheritance Items (will be removed from game)
		Safe safe = new Safe("safe","a small wooden box");
		town.setItem("safe", safe);
		Combination shovel = new Combination("shovel", "a new shiny shovel with numbers on it.");
		beach.setItem("shovel", shovel);
		
		return house;
	}
	
	
}
