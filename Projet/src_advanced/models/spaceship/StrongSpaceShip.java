package models.spaceship;

import models.shape.Polygon;

/**
 * <b>StrongSpaceShip class represent the fast ship</b>
 * <p>
 *     The StrongSpaceShip class extends the SpaceshipType class
 * </p>
 * 
 *
 * @see SpaceshipType
 * @author meryl
 * @version src_advanced
 * **/
public class StrongSpaceShip extends SpaceshipType {

	/**
	 * Constructors of the basic spaceship
	 * call the parent constructor with its stats and defined a shape.
	 */
	public StrongSpaceShip() {
		super(15, 1, 25);
		
		double[] Xs = new double[] {
				
				0,
				0,
				this.width,
				this.width
		};
		double[] Ys = new double[] {
				0,
				this.height,
				this.height,
				0
		};
		this.spaceshipShape = new Polygon(Xs, Ys);
		
	}
}
