package Models.shape;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle extends Shape{
	
	private double x, y;
	private double radius;
	
	/**
	 *  Circle constructor
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
	
	/**
	 * Getter && setter
	 * 
	 */
	
	public double getRadius() {
		return this.radius;
	}
	
	public double getX()
	{
		return this.x;
	}
	
	public double getY() {
		return this.y;
	}
	
	public double[] position() {
		double[] position = {this.x, this.y};
		return position;
	}

	public boolean intersects(Shape shape) {
		// TODO find the condition
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
			//return this.distance(shape) > 0
		}

	public double distance(Circle shape) {
			double radiusSum  = this.radius + shape.getRadius();
			return this.distPoint(shape.position()[0], shape.position()[1]) - radiusSum;
		}
	
	public double distPoint(double p1X, double p1Y) {
		return Math.sqrt(Math.pow((p1X - this.x), 2) + Math.pow((p1Y - this.y), 2));
	}

	public void drawShape(GraphicsContext gc, Color rgb) {
		super.drawShape(gc, rgb);
		gc.strokeOval(x - radius, y - radius, radius*2, radius*2);
		gc.fillOval(x - radius, y - radius, radius*2, radius*2);
	}
	
	public boolean isInside(double px, double py) {
		 return px<=(this.x+this.radius) && px>=(this.x-this.radius)
				 && py>=(this.y-this.radius) &&py<=(this.y+this.radius);
		 
	}

	@Override
	public void setPosition(double posX, double posY) {
		// TODO Auto-generated method stub
		this.x = posX;
		this.y = posY;
	}
}
	
