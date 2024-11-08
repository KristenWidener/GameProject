package Room;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {

	public static void main(String[] args0) {
		
		runGame();
	
	}//end of main

	public static Room currentRoom = World.buildWorld();
	public static ArrayList<Item> inventory = new ArrayList<Item>();
	
	//Returns item from inventory, needs item
	public static Item getItem(Item i) {
		int in = inventory.indexOf(i);
		return inventory.get(in);
	}
	
	public static void runGame() {
		Scanner input = new Scanner(System.in);
		
		String command;//player's command
		
		do {
			System.out.println(currentRoom);
			//printed items in description
			//if(currentRoom.item != null) {
			//System.out.println("There is " + currentRoom.item.getDescription());
			//}
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
				if(currentRoom.getExit(command.charAt(0)).getLock() == true) {
					System.out.println("This way is locked, come back with a key");
				}
				else if(currentRoom.getExit(command.charAt(0)) == null) {
					System.out.println("You can not go this way.");
				}
				else {
				currentRoom = currentRoom.getExit(command.charAt(0));//Makes command into a char, so we can call getExit
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
	
	
}
