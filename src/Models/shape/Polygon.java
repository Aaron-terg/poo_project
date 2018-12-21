package Models.shape;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
/**
 * The class Polygon extends the class Shape and represents a polygon
 * <p>
 * 	 A polygon has the following properties : 
 * <ul>
 * 		<li>A table of points which represent the coordinates of the polygon's tops</li>
 * 	</li>
 * </p>
 * @author virginie
 *
 */
public class Polygon extends Shape{

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
	}
	
	public Polygon(double[] x, double[] y, Color rgb) {
		super(x[1], y[2], rgb);
		this.x = x;
		this.y = y;
	}
	public Polygon(Polygon polygon) {
		this(polygon.x, polygon.y, polygon.rgb());
	}
	/***********************************\
	 * 								   *
	 * 			Getter && Setter	   *
	 * 								   *
	\***********************************/
	
	/**
	 * 
	 * @return the table of X polygon's coordinates
	 */
	public double[] getX() {
		return x;
	}
	/**
	 * 
	 * @return the table of Y polygon's coordinates
	 */
	public double[] getY() {
		return y;
	}

	/***********************************\
	 * 								   *
	 * 				Method			   *
	 * 								   *
	\***********************************/
	@Override
	public void drawShape(GraphicsContext gc) {
		super.drawShape(gc);
		gc.fillPolygon(x, y, x.length);;
		
	}


	@Override
	public double[] position() {
		return (new double[]{this.x[0],y.clone()[0]});
	}
	
	@Override
	/**
	 * set a point of the polygon at a specified position and calculate the others new positions;
	 * @param posX : the X coordinate of the new position
	 * @param posY : the Y coordinate of the new position
	 */
	public void setPosition(double posX, double posY) {
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
