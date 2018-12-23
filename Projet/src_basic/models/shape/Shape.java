package models.shape;

import java.io.Serializable;

import models.spaceship.SpaceshipType;
import models.planet.Planet;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * <b>The shape abstract class contains basics functions of a shape</b>
 * A Shape has the following properties : 
 * <p>
 * <ul>

 * 		<li> a height && a width </li>
 * </p>
 * @see Planet
 * @see SpaceshipType
 * @author meryl,Virginie
 * @version 2.1
 */
public abstract class Shape implements Serializable {
	
	protected double height, width;
	/**
	 * Shape constructors
	 */
	public Shape() {
	}
	
	public Shape(double width, double height) {
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

	
	
	/***********************************\
	 * 								   *
	 * 				Method			   *
	 * 								   *
	\***********************************/
	
	/**
	 * Get the actual position of the shape 
	 * @return a table of Coordinates
	 */

	public abstract double[] position();
	

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
	public void drawShape(GraphicsContext gc, Color rgb) {
		gc.setFill(rgb);
		gc.setStroke(Color.BLACK);
		
	}

}
