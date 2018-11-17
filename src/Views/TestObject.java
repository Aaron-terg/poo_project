package Views;

import Models.Player;
import Models.planet.Owned;
import Models.planet.Planet;

public class TestObject {
	public static void main(String[] args) {
		Planet planet = new Planet();
		
		System.out.println(planet.isOwn());
		planet.setPlanetState(new Owned(new Player()));
		System.out.println(planet.isOwn());
	}
}
