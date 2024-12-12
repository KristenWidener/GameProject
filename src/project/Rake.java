package project;

public class Rake extends Item{
	
	public static boolean raked = false;
	
	public Rake(String n, String d) {
		super(n,d);
	}
	
	@Override
	public void use() {
		if(Game.currentRoom.getName() == "town") {
			Game.print("You use the rake to clean up the leaves and find worms, ew. Who would want this?");
			Worms worms = new Worms("worms", "a bunch of earth worms");
			Game.inventory.add(worms);
			raked = true;
		}
		else {
			Game.print("There is nothing to rake here.");
		}
	}
}
