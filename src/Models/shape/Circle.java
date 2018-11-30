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
		super();
		this.x = 0;
		this.y = 0;
		this.radius = 1;
	}
	
	public Circle(double x, double y, double radius, Color color) {
		super(color,radius);
		this.x = x;
		this.y = y;
		this.radius = super.getHeight();
		
	}
	
	public Circle(Circle c) {
		this(c.x, c.y, c.radius, c.rgb);
	}
	
	/**
	 * Getter && setter
	 * 
	 */
	
	public double radius() {
		return this.radius;
	}
	
	public double getX()
	{
		return this.x;
	}
	
	public double getY() {
		return this.y;
	}
	
	@Override
	public double[] position() {
		double[] position = {this.x, this.y};
		return position;
	}

	public boolean intersects(Shape shape) {
		// TODO find the condition
			double newRadius  = this.radius + ((Circle)shape).radius();
			return Math.abs(shape.position()[0] - this.position()[0])  <= newRadius
					&& Math.abs(shape.position()[1] - this.position()[1])  <= newRadius;
					
			//return this.distance(shape) > 0
		}

	public double distance(Shape shape) {
		// TODO Auto-generated method stub
		
			Circle circle = (Circle)shape;
			double radiusSum  = this.radius + circle.radius();
			return this.distPoint(shape.position()[0], shape.position()[1]) - radiusSum;
		}
	
	public double distPoint(double p1X, double p1Y) {
		return Math.sqrt(Math.pow((p1X - this.x), 2) + Math.pow((p1Y - this.y), 2));
	}

	@Override
	public void drawShape(GraphicsContext gc) {
		super.drawShape(gc);
		gc.fillOval(x - radius, y - radius, radius*2, radius*2);

	}
	
	
	public void validPosition(double frameWidth, double frameHeight) {
		double offset = this.radius + 5;
		if(this.x + this.radius >= frameWidth) 
			this.x -= offset;	
		
		if(this.x - this.radius <= 0)
			this.x += offset;
		
		if(this.y + this.radius >= frameHeight)
			this.y -= offset;
		
		if(this.y - this.radius <= 0)
			this.y += offset;
		
	}
	public boolean outOfWindow(double frameWidth, double frameHeight) {
		return x+radius> frameWidth || x-radius <0 || y+radius > frameHeight || y-radius <0;
	}

	public boolean isInside(double px, double py) {
		 return px<=(this.x+this.radius) && px>=(this.x-this.radius)
				 && py>=(this.y-this.radius) &&py<=(this.y+this.radius);
		 
	}
}
	
