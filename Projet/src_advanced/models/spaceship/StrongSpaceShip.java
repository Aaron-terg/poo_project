package models.spaceship;

import models.shape.Polygon;

public class StrongSpaceShip extends SpaceshipType {

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
