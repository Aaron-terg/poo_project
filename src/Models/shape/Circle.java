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
	
	public double radius() {
		return this.radius;
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
			double newRadius  = this.radius + ((Circle)shape).radius();
			return Math.abs(shape.position()[0] - this.position()[0])  <= newRadius
					&& Math.abs(shape.position()[1] - this.position()[1])  <= newRadius;
					
			//return this.distance(shape) > 0;
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
				int newInd = (i+1)%4;
				result |= (pointsX[i] <= this.position()[0] + this.radius) || (pointsX[i] <= this.position()[0] - this.radius)
					    && (pointsY[i] <= this.position()[0] + this.radius) || (pointsY[i] <= this.position()[0] - this.radius);
				
			}
			return result;
		}
		

		if(shape instanceof Polygon) {
			Polygon polygon = (Polygon) shape;
			return this.distance(polygon) <= 0;
		}
		return false;
	}
	

	@Override
	public double distance(Shape shape) {
		// TODO Auto-generated method stub
		if(shape instanceof Circle) {
			Circle circle = (Circle)shape;
			double radiusSum  = this.radius + circle.radius();
			return this.distPoint(shape.position()[0], shape.position()[1]) - radiusSum;
		}
		if(shape instanceof Rectangle) {
			Rectangle rect = (Rectangle) shape;
			double rectX = rect.position()[0];
			double rectY = rect.position()[1];
			double[] pointsX = new double[] {
					rectX-(rect.width())/2 , rectX+rect.width()/2
			};
			double[] pointsY = new double[] {
					rectY - rect.height()/2, rectY + (rect.height())/2
			};
			double distTopL = this.distPoint(pointsX[0], pointsY[0]);
			double distTopR = this.distPoint(pointsX[1], pointsY[0]);
			double distBotL = this.distPoint(pointsX[0], pointsY[1]);
			double distBotR = this.distPoint(pointsX[1], pointsY[1]);
			
		}
		if(shape instanceof Polygon) {
			Polygon poly = (Polygon)shape;
			return this.distPoint(poly.position()[0], poly.position()[1]) - this.radius;
		}
		return -1;
	}
	
	public double distPoint(double p1X, double p1Y) {
		return Math.sqrt(Math.pow((p1X - this.x), 2) + Math.pow((p1Y - this.y), 2));
	}

	@Override
	public void drawShape(GraphicsContext gc) {
		Paint gcFill = gc.getFill();
		Paint gcStroke = gc.getStroke();
		double gcLineWidth = gc.getLineWidth();
		gc.setFill(rgba);
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(1);
		gc.fillOval(x - radius, y - radius, radius*2, radius*2);
		gc.setFill(gcFill);
		gc.setStroke(gcStroke);
		gc.setLineWidth(gcLineWidth);
	}
	
	/*public void setPosition(double x, double y) {
		this.x = x; 
		this.y = y;
		validPosition();
	}*/
	
	@Override
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
	
}
	
