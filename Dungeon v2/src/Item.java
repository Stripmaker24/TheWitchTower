public class Item {
	// een Item heeft een naam, beschrijving, gebruik tekst en een tekst als hij getriggerd wordt.
	private String name;
	private String description;
	private String useText;
	private String triggerText;
	// een Item heeft een kamer waarin hij wordt getriggerd.
	private Room triggerRoom;
	// een Item weet in welk Item hij moet veranderen of als hij gekoppeld wordt moet terug geven.
	private Item changeWhenTriggerd;
	private Item whenCombined;
	
	// constructor
	public Item(String name, String description, String useText, String triggerText, Room triggerRoom, Item changeWhenTriggerd) {
		this.name = name;
		this.description = description;
		this.useText = useText;
		this.triggerText = triggerText;
		this.triggerRoom = triggerRoom;
		this.changeWhenTriggerd = changeWhenTriggerd;
	}
	
	//getters
	public String getName() {
		return name;
	}
	
	public String getDescription(){
		return description;
	}
	
	public String getUseText() {
		return useText;
	}
	
	public String getTriggerText() {
		return triggerText;
	}
	
	public Item getWhenCombined() {
		return whenCombined;
	}
	
	public Room getTriggerRoom() {
		return triggerRoom;
	}
	
	public Item getTriggerd() {
		return changeWhenTriggerd;
	}
	//setter
	public void setWhenCombined(Item whenCombined) {
		this.whenCombined = whenCombined;
	}
}
