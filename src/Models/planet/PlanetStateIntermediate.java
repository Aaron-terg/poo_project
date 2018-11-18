package Models.planet;

import Models.Player;

/**
 * <b>PlanetStateIntermediate is the abstract class for Neutral and Owned</b>
 * <p>
 *     A planet has the owner of the planet if there is one.
 * </p>
 * 
 * @see PlanetState
 * @see Player
 * @see Neutral
 * @see Owned
 * 
 * @author meryl
 * @version 1.1
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
	 * The mutual Planet Constructor for Neutral and Owned
	 * <p>
	 * 		Instanciate a planet with its current owner, the spaceship whom conquer the planet.
	 * </p>
	 * 
	 * @param player
	 * 			The current owner of the planet.
	 * 
	 * @see Player
	 * @see Neutral
	 * @see Owned
	 */
	public PlanetStateIntermediate(Player owner) {
		this.owner = owner;

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


}
