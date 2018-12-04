package Models.shape;


import Models.planet.Planet;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Polygon{

	private double[] x, y; 
	private Color rgb;

	
	
	/**
	 * Triangle constructor
	 */
	public Polygon() {
		this(new double[]{0,20,10}, new double[]{0, 0 ,20},Color.BLACK);
	}
	
	public Polygon(double[] x, double[] y) {
		for(int i = 0; i<x.length; i++) {
				this.x[i]= x[i];
				this.y[i]= y[i];
		}
	}
	
	public Polygon(double[] x, double[] y, Color rgb) {
		this.rgb = rgb;
		this.x = x;
		this.y = y;
	}
	public Polygon(Polygon polygon) {
		this(polygon.x, polygon.y, polygon.rgb);
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
	public void setColor(Color rgb) {
		this.rgb = rgb;
	}

	public void drawShape(GraphicsContext gc) {
		gc.setFill(rgb);
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(1);
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
	public void moveTo(double pos_x, double pos_y) {
		double x_int = x[1];
		double y_int = y[1];
		for(int i = 0; i<this.x.length; i++) {
			double distance_x= Math.abs(x_int-x[i]);
			double distance_y = Math.abs(y_int-y[i]);
			x[i] = pos_x - distance_x;
			y[i]= pos_y-distance_y;
		}

	}
	public boolean inInside(Planet planet) {
		return planet.getPlanetShape().isInside(this.getX()[0], this.getY()[0])
				||planet.getPlanetShape().isInside(this.getX()[1], this.getY()[1])
				|| planet.getPlanetShape().isInside(this.getX()[2], this.getY()[2]);
		
	}
	

	

}
