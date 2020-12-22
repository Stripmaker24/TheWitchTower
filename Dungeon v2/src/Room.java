import java.util.ArrayList;
public class Room {
	// een kamer heeft een naam, een stuk tekst als je er voor het eerst komt en een stuk tekst als je er later nog een keer komt.
	private String name;
	private String firstText;
	private String afterText;
	// een kamer kan voor het eerst bezocht worden en kan een koppelkamer zijn.
	private boolean firstTime = true;
	private boolean isCombineChamber = false;
	// een kamer houdt een lijst bij met zijn uitgangen, items, sleutels en als hij een koppelkamer is zijn koppel-items.
	private ArrayList <Exit> exitsOfRoom = new ArrayList <Exit>();
	private ArrayList <Item> itemsInRoom = new ArrayList <Item>();
	private ArrayList <KeyItem> keysInRoom = new ArrayList <KeyItem>();
	private ArrayList <Item> combineItems = new ArrayList <Item>();
	private ArrayList <Enemy> enemiesInRoom = new ArrayList <Enemy>();
	private ArrayList <Enemy> deadEnemiesInRoom = new ArrayList<Enemy>();
	
	// constructor van een kamer.
	public Room(String name,String firstText, String afterText) {
		this.name = name;
		this.firstText = firstText;
		this.afterText = afterText;
	}
	// alle getters.
	public ArrayList<Item> getCombineItems() {
		return combineItems;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean getIsCombineChamber() {
		return isCombineChamber;
	}
	
	public ArrayList<Item> getItemList() {
		return itemsInRoom;
	}
	
	public ArrayList<KeyItem> getKeyList(){
		return keysInRoom;
	}
	
	public ArrayList<Exit> getExitsOfRoom() {
		return exitsOfRoom;
	}
	
	public ArrayList<Enemy> getEnemiesInRoom(){
		return enemiesInRoom;
	}
	public ArrayList<Enemy> getDeadEnemiesInRoom(){
		return deadEnemiesInRoom;
	}
	
	//alle setters.
	public void setIsCombineChamber(boolean isCombineChamber) {
		this.isCombineChamber = isCombineChamber;
	}
	// alle add functies.
	public void addExits(Exit exit) {
		exitsOfRoom.add(exit);
	}
	
	public void addItem(Item item) {
		itemsInRoom.add(item);
	}
	
	public void addKeyItem(KeyItem key) {
		keysInRoom.add(key);
	}
	
	public void addCombineItem(Item item) {
		combineItems.add(item);
	}
	
	public void addEnemyInRoom(Enemy enemy) {
		enemiesInRoom.add(enemy);
	}
	
	public void addDeadEnemyInRoom(Enemy enemy) {
		deadEnemiesInRoom.add(enemy);
	}
	
	public void getRoomDescription(){
		if(firstTime == true) {
		System.out.println(firstText);
		firstTime = false;
		}
		else {
			System.out.println(afterText);
		}
	}
	
	public void getLookText(){
		// geef alle informatie van een ruimte.
		if(!enemiesInRoom.isEmpty()) {
			if(enemiesInRoom.size() == 1) {
				System.out.println("Enemy spotted!");
				System.out.println("It is: "+ enemiesInRoom.get(0).getName());
			}
			else {
				System.out.println("Enemies spotted!");
				System.out.print("They are: ");
				for(int x = 0; x < enemiesInRoom.size(); x++) {
					System.out.println(enemiesInRoom.get(x).getName());
				}
			}
		}
		else {
			System.out.println("The exits to this room are ");
			for(int x = 0; x < exitsOfRoom.size(); x++) {
				exitsOfRoom.get(x).printDirection();
				if(exitsOfRoom.get(x).getLocked() == true) {
					System.out.println(" this room is locked");
				} 
				else {
				System.out.println("");	
				}
			}
			if(!itemsInRoom.isEmpty()) {
				System.out.println("The items I could use in this room are: ");
				for(int x = 0; x < itemsInRoom.size(); x++) {
					System.out.println(itemsInRoom.get(x).getName());
				}
			}
			if(!keysInRoom.isEmpty()) {
				System.out.println("The keys I could use in this room are: ");
				for(int x = 0; x < keysInRoom.size(); x++) {
					System.out.println(keysInRoom.get(x).getName());
				}
			}
			if(!deadEnemiesInRoom.isEmpty()) {
				System.out.println("The enemies that might have loot are: ");
				for(int x = 0; x < deadEnemiesInRoom.size(); x++) {
					System.out.println(deadEnemiesInRoom.get(x).getName());
				}
			}
		}
	}

	public Item retrunWhenCombined(Item item) {
		// geef het Item terug als alle koppel-Items in de array in de kamer zijn.
			if(itemsInRoom.containsAll(combineItems)) {
				isCombineChamber = false;
				return item.getWhenCombined();
			}
			else {
				return null;
			}
		}

	public Item getItemInRoom(String itemName) {
		// geef een item door als hij in de kamer is.
		if(!itemsInRoom.isEmpty()) {
			for(int x = 0; x < itemsInRoom.size(); x++) {
				if(itemsInRoom.get(x).getName().toLowerCase().equals(itemName)) {
					return itemsInRoom.get(x);
				}
			}
		} 
		return null;
	}
	
	public KeyItem getKeyInRoom(String keyName) {
		//geef een key door als hij is de kamer is.
		if(!keysInRoom.isEmpty()) {
			for(int x = 0; x < keysInRoom.size(); x++) {
				if(keysInRoom.get(x).getName().toLowerCase().equals(keyName)) {
					return keysInRoom.get(x);
				}
			}
		}
		return null;
	}
	
	public Room getExit(String direction) {
		// krijg de kamer als er een uitgang is in die richting.
		for(int x = 0; x < exitsOfRoom.size(); x++) {
			if(exitsOfRoom.get(x).getDirection().equals(direction)) {
				return exitsOfRoom.get(x).getNextRoom();
			}
		}
		return null; 
	}

	public boolean checkExits(String direction) {
		// geef aan of er een kamer is in die richting.
		// als hij er is stuur true
		// als hij er is maar dicht stuur false;
		// als hij er niet is stuur false.
		if(!exitsOfRoom.isEmpty() && enemiesInRoom.isEmpty()) {
			for(int x = 0; x < exitsOfRoom.size(); x++) {
				if(exitsOfRoom.get(x).getDirection().equals(direction)) {
					if(exitsOfRoom.get(x).getLocked() != true) {
						return true;
						} 
					else {
						System.out.println("This door is locked, I need a key");
						return false;
					}
				}
			}
		} 
		else {
			return false;
		}
		return false;
	}
}
