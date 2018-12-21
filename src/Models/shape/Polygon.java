package Models.shape;


import Models.planet.Planet;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Polygon extends Shape{
	private double headX, headY;
	private double[] x, y; 
	
	
	/**
	 * Triangle constructor
	 */
	public Polygon() {
		this(new double[]{0,20,10}, new double[]{0, 0 ,20},Color.BLACK);
	}
	
	public Polygon(double x, double y) {
		this(new double[] {x-12.5, x+12.5, x},
				new double[] {y-25, y-25, y},
				Color.BLACK);
		this.headX = x;
		this.headY = y;
	}
	
	public Polygon(double[] x, double[] y, Color rgb) {
		super(x[1], y[2], rgb);
		this.x = x;
		this.y = y;
	}
	public Polygon(Polygon polygon) {
		this(polygon.x, polygon.y, polygon.rgb());
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

	public void drawShape(GraphicsContext gc) {
		super.drawShape(gc);
//		this.x = new double[] {this.headX-12.5, this.headX, this.headX+12.5};
//		this.y = new double[] {this.headY-25, this.headY, this.headY+25};
		gc.fillPolygon(x, y, x.length);;
		
	}

	
	public boolean outOfWindow(double frameWidth, double frameHeight) {
			return(this.x[0]>frameWidth ||this.y[0]>frameHeight ||this.x[0]<0 ||this.y[0]<0 ||
					this.x[1]>frameWidth ||this.y[1]>frameHeight ||this.x[1]<0 ||this.y[1]<0 ||
					this.x[2]>frameWidth ||this.y[2]>frameHeight ||this.x[2]<0 ||this.y[2]<0 );
	
	}

	@Override
	public double[] position() {
		// TODO Auto-generated method stub
		return new double[] {headX, headY};
	}
	
	@Override
	public void setPosition(double posX, double posY) {
//		this.headX = posX;
//		this.headY = posY;
		double x_int = x[0];
		double y_int = y[0];
		for(int i = 0; i<this.x.length; i++) {
			double distance_x= x_int-x[i];
			double distance_y = y_int-y[i];
			x[i] = posX + distance_x;
			y[i]= posY+distance_y;
		}

	}
	
}
