package models.spaceship;

import models.shape.Polygon;
/**
 * <b>FastSpaceship class represents the fast ship</b>
 * <p>
 *     The FastSpaceship class extends the SpaceshipType class
 * </p>
 * 
 *
 * @see SpaceshipType
 * @author meryl
 * @version src_advanced
 * **/
public class FastSpaceship extends SpaceshipType{
	
	/**
	 * Constructors of the Fast spaceship
	 * call the parent constructor with its stats and defined a shape.
	 */
	public FastSpaceship() {
		super(10, 3, 15);
		double[] Xs = new double[] {
				0,
				this.width,
				this.width /2,
				this.width /2
		};
		double[] Ys = new double[] {
				0,
				0,
				this.height/3, 
				this.height
		};
		this.spaceshipShape = new Polygon(Xs, Ys);
	}
	
}
