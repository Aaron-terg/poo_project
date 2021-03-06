package controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import javafx.scene.canvas.GraphicsContext;
import models.planet.Planet;
import models.shape.Renderable;

/**
 * 
 * <b>Universe</b>
 *  <p> Universe implements {@link Renderable}, {@link Serializable}</p>
 * <p>
 * 		Universe class is the manager. its tasks are:
 * 		<ul>
 * 			<li>Create a set of planet</li>
 * 			<li>Check if the planets respect a minimum distance</li>
 * 			<li>give information about them</li>
 * 			<li>render them<li>
 * 
 * @see Planet
 * @see UniverseSetting
 * @author meryl
 * @version src_basic
 *
 */
public class Universe implements Renderable, Serializable{
	
	/**
	 * <b>The set of planets.</b>
	 * 
	 * @see Planet
	 * @see Universe#getPlanets()
	 * @see Universe#render(GraphicsContext)
	 */
	private ArrayList<Planet> planets;
	
	/**
	 * <b>Universe constructor</b>
	 * <p> create a set of planet randomly generated.
	 * 
	 * @param nbPlanets the number of planets to create bond within 0 and 20
	 */
	public Universe(int nbPlanets) {
		
		
		nbPlanets = Math.min(UniverseSetting.PLANET_MAX, nbPlanets);
		nbPlanets = Math.max(2, nbPlanets);
		int minDist = 100;
		// Making of a set of planet
		planets = new ArrayList<Planet>();
		boolean superImposedTest = true;
		while(planets.size() < nbPlanets) {
			
			//check if the planet is not over an other planet
			Planet planet = Planet.planetGenerator();
			int j = planets.size() - 1;
			while(j >= 0) {
				Planet prevPlanet = planets.get(j);
				superImposedTest &= !planet.superimposed(prevPlanet, minDist);
				j--;
			}
			if(superImposedTest)
				planets.add(planet);
				
			superImposedTest = true;
		}	
	}

	/**
	 * <b>Get the planet set</b>
	 * 
	 * @return an ArrayList of planet
	 */
	public ArrayList<Planet> getPlanets() {
		return planets;
	}

	/**
	 * render each planet of the planet set
	 * 
	 */
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
