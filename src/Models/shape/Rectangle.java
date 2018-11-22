package Models.shape;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Rectangle implements Shape{

	private double x, y;
	private double width, height;
	private double maxWidth, maxHeight;
	private Color rgba;
	
	
	public Rectangle() {
		this.x = 0;
		this.y = 0;
		this.width = 50;
		this.height = 50;
		this.rgba = Color.BLACK;
	}
	public Rectangle(double x, double y, double width, double height, int r, int g, int b, double a) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.rgba = Color.rgb(r, g, b, a);
	}
	
	public Rectangle(double x, double y, double width, double height, int r, int g, int b) {
		this(x, y, width, height, r, g, b, 1);
	}
	
	public Rectangle(double x, double y, double width, double height, int[] rgb) {
		this(x, y, width, height, rgb[0], rgb[1], rgb[2], 1);
	}
	/***********************************\
	 * 								   *
	 * 			Getter/Setter		   *
	 * 								   *
	\***********************************/

	public double width() {
		return width;
	}
	public double height() {
		return height;
	}
	
	@Override
	public double[] position() {
		double[] position = {this.x, this.y};
		// Set the position to the center of the rectangle.
		position[0] += width/2;
		position[1] += height/2;
		return position;
	}

	@Override
	public boolean intersects(Shape shape) {
		// TODO Auto-generated method stub
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
		gc.fillRect(x, y, width, height);
		gc.setFill(gcFill);
		gc.setStroke(gcStroke);
		gc.setLineWidth(gcLineWidth);
	}
	
	public void validPosition() {
		
		}
	public boolean no_superimposed(Shape s) {
		return true;
	}

}
