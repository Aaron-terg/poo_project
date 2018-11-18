package Models;

import java.util.Collection;

import Models.planet.Planet;

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
	private Collection<Planet> territory;
	
	/**
	 * The default constructor, set the player tag to default.
	 */
	public Player() {
		this.playerTag = "default";
	}
}
