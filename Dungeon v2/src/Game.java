import java.util.ArrayList;
public class Game {
	// Variablen voor een game. 
	private boolean wantsToQuit = false;
	private boolean keepPlaying = true;
	private boolean hasPickedMap = false;
	private boolean hasWon = false;
	
	private ArrayList<Room> map = new ArrayList<Room>();
	private ArrayList<Item> item = new ArrayList<Item>();
	// nodig om een map te kunnen kiezen okal is er maar 1.
	private Map gameMap;
	//Voor user input.
	private ConsoleIO io;
	
	private Player gamePlayer;
	//Dit is om te kijken welke map er wordt gebruikt.
	private String mapName;
	//start de game met een map kiezen.
	public Game() {
		io = new ConsoleIO();
		gameMap = new Map(map,item);
		while(hasPickedMap == false) {
			System.out.println("Pick a map out of the following and Type it out:");
			System.out.println("WitchTower");
			System.out.println("WitchDen");
			String input = io.readInput();
			if(input.equals("WitchTower")) {
				mapName = "WitchTower";
				gameMap.witchTower();
				hasPickedMap = true;
				gamePlayer = new Player("Thomas", gameMap.getMap().get(0), 10, 5);
				callCommand();
				gamePlayer.getCurRoom().getRoomDescription();
				System.out.println("Your brother and his family are captured by a witch clan.");
				System.out.println("You have to save them by sneeking into the clan's tower!");
				run();
			}
			if(input.equals("WitchDen")) {
				mapName = "WitchDen";
				gameMap.witchDen();
				hasPickedMap = true;
				gamePlayer = new Player("Gemalkin",gameMap.getMap().get(0),60,5);
				callCommand();
				System.out.println("You have been training to become the new assasin for the Malkinclan.");
				System.out.println("Before you can become that you need to defeat the current clan-assasin!");
				run();
			}
			else {
				//blijf vragen tot er een bestaande map is gekozen.
				System.out.println("That is not an option. Try again");
				continue;
			}
		}
	}
	
	/**
	* run the game
	*/
	private void run() {
		//stop niet met spelen behalve als de speler heeft opgegeven of gewonnen.
		while (keepPlaying) {
			//einde kan per map verschillen.
			if(mapName.equals("WitchTower")) {
				if(gamePlayer.getCurRoom() == gameMap.getMap().get(20)){
					if(gamePlayer.getBackPack().contains(gameMap.getItem().get(6))) {
						hasWon = true;
					}
				}
			}
			// speler geeft het op.
			if(wantsToQuit) {
				System.out.println("You didn't save your brother and his family. Shame on you!");
				keepPlaying = false;
				return;
			}
			//speler heeft gewonnen.
			if(hasWon) {
				if(mapName.equals("WithTower")) {
					System.out.println("You saved your brother and his family form the witches!");
					System.out.println("Thanks for playing!");
					System.out.println("credits:");
					System.out.println("Code: Michelle van Setten");
					System.out.println("Story based on:'The Spooks Battle' written by Joshep Delaney");
					keepPlaying = false;
					return;
				}
			}
			// speler speelt verder.
			else {
				System.out.println("What should I do?:");
				String input = io.readInput();
				//Ik maak het allemaal kleine letters zodat het niet fout kan gaan.
				handleCommand(input.toLowerCase());
			}
		}
	}
	private void handleCommand(String userInput) {
		String command[] = userInput.split(" ");
		if(command.length == 2) {
			//We gaan verplaatsen 
			if(command[0].equals("go")) {
				if(checkRoomTravel(command[1]) == true) {
					gamePlayer.travelToRoom(gamePlayer.getCurRoom().getExit(command[1]));
					gamePlayer.getCurRoom().getRoomDescription();
					return;
				}
				else {
					System.out.println("Can't go that way");
				}
			} 
			else {
				switch(command[0]) {
				// We gaan iets gebruiken
				case "use": handleUseCommand(command[1]);
					break;
				//We gaan iets achterlaten
				case "drop": handleDropCommand(command[1]);
					break;
				//We gaan iets oppakken
				case "get": handleGetCommand(command[1]);
					break;
				case "attack": handleAttackCommand(command[1]);
					break;
				case "loot": handleLootCommand(command[1]);
					break;
				// er is een command van twee woorden gegeven die niet klopt.
				default:
					System.out.println("That is not a option");
					break;
				}
			return;
			}
		}
		if(command.length == 1) {
			switch(command[0]) {
			//speler wil stoppen.
			case "quit":
				wantsToQuit = true;
				break;
			//speler heeft hulp nodig
			case "help":
				callCommand();
				break;
			//speler kijkt door de kamer.
			case "look":
				gamePlayer.getCurRoom().getLookText();
				break;
			//speler wil weten welke spullen hij heeft
			case "pack":
				gamePlayer.lookAtInventory();
				break;
			//er is een command gegeven maar die bestaat niet.
			default:
				System.out.println("That is not a option");
				break;
			}
		return;
		}
		else {
			System.out.println("That is not a option");
			return;
		}
	}
	
	private void handleUseCommand(String itemName) {
		// speler kijkt eerst in zijn rugzak daarna aan zijn keyring.
		// In beide commando's is verwerkt dat als hij hem niet kan vinden in de kamer kijkt.
		// als hij helemaal niks kan vinden print de game de else uit.
		if(gamePlayer.checkBackPack(itemName.toLowerCase()) != null) {
			gamePlayer.useItem(gamePlayer.checkBackPack(itemName.toLowerCase()));
			return;
		}
		if(gamePlayer.checkKeyRing(itemName.toLowerCase()) != null) {
			gamePlayer.useKeyItem(gamePlayer.checkKeyRing(itemName.toLowerCase()));
			return;
		}
		else {
			System.out.println("I don't have that");
		}
	}
	
	private void handleDropCommand(String itemName) {
		// Je kan wel items droppen maar geen keys.
		// Het kan zijn dat je items niet nodig hebt maar keys wil je bewaren.
		gamePlayer.dropItemInRoom(itemName.toLowerCase());
	}
	
	private void handleGetCommand(String itemName) {
		//hetzelfde als bij use.
		// eerst kijkt de speler in zijn rugzak daarna aan zijn keyring.
		if(!gamePlayer.getCurRoom().getItemList().isEmpty()) {
			gamePlayer.getItemInRoom(itemName.toLowerCase());
			return;
		}
		if(!gamePlayer.getCurRoom().getKeyList().isEmpty()) {
			gamePlayer.getKeyInRoom(itemName.toLowerCase());
			return;
		}
		else {
			System.out.println("Can't get that");
		}
	}

	private boolean checkRoomTravel(String command) {
		// bij deze boolean wordt gekeken of de speler naar een kamer kan verplaatsten.
		// als hij dat kan geeft hij true door zodat hij zich bij travel kan verplaatsten.
		// anders geeft hij false.
		if(gamePlayer.getCurRoom().checkExits(command) == true) {
			return true;
		} else {
			return false;
		}
	}
	
	private void handleAttackCommand(String enemyName) {
		//val enemy aan met deze naam.
			gamePlayer.AttackEnemy(enemyName);
	}
	
	private void handleLootCommand(String enemyName) {
		gamePlayer.lootDeadEnemy(enemyName);
	}
	
	private void callCommand() {
		System.out.println("Commands are:");
		System.out.println("go [Direction], use [Item], drop [Item], get [Item], look, pack, quit, help");
	}
	
}
