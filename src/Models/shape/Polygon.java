package Models.shape;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Polygon extends Shape {

	private double headX, headY;
	private double[] x, y; 

	
	
	/**
	 * Triangle constructor
	 */
	public Polygon() {
		this(new double[]{100,112.5,125}, new double[]{50, 75 ,50}, Color.BLACK);
	}
	
	public Polygon(double[] x, double[] y) {
		for(int i = 0; i<x.length; i++) {
				this.x[i]= x[i];
				this.y[i]= y[i];
		}
	}
	
	public Polygon(double[] x, double[] y, Color rgb) {
		super(rgb);
		this.x = x;
		this.headX = x[1];
		this.y = y;
		this.headY = y[1];
	}
	
	/**
	 * Getter && setter
	 */
	
	public double[] getX() {
		return x;
	}

	public double[] getY() {
		return y;
	}
	public double getHeadX() {
		return this.headX;
	}
	public double getHeadY() {
		return this.headY;
	}
	public void setHeadXY(double headX, double headY) {
		this.headX = headX;
		this.headY= headY;
	}

	public double[] position() {
		double[] position = {this.headX, this.headY};
		return position;
	}
	
	
	public void drawShape(GraphicsContext gc) {
		super.drawShape(gc);
		gc.fillPolygon(x, y, x.length);;
		
	}

	
	public boolean outOfWindow(double frameWidth, double frameHeight) {
			return(this.x[0]>frameWidth ||this.y[0]>frameHeight ||this.x[0]<0 ||this.y[0]<0 ||
					this.x[1]>frameWidth ||this.y[1]>frameHeight ||this.x[1]<0 ||this.y[1]<0 ||
					this.x[2]>frameWidth ||this.y[2]>frameHeight ||this.x[2]<0 ||this.y[2]<0 );
	
	}
	public boolean no_superimposed(Shape s) {
		return true;
	}
	public void rgb(Color rgb) {
		
	}
	public void moveTo(double pos_x, double pos_y) {
		double x_int = x[0];
		double y_int = y[0];
		for(int i = 0; i<this.x.length; i++) {
			double distance_x= x_int-x[i];
			double distance_y = y_int-y[i];
			x[i] = pos_x + distance_x;
			y[i]= pos_y+distance_y;
		}

	}
	

	

}
