package models.shape;

import java.io.Serializable;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
/**
 * The line making the joints between the selected gameobject and the position underneath the cursor.
 * 
 * @see Shape
 * @author meryl
 * @version src_basic
 */
public class Line extends Shape implements Serializable{
	/**
	 * the position of the shape
	 */
	private double x, y;
	/**
	 * Line constructor
	 * @param originX
	 * @param originY
	 */
	public Line(double originX, double originY) {
		this(originX, originY, originX, originY);
	}
	/**
	 * Line constructor 
	 * @param originX 
	 * @param originY
	 * @param x : the coordinate of a new point
	 * @param y : the coordinate of a new point
	 */
	public Line(double originX, double originY, double x, double y) {
		super(x, y);
		this.x = originX;
		this.y = originY;
	}
	
	/***********************************\
	 * 								   *
	 * 			Method				   *
	 * 								   *
	\***********************************/
	@Override
	public double[] position() {
		return new double[] {width, height};
	}

	@Override
	public void setPosition(double posX, double posY) {
		this.width += posX -this.width;
		this.height += posY - this.height;
	}

	@Override
	public void drawShape(GraphicsContext gc, Color rgb) {
		super.drawShape(gc, rgb);
		gc.setLineWidth(2f);
		gc.strokeLine(x, y, width, height);
		gc.setLineWidth(1f);
	}


}
