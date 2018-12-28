package models.spaceship;

import models.shape.Polygon;

public class StrongSpaceShip extends SpaceshipType {

	public StrongSpaceShip() {
		super(15, 1, 25);
		
		double[] Xs = new double[] {
				this.width,
				this.width,
				0,
				0
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
