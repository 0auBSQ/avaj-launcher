import java.util.*;

public abstract class Tower {
	private List<Flyable> observers = new ArrayList<>();
	
	public void register(Flyable flyable) {
		this.observers.add(flyable);
		flyable.registerTower((WeatherTower)this);
	}
	
	public void unregister(Flyable flyable) {
		this.observers.remove(flyable);
	}
	
	protected void conditionsChanged() {
		for (int i = 0; i < observers.size(); i++){
			observers.get(i).updateConditions();
		}
	}
}