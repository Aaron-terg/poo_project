package models.shape;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
/**
 * The class Polygon extends the class Shape and represents a polygon
 * <p> Polygon extends {@link Shape}</p>
 * <p>
 * 	 A polygon has the following properties : 
 * <ul>
 * 		<li>A table of points which represents the coordinates of the polygon's tops</li>
 * 	</li>
 * </p>
 * @author meryl
 * 
 *  @see Shape
 *
 */
public class Polygon extends Shape{

	/**
	 * the position of the vertices of the polygon
	 */
	private double[] x, y; 
	
	
	/**
	 * Triangle constructor
	 */
	public Polygon() {
		this(new double[]{0,18,10}, new double[]{0, 0 ,18});
	}
	
	/**
	 * polygon constructor
	 * @param x an array of x coordinates
	 * @param y an array of y coordinates
	 */
	public Polygon(double[] x, double[] y) {
		super(x[1], y[2]);
		this.x = x;
		this.y = y;
	}

	/***********************************\
	 * 								   *
	 * 				Method			   *
	 * 								   *
	\***********************************/
	
	/**
	 * Draw the shape on the window 
	 * @param the GraphicsContext gc
	 * @param rgb the color of the shape
	 */
	@Override
	public void drawShape(GraphicsContext gc, Color rgb) {
		super.drawShape(gc, rgb);
		
		gc.strokePolygon(x, y, x.length);
		gc.fillPolygon(x, y, x.length);
		
	}

	
	/**
	 * set a point of the polygon at a specified position and calculate the others coordinates new positions;
	 * @param posX : the X coordinate of the new position
	 * @param posY : the Y coordinate of the new position
	 */
	@Override
	public void setPosition(double posX, double posY) {
		double x_int = x[0];
		double y_int = y[0];
		for(int i = 0; i<this.x.length; i++) {
			double distance_x= x_int-x[i];
			double distance_y = y_int-y[i];
			x[i] = posX - distance_x;
			y[i] = posY - distance_y;
		}

	}
	
}
