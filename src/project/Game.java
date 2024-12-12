package project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game {

	public static String command;
	public static Window window;
	
	public static void main(String[] args0) {
		window = new Window();
		loadRoomDescriptions();
		Game.print(currentRoom.getDescription());
	}//end of main

	public static Scanner input = new Scanner(System.in);
	
	public static Room currentRoom = World.buildWorld();
	public static ArrayList<Item> inventory = new ArrayList<Item>();
	
	//Rooms HashMap
	public static HashMap<String, String> roomdescriptions = new HashMap<String, String>();//stores room descriptions, NOT ROOMS
	
	public static boolean permit = false;
	public static boolean planted = false;
	public static boolean hat = false;
	
	//Returns item from inventory, needs item
	public static Item getItem(String i) {
		int in = inventory.indexOf(i);
		return inventory.get(in);
	}
	
	public static void resetRoomDescriptions() {
		boolean rake = false;
		boolean $3 = false;
		boolean postit = false;
		boolean water = false;
		boolean key = false;
		boolean hat = false;
		
		//check inventory
		for(int i = 0; i < inventory.size(); i++) {
			if(inventory.get(i).getName().equals("rake")) {
				rake = true;
			}
			if(inventory.get(i).getName().equals("$3")) {
				$3 = true;
			}
			if(inventory.get(i).getName().equals("postit")) {
				postit = true;
			}
			if(inventory.get(i).getName().equals("water")) {
				water = true;
			}
			if(inventory.get(i).getName().equals("key")) {
				key = true;
			}
			if(inventory.get(i).getName().equals("hat")) {
				hat = true;
			}
		}
		
		//House
		if(currentRoom.getName().equals("house")) {
			if(rake == true && postit == false && $3 == false) {
				World.house.setNewDescription("house_2");
			}
			else if($3 == true && postit == false && rake == false) {
				World.house.setNewDescription("house_3");
			}
			else if(postit == false && rake == true && $3 == true) {
				World.house.setNewDescription("house_4");
			}
			else if(rake == true && postit == true && $3 == false) {
				World.house.setNewDescription("house_5");
			}
			else if($3 == true && postit == true && rake == false) {
				World.house.setNewDescription("house_6");
			}
			else if($3 == true && postit == true && rake == true) {
				World.house.setNewDescription("house_7");
			}
			else if(postit == true && rake == false && $3 == false) {
				World.house.setNewDescription("house_8");
			}
		}
		//Farm
		else if(currentRoom.getName().equals("farm")) {
			if(water == true) {
				World.farm.setNewDescription("farm_2");
			}
		}
		//Town
		else if(currentRoom.getName().equals("town")) {
			if(Rake.raked == true && Mayor.moved == false) {
				World.town.setNewDescription("town_2");
			}
		}
		
		//Office
		else if(currentRoom.getName().equals("office")) {
			if(Mayor.moved == true) {
				World.office.setNewDescription("office_2");
				World.town.setNewDescription("town_3");
			}
		}
		
		//Beach
		else if(currentRoom.getName().equals("beach")) {
			if(hat == true) {
				World.beach.setNewDescription("beach_2");
			}
		}
		//Market
		else if(currentRoom.getName().equals("market")) {
			if(key == true) {
				World.market.setNewDescription("market_2");
			}
		}
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
//			 if(rooms.get(p).getName().equals(names.get(p))){
//				 Game.print("match");
//				 rooms.get(p).setDescription(descriptions.get(p));
//			 }
		}
		
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
			Game.print("Game Saved.");
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
	
	//window method
	public static void processCommand(String command) {
		
		String[] words = command.split(" ");

		switch(words[0]) {
		case "e":
		case "w":
		case "n":
		case "s":
		case "u":
		case "d":
			if(currentRoom.getExit(words[0].charAt(0)) == null) {
				 Game.print("You can not go this way.");
				}
			else if(currentRoom.getExit(words[0].charAt(0)).getLock() == true) {
				Game.print("This way is locked, come back with a key");
			}
			else {
			currentRoom = currentRoom.getExit(words[0].charAt(0));//Makes command into a char, so we can call getExit
			window.area.setText("");
			Game.print(currentRoom.getDescription());
			}
			break;
		case "take":
			if(currentRoom.getItem(words[1]) != null) {
				inventory.add(currentRoom.getItem(words[1]));
				currentRoom.removeItem(words[1]);
				Game.print("You take " + words[1] + ".");
			}
			else if(words[1].equals("postit")) {
				Game.print("Don't move your to-do list.");
			}
			else { 
				Game.print("There is nothing to pick up here.");
			}
			break;
		case "i":
			if(!inventory.isEmpty()) {
				Game.print("You are carrying:");
				for(Item i : inventory) {
					Game.print("\t - " + i);
				}
			}
			else { 
				Game.print("Your inventory is currently empty.");
			}
			break;
		case "look":
			//You can look at an item in the room OR inventory
			if(currentRoom.getItem(words[1]) == null && inventory.isEmpty()){
				Game.print("You can not look at this.");
			}
			else if(currentRoom.getItem(words[1]) != null) {
				Game.print(currentRoom.getItem(words[1]).getDescription());
			}
			else {
			for(int i = 0; i < inventory.size(); i++) {
				if(inventory.get(i).getName().equals(words[1])) {
					Game.print(inventory.get(i).getDescription());
				}
			}}
			break;
		case "open":
			if(currentRoom.getItem(words[1]) == null && inventory.isEmpty()){
				Game.print("You can not open this.");
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
				Game.print("You can not use this.");
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
			Game.print(currentRoom.getDescription());
			break;
		case "talk":
			if(currentRoom.getNPC(words[1]) != null) {
				currentRoom.getNPC(words[1]).talk();
			}
			else {
				Game.print("You can not speak to " + words[1]);
			}
			break;
		case "give":
			if(currentRoom.getNPC(words[1]) != null) {
				for(int i = 0; i < inventory.size(); i++) {
					if (inventory.get(i).getName().equals(words[2])) {
						currentRoom.getNPC(words[1]).give();
					}
				}
			Game.print("You give " + words[2] + " to " + words[1]);
			}
			else {
				Game.print("You can not give " + words[2] + " to " + words[1] + ".");
				};
			break;
		case "x":
			Game.print("Thank you for exploring!");
			break;
		case "room":
			Game.print(currentRoom.getDescription());
		//case "guide":
			//print out cases, how to call and what they do
		default:
			Game.print("You can only go n, e, s, w, u, or d.");
		}
		
		resetRoomDescriptions();
	}
	
	public static void print(Object obj) {
		window.area.append(obj.toString()+"\n");
	}
}
