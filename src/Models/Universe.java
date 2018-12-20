package Models;

import java.util.ArrayList;
import java.util.Iterator;

import Models.planet.Planet;
import Models.shape.Renderable;
import javafx.scene.canvas.GraphicsContext;

public class Universe implements Renderable{
	
	private ArrayList<Planet> planets;
	
	public Universe(int nbPlanets) {
		// Making of a set of planet
		
		planets = new ArrayList<Planet>();
		boolean superImposedTest = true;
		while(planets.size() < nbPlanets) {
			
			Planet planet = new Planet();
			int j = planets.size() - 1;
			while(j >= 0) {
				Planet prevPlanet = planets.get(j);
				superImposedTest &= planet.superimposed(prevPlanet);
				j--;
			}
			if(superImposedTest) {
				planets.add(planet);
				
			}
			superImposedTest = true;
		}	
	}

	public ArrayList<Planet> getPlanets() {
		return planets;
	}

	@Override
	public void render(GraphicsContext gc) {
		for (Iterator<Planet> iterator = planets.iterator(); iterator.hasNext();) {
			Planet planet = iterator.next();
			planet.render(gc);
		}

		
	}
	
}
