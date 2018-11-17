package Models.planet;

import Models.Player;
import Models.Spaceship.SpaceshipType;

/**
 * <b>PlanetStateIntermediate is the abstract class for Neutral and Owned</b>
 * <p>
 *     A planet has the following property:
 *     <ul>
 *         <li>The owner of the planet if there is one</li>
 *         <li>The spaceship type it produce</li>
 *         <li>The production rate of spaceship</li>
 *     </li>
 * </p>
 * 
 * @see PlanetState
 * @see Player
 * @see SpaceshipType
 * @see Neutral
 * @see Owned
 * 
 * @author meryl
 * @version 1.0
 *
 */
public abstract class PlanetStateIntermediate implements PlanetState{
	/**
	 * The owner of the planet
	 * 
	 * @see Player
	 */
	private Player owner;

	/**
	 * The type of Spaceship the planet produce
	 * 
	 * @see SpaceshipType
	 */
	private SpaceshipType spaceshipType; 
	
	/**
	 * The ratio of to create a Spaceship
	 */
	private int productionRate;
	
	/**
	 * The mutual Planet Constructor for Neutral and Owned
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
	 * @see SpaceshipType
	 * @see Neutral
	 * @see Owned
	 */
	public PlanetStateIntermediate(Player owner, SpaceshipType spaceshipType, int productionRate) {
		this.owner = owner;
		this.spaceshipType = spaceshipType;
		this.productionRate = productionRate;
	}
	
	/***********************************\
	 * 								   *
	 * 			Getter/Setter		   *
	 * 								   *
	\***********************************/
	@Override
	public Player getOwner() {
		return this.owner;
	}

	@Override
	public void setOwner(Player player) {
		this.owner = player;
	}

	@Override
	public SpaceshipType getSpaceShipeType() {
		return this.spaceshipType;
	}

	@Override
	public void setSpaceShipeType(SpaceshipType spaceshipType) {
		this.spaceshipType = spaceshipType;
	}

	@Override
	public int getProductionRate() {
		return this.productionRate;
	}

}
