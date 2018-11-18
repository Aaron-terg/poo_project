package Models.planet;

import Models.Player;

/**
 * <b>Owned class represent a planet own by a player</b>
 * <p>
 *     A planet has the owner of the planet if there is one.
 * </p>
 * 
 * @see PlanetStateIntermediate
 * @see Player
 * 
 * @author meryl
 * @version 1.1
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
		super(player);
	}
	
}
