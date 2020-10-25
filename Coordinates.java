public class Coordinates {
	private int longitude;
	private int latitude;
	private int height;
	
	Coordinates(int longitude, int latitude, int height) {
		this.longitude = longitude;
		this.latitude = latitude;
		this.height = height;
		if (this.longitude < 0)
			this.longitude = 0;
		if (this.latitude < 0)
			this.latitude = 0;
		if (this.height < 0)
			this.height = 0;
		if (this.height > 100)
			this.height = 100;
	}
	
	public int getLongitude() {
		return this.longitude;
	}
	
	public int getLatitude() {
		return this.latitude;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	@Override
	public String toString() {
		return ("[x: " + this.longitude + ",y: " + this.latitude + ",z: " + this.height + "]");
	}
}