package project;

import java.util.HashMap;

public class World {
	public static HashMap<String, Room> currentWorldMap = new HashMap<String, Room>();//stores the Room objects
	public static Room house = new Room("house");
	public static Room farm = new Room("farm");
	public static Room town = new Room ("town");
	public static Room market = new Room("market");
	public static Room beach = new Room("beach");
	public static Room baitshop = new Room("baitshop");
	public static Room townhall = new Room("townhall");
	public static Room office = new Room("office");
	
	
	public static Room buildWorld() {
		
		//Locations
//		Room house = new Room("house");
//		Room farm = new Room("farm");
//		Room town = new Room ("town");
//		Room market = new Room("market");
//		Room beach = new Room("beach");
//		Room baitshop = new Room("baitshop");
//		Room townhall = new Room("townhall");
//		Room office = new Room("office");
		
		//House directions & Items
		house.addExit(farm, 's');
		
//		Item pen = new Item("pen", "it's a fancy pen.");
//		house.setItem("pen", pen);
		
		Rake rake = new Rake("rake", "it's a rake!");//use in town to get worms
		house.setItem("rake", rake);
		
		Item $3 = new Item("$3", "3 dollars!");//give to sally to get seeds (need permit)
		house.setItem("$3", $3);
		
		Item postit = new Item("postit", "It's to-do list with just one item and uncheck box. It says \"Become a real farmer.\".");
		house.setItem("postit", postit);
		
		Puppy puppy = new Puppy();
		house.setNPC("puppy", puppy);
		
		//Farm directions & items
		farm.addExit(house, 'n');
		farm.addExit(town, 'e');
		
		Water water = new Water("water", "it's a watering can.");
		farm.setItem("water", water);
		
		//Town directions & items
		town.addExit(farm, 'w');
		town.addExit(market, 'n');
		town.addExit(townhall,'e');
		town.addExit(beach, 's');
		
		//Market directions & items
		market.addExit(town, 's');
		market.setLock(true);
		
		Sally sally = new Sally();
		market.setNPC("sally", sally);
		
		Key key = new Key("key", "it's a fancy key");//unlocks office
		market.setItem("key", key);
		
		//Townhall directions
		townhall.addExit(town, 'w');
		townhall.addExit(office, 'u');
		
		//Office directions & locks
		office.addExit(townhall, 'd');
		office.setLock(true);
		
		Mayor mayor = new Mayor();
		office.setNPC("mayor", mayor);//give you permit
		
		//Beach directions & items
		beach.addExit(town, 'n');
		beach.addExit(baitshop, 'e');
		
		Hat hat = new Hat();//add use to wear
		beach.setItem("hat", hat);
		
		//Baitshop directions
		baitshop.addExit(beach, 'w');
		
		Fisherman fisherman = new Fisherman();
		baitshop.setNPC("fisherman", fisherman);//unlocks market
		
		return house;
	}
	
	
}
