package models.spaceship;

import models.shape.Polygon;
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
		super(3, 2, 3);
		
		this.spaceshipShape = new Polygon();
		
	}
	
}
