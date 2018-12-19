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
	private Color color;
	public int percent=100;
	/**
	 * The default constructor, set the player tag to default.
	 */
	public Player() {
		this.playerTag = "default";
		this.territory= new ArrayList<Planet>();
		this.spacefleets = new ArrayList<>();
		this.color= Color.RED;
	}
	public Player(String name) {
		this();
		this.playerTag = name;
		this.color = Color.GREEN;
	}
	
	public ArrayList<Planet> getTerritory(){
		return this.territory;
	}
	
	public ArrayList<Spacefleet> getFleets(){
		return this.spacefleets;
	}
	
	public boolean hasTerritory() {
		return territory.size() != 0;
	}
	
	public Color getColor() {
		return this.color;
	}
	/**
	 * Choose the palyer's first planet and fill her with a specified color
	 * @param u,
	 */
	public void firstPlanet(Universe u) {
		Random random = new Random();
		int indexPlanet = random.nextInt(u.getPlanets().size());
		while(u.getPlanets().get(indexPlanet).isOwn())
			indexPlanet = random.nextInt(u.getPlanets().size());
		this.myPlanet(u.getPlanets().get(indexPlanet));
		
	}
	
	/**
	 * add a planet in the list of planet owned by the player
	 * fill them with the specified color
	 * @param p
	 */
	public void myPlanet(Planet p) {
		this.territory.add(p);
		p.newOwner(this);

	}
	
	public Spacefleet newLaunch(int percent, Planet start) {
		int nbShip = (int)(percent*start.nbShipOnPlanet())/100;
		int indexSpacefleet = getFleets().size();
		Spacefleet spacefleet = new Spacefleet(nbShip, start, indexSpacefleet);
		this.spacefleets.add(spacefleet);
		return spacefleet;
	}
	
	public boolean inAction() {
		
		if(this.spacefleets.isEmpty())
			return false;
		boolean res = false;
		for (Iterator iterator = spacefleets.iterator(); iterator.hasNext();) {
			Spacefleet spacefleet = (Spacefleet) iterator.next();
			res |= spacefleet.getDestination() != null;
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
		return this.playerTag;
	}
		
	
}
