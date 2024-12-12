package project;

public class Hat extends Item {

	public Hat() {
		super("hat", "it's a cool farmer's hat! Finder's Keepers!");
	}
	
	@Override
	public void use() {
		Game.print("You put on a the super cold hat");
		Game.hat = true;
	}
}
