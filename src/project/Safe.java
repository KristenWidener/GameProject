package project;

public class Safe extends Item{

	public Safe(String n, String d) {
		super(n, d);
	}
	
	@Override
	public void open() {
		Boolean open = false;
	
		for(int i = 0; i < Game.inventory.size(); i++) {
			if(Game.inventory.get(i).getName().equals("shovel")) {
				Game.print("Using the combination, you open the safe and find a diamond inside! Naturally, you pocket the diamond.");
				Item diamond = new Item("diamond", "a very shiny blue diamond");
				Game.inventory.add(diamond);
				open = true;
			}
		} 
		if(open == false) {
			Game.print("The safe is locked and you don't have the combination.");
		}
	}
}
