package models.planet;

import java.io.Serializable;

import models.GameObject;
import models.shape.Polygon;
import models.spaceship.FastSpaceship;

/**
 * <b>triangle planet. third planet with faster but weaker spaceship type.</b>
 * <p> TrianglePlanet extends {@link Planet}</p>
 *  <p> TrianglePlanet implements {@link Serializable}</p>
 * @author meryl
 *
 * @see GameObject
 * @see Planet
 * @see FastSpaceship
 * @see Polygon
 */
public class TrianglePlanet extends Planet{

	/**
	 * * The triangle planet constructor calls its parent constructor then setting planet shape
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
