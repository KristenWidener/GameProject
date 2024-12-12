package project;

public class Fisherman extends NPC{

	public static int count = 0;
	public static boolean gived = false;
	public Fisherman() {
		super("fisherman", "a tired nice old man who unfortunately smells like fish.");
	}
	
	@Override
	public void talk() {
		if(count == 0) {
			say("Welcome! Welcome! Good morning kiddo! I don't think I have seen you here before, what can I help you with?");
			String[] options = {
					"I am trying to start a farm can you help?",
					"I'm here to buy something."
			};
			getResponse(options);
			count++;
		}
		else if(count == 1) {
			say("I am so behind on my orders, I need to get worms for my custumers but the place I order them from cancelled my order." +
					"\nIf I can just get a few I can fill my orders for now, but I'm not sure where to look, and my knees are not what they used to be.");
			String[] options = {
				"I can get you some worms!",
				"I really need to get seeds."
			};
			getResponse(options);
			count ++;
		}
		else if(count == 2 && gived == false) {
			say("Once you get the worms, give them to me and I will call them market okay?");
		}
		else if(count == 2 && gived == true) {
			say("Thank you! I will give the market a call, if you head over now they should be open.");
			count++;
		}
		else {
			say("Thanks for your help, good luck with the farm!");
		}
	
		
	}
	
	@Override
	public void response(int option) {
		if(count == 0) {
			switch(option) {
				case 1:
					say("Haha, I don't much about farming kiddo, sorry. You could problaly get some seeds at Franks Fesh Market, but their closed today.");
					break;
				case 2:
					say("Unfortunately I don't have any bait in yet, I am actually behind on an order already...");
					break;
			}
		}
		else if(count == 1) {
			switch(option) {
			case 1:
				say("Really? I would really owe you one. I'm good friends with the owner of the market maybe I could get them to open it up for you in exchange.");
				break;
			case 2:
				say("I know that owner of the market, if you can help me by getting some worms, I can probalby get them to open u for you for a bit. ");
				break;
			}
		}
	}
	
	@Override
	public void give() {
		if(count == 2) {
			gived = true;
			Game.currentRoom.getExit('w').getExit('n').getExit('n').setLock(false);
			for (int i = 0; i < Game.inventory.size(); i++) {
				if (Game.inventory.get(i).getName() == "worms") {
					Game.inventory.remove(i);
				}
			}
		}
		else {
			Game.print("Talk to the fisherman more first.");
		}
		
	}
	
}