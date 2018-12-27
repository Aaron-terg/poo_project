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
	
	/**
	 *  Circle constructor, default constructor
	 */
	public Circle() {
		this.x = 0;
		this.y = 0;
		this.radius = 1;
	}
	
	public Circle(double x, double y, double radius) {
		this.x = x;
		this.y = y;
		this.radius = radius;
	}
	
	public Circle(Circle c) {
		this(c.x, c.y, c.radius);
	}
	
	/***********************************\
	 * 								   *
	 * 			Getter/Setter		   *
	 * 								   *
	\***********************************/
	
	/**
	 * Return the radius of the circle
	 * @return
	 */
	public double getRadius() {
		return this.radius;
	}
	/**
	 * return the X coordinate of the circle's center
	 * @return
	 */
	public double getX(){
		return this.x;
	}
	/**
	 * return the Y coordinate of the circle's center
	 * @return
	 */
	public double getY() {
		return this.y;
	}
	/**
	 * Return a table which contains the coordinates of the circle's center 
	 * @return a table 
	 */
	public double[] position() {
		double[] position = {this.x, this.y};
		return position;
	}
	/***********************************\
	 * 								   *
	 * 				Method			   *
	 * 								   *
	\***********************************/
	
/**
 * Check if a shape intersects the circle; 
 * return true if there is a collision
 * @param shape
 * @return
 */
	public boolean intersects(Shape shape) {
		if(shape instanceof Circle) {
			double newRadius  = this.radius + ((Circle)shape).radius;
			return Math.abs(shape.position()[0] - this.position()[0])  <= newRadius
					&& Math.abs(shape.position()[1] - this.position()[1])  <= newRadius;
		}else if(shape instanceof Polygon) {
			Polygon poly  = (Polygon)shape;
			boolean result = false;
			for (int i = 0; i < poly.getX().length; i++) {
				result |= distPoint(poly.getX()[i], poly.getY()[i]) <= radius;
			}
			return result;
		}
		return false;
		}
	/**
	 * Calculate the distance between two circles.
	 * @param shape : the second circle
	 * @return the distance
	 */
	public double distance(Circle shape) {
			double radiusSum  = this.radius + shape.getRadius();
			return this.distPoint(shape.position()[0], shape.position()[1]) - radiusSum;
		}
	/**
	 * Calculate the distance between a circle and a point
	 * @param p1X : the X coordinate of the point
	 * @param p1Y : the Y cooridnate of the point
	 * @return a distance (double)
	 */
	public double distPoint(double p1X, double p1Y) {
		return Math.sqrt(Math.pow((p1X - this.x), 2) + Math.pow((p1Y - this.y), 2));
	}

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
	
