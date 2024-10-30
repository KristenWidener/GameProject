package Room;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {

	public static void main(String[] args0) {
		
		runGame();
	
	}//end of main

	
	public static ArrayList<Item> inventory = new ArrayList<Item>();
	
	public static void runGame() {
		Room currentRoom = World.buildWorld();
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
				if(currentRoom.getExit(command.charAt(0)) == null) {
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
				if(currentRoom.getItem(words[1]) != null) {
					System.out.println(currentRoom.getItem(words[1]).getDescription());
					
				}
				if (inventory.contains(words[1])){
					int i = inventory.indexOf(words[1]);
					System.out.println(inventory.get(i).getDescription());
				}
				else {
					System.out.println("You can not look at this.");
				}
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
	
	
}
