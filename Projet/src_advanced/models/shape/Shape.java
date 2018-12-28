package models.shape;

import java.io.Serializable;

import models.spaceship.SpaceshipType;
import models.planet.Planet;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * <b>The shape abstract class contains basics functions of a shape</b>
 *  <p> Shape implements {@link Serializable}</p>
 * A Shape has the following properties : 
 * <p>
 * <ul>

 * 		<li> a height && a width </li>
 * </p>
 * @see Planet
 * @see SpaceshipType
 *  @see Circle
 *  @see Line
 *  @see Polygon
 * @author meryl,Virginie
 * @version src_advanced
 */
public abstract class Shape implements Serializable {
	
	protected double height, width;
	/**
	 * Shape constructors
	 */
	public Shape() {
	}
	
	/**
	 * Shape constructors setting the width and the height of the shape
	 */
	public Shape(double width, double height) {
		this.width = width;
		this. height = height;
	}
	
	/***********************************\
	 * 								   *
	 * 				Method			   *
	 * 								   *
	\***********************************/

	/**
	 * <b>abstract method</b>
	 * <p>Set the shape at a specific position. to be implemented</p>
	 * @param posX : the X coordinate of the new position
	 * @param posY : the Y coordinate of the new position
	 */	
	public abstract void setPosition(double posX, double posY);
	

	/**
	 * <p>This method permits to draw the shape on the window</p>
	 * @param gc
	 */
	public void drawShape(GraphicsContext gc, Color rgb) {
		gc.setFill(rgb);
		gc.setStroke(Color.BLACK);
	}

}
