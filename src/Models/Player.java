package Models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import Models.planet.Planet;
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
	 * The spaceship fleets of the player
	 */
	private ArrayList<Spacefleet> spacefleets;
	/**
	 * The default constructor, set the player tag to default.
	 */
	public Player() {
		this.playerTag = "default";
		this.territory= new ArrayList<Planet>();
		this.spacefleets = new ArrayList<>();
	}
	
	public ArrayList<Planet> getTerritory(){
		return this.territory;
	}
	
	public ArrayList<Spacefleet> getFleets(){
		return this.spacefleets;
	}
	
	
	/**
	 * Choose the palyer's first planet and fill her with a specified color
	 * @param u,
	 */
	public void firstPlanet(Universe u) {
		Random random = new Random();
		int indexPlanet = random.nextInt(u.getPlanets().size());
		this.territory.add(u.getPlanets().get(indexPlanet));
		u.getPlanets().get(indexPlanet).getPlanetShape().rgb(Color.RED);
	}
	
	/**
	 * add a planet in the list of planet owned by the player
	 * fill them with the specified color
	 * @param p
	 */
	public void myPlanet(Planet p) {
		this.territory.add(p);
		p.getPlanetShape().rgb(Color.RED);
	}
	
	public void newLaunch(Spacefleet s) {
		this.spacefleets.add(s);
	}
	
	public boolean inAction() {
		
		if(getFleets().isEmpty())
			return false;
		boolean res = false;
		for (Iterator iterator = spacefleets.iterator(); iterator.hasNext();) {
			Spacefleet spacefleet = (Spacefleet) iterator.next();
			res |= spacefleet.getDestination() != null;
		}
		return res;
	}
}
