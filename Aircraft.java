public abstract class Aircraft {
	protected long id;
	protected String name;
	protected Coordinates coordinates;
	private static long idCounter = 0;
	
	protected Aircraft(String name, Coordinates coordinates) {
		this.name = name;
		this.coordinates = coordinates;
		this.id = this.nextId();
	}
	
	public static int heightClamp(int h) {
		if (h < 0)
			h = 0;
		if (h > 100)
			h = 100;
		return (h);
	}
	
	private long nextId() {
		Aircraft.idCounter += 1;
		return (Aircraft.idCounter);
	}
}
