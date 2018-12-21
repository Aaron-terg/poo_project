package Models.Spaceship;

import Models.Player;
import Models.shape.Polygon;
/**
 * <b>BasicSpaceShipType class represent the basicship</b>
 * <p>
 *     The BasicSpaceShip class extends the SpaceshipType class
 * </p>
 * 
 *
 * @see SpaceshipType
 * @author Virginie
 * @version 2.1
 * **/
public class BasicSpaceshipType extends SpaceshipType {
	/**
	 * Constructors
	 */
	public BasicSpaceshipType() {
		super();
		this.speed = 2;
	}
	
	public BasicSpaceshipType(Player player) {
		this(); 
		this.player = player;
	}
	public BasicSpaceshipType(SpaceshipType spaceship) {
		super(spaceship);
	}
	
	public BasicSpaceshipType(Player player, double posX, double posY) {
		super();
		this.player = player;
		this.spaceshipShape.setPosition(posX, posY);
	}
	




	
	
}
