package Models.shape;

import Models.Spaceship.SpaceshipType;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * <b>The shape interface contain basic function of a shape</b>
 * 
 * @see Planet
 * @see SpaceshipType
 * @author meryl
 * @version 1.2
 */
public abstract class Shape {
	
	protected Color rgb;
	protected double height, width;
	
	public Shape() {
		this.rgb = Color.BLACK;
	}
	public Shape(Color color) {
		this.rgb = color;
	}
	
	public Shape(Color color, double height) {
		this.rgb= color;
		this. height = height;
	}
	public double getHeight() {
		return this.height;
	}
	

	public abstract double[] position();
	
	public abstract void setPosition(double posX, double posY);
	
	public void drawShape(GraphicsContext gc) {
		gc.setFill(rgb);
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(1);
		
	}
	public boolean isInside(double x, double y) {
		
		// TODO Auto-generated method stub
		return false;
	}
	
	public void validPosition(double frameWidth, double frameHeight) {
		// TODO Auto-generated method stub
		
	}
	
	public double distance (Shape shape) {
		// TODO Auto-generated method stub
		return 0;
	}
	public void rgb(Color red) {
		this.rgb =red;
		
	}
	
}
