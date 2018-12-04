package Models;

import java.util.ArrayList;
import java.util.Random;

import Models.planet.Planet;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * <b>Player class represent the player</b>
 * <p>
 *     A player has the following property:
 *     <ul>
 *         <li>A collection of the planet owned</li>
 *         <li>its tag name</li>
 *     </li>
 * </p>
 * 
 * @see Planet
 * @see SpaceShip
 * 
 * @author meryl
 * @version 1.0
 *
 */
public class Player {

	/**
	 * The player tag
	 */
	public String playerTag;
	
	/**
	 * The planet owned
	 * 
	 * @see Planet
	 */
	private ArrayList<Planet> territory;
	
	/**
	 * The default constructor, set the player tag to default.
	 */
	public Player() {
		this.playerTag = "default";
		this.territory= new ArrayList<Planet>();
		
	}
	public ArrayList<Planet> getTerritory(){
		return this.territory;
	}
	
	
	
	/**
	 * Choose the palyer's first planet and fill her with a specified color
	 * @param u,
	 * @param gc
	 */
	public void firstPlanet(Universe u, GraphicsContext gc) {
		Random random = new Random();
		int indexPlanet = random.nextInt(u.getPlanets().size());
		this.territory.add(u.getPlanets().get(indexPlanet));
		u.getPlanets().get(indexPlanet).getPlanetShape().rgb(Color.RED);
		u.getPlanets().get(indexPlanet).getPlanetShape().drawShape(gc);
	}
	
	/**
	 * add a planet in the list of planet owned by the player
	 * fill them with the specified color
	 * @param p
	 * @param gc
	 */
	public void myPlanet(Planet p) {
		this.territory.add(p);
		p.getPlanetShape().rgb(Color.RED);
	}
	
	
}
