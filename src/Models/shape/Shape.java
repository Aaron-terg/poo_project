package Models.shape;

import java.io.Serializable;

import Models.Spaceship.SpaceshipType;
import Models.planet.Planet;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * <b>The shape abstract class contains basics functions of a shape</b>
 * A Shape has the following properties : 
 * <p>
 * <ul>
 * 		<li> A specified color </li>
 * 		<li> a height && a width </li>
 * </p>
 * @see Planet
 * @see SpaceshipType
 * @author meryl,Virginie
 * @version 2.1
 */
public abstract class Shape implements Serializable {
	
	protected double[] color;
	protected double height, width;
	/**
	 * Shape constructors
	 */
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
	/***********************************\
	 * 								   *
	 * 			Getter/Setter		   *
	 * 								   *
	\***********************************/
	/**
	 * return the height of the shape.
	 * @return
	 */
	public double getHeight() {
		return this.height;
	}
	/**
	 * 
	 * @return the width of the shape
	 */
	public double width() {
		return width;
	}
	/**
	 * Set the specified color to the shape
	 * @param color : the color to apply
	 */
	public void rgb(Color color) {
		this.color[0] = color.getRed();
		this.color[1] = color.getGreen();
		this.color[2] = color.getBlue();
	}
	/**
	 * 
	 * @return the actual color of the shape
	 */
	public Color rgb() {
		return Color.color(color[0], color[1], color[2]);

	}
	/**
	 * Get the actual position of the shape 
	 * @return a table of Coordinates
	 */
	public abstract double[] position();
	
	
	
	/***********************************\
	 * 								   *
	 * 				Method			   *
	 * 								   *
	\***********************************/
	
	/**
	 * Set the shape at a specific position
	 * @param posX : the X coordinate of the new position
	 * @param posY : the Y coordinate of the new position
	 */	
	public abstract void setPosition(double posX, double posY);
	

	/**
	 * This method permits to draw the shape on the window
	 * @param gc
	 */
	public void drawShape(GraphicsContext gc) {
		gc.setFill(rgb());
		gc.setStroke(Color.BLACK);
		
	}

}
