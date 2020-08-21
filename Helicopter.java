public class Helicopter extends Aircraft implements Flyable {
	private WeatherTower weatherTower;
	
	Helicopter(String name, Coordinates coordinates) {
		super(name, coordinates);
	}
	
	@Override
	public void updateConditions() {
		Coordinates o = this.coordinates;
		String weather = this.weatherTower.getWeather(o);
		
		if (weather.equals("SUN")) {
			System.out.println("Helicopter#" + this.name + "(" + this.id + "): Sun");
			this.coordinates = new Coordinates(o.getLongitude() + 10, o.getLatitude(), Aircraft.heightClamp(o.getHeight() + 2));
		}
		else if (weather.equals("RAIN")) {
			System.out.println("Helicopter#" + this.name + "(" + this.id + "): Rain");
			this.coordinates = new Coordinates(o.getLongitude() + 5, o.getLatitude(), o.getHeight());
		}
		else if (weather.equals("FOG")) {
			System.out.println("Helicopter#" + this.name + "(" + this.id + "): Fog");
			this.coordinates = new Coordinates(o.getLongitude() + 1, o.getLatitude(), o.getHeight());
		}
		else if (weather.equals("SNOW")) {
			System.out.println("Helicopter#" + this.name + "(" + this.id + "): Snow");
			this.coordinates = new Coordinates(o.getLongitude(), o.getLatitude(), Aircraft.heightClamp(o.getHeight() - 12));
		}
		
		if (this.coordinates.getHeight() <= 0) {
			System.out.println("Helicopter#" + this.name + "(" + this.id + ") reported crashed, 12 deaths and 8 injuries.");
			this.weatherTower.unregister((Flyable)this);
		}
	}
	
	@Override
	public void registerTower(WeatherTower weatherTower) {
		System.out.println("Tower says: Helicopter#" + this.name + "(" + this.id + ") registered to weather tower.");
		this.weatherTower = weatherTower;
	}
}