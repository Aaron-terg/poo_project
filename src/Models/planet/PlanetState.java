package Models.planet;

import Models.Player;

/**
 * <b>PlanetState is the interface allowing the distinction between Neutral and Owned</b>
 * <p>
 *     The PlanetState interface has the owner's getter and setter of the planet if there is one.
 * </p>
 * 
 * @see Player
 * @see Neutral
 * @see Owned
 * 
 * @author meryl
 * @version 1.1
 *
 */
public interface PlanetState {
	
	public Player getOwner();
	public void setOwner(Player player);
}
