package Room;

public class Seeds extends Item{

	public Seeds(String n, String d) {
		super(n,d);
	}
	
	@Override
	public void use() {
		if(Game.currentRoom.getName() == "Farm") {
			Game.print("With a little sun, water, and care you plant the carrot seeds and grow a carrot.");
			Item carrot = new Item("carrot", "it's a carrot! wow! you grew that!");
			Game.inventory.add(carrot);
			for(int i = 0; i < Game.inventory.size(); i++) {
				if(Game.inventory.get(i).getName().equals("carrotseeds")) {
					Game.inventory.remove(i);
				}
			}
		}
		else {
			Game.print("You can not plant this here.");
		}
	}
}
