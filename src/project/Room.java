package project;

import java.io.Serializable;
import java.util.HashMap;

public class Room implements Serializable {

	//Variables
	private Room north;
	private Room east;
	private Room south;
	private Room west;
	private Room up;
	private Room down;
	private boolean lock;
	private String name;
	
	private HashMap<String, Item> item = new HashMap<String, Item>();
	private HashMap<String, NPC> characters = new HashMap<String, NPC>();
 	
	//Sets room description
	public Room(String n) {
		name = n;
		lock = false;
		try{World.currentWorldMap.put(n, this);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	//Returns exit, or null if no a char from the list
	public Room getExit(char x){
		if (x == 'n') {
			return north;	
		}
		if (x == 'e') {
			return east;
		}
		if (x == 's') {
			return south;
		}
		if (x == 'w') {
			return west;
		}
		if (x == 'u') {
			return up;
		}
		if (x == 'd') {
			return down;
		}
		else {
			return null;
		}
	}//end of getExit
	
	public Room addExit(Room room, char x) {
		if(x == 'n') {
			north = room;
		}
		if(x == 'e') {
			east = room;
		}
		if(x == 's') {
			south = room;
		}
		if(x == 'w') {
			west = room;
		}
		if(x == 'u') {
			up = room;
		}
		if(x == 'd') {
			down = room;
		}
			return null;
	}//end of addExit
	
	//Set/Get item
	public void setItem(String s, Item i) {
		item.put(s, i);
	}
	public Item getItem(String s) {
		return item.get(s);
	}
	public void removeItem(String s) {
		item.remove(s);
	}
	
	//Get/Set NPC
	public void setNPC(String s, NPC n) {
		characters.put(s, n);
	}
	public NPC getNPC(String s) {
		return characters.get(s);
	}
	public void removeNPC(String s) {
		characters.remove(s);
	}
	
	//Lock Methods
	public void setLock(boolean n) {
		lock = n;
	}
	
	public boolean getLock() {
		return lock;
	}

	//Name Methods
	public String getName() {
		return name;
	}

	public void setName(String n) {
		name = n;
	}
	
	public String getDescription() {
		return Game.roomdescriptions.get(name);
	}

}
