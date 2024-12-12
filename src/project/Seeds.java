package project;

public class Seeds extends Item{

	public Seeds(String n, String d) {
		super(n,d);
	}
	
	@Override
	public void use() {
		if(Game.currentRoom.getName() == "farm") {
			Game.print("With a little water this might grow");
			//Update room description to say a carrot is planted
			for(int i = 0; i < Game.inventory.size(); i++) {
				if(Game.inventory.get(i).getName().equals("carrotseeds")) {
					Game.inventory.remove(i);
				}
			}
			Game.planted = true;
		}
		else {
			Game.print("You can not plant this here.");
		}
	}
}
