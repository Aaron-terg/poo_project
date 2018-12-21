package Controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import Models.planet.Planet;
import Models.shape.Renderable;
import javafx.scene.canvas.GraphicsContext;

/**
 * 
 * <b>Universe</b>
 * <p>
 * 		Universe class is the manager. its tasks are:
 * 		<ul>
 * 			<li>Create a set of planet</li>
 * 			<li>Check if the planets respect a minimum distance</li>
 * 			<li>give information about them</li>
 * 			<li>render them<li>
 * 
 * @see Planet
 * 
 * @author meryl
 * @version 1.5
 *
 */
public class Universe implements Renderable, Serializable{
	
	/**
	 * The set of planet.
	 * 
	 * @see Planet
	 * @see Universe#getPlanets()
	 * @see Universe#render(GraphicsContext)
	 */
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
	
	@Override
	public String toString() {
		String result = "";
		for (Iterator<Planet> iterator = planets.iterator(); iterator.hasNext();) {
			Planet planet = iterator.next();
			if(planet.isOwn())
				result +=  planet + "\n";
		}
		return result;
	}
	
}
