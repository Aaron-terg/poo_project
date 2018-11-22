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

	@Override
	public boolean intersects(Shape shape) {
		// TODO find the condition 
		if(shape instanceof Circle) {
			double newRadius  = this.radius + ((Circle)shape).radius;
			return (shape.position()[0] <= this.position()[0] + newRadius
					||  shape.position()[0] <= this.position()[0] - newRadius)
					&& (shape.position()[1] <= this.position()[1] + newRadius
					|| shape.position()[1] <= this.position()[1] - newRadius);
		}
		/*
		 * if(shape instanceof Rectangle) {
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
				int newInd = (i+1)%4;
				result |= (pointsX[i] <= this.position()[0] + this.radius) || (pointsX[i] <= this.position()[0] - this.radius)
					    && (pointsY[i] <= this.position()[0] + this.radius) || (pointsY[i] <= this.position()[0] - this.radius);
				
			}
			return result;
		}
		*/

		if(shape instanceof Polygon) {
			Polygon polygon = (Polygon) shape;
			return (shape.position()[0] <= this.position()[0] + this.radius
					||  shape.position()[0] <= this.position()[0] - this.radius)
				&& (shape.position()[1] <= this.position()[1] + this.radius
						||  shape.position()[1] <= this.position()[1] - this.radius);
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
	
	public double distance(Shape p) {
		double d1 = this.x -p.position()[0];
		double d2 = this.y -p.position()[1];
		return Math.sqrt(d1*d1+d2*d2);
	}
	
	public boolean no_superimposed(Shape s) {
		return this.distance(s)<this.radius+((Circle)s).radius;
	}

	

	
}
	
