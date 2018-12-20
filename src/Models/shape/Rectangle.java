package Models.shape;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Rectangle extends Shape{

	private double x, y;
	
	public Rectangle() {
		this.x = 0;
		this.y = 0;
		this.width = 50;
		this.height = 50;
		this.rgb = Color.BLACK;
	}
	public Rectangle(double x, double y, double width, double height, int r, int g, int b, double a) {
		super(width, height, Color.rgb(r, g, b, a));
		this.x = x;
		this.y = y;
		
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
	public void drawShape(GraphicsContext gc) {
		super.drawShape(gc);
		gc.fillRect(x, y, width, height);
	}
	
	public boolean outOfWindow(double frameWidth, double frameHeight) {
		return x+width > frameWidth || x<0 || y+height >frameHeight || y<0;
	}
	
	@Override
	public void setPosition(double posX, double posY) {
		// TODO Auto-generated method stub
		
	}
	

}
