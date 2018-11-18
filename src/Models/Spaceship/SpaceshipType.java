package Models.Spaceship;

import javafx.scene.shape.Shape;

public abstract class SpaceshipType {
	
	private int attPower;
	private int speed;
	private double productionTime;
	private Shape spaceshipShape;
	
	public SpaceshipType(int attPower, int speed, double productionTime, Shape spaceshipShape) {
		this.attPower = attPower;
		this.speed = speed;
		this.productionTime = productionTime;
		this.spaceshipShape = spaceshipShape;
	}
	
	/***********************************\
	 * 								   *
	 * 			Getter/Setter		   *
	 * 								   *
	\***********************************/
	
	public int getAttPower() {
		return this.attPower;
	}
	
	public int getSpeed() {
		return this.speed;
	}
	
	public double getProductionTime() {
		return this.productionTime;
	}
	
	public Shape getSpaceshipShape() {
		return spaceshipShape;
	}
}
