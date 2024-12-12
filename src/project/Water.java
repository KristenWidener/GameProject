package project;

public class Water extends Item{

	public Water(String n, String d) {
		super(n,d);
	}
	
	@Override
	public void use() {
		if(Game.currentRoom.getName().equals("farm")) {
			if(Game.planted == true) {
			Game.print("With a little sun, water, and care the carrot seeds grow into a perfect carrot.");
			Game.print("Congrats! You win and are a real farmer!");
			Item carrot = new Item("carrot", "it's a carrot! wow! you grew that!");
			Game.inventory.add(carrot);
			}
			else {
				Game.print("There is nothing planted here to water");
			}
		}
		else {
			Game.print("This is not your farm, you have no right to pour water on things here, what is wrong with you?");
		}
	}
}
