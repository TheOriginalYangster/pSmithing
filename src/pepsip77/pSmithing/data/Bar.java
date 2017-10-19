package pepsip77.pSmithing.data;

public enum Bar {
	BRONZE(new int[] {436, 438}, 2349, 2414, 0),
	IRON(new int[] {440}, 2351, 3988, 1),
	STEEL(new int[] {453}, 2353, 3996, 2),
	MITHRIL(new int[] {447}, 2359, 4158, 3),
	ADAMANT(new int[] {449}, 2361, 7442, 4),
	RUNE(new int[] {451}, 2363, 7447, 5);
	
	private int[] ores;
	private int barId;
	private int smeltingAction;
	private int id;
	
	Bar(int[] ores, int barId, int smeltingAction, int id){
		this.ores = ores;
		this.barId = barId;
		this.smeltingAction = smeltingAction;
		this.id = id;
	}
	
	public int[] getOreIds() {
		return ores;
	}
	
	public int getBarId() {
		return barId;
	}
	
	public int getSmeltingAction() {
		return smeltingAction;
	}
	
	public int getId() {
		return id;
	}
}
