package shape;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Circle implements Shape{
	
	private double x, y;
	private double radius;
	private Color rgba;
	
	public Circle() {
		this.x = 0;
		this.y = 0;
		this.radius = 1;
		this.rgba = Color.BLACK;
	}
	
	public Circle(double x, double y, double radius, int r, int g, int b, double a) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.rgba = Color.rgb(r, g, b, a);
	}
	
	public Circle(double x, double y, double radius, int r, int g, int b) {
		this(x, y, radius, r, g, b, 1);
	}
	
	public Circle(double x, double y, double radius, int[] rgb) {
		this(x, y, radius, rgb[0], rgb[1], rgb[2], 1);
	}
	
	@Override
	public double[] position() {
		double[] position = {this.x, this.y};
		return position;
	}

	@Override
	public boolean intersects(Shape shape) {
		// TODO find the condition 
		if(shape instanceof Circle)
			return true;
		return false;
	}

	@Override
	public void render(GraphicsContext gc) {
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

}
