package project;

public class Mayor extends NPC{
	
	public static int count = 0;
	public static boolean distressed = false;
	public static boolean moved = false;
	
	public Mayor() {
		super("mayor", "it's the mayor!");
	}
	
	@Override
	public void talk() {
		if(count == 0) {
			say("HELLO! Oh my god! Thank you for opening the door! I was stuck in there forever!"
					+ "\nThank god I gave Sally that emergency key!");
			String[] options = {
				"Yeah not problem",	
				"Can I get a planting permit?"
			};
			getResponse(options);
			count++;
		}
		else if(count == 1 && distressed == false) {
			say("Do you need anything before I head out?");
			String[] options = {
					"Yes, can you help me get a planting permit?"
			};
			getResponse(options);
			count++;
		}
		else if(count == 1 && distressed == true) {
			say("Sorry filling it out real quick..." + "Here you go! I'm gonna get some fresh air, see you around!");
			Item permit = new Item("permit", "you can now plant things on your farm!");
			Game.inventory.add(permit);
			Game.permit = true;
			World.town.setNPC("mayor", World.office.getNPC("mayor"));
			World.office.removeNPC("mayor");
			moved = true;
			count++;
		}
		else {
			say("Hello!");
		}
	}
	
	@Override
	public void response(int option) {
		if(count == 0) {
			switch(option) {
				case 1:
					say("I totally owe you one! I think I am going to get some fresh air unless you need anything.");
					break;
				case 2:
					say("Oh! Umm yes!");
					distressed = true;
					break;
			}
		}
		else if(count == 1 && distressed == false) {
			switch(option) {
			case 1:
				say("Of course! Let me fill it out really quick..." +
					"Here you go! Thanks again!");
				Item permit = new Item("permit", "you can now plant thing on your farm!");
				Game.inventory.add(permit);
				Game.permit = true;
				World.town.setNPC("mayor", World.office.getNPC("mayor"));
				World.office.removeNPC("mayor");
				moved = true;
				break;
			}
		}
	}
}
