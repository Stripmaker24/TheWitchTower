import java.util.ArrayList;

public class Enemy {
	private String name;
	private int health;
	private int attack;
	private ArrayList<Item> basicloot = new ArrayList <Item>();
	private ArrayList<KeyItem> keys = new ArrayList <KeyItem>();
	
	public Enemy(String name, int health, int attack) {
		this.name = name;
		this.health = health;
		this.attack = attack;
	}
	// getters
	public String getName() {
		return name;
	}

	public int getHealth() {
		return health;
	}

	public int getAttack() {
		return attack;
	}

	public ArrayList<Item> getBasicloot() {
		return basicloot;
	}

	public ArrayList<KeyItem> getKeys() {
		return keys;
	}
	//setters
	public void setBasicloot(ArrayList<Item> basicloot) {
		this.basicloot = basicloot;
	}
	
	public void setKeys(ArrayList<KeyItem> keys) {
		this.keys = keys;
	}
	
	public void takeDamage(int damage) {
		health = health - damage;
	}
	
	public void showLoot() {
		if(!basicloot.isEmpty()) {
			for(int x = 0; x < basicloot.size(); x++) {
				System.out.println(basicloot.get(x).getName() + ": " + basicloot.get(x).getDescription());
			}
		}
		if(!keys.isEmpty()) {
			for(int x = 0; x < keys.size(); x++) {
				System.out.println(keys.get(x).getName() + ": " + keys.get(x).getDescription());
			}
		}
	}
	
}
