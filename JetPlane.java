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
			Simulator.add("JetPlane#" + this.name + "(" + this.id + "): Sun\n");
			this.coordinates = new Coordinates(o.getLongitude(), o.getLatitude() + 10, Aircraft.heightClamp(o.getHeight() + 2));
		}
		else if (weather.equals("RAIN")) {
			Simulator.add("JetPlane#" + this.name + "(" + this.id + "): Rain\n");
			this.coordinates = new Coordinates(o.getLongitude(), o.getLatitude() + 5, o.getHeight());
		}
		else if (weather.equals("FOG")) {
			Simulator.add("JetPlane#" + this.name + "(" + this.id + "): Fog\n");
			this.coordinates = new Coordinates(o.getLongitude(), o.getLatitude() + 1, o.getHeight());
		}
		else if (weather.equals("SNOW")) {
			Simulator.add("JetPlane#" + this.name + "(" + this.id + "): Snow\n");
			this.coordinates = new Coordinates(o.getLongitude(), o.getLatitude(), Aircraft.heightClamp(o.getHeight() - 7));
		}
		
		if (this.coordinates.getHeight() <= 0) {
			Simulator.add("JetPlane#" + this.name + "(" + this.id + ") reported crashed, 2 deaths. " + o + "\n");
			this.weatherTower.unregister((Flyable)this);
		}
	}
	
	@Override
	public void registerTower(WeatherTower weatherTower) {
		Simulator.add("Tower says: JetPlane#" + this.name + "(" + this.id + ") registered to weather tower.\n");
		this.weatherTower = weatherTower;
	}
	
	@Override
	public void notifyUnregister() {
		Simulator.add("Tower says: JetPlane#" + this.name + "(" + this.id + ") unregistered fron weather tower\n");
	}
}