
public class Exit {
	// een uitgang weet naar welke kamer hij leidt, de richting voor de speler en of de deur dicht zit.
	private Room nextRoom;
	private String direction;
	private boolean locked;
	//constructor
	public Exit(Room nextRoom, String direction,boolean locked) {
		this.nextRoom = nextRoom;
		this.direction = direction;
		this.locked = locked;
	}
	//getters
	public Room getNextRoom() {
		return nextRoom;
	}
	
	public String getDirection() {
		return direction;
	}
	
	public boolean getLocked() {
		return locked;
	}
	//setter
	public void setLocked(boolean locked) {
		this.locked = locked;
	}
	//print informatie uit!
	public void printDirection() {
		System.out.print(direction);
	}
}
