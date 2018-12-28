package models.planet;

import models.GameObject;
import models.shape.Polygon;
import models.spaceship.FastSpaceship;

/**
 * triangle planet. third planet with faster but weaker spaceship type.
 * inherits from Planet
 * @author meryl
 *
 * @see GameObject
 * @see Planet
 * @see FastSpaceship
 * @see Polygon
 */
public class TrianglePlanet extends Planet{

	/**
	 * * The triangle planet constructor call its parent constructor then setting planet shape
	 * with a set of x and y coordinates.
	 * 
	 * @see Planet#planetGenerator()
	 * @see Planet#Planet(models.spaceship.SpaceshipType)
	 */
	public TrianglePlanet() {
		super(new FastSpaceship());

		double[] Xs = new double[] {
				this.x ,
				this.x ,
				this.x+ this.width
		};
		double[] Ys = new double[] {
				this.y,
				this.y+ this.height,
				this.y , 
		};
		
		this.planetShape = new Polygon(Xs, Ys);
	}

}
