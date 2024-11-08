package Room;

public class Key extends Item {

	public Key(String n, String d) {
		super(n,d);
	}
	
	@Override
	public void use() {
		
		if(Game.currentRoom.getName() == "Townhall") {
			Game.currentRoom.getExit('u').setLock(false);
			Game.print("The Office door unlocked!");
		}
	}
}
