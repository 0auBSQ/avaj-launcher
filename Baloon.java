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
			Simulator.add("Baloon#" + this.name + "(" + this.id + "): Sun\n");
			this.coordinates = new Coordinates(o.getLongitude() + 2, o.getLatitude(), Aircraft.heightClamp(o.getHeight() + 4));
		}
		else if (weather.equals("RAIN")) {
			Simulator.add("Baloon#" + this.name + "(" + this.id + "): Rain\n");
			this.coordinates = new Coordinates(o.getLongitude(), o.getLatitude(), Aircraft.heightClamp(o.getHeight() - 5));
		}
		else if (weather.equals("FOG")) {
			Simulator.add("Baloon#" + this.name + "(" + this.id + "): Fog\n");
			this.coordinates = new Coordinates(o.getLongitude(), o.getLatitude(), Aircraft.heightClamp(o.getHeight() - 3));
		}
		else if (weather.equals("SNOW")) {
			Simulator.add("Baloon#" + this.name + "(" + this.id + "): Snow\n");
			this.coordinates = new Coordinates(o.getLongitude(), o.getLatitude(), Aircraft.heightClamp(o.getHeight() - 15));
		}
		
		if (this.coordinates.getHeight() <= 0) {
			Simulator.add("Baloon#" + this.name + "(" + this.id + ") exploded, oof. " + o + "\n");
			this.weatherTower.unregister((Flyable)this);
		}
	}
	
	@Override
	public void registerTower(WeatherTower weatherTower) {
		Simulator.add("Tower says: Baloon#" + this.name + "(" + this.id + ") registered to weather tower.\n");
		this.weatherTower = weatherTower;
	}
	
	@Override
	public void notifyUnregister() {
		Simulator.add("Tower says: Baloon#" + this.name + "(" + this.id + ") unregistered fron weather tower\n");
	}
}