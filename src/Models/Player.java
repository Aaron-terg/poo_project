package Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import Controllers.Universe;
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
public class Player implements Serializable{

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
	private double[] color;
	public int percent=100;
	/**
	 * The default constructor, set the player tag to default.
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
	public Player(String name, Color color) {
		this();
		this.playerTag = name;
		this.color = new double[] {
				color.getRed(),
				color.getGreen(),
				color.getBlue()
		};
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
		return Color.color(color[0], color[1], color[2]);
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
		Spacefleet spacefleet = new Spacefleet(nbShip, start);
		this.spacefleets.add(spacefleet);
		return spacefleet;
	}
	
	public boolean inAction() {
		
		if(this.spacefleets.isEmpty())
			return false;
		boolean res = false;
		for (Iterator iterator = spacefleets.iterator(); iterator.hasNext();) {
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
				+ "Color : \nred: " + color[0] + ", green: "+ color[1] +", blue: " + color[2] + "\n"
				+ "nb conquered planet: " + territory.size();
	}
		
	
}
