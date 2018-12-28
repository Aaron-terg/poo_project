package models.planet;

import java.io.Serializable;

import models.shape.Polygon;
import models.spaceship.StrongSpaceShip;

public class SquarePlanet extends Planet  implements Serializable {

	public SquarePlanet() {
		super(new StrongSpaceShip());
		double x = this.getX();
		double y = this.getY();
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
