package project;

public class Worms extends Item{

	public Worms(String n, String d) {
		super(n,d);
	}

//	@Override
//	public void use() {
//		if (Game.currentRoom.getName() == "baitshop") {
//			//Unlocks Market & moves player to market
//			Game.currentRoom.getExit('w').getExit('n').getExit('n').setLock(false);
//			Game.currentRoom = Game.currentRoom.getExit('w').getExit('n').getExit('n');
//			for(int i = 0; i < Game.inventory.size(); i++) {
//				if(Game.inventory.get(i).getName().equals("worms")) {
//					Game.inventory.remove(i);
//				}
//			}
//		}
//		else {
//			Game.print("You can not use this here");
//		}
//	}
}
