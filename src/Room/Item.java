package Room;

public class Item {
	
	public String name;
	public String description;
	
	//Constructor
	public Item(String n, String d) {
		name = n; 
		description = d;
	}
	
	
	//set Methods
	public void setName(String n) {
		name = n;
	}
	
	public void setDescription(String d) {
		description = d;
	}
	
	//get Methods
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	//to String
	public String toString() {
		return name;
	}
}
