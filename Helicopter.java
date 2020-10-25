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
			Simulator.add("Helicopter#" + this.name + "(" + this.id + "): Sun\n");
			this.coordinates = new Coordinates(o.getLongitude() + 10, o.getLatitude(), Aircraft.heightClamp(o.getHeight() + 2));
		}
		else if (weather.equals("RAIN")) {
			Simulator.add("Helicopter#" + this.name + "(" + this.id + "): Rain\n");
			this.coordinates = new Coordinates(o.getLongitude() + 5, o.getLatitude(), o.getHeight());
		}
		else if (weather.equals("FOG")) {
			Simulator.add("Helicopter#" + this.name + "(" + this.id + "): Fog\n");
			this.coordinates = new Coordinates(o.getLongitude() + 1, o.getLatitude(), o.getHeight());
		}
		else if (weather.equals("SNOW")) {
			Simulator.add("Helicopter#" + this.name + "(" + this.id + "): Snow\n");
			this.coordinates = new Coordinates(o.getLongitude(), o.getLatitude(), Aircraft.heightClamp(o.getHeight() - 12));
		}
		
		if (this.coordinates.getHeight() <= 0) {
			Simulator.add("Helicopter#" + this.name + "(" + this.id + ") reported crashed, 12 deaths and 8 injuries. " + o + "\n");
			this.weatherTower.unregister((Flyable)this);
		}
	}
	
	@Override
	public void registerTower(WeatherTower weatherTower) {
		Simulator.add("Tower says: Helicopter#" + this.name + "(" + this.id + ") registered to weather tower.\n");
		this.weatherTower = weatherTower;
	}
	
	@Override
	public void notifyUnregister() {
		Simulator.add("Tower says: Helicopter#" + this.name + "(" + this.id + ") unregistered fron weather tower\n");
	}
}