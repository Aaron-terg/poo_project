package Models.shape;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Polygon implements Shape{

	private double headX, headY;
	private double[] x, y; 
	private Color rgb;
	
	public Polygon() {
		this(new double[]{100,112.5,125}, new double[]{50, 75 ,50}, Color.BLACK);
	}
	
	public Polygon(double[] x, double[] y,int[] rgb) {
		this(x, y, Color.rgb(rgb[0], rgb[1], rgb[2]));
	}
	
	public Polygon(double[] x, double[] y, Color rgb) {
		this.x = x;
		this.headX = x[1];
		this.y = y;
		this.headY = y[1];
		this.rgb = rgb;
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
		gc.setFill(rgb);
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
