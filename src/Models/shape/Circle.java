package Models.shape;

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
		if(shape instanceof Circle) {
			double newRadius  = this.radius + ((Circle)shape).radius;
			return (shape.position()[0] <= this.position()[0] + newRadius
					||  shape.position()[0] <= this.position()[0] - newRadius)
				&& (shape.position()[1] <= this.position()[1] + newRadius
						||  shape.position()[1] <= this.position()[1] - newRadius);
		}
		if(shape instanceof Rectangle) {
			Rectangle rect = (Rectangle) shape;
			double rectX = rect.position()[0];
			double rectY = rect.position()[1];
			double[] pointsX = new double[] {
					rectX				 , rectX+rect.width()/2,
					rectX + rect.height()/2, rectX + (rect.width()+ rect.height())/2
			};
			double[] pointsY = new double[] {
					rectX				 , rectY+rect.width()/2,
					rectY + rect.height()/2, rectY + (rect.width()+ rect.height())/2
			};
			
			boolean result = false;
			for (int i = 0; i < pointsY.length; i++) {
				result |= (pointsX[i] <= this.position()[0] + this.radius) || (pointsX[i] <= this.position()[0] - this.radius)
					    && (pointsY[i] <= this.position()[0] + this.radius) || (pointsY[i] <= this.position()[0] - this.radius);
				
			}
			return result;
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

}
