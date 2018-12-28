package models.shape;

import models.planet.Planet;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * 
 * <b>Circle</b>
 * <p>
 * 		Circle class represents the shape of the planet. A circle has the following properties:
 * 		<ul>
 * 			<li>The coordinates of its center : x & y;</li>
 * 			<li> the radius</li>
 * </li>
 * 
 * 	<p>	
 * @see Planet
 * @see Shape
 * @author meryl,Virginie
 * @version src_advanced
 *
 */
public class Circle extends Shape{
	
	/**
	 * the position of the shape
	 */
	private double x, y;
	
	/**
	 * the radius of the shape.
	 */
	private double radius;
	
	/**
	 * The circle constructor
	 * @param x coordinates
	 * @param y coordinates
	 * @param diameter
	 */
	public Circle(double x, double y, double diameter) {
		this.x = x;
		this.y = y;
		this.radius = diameter/2;
	}

	/***********************************\
	 * 								   *
	 * 				Method			   *
	 * 								   *
	\***********************************/
	
	/**
	 * Draw the shape on the window 
	 * @param the GraphicsContext gc
	 * @param rgb the color of the shape
	 */
	@Override
	public void drawShape(GraphicsContext gc, Color rgb) {
		super.drawShape(gc, rgb);
		gc.strokeOval(x - radius, y - radius, radius*2, radius*2);
		gc.fillOval(x - radius, y - radius, radius*2, radius*2);
	}

	@Override
	public void setPosition(double posX, double posY) {
		this.x = posX;
		this.y = posY;
	}
}
	
