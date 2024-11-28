package project;

public class Sally extends NPC {

	public static int count = 0;
	public static boolean paid = false;
	
	
	public Sally() {
		super("sally", "the woman who runs Franks Fresh Market");
	}
	
	@Override
	public void talk() {
		if(count == 0) {
			say("Hi! I'm Sally I own the market, inherited from my dad, the one and only frank. How can I help you?");
			String[] options = {
				"I want to buy some seeds",
				"Can I have some seeds?"
			};
			getResponse(options);
			count++;
		}
		else if (count == 1) {
			say("Oh! I almost forget I can't sell you the seeds till you have a planter's permit." +
				"You can get on from the major's office, I all keep the store open while you pick on up.");
			count++;
		}
		else if(count == 2 && paid == true) {
			for(int i = 0; i < Game.inventory.size(); i++) {
				if(Game.inventory.get(i).getName().equals("permit")) {
					say("Alright, your all set! Happy planting.");
					Seeds carrotseeds = new Seeds("carrotseeds", "it's carrot seeds.");
					Game.inventory.add(carrotseeds);
				}
				else {
					say("Let me know once you gotten the permit.");
				}
			}
			count++;
		}
		else if(count == 2 && paid == false) {
			for(int i = 0; i < Game.inventory.size(); i++) {
				if(Game.inventory.get(i).getName().equals("permit")) {
					say("I see you got your permit! I just need the payment and the seeds are all yours!");
				}
				else {
					say("Let me know once you gotten the permit.");
				}
			}
			count++;
		}
		else if(count == 3 && Game.permit == false && paid == true) {
			say("Let me know once you gotten the permit.");
		}
		else if(count == 3 && Game.permit == true && paid == false) {
			say("Okay, I just need your payment and we are all set!");
		}
		else if(count == 3 && Game.permit == true && paid == true) {
			say("Alright, your all set! Happy planting.");
			Seeds carrotseeds = new Seeds("carrotseeds", "it's carrot seeds.");
			Game.inventory.add(carrotseeds);
			count++;
		}
		else { 
			say("Happy planting!");
		}
	}
	
	@Override
	public void  response(int option) {
		if(count == 0) {
			switch(option) {
			case 1: 
				say("Of course, I only have carrot seeds right now, and it's $3 per bag.");
				break;
			case 2:
				say("For $3 you can. We only have carrot seeds instock right now. We are usually are closed today for restocking.");
				break;
			}
		}
	}
	
	@Override
	public void give() {
		if(count == 1) {
			paid = true;
			for (int i = 0; i < Game.inventory.size(); i++) {
				if (Game.inventory.get(i).getName() == "$3") {
					Game.inventory.remove(i);
				}
			}
		}
		else if(count == 0) {
			Game.print("Talk to Sally first");
		}
		else {
			Game.print("You have no money.");
		}
	}
}
