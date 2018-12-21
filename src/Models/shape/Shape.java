package Models.shape;

import java.io.Serializable;

import Models.Spaceship.SpaceshipType;
import Models.planet.Planet;
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
public abstract class Shape implements Serializable {
	
	protected double[] color;
	protected double height, width;
	
	public Shape() {
		this.color = new double[] {0,0,0};
	}
	public Shape(Color color) {
		this.color = new double[] {
				color.getRed(),
				color.getGreen(),
				color.getBlue()
		};
	}
	
	public Shape(double width, double height, Color rgb) {
		this(rgb);
		this.width = width;
		this. height = height;
	}
	
	public double getHeight() {
		return this.height;
	}
	

	public abstract double[] position();
	
	public abstract void setPosition(double posX, double posY);
	
	public void drawShape(GraphicsContext gc) {
		gc.setFill(rgb());
		gc.setStroke(Color.BLACK);
		
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
	
	public void rgb(Color color) {
		this.color[0] = color.getRed();
		this.color[1] = color.getGreen();
		this.color[2] = color.getBlue();
	}
	public Color rgb() {
		return Color.color(color[0], color[1], color[2]);

	}
	
	public double width() {
		return width;
	}
	public double height() {
		return height;
	}
	
}
