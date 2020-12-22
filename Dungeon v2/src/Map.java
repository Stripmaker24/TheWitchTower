 import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
public class Map {
	// een map heeft een lijst met kamers en een lijst met alle items.
	private ArrayList<Room> map;
	private ArrayList<Item> item;
	private Random randomGenerator = new Random();
	private int roomSelector;
	//constructor
	public Map(ArrayList<Room> map, ArrayList<Item> item) {
		this.map = map;
		this.item = item;
	}
	//getters
	public ArrayList<Room> getMap() {
		return map;
	}
	
	public ArrayList<Item> getItem() {
		return item;
	}
	//setter
	public void setItem(ArrayList<Item> item) {
		this.item = item;
	}
	
	// de map WitchTower, trouwens ook de enige map tot nu toe.
	// misschien later leuk om wat meer te maken.
	public void witchTower() {
		Room graveyard = new Room("Graveyard","A small graveyard that is overrun with plants. In front of me is a sepulchre with a tree growing out of the roof.","This is the graveyard");
		Room sepulchre = new Room("Sepulchre","This was a grave of some rich folk, but I am not sure. Mab told me there is a undergound tunnel under the tree.","This is the sepulchre");
		Room tunnel = new Room("Tunnel","A dark tunnel. I hear a sound of falling earth behind me. that will not be my way out. I see a light in front of me and I can hear the sound of water.","This is the dark tunnel");
		Room lake = new Room("Lake","I found a lake. The water is clear and there is a walkway next to it.","This is the lake");
		Room cells = new Room("CellBlok","This is the cellblock! I can hear the faint cry of the dead that haunts here. I can also see a light in one of the cells. I came close and could hear Ellie talk to Jack.","This is the cellblock.");
		Room familycell = new Room("Cell","When I opend the door, Ellie cried out in fear and little Mary started crying. They tought I was one of the witches. When I looked for Jack I saw him lying on the ground wounded. After I talking with Ellie we came to the conclusion we can't move Jack like this.","This is the cell");
		Room torture = new Room("Torture chamber","A torture chamber. there are all sorts of things in here. Perhapes they also left the cellkeys in here.","This is the torture room");
		Room dungeonStairs = new Room("DungeonStairs","I found a round chamber. I can see chains hanging from the ceiling, I don't want to know what they are used for.","These are the dungeonstairs");
		Room kitchen = new Room("Kitchen","The dungeonstairs lead me to the kitchen. There is a smell of decay comming from a door to my right. I will leave the heavy trapdoor open so I can get back to the dungeon.","This is the kitchen");
		Room pantry = new Room("pantry","I found the smell, it is rotten food. There is also normal food and alchol in this small pantry.","This is the pantry");
		Room firstFloorStairs = new Room ("Firstfloorstairs","A room with as set of stairs to the first floor. there is a faint light comming from a room to the right.","These are groundfloorstairs");
		Room commonroom = new Room("Commonroom","This must be the commonroom. there are some chairs and tables in the room and a big fireplace.","This is the commonroom");
		Room gate = new Room("Gate","It is a gate. This might be our only escape.","This is the gate");
		Room secondFloorStairs = new Room("Secondfloorstairs","Another set of stairs that go up. There is a door to my left and right.","These are the firstfloorstairs");
		Room restroom = new Room("Restroom","It smells in here because it is the restroom of the witches. They are not very clean are they?","This is the restroom");
		Room sleepingQuarters = new Room("Sleeping Quarters","Beds! I could take a nap but I don't have time for that. It also smells like old people in here.","This is the sleepingquarter");
		Room thirdFloorStairs = new Room("Thirdfloorstairs","Yet another set of stairs. same layout as on the first floor. A door to the left and a door to the right.","These are the secondfloorstairs");
		Room study = new Room("Study","13 desks. This must be a room that only the witch coven uses. Propably some sort of study","This is the study");
		Room library = new Room("Library","This is a small library. Most of the books are filled with spells. There is a book on the table next to the door.","this is the library");
		Room battlements = new Room("Battlements","Finaly at the end of all these stairs I found the roof. The battlements are in bad shape some of the stones are broken.","this is the roof");
		Room endroom = new Room("EndRoom","Just outside the tower. Don't want to get seen so I shouldn't linger too long.","Just outside the tower");
		// zet alle rooms alvast in een mapje
		map.addAll(Arrays.asList(graveyard,sepulchre,tunnel,lake,cells,familycell,torture,dungeonStairs,kitchen,pantry,firstFloorStairs,commonroom,gate,secondFloorStairs,restroom,sleepingQuarters,thirdFloorStairs,study,library,battlements,endroom));
		// nu de size van de map is bepaald zorg er voor dat roomSelector een random room PER GAME pakt.
		roomSelector = randomGenerator.nextInt(map.size());
		// geef alle kamers hun uitgangen maar.
		graveyard.addExits(new Exit(sepulchre,"forward",false));
		sepulchre.addExits(new Exit(graveyard,"back",false));
		sepulchre.addExits(new Exit(tunnel,"down",false));
		tunnel.addExits(new Exit(lake,"forward",false));
		lake.addExits(new Exit(tunnel,"back",false));
		lake.addExits(new Exit(dungeonStairs,"forward",false));
		lake.addExits(new Exit(map.get(roomSelector),"down",true));
		dungeonStairs.addExits(new Exit(torture,"left",false));
		dungeonStairs.addExits(new Exit(cells,"forward",false));
		dungeonStairs.addExits(new Exit(kitchen,"up",false));
		dungeonStairs.addExits(new Exit(lake,"back",false));
		cells.addExits(new Exit(dungeonStairs,"back",false));
		cells.addExits(new Exit(familycell,"forward",true));
		familycell.addExits(new Exit(cells,"back",false));
		torture.addExits(new Exit(dungeonStairs,"back",false));
		kitchen.addExits(new Exit(dungeonStairs,"down",false));
		kitchen.addExits(new Exit(pantry,"right",false));
		kitchen.addExits(new Exit(firstFloorStairs,"forward",false));
		pantry.addExits(new Exit(kitchen,"back",false));
		firstFloorStairs.addExits(new Exit(kitchen,"back",false));
		firstFloorStairs.addExits(new Exit(commonroom,"right",false));
		firstFloorStairs.addExits(new Exit(secondFloorStairs,"up",false));
		commonroom.addExits(new Exit(gate,"forward",false));
		commonroom.addExits(new Exit(firstFloorStairs,"back",false));
		gate.addExits(new Exit(commonroom,"back",false));
		gate.addExits(new Exit(endroom,"forward", true));
		endroom.addExits(new Exit(gate,"back",false));
		secondFloorStairs.addExits(new Exit(firstFloorStairs,"down",false));
		secondFloorStairs.addExits(new Exit(thirdFloorStairs,"up",false));
		secondFloorStairs.addExits(new Exit(restroom,"left",false));
		secondFloorStairs.addExits(new Exit(sleepingQuarters,"right",false));
		restroom.addExits(new Exit(secondFloorStairs,"back",false));
		sleepingQuarters.addExits(new Exit(secondFloorStairs,"back",false));
		thirdFloorStairs.addExits(new Exit(secondFloorStairs,"down",false));
		thirdFloorStairs.addExits(new Exit(battlements,"up",false));
		thirdFloorStairs.addExits(new Exit(study,"left",false));
		thirdFloorStairs.addExits(new Exit(library,"right",false));
		study.addExits(new Exit(thirdFloorStairs,"back",false));
		library.addExits(new Exit(thirdFloorStairs,"back",false));
		battlements.addExits(new Exit(thirdFloorStairs,"down",false));
		//maak alle items in deze map.
		KeyItem gateKey = new KeyItem("Big_Key","It is a big key for a big lock","I need the right lock for this key", gate.getExitsOfRoom().get(1));
		KeyItem cellKey = new KeyItem("Small_Key","It is a small key for a small lock","I need the right lock for this key", cells.getExitsOfRoom().get(1));
		KeyItem lakeKey = new KeyItem("Strange_Hook","It is small but feels very heavy","I need th right lock for this key",lake.getExitsOfRoom().get(2));
		Item strip_Of_Cloth = new Item("Strip_of_cloth","It is a strip of cloth.","It still smells like old people.","I gave the strip to Ellie",familycell,null);
		Item stone = new Item("Sharp_Stone","A sharp stone, I could cut things with this.","I can cut the air?","I took one of the bedsheets and cut off a strip.",sleepingQuarters,strip_Of_Cloth);
		Item alcholBukkit = new Item("Alchol_Bukkit","A bukkit full of alchol.","I don't drink.","I gave the bukkit to Ellie",familycell,null);
		Item bukkit = new Item("bukkit","An empty bukkit.","I just cleaned it and I still don't have to go!","I poured the alchol in the bukkit",pantry,alcholBukkit);
		Item dirtyBukkit = new Item("Dirty_Bukkit","This bukkit was used as a toilet.","I don't have to go.","I washed the bukkit. It is cleaner then before.",lake,bukkit);
		Item book = new Item("FirstAid_Book","This Book contains free knowledge","Can't read here it is too dark.","There is pharagraf about moving someone with big wounds.'before moving make sure that the wound is clean and there is a bandage around it!'",library,null);
		Item family = new Item("Jack's family","Jack, Ellie and litte Mary","I apologise for what happend to them","I lead them out of the tower",gate,null);
		// maak enemies aan
		Enemy wight = new  Enemy("Wight",10,1);
		// zet items in een lijst.
		item.addAll(Arrays.asList(strip_Of_Cloth, stone, alcholBukkit,bukkit,dirtyBukkit,book,family));
		// zet de juiste items in de juiste kamer.
		torture.addKeyItem(cellKey);
		study.addKeyItem(gateKey);
		battlements.addItem(stone);
		restroom.addItem(dirtyBukkit);
		library.addItem(book);
		// zet de enemies in een kamer.
		lake.addEnemyInRoom(wight);
		// geef de enemy loot
		wight.getKeys().add(lakeKey);
		//maak van deze kamer een koppelkamer en geef de items door die hier gekoppeld moeten worden.
		familycell.setIsCombineChamber(true);
		familycell.addCombineItem(alcholBukkit);
		familycell.addCombineItem(strip_Of_Cloth);
		// als de items gekoppeld worden veranderen ze in hetzelfde.
		// zou ik dit maar voor 1 doen en de speler geeft ze in de verkeer de volgorde gaat het mis.
		alcholBukkit.setWhenCombined(family);
		strip_Of_Cloth.setWhenCombined(family);
	}
	
	public void witchDen() {
		Room denEntrance = new Room("Witch Den Entrance","1","1");
		Room denRoomA = new Room("Forest partA","2","2");
		Room denRoomB = new Room("Forest partB","3","3");
		Room denRoomC = new Room("Forest partC","4","4");
		Room denRoomD = new Room("Forest partD","5","5");
		Room denRoomE = new Room("Forest partE","6","6");
		Room denRoomF = new Room("Forest partF","7","7");
		Room denRoomG = new Room("Forest partG","8","8");
		Room denRoomH = new Room("Forest partH","9","9");
		Room oakTree = new Room("Oak tree","10","10");
		
		map.addAll(Arrays.asList(denEntrance,denRoomA,denRoomB,denRoomC,denRoomE,denRoomF,denRoomG,denRoomH,oakTree));
		
		denEntrance.addExits(new Exit(denRoomA,"forward",false));
		denRoomA.addExits(new Exit(denRoomB,"right",false));
		denRoomA.addExits(new Exit(oakTree,"forward",true));
		denRoomB.addExits(new Exit(denRoomC,"forward",false));
		denRoomC.addExits(new Exit(denRoomD,"forward",false));
		denRoomD.addExits(new Exit(denRoomE,"left",false));
		denRoomE.addExits(new Exit(denRoomF,"left",false));
		denRoomF.addExits(new Exit(denRoomG,"back",false));
		denRoomG.addExits(new Exit(denRoomH,"back",false));
		denRoomH.addExits(new Exit(denRoomA,"right",false));
		
		KeyItem log = new KeyItem("Log","A log","this is not where I need it",denRoomA.getExitsOfRoom().get(1));
		
		denRoomG.addKeyItem(log);
	}
}
