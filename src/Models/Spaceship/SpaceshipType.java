package Models.Spaceship;

import Models.shape.Renderable;
import Models.shape.Shape;
import javafx.scene.canvas.GraphicsContext;

public abstract class SpaceshipType implements Renderable{
	
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
	
	public void render(GraphicsContext gc) {
		this.spaceshipShape.drawShape(gc);
	}
}
