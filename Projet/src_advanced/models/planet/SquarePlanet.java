package models.planet;

import java.io.Serializable;

import models.GameObject;
import models.shape.Polygon;
import models.spaceship.StrongSpaceShip;

/**
 * <b>Square planet. Second planet with stronger spaceship type.</b>
 * <p> SquarePlanet extends {@link Planet}</p>
 *  <p> SquarePlanet implements {@link Serializable}</p>
 * @author meryl
 *
 * @see GameObject
 * @see Planet
 * @see StrongSpaceShip
 * @see Polygon
 */
public class SquarePlanet extends Planet  implements Serializable {

	/**
	 * * The square planet constructor call its parent constructor then setting planet shape
	 * with a set of x and y coordinates.
	 * 
	 * @see Planet#planetGenerator()
	 * @see Planet#Planet(models.spaceship.SpaceshipType)
	 */
	public SquarePlanet() {
		super(new StrongSpaceShip());
		double[] Xs = new double[] {
				this.x + this.width,
				this.x + this.width,
				this.x,
				this.x
		};
		double[] Ys = new double[] {
				this.y,
				this.y + this.height,
				this.y + this.height, 
				this.y
		};
		this.planetShape = new Polygon(Xs, Ys);
	}
}
