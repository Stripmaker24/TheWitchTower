
public class KeyItem {
	// een sleutel heeft een naam, beschrijving en gebruik tekst.
	private String name;
	private String description;
	private String useText;
	//daarnaast weet hij welke deur hij opent.
	private Exit openThisExit;
	//constructor
	public KeyItem(String name, String description, String useText, Exit openThisExit) {
		this.name = name;
		this.description = description;
		this.useText = useText;
		this.openThisExit = openThisExit;
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
	
	public Exit getOpenThisExit() {
		return openThisExit;
	}
}
