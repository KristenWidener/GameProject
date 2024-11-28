package project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Game {

	public static void main(String[] args0) {
		runGame();
	
	}//end of main

	public static Scanner input = new Scanner(System.in);
	
	public static Room currentRoom = World.buildWorld();
	public static ArrayList<Item> inventory = new ArrayList<Item>();
	
	//Rooms HashMap
	public static HashMap<String, String> roomdescriptions = new HashMap<String, String>();//stores room descriptions, NOT ROOMS
//	public static HashMap<String, Room> currentWorldMap = new HashMap<String, Room>();//stores the Room objects
	
	public static boolean permit = false;
	
	//Returns item from inventory, needs item
	public static Item getItem(String i) {
		int in = inventory.indexOf(i);
		return inventory.get(in);
	}
	
	public static void runGame() {
		
		loadRoomDescriptions();
		
		//Scanner input = new Scanner(System.in);
		String command;//player's command
		
		do {
			System.out.println(currentRoom.getDescription());
			//System.out.println(currentRoom.getNPC(World.puppy).getDescription());
			System.out.print("Where do you want to go? ");
			
			command = input.nextLine();
			String[] words = command.split(" ");
			
			switch(words[0]) {
			case "e":
			case "w":
			case "n":
			case "s":
			case "u":
			case "d":
				 if(currentRoom.getExit(words[0].charAt(0)) == null) {
						System.out.println("You can not go this way.");
					}
				else if(currentRoom.getExit(words[0].charAt(0)).getLock() == true) {
					System.out.println("This way is locked, come back with a key");
				}
				else {
				currentRoom = currentRoom.getExit(words[0].charAt(0));//Makes command into a char, so we can call getExit
				}
				break;
			case "take":
				System.out.println("You are trying to take " + words[1] + ".");
				if(currentRoom.getItem(words[1]) != null) {
					inventory.add(currentRoom.getItem(words[1]));
					currentRoom.removeItem(words[1]);
				}
				else { 
					System.out.println("There is nothing to pick up here.");
				}
				break;
			case "i":
				if(!inventory.isEmpty()) {
					for(Item i : inventory) {
						System.out.println(i);
					}
				}
				else { 
					System.out.println("Your inventory is currently empty.");
				}
				break;
			case "look":
				//if an item is in a room or inventory prints it description, else print 
				//System.out.println(words[1]);
				if(currentRoom.getItem(words[1]) == null && inventory.isEmpty()){
					System.out.println("You can not look at this.");
				}
				else if(currentRoom.getItem(words[1]) != null) {
					System.out.println(currentRoom.getItem(words[1]).getDescription());
				}
				else {
				for(int i = 0; i < inventory.size(); i++) {
					if(inventory.get(i).getName().equals(words[1])) {
					System.out.println(inventory.get(i).getDescription());
					}
				}}
				break;
			case "open":
				if(currentRoom.getItem(words[1]) == null && inventory.isEmpty()){
					System.out.println("You can not open this.");
				}
				else if(currentRoom.getItem(words[1]) != null) {
					currentRoom.getItem(words[1]).open();
				}
				else {
				for(int i = 0; i < inventory.size(); i++) {
					if(inventory.get(i).getName().equals(words[1])) {
						inventory.get(i).open();
					}
				}}
				break;
			case "use":
				if(currentRoom.getItem(words[1]) == null && inventory.isEmpty()){
					System.out.println("You can not use this.");
				}
				else if(currentRoom.getItem(words[1]) != null) {
					currentRoom.getItem(words[1]).use();
				}
				else {
				for(int i = 0; i < inventory.size(); i++) {
					if(inventory.get(i).getName().equals(words[1])) {
					inventory.get(i).use();
					}
				}}
				break;
			case "save":
				saveGame("currentSave");
				break;
			case "load":
				loadGame("currentSave");
				break;
			case "talk":
				if(currentRoom.getNPC(words[1]) != null) {
					currentRoom.getNPC(words[1]).talk();
				}
				else {
					System.out.println("You can not speak to " + words[1]);
				}
				break;
			case "give":
				if(currentRoom.getNPC(words[1]) != null) {
					for(int i = 0; i < inventory.size(); i++) {
						if (inventory.get(i).getName().equals(words[2])) {
							currentRoom.getNPC(words[1]).give();
						}
					}
				}
				else {
					System.out.println("You can not give " + words[2] + " to " + words[1] + ".");
					};
				break;
			case "x":
				System.out.println("Thank you for exploring!");
				break;
			default:
				System.out.println("You can only go n, e, s, w, u, or d.");
			}
			
		}while(!command.equals("x"));//runs till user enters x
		
		input.close();
		
	}//end of runGame
	
	public static void print(Object obj) {
		 System.out.println(obj.toString());
		}
	
	//Load Hashmap with descriptions for rooms
	public static void loadRoomDescriptions() {
	
		ArrayList<String> filelines = new ArrayList<String>();
		
		//Adds ALL file lines to array filelines
		try (Scanner file = new Scanner(new File("Rooms"))){
			while(file.hasNextLine()) {//reads file line by line =	
				filelines.add(file.nextLine());
			}
		}catch(FileNotFoundException e) {
			System.out.println("\"Rooms\" file could not be found/opened");
		}
		
		Iterator<String> it = filelines.iterator();
		ArrayList<String> names = new ArrayList<String>();
		ArrayList<String> descriptions = new ArrayList<String>();
		ArrayList<String> sort = new ArrayList<String>();
		
		//Adds all lines but # to sort array
		while(it.hasNext()) {
			String placeholder = it.next();
			if(!placeholder.equals("#")) {
				sort.add(placeholder);
				it.remove();
			}
			else if(placeholder.equals("#")) {
				it.remove();
				
			}
		}
		
		//Puts names & descriptions in different arrays
		for(int i = 0; i < sort.size(); i++) {
			if(i % 2 == 0) {
				names.add(sort.get(i));
			}
			else {
				descriptions.add(sort.get(i));
			}
		}
		
		//loads the roomdescription hashmap
		for(int p = 0; p < names.size(); p++) {
			 roomdescriptions.put(names.get(p), descriptions.get(p));
		}
		
		//Print test
//		for(String n : roomdescriptions.keySet()) {
//		System.out.println(n + " " + roomdescriptions.get(n).toString());
//		}
	}//end of loadRoomDescriptions
	
	//Saves currentRoom, inventory, and currentWorldMap to a file
	public static void saveGame(String saveFile) {
		File f = new File(saveFile);
		try {
			FileOutputStream fos = new FileOutputStream(f);
			ObjectOutputStream stream = new ObjectOutputStream(fos);
			stream.writeObject(currentRoom);
			stream.writeObject(inventory);
			stream.writeObject(World.currentWorldMap);
			stream.close();
			System.out.println("Game Saved.");
		} catch (FileNotFoundException e) {
			System.out.println("File "+ saveFile +" not found.");
		} catch (IOException ex) {
			System.out.println("Other Failure.");
		}
	}//end of saveGame
	
	public static void loadGame(String savedFile) {
		File f = new File(savedFile);
		try {
			FileInputStream fos = new FileInputStream(f);
			ObjectInputStream stream = new ObjectInputStream(fos);
			currentRoom = (Room) stream.readObject();
			inventory = (ArrayList) stream.readObject();
			World.currentWorldMap = (HashMap) stream.readObject();
			stream.close();
		}catch(FileNotFoundException e) {
			System.out.println("File "+ savedFile +" not found.");
			System.exit(0);
		} catch (IOException ex) {
			System.out.println("Other Failure");
		} catch (ClassNotFoundException ex) {
			System.out.println("Not an object.");
		}
	}//end of loadGame
}
