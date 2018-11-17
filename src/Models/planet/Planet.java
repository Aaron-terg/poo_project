package Models.planet;

import Models.Player;

/**
 * <b>Planet class represent the planet</b>
 * <p>
 *     A planet has the following property:
 *     <ul>
 *         <li>The state of the planet showing if it is own or not</li>
 *         <li>The number of ship on it</li>
 *     </li>
 * </p>
 * 
 * @see PlanetState
 * @see Player
 * 
 * @author meryl
 * @version 2.0
 *
 */
public class Planet {

	/**
	 * The number of spaceship on the planet.
	 * 
	 * @see PlanetState
	 */
	private PlanetState planetState = new Neutral();
	
	/**
	 * The number of spaceship on the planet
	 * 
	 */
	private int shipOnPlanet;
	
	/**
	 * Planet Constructor
	 * 
	 */
	public Planet() {
		
	}
	/***********************************\
	 * 								   *
	 * 			Getter/Setter		   *
	 * 								   *
	\***********************************/

	public int getShipOnPlanet() {
		return shipOnPlanet;
	}

	public int getProductionRate() {
		return planetState.getProductionRate();
	}
	
	public Player getOwner() {
		return planetState.getOwner();
	}
	
	public void setOwner(Player player) {
		this.planetState.setOwner(player);
	}
	
	public void setPlanetState(PlanetState state) {
		this.planetState = state;
	}
	
	
	/***********************************\
	 * 								   *
	 * 				Method			   *
	 * 								   *
	\***********************************/
	
	/**
	 * Test if the planetState is currently own.
	 * 
	 * @return a boolean showing if the planetState is currently own.
	 * 
	 * @see PlayerState
	 */
	public boolean isOwn() {
		return !(this.planetState instanceof Neutral); 
	}
	
	
	
}
