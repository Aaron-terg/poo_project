package Models.planet;

import Models.Player;
import Models.Spaceship.SpaceshipType;

/**
 * <b>PlanetState is the interface allowing the distinction between Neutral and Owned</b>
 * <p>
 *     The PlanetState interface has the following property:
 *     <ul>
 *         <li>The owner's getter and setter of the planet if there is one</li>
 *         <li>The spaceship type's getter and setter it produce</li>
 *         <li>The production rate's getter and setter of spaceship</li>
 *     </li>
 * </p>
 * 
 * @see Player
 * @see SpaceshipType
 * @see Neutral
 * @see Owned
 * 
 * @author meryl
 * @version 1.0
 *
 */
public interface PlanetState {
	
	public Player getOwner();
	public void setOwner(Player player);
	public SpaceshipType getSpaceShipeType();
	public void setSpaceShipeType(SpaceshipType spaceshipType);
	public int getProductionRate();
}
