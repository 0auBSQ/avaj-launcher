public class WeatherProvider {
	private static WeatherProvider weatherProvider = null;
	private static String[] weather = {"SUN", "RAIN", "FOG", "SNOW"};
	
	private WeatherProvider() {

	}
	
	public static WeatherProvider getProvider() {
		if (weatherProvider == null) {
			weatherProvider = new WeatherProvider();
		}
		return weatherProvider;
	}
	
	// Weather depends of long-lat, no rain/fog under 30 height to avoid ballons stupidly crashing
	public String getCurrentWeather(Coordinates coordinates) {
		int formula = ((coordinates.getLongitude() / 50) + (coordinates.getLatitude() / 50)) % 4;
		if (formula < 0)
			formula = -formula;
		if (coordinates.getHeight() < 30 && formula <= 2) {
			formula = 0;
		}
		return WeatherProvider.weather[formula];
	}
}