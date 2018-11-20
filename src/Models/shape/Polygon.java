package Models.shape;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Polygon implements Shape{

	private double headX, headY;
	private double[] x, y; 
	private Color rgba;
	
	public Polygon() {
		this.x = new double[]{100,150,100};
		this.headX = x[0];
		this.y = new double[]{50,100,25};
		this.headY = y[0];
		this.rgba = Color.ALICEBLUE;
	}
	
	public double[] getX() {
		return x;
	}

	public double[] getY() {
		return y;
	}

	@Override
	public double[] position() {
		double[] position = {this.headX, this.headY};
		return position;
	}

	@Override
	public boolean intersects(Shape shape) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public double distance(Shape shape) {
		// TODO Auto-generated method stub
		if(shape instanceof Circle) {
			Circle circle = (Circle)shape;
		}
		if(shape instanceof Rectangle) {
			
		}
		if(shape instanceof Polygon) {
			
		}
		return -1;
	}

	@Override
	public void drawShape(GraphicsContext gc) {

		Paint gcFill = gc.getFill();
		Paint gcStroke = gc.getStroke();
		double gcLineWidth = gc.getLineWidth();
		gc.setFill(rgba);
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(1);
		gc.fillPolygon(x, y, x.length);;
		gc.setFill(gcFill);
		gc.setStroke(gcStroke);
		gc.setLineWidth(gcLineWidth);
	}
	public void validPosition(double frameWidth, double frameHeight) {
		
	}

}
