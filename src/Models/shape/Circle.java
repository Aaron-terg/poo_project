package Models.shape;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Circle implements Shape{
	
	private double x, y;
	private int maxWidth, maxHeight;
	private double radius;
	private Color rgba;
	
	public Circle() {
		this.x = 0;
		this.y = 0;
		this.radius = 1;
		this.rgba = Color.BLACK;
	}
	
	public Circle(double x, double y, double radius, int r, int g, int b, double a, int w, int h) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.rgba = Color.rgb(r, g, b, a);
		this.maxWidth= w;
		this.maxHeight=h;
	}
	
	public Circle(double x, double y, double radius, int r, int g, int b, int w, int h) {
		this(x, y, radius, r, g, b, 1, w,h);
	}
	
	public Circle(double x, double y, double radius, int[] rgb, int w, int h) {
		this(x, y, radius, rgb[0], rgb[1], rgb[2], 1, w, h);
	}
	
	@Override
	public double[] position() {
		double[] position = {this.x, this.y};
		return position;
	}

	@Override
	public boolean intersects(Shape shape) {
		if(shape instanceof Circle) {
			double newRadius  = this.radius + ((Circle)shape).radius;
			return (shape.position()[0] <= this.position()[0] + newRadius
					||  shape.position()[0] <= this.position()[0] - newRadius)
					&& (shape.position()[1] <= this.position()[1] + newRadius
					|| shape.position()[1] <= this.position()[1] - newRadius);
		}
		return false;
	}
	

	@Override
	public void drawShape(GraphicsContext gc) {
		Paint gcFill = gc.getFill();
		Paint gcStroke = gc.getStroke();
		double gcLineWidth = gc.getLineWidth();
		gc.setFill(rgba);
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(1);
		gc.fillOval(x, y, radius, radius);
		gc.setFill(gcFill);
		gc.setStroke(gcStroke);
		gc.setLineWidth(gcLineWidth);
	}
	
	/*public void setPosition(double x, double y) {
		this.x = x; 
		this.y = y;
		validPosition();
	}*/
	
	
	public void validPosition() {
		if(this.x+radius >= this.maxWidth) {
			this.x=this.x-radius;	
		}
		if(this.x-radius <=0) {
			this.x=(this.x+radius);
		}
		
		if(y+radius >= this.maxHeight) {
			this.y=y-radius;
		}
		if(y-radius<=0) {
			this.y = y+radius;
		}
		
	}
	
	
	
	
	
}
	
