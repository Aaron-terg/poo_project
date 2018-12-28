package models.spaceship;

import models.shape.Polygon;

public class FastSpaceship extends SpaceshipType{
	
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
