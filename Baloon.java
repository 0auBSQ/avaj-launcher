public class Baloon extends Aircraft implements Flyable {
	private WeatherTower weatherTower;
	
	Baloon(String name, Coordinates coordinates) {
		super(name, coordinates);
	}
	
	@Override
	public void updateConditions() {
		Coordinates o = this.coordinates;
		String weather = this.weatherTower.getWeather(o);
		
		if (weather.equals("SUN")) {
			System.out.println("Baloon#" + this.name + "(" + this.id + "): Sun");
			this.coordinates = new Coordinates(o.getLongitude() + 2, o.getLatitude(), Aircraft.heightClamp(o.getHeight() + 4));
		}
		else if (weather.equals("RAIN")) {
			System.out.println("Baloon#" + this.name + "(" + this.id + "): Rain");
			this.coordinates = new Coordinates(o.getLongitude(), o.getLatitude(), Aircraft.heightClamp(o.getHeight() - 5));
		}
		else if (weather.equals("FOG")) {
			System.out.println("Baloon#" + this.name + "(" + this.id + "): Fog");
			this.coordinates = new Coordinates(o.getLongitude(), o.getLatitude(), Aircraft.heightClamp(o.getHeight() - 3));
		}
		else if (weather.equals("SNOW")) {
			System.out.println("Baloon#" + this.name + "(" + this.id + "): Snow");
			this.coordinates = new Coordinates(o.getLongitude(), o.getLatitude(), Aircraft.heightClamp(o.getHeight() - 15));
		}
		
		if (this.coordinates.getHeight() <= 0) {
			System.out.println("Baloon#" + this.name + "(" + this.id + ") exploded, oof.");
			this.weatherTower.unregister((Flyable)this);
		}
	}
	
	@Override
	public void registerTower(WeatherTower weatherTower) {
		System.out.println("Tower says: Baloon#" + this.name + "(" + this.id + ") registered to weather tower.");
		this.weatherTower = weatherTower;
	}
}