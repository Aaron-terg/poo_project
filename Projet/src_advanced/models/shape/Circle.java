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
 * 
 * @author meryl,Virginie
 * @version src_basic
 *
 */
public class Circle extends Shape{
	
	private double x, y;
	private double radius;
	
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
	
