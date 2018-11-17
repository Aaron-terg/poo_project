package Models.planet;

import Models.Player;
import Models.Spaceship.BasicSpaceshipType;
import Models.Spaceship.SpaceshipType;

/**
 * <b>Owned class represent a planet own by a player</b>
 * <p>
 *     A planet has the following property:
 *     <ul>
 *         <li>The owner of the planet if there is one</li>
 *         <li>The spaceship type it produce</li>
 *         <li>The production rate of spaceship</li>
 *         <li>The number of ship on it</li>
 *     </li>
 * </p>
 * 
 * @see PlanetStateIntermediate
 * @see Player
 * @see SpaceshipType
 * 
 * @author meryl
 * @version 1.0
 *
 */
public class Owned extends PlanetStateIntermediate{

	/**
	 * Owned Planet Constructor
	 * <p>
	 * 		Instanciate a planet with its current owner.
	 * </p>
	 * @param player
	 * 			The current owner of the planet.
	 * 
	 * @see Player
	 */
	public Owned(Player player) {
		super(player, new BasicSpaceshipType(), 1);
	}
	
	/**
	 * Owned Planet Constructor
	 * <p>
	 * 		Instanciate a planet with its current owner, the spaceship whom conquer the planet.
	 * </p>
	 * 
	 * @param player
	 * 			The current owner of the planet.
	 * 
	 * @param spaceship type
	 * 			The type of spaceship the planet will instanciate.
	 * 
	 * @see Player
	 */
	public Owned(Player owner, SpaceshipType spaceshipType, int productionRate) {
		super(owner, spaceshipType, productionRate);
	}
}
