package Models;

import java.util.Collection;

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
	 * The planet owned
	 * 
	 * 
	 */
	private Collection<Planet> territory;
}