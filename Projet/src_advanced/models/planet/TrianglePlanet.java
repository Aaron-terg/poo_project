package models.planet;

import models.GameObject;
import models.shape.Polygon;
import models.spaceship.FastSpaceship;

public class TrianglePlanet extends Planet{

	public TrianglePlanet() {
		super(new FastSpaceship());

		double x = this.getX();
		double y = this.getY();
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
