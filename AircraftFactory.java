public abstract class AircraftFactory {
	public static Flyable newAircraft(String type, String name, int longitude, int latitude, int height) {
		if (height > 100 || height < 0) {
			height = 100;
		}
		
		if (type.equals("Helicopter") || type.equals("2AB8B43468E8B92B0FC5C81E70E35A2D")) {
			return new Helicopter(name, new Coordinates(longitude, latitude, height));
		}
		else if (type.equals("JetPlane") || type.equals("554CD647D6B135F7E36AB1214C5E816A")) {
			return new JetPlane(name, new Coordinates(longitude, latitude, height));
		}
		else if (type.equals("Baloon") || type.equals("994736B4F0AEC72F6E5AE580051D012F")) {
			return new Baloon(name, new Coordinates(longitude, latitude, height));
		}
		else {
			throw new ParsingException("Aircraft type must be [Helicopter|JetPlane|Baloon]");
		}
	}
}