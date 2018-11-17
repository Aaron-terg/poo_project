package Models.planet;

/**
 * <b>Neutral class represent a planet not own by a player yet</b>
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
 * 
 * @author meryl
 * @version 1.0
 *
 */
public class Neutral extends PlanetStateIntermediate{
	
	/**
	 * Owned Planet Constructor
	 * <p>
	 * 		Instanciate a neutral planet with default value.
	 * </p>
	 */
	public Neutral() {
		super(null, null, 0);
	}
	

}
