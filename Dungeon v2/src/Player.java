import java.util.ArrayList;
public class Player {
	// de speler weet zijn naam, de kamer waar hij in staat en wat hij allemaal heeft.
	private String name;
	private Room curRoom;
	private int health;
	private int attack;
	private ArrayList <Item> backPack = new ArrayList <Item>();
	private ArrayList <KeyItem> keyRing = new ArrayList <KeyItem>(); 
	
	// constructor voor de speler.
	public Player(String name, Room curRoom, int health, int attack) {
		this.name = name;
		this.curRoom = curRoom;
		this.health = health;
		this.attack = attack;
	}
	
	// alle getters
	public String getName() {
		return name;
	}
	
	public Room getCurRoom() {
		return curRoom;
	}
	
	public void travelToRoom(Room nextRoom) {
		curRoom = nextRoom;
	}
	
	public ArrayList<Item> getBackPack() {
		return backPack;
	}

	public void lookAtInventory() {
		// de speler krijgt alle informatie over wat hij heeft.
		// als een ArrayList leeg is geeft hij 'nothing' terug.
		System.out.println("I have in my bag:");
		if(!backPack.isEmpty()) {
			for(int x = 0; x < backPack.size(); x++) {
				System.out.println(backPack.get(x).getName() + ": " + backPack.get(x).getDescription());
			}
		}
		else {
			System.out.println("Nothing");
		}
		
		System.out.println("I have on my keychain: ");
		if(!keyRing.isEmpty()) {
			for(int x = 0; x < keyRing.size(); x++) {
				System.out.println(keyRing.get(x).getName() +": "+ keyRing.get(x).getDescription());
			}
		}
		else {
			System.out.println("Nothing");
		}
	}
	
	public Item checkBackPack(String itemName) {
		// Hier wordt gekeken of een speler een item met de naam 'itemName' in zijn backpack heeft.
		// Als dat zo is geeft hij die door annder gaat hij in de kamer zoeken.
		// Als hij ook daar niks vind geeft hij null terug.
		if(!backPack.isEmpty()){
			for(int x = 0; x < backPack.size(); x++) {
				if(backPack.get(x).getName().toLowerCase().equals(itemName)) {
					return backPack.get(x);
				}
			}
		} 
		else {
			curRoom.getItemInRoom(itemName);
		}
		return null;
	}
	
	public KeyItem checkKeyRing(String keyName) {
		// hetzelfde als bij de rugzak alleen dan met sleutels.
		if(!keyRing.isEmpty()) {
			for(int x = 0; x < keyRing.size(); x++) {
				if(keyRing.get(x).getName().toLowerCase().equals(keyName)) {
					return keyRing.get(x);
				}
			}
		}
		else {
			curRoom.getKeyInRoom(keyName);
		}
		return null;
	}
	
	public void useItem(Item item) {
		// Er wordt gekeken of de speler in de triggerroom van het item staat.
		// Daarna wordt er gekeken wat er moet gebeuren als een item getriggerd is.
		// Als dat niet null is krijgt de speler een ander item terug.
		// Het item is 'getransformeerd' naar een ander item.
		// Als er wel null uit komt wordt er gekeken of het item in een combineer kamer is.
		// Als dat zo is wordt er gekeken of het item in de combineerlijst zit van de kamer.
		// Is dat ook waar wordt er gekeken of alle items van de combineerlijst in de kamer zijn.
		// Is dit weer waar wordt het de items 'gecombineerd' en krijgt de speler een nieuw item.
		// Mocht het zo zijn dat het item er wel hoort maar niet de laatste is om toegevoegd te worden dan wordt het item alleen in de kamer achtergelaten.
		// Als niks waar is wordt de normale text geprint.
		if(curRoom == item.getTriggerRoom()) {
			System.out.println(item.getTriggerText());
			if(item.getTriggerd() != null) {
					backPack.add(item.getTriggerd());
					backPack.remove(item);
				}
			else {
				if(curRoom.getIsCombineChamber() == true) {
					for(int x = 0; x < backPack.size(); x++) {
						if(backPack.get(x) == item && curRoom.getCombineItems().contains(item)) {
							curRoom.addItem(item);
							backPack.remove(item);
							if(curRoom.retrunWhenCombined(item) != null) {
								backPack.add(curRoom.retrunWhenCombined(item));
								System.out.println("I got: "+curRoom.retrunWhenCombined(item).getName());
							}
						}
					}
				}
			}
		}
		else {
			System.out.println(item.getUseText());
		}
	}
	
	public void useKeyItem(KeyItem key) {
		//Er wordt gekeken of de sleutel de kamer hoort.
		//Als dat zo is wordt er gekeken welke deur er bij deze sleutel hoort.
		// Daarna wordt hij geopend.
		// Als hij niet bij deze kamer hoort wordt dat aangegeven.
		if(curRoom.getExitsOfRoom().contains(key.getOpenThisExit())) {
			if(key.getOpenThisExit().getLocked() == true) {
				System.out.println("Opened exit to: "+ key.getOpenThisExit().getNextRoom().getName());
				key.getOpenThisExit().setLocked(false);
				keyRing.remove(key);
			}
		}
		else {
			System.out.println(key.getUseText());
		}
	}
	
	public void dropItemInRoom(String itemName) {
		// als Item in de tas zit wordt hij gedropt.
		if(!backPack.isEmpty()) {
			for(int x = 0; x < backPack.size(); x++) {
				if(backPack.get(x).getName().toLowerCase().equals(itemName)) {
					curRoom.addItem(backPack.get(x));
					backPack.remove(x);
					System.out.println("Dropped: "+ itemName);
				}
			}
		}
		else {
			System.out.println("Don't have that.");
		}
	}
	
	public void getItemInRoom(String itemName) {
		// als er in de kamer een item met deze naam is wordt hij opgepakt.
		if(curRoom.getItemInRoom(itemName) != null) {
			backPack.add(curRoom.getItemInRoom(itemName));
			curRoom.getItemList().remove(curRoom.getItemInRoom(itemName));
			System.out.println("I got: "+ itemName);
		} 
		else {
			System.out.println("Can't get that.");
		}
	}
	
	public void getKeyInRoom(String itemName) {
		// als er in de kamer een sleutel met deze naam is wordt hij opgepakt. 
		if(curRoom.getKeyInRoom(itemName) != null) {
			keyRing.add(curRoom.getKeyInRoom(itemName));
			curRoom.getKeyList().remove(curRoom.getKeyInRoom(itemName));
			System.out.println("I got: "+ itemName);
		}
		else {
			System.out.println("Can't get that.");
		}
	}
	
	public void AttackEnemy(String enemyName) {
		if(!curRoom.getEnemiesInRoom().isEmpty()) {
			for(int x = 0; x < curRoom.getEnemiesInRoom().size(); x++) {
				if(curRoom.getEnemiesInRoom().get(x).getName().toLowerCase().equals(enemyName)) {
					curRoom.getEnemiesInRoom().get(x).takeDamage(attack);
					System.out.println("You did "+ attack +" point(s) damage to "+curRoom.getEnemiesInRoom().get(x).getName());
					if(curRoom.getEnemiesInRoom().get(x).getHealth() == 0) {
						System.out.println("You defeated: "+ curRoom.getEnemiesInRoom().get(x).getName());
						curRoom.addDeadEnemyInRoom(curRoom.getEnemiesInRoom().get(x));
						curRoom.getEnemiesInRoom().remove(x);
					}
					else {
						System.out.println("You Took "+curRoom.getEnemiesInRoom().get(x).getAttack()+" point damage!");
						health = health - curRoom.getEnemiesInRoom().get(x).getAttack();
					}
				}
			}
		}
		else {
			System.out.println("This enemy doesn't exist.");
		}
	}
	
	public void lootDeadEnemy(String enemyName) {
		if(!curRoom.getDeadEnemiesInRoom().isEmpty()) {
			for(int x = 0; x < curRoom.getDeadEnemiesInRoom().size(); x++) {
				if(curRoom.getDeadEnemiesInRoom().get(x).getName().toLowerCase().equals(enemyName)) {
					if(!curRoom.getDeadEnemiesInRoom().get(x).getBasicloot().isEmpty()) {
						backPack.addAll(curRoom.getDeadEnemiesInRoom().get(x).getBasicloot());
					}
					if(!curRoom.getDeadEnemiesInRoom().get(x).getKeys().isEmpty()) {
						keyRing.addAll(curRoom.getDeadEnemiesInRoom().get(x).getKeys());
					}
					System.out.println("You looted the enemey and got: ");
					curRoom.getDeadEnemiesInRoom().get(x).showLoot();
					curRoom.getDeadEnemiesInRoom().get(x).getBasicloot().removeAll(curRoom.getDeadEnemiesInRoom().get(x).getBasicloot());
					curRoom.getDeadEnemiesInRoom().get(x).getKeys().removeAll(curRoom.getDeadEnemiesInRoom().get(x).getKeys());
				}
			}
		}
	}
}
