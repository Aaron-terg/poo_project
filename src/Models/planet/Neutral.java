package Models.planet;

/**
 * <b>Neutral class represent a planet not own by a player yet</b>
 * <p>
 *     A planet has the owner of the planet if there is one.
 * </p>
 * 
 * @see PlanetStateIntermediate
 * 
 * @author meryl
 * @version 1.1
 *
 */
public class Neutral extends PlanetStateIntermediate{
	
	/**
	 * Neutral Planet Constructor
	 * <p>
	 * 		Instanciate a neutral planet with default value.
	 * </p>
	 */
	public Neutral() {
		super(null);
	}
	

}
