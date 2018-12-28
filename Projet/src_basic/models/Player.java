package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import controllers.Universe;
import controllers.UserInput;
import javafx.scene.paint.Color;

/**
 * <b>Player class represents the player</b>
 *  <p> Player implements {@link Serializable}</p>
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
 * @author meryl, Virginie
 * @version src_basic
 * @since src_basic
 *
 */
public class Player implements Serializable{

	/**
	 * The player tag
	 */
	public String playerTag;
	
	/**
	 * a set of planet owned by the player
	 * 
	 * @see Player#getTerritory()
	 * @see Planet
	 */
	protected ArrayList<Planet> territory;
	
	/**
	 * all the fleets of the player
	 * 
	 * @see Player#getFleets()
	 * @see Spacefleet
	 */
	protected ArrayList<Spacefleet> spacefleets;
	
	/**
	 * The color of the player
	 * 
	 * @see Player#getColor()
	 */
	protected double[] color;
	
	/**
	 * the percentage of spaceships to send from a selected planet.
	 * by default: 50
	 * it can be augmented with the SHIFT key and regress with the ALT key
	 * 
	 * @see UserInput#keyPressed()
	 */
	public int percent=50;
	
	/**
	 * Default Player constructor, set the player tag to default. and the color to red
	 */
	public Player() {
		this.playerTag = "default";
		this.territory= new ArrayList<Planet>();
		this.spacefleets = new ArrayList<>();
		this.color= new double[] {
				Color.RED.getRed(),
				Color.RED.getGreen(),
				Color.RED.getBlue()
		};
	}
	
	/**
	 * Player constructor 
	 * @param name the player tag of the player
	 * @param color his color for the game
	 */
	public Player(String name, Color color) {
		this();
		this.playerTag = name;
		this.color = new double[] {
				color.getRed(),
				color.getGreen(),
				color.getBlue()
		};
	}
	
	/***********************************\
	 * 								   *
	 * 			Getter/Setter		   *
	 * 								   *
	\***********************************/
	
	/**
	 * Get the set of planets owned by the player
	 * 
	 * @return the set of planet owned by the player
	 * @see Player#territory
	 * @see Planet
	 */
	public ArrayList<Planet> getTerritory(){
		return this.territory;
	}
	
	/**
	 * Get the fleets of the player
	 * 
	 * @return the set of planet owned by the player
	 * @see Player#spacefleets
	 * @see Spacefleet
	 */
	public ArrayList<Spacefleet> getFleets(){
		return this.spacefleets;
	}
	
	/**
	 * Get the color of Player
	 * @return the color of the player
	 * @see Player#color
	 */
	public Color getColor() {
		return Color.color(color[0], color[1], color[2]);
	}
	
	/***********************************\
	 * 								   *
	 * 				Method			   *
	 * 								   *
	\***********************************/

	/**
	 * Check if the player can still play
	 * @return true if the number of planet owned by the player is greater than 0
	 */
	public boolean hasTerritory() {
		return territory.size() != 0;
	}
	
	/**
	 * Choose the player's first planet and fill her with a specified color
	 * @param u the universe to choose the planet from
	 */
	public void firstPlanet(Universe u) {
		Random random = new Random();
		int indexPlanet = random.nextInt(u.getPlanets().size());
		while(u.getPlanets().get(indexPlanet).isOwn())
			indexPlanet = random.nextInt(u.getPlanets().size());
		this.myPlanet(u.getPlanets().get(indexPlanet));
		
	}
	
	/**
	 * Add a planet in the list of planet owned by the player
	 * and set the owner of the planet
	 * @param p the planet conquered
	 * 
	 * @see Player#territory
	 * @see Planet#owner()
	 * @see Planet#newOwner(Player)
	 */
	public void myPlanet(Planet p) {
		this.territory.add(p);
		p.newOwner(this);

	}
	
	/**
	 * Prepare a new fleet and add it to the {@link Player#spacefleets}
	 * @param percent percentage of ship to send
	 * @param start the planet of departure
	 * @return a new Spacefleet ready to be sent
	 */
	public Spacefleet newLaunch(int percent, Planet start) {
		
		int nbShip = (int)(percent*start.nbShipOnPlanet())/100;
		if(nbShip <= 0)
			return null;
		Spacefleet spacefleet = new Spacefleet(nbShip, start);
		this.spacefleets.add(spacefleet);
		return spacefleet;
	}
	
	/**
	 * set the new destination for the current selected spacefleet.
	 * @param planet the new destination
	 * 
	 * @see UserInput#mouseReleased()
	 */
	public void newDest(Planet planet){
		Spacefleet currentSpacefleet;
		if(GameObject.selected instanceof Planet) {
			Planet currentPlanet = (Planet)Planet.selected;

			if(currentPlanet != planet) {

				currentSpacefleet = this.newLaunch(this.percent, currentPlanet);
				if(currentSpacefleet != null)
					currentSpacefleet.setDestination(planet); 

			}

		}else if(GameObject.selected instanceof Spacefleet) {
			currentSpacefleet = (Spacefleet)Spacefleet.selected;
			currentSpacefleet.setDestination(planet); 
		}
	}
	/**
	 * Check if the player has fleet in action
	 * @return true if the player has a fleet with a destination set
	 */
	public boolean inAction() {
		
		if(this.spacefleets.isEmpty())
			return false;
		boolean res = false;
		for (Iterator<Spacefleet> iterator = spacefleets.iterator(); iterator.hasNext();) {
			Spacefleet spacefleet = (Spacefleet) iterator.next();
			res |= spacefleet.hasDestination();
		}
		return res;
	}
	 
	@Override
	public boolean equals(Object o) {
		if(o != null && (o instanceof Player)) {
			Player player = (Player)o;
			return this.playerTag == player.playerTag;
		}
		return false;
			
		
	}
	
	@Override
	public String toString() {
		return this.playerTag + "\n"
				+ "nb conquered planet: " + territory.size();
	}
		
	
}
