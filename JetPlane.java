public class JetPlane extends Aircraft implements Flyable {
	private WeatherTower weatherTower;
	
	JetPlane(String name, Coordinates coordinates) {
		super(name, coordinates);
	}
	
	@Override
	public void updateConditions() {
		Coordinates o = this.coordinates;
		String weather = this.weatherTower.getWeather(o);
		
		if (weather.equals("SUN")) {
			System.out.println("JetPlane#" + this.name + "(" + this.id + "): Sun");
			this.coordinates = new Coordinates(o.getLongitude(), o.getLatitude() + 10, Aircraft.heightClamp(o.getHeight() + 2));
		}
		else if (weather.equals("RAIN")) {
			System.out.println("JetPlane#" + this.name + "(" + this.id + "): Rain");
			this.coordinates = new Coordinates(o.getLongitude(), o.getLatitude() + 5, o.getHeight());
		}
		else if (weather.equals("FOG")) {
			System.out.println("JetPlane#" + this.name + "(" + this.id + "): Fog");
			this.coordinates = new Coordinates(o.getLongitude(), o.getLatitude() + 1, o.getHeight());
		}
		else if (weather.equals("SNOW")) {
			System.out.println("JetPlane#" + this.name + "(" + this.id + "): Snow");
			this.coordinates = new Coordinates(o.getLongitude(), o.getLatitude(), Aircraft.heightClamp(o.getHeight() - 7));
		}
		
		if (this.coordinates.getHeight() <= 0) {
			System.out.println("JetPlane#" + this.name + "(" + this.id + ") reported crashed, 2 deaths.");
			this.weatherTower.unregister((Flyable)this);
		}
	}
	
	@Override
	public void registerTower(WeatherTower weatherTower) {
		System.out.println("Tower says: JetPlane#" + this.name + "(" + this.id + ") registered to weather tower.");
		this.weatherTower = weatherTower;
	}
}