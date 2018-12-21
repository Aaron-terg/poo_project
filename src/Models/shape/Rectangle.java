package Models.shape;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Rectangle extends Shape{

	private double x, y;
	
	public Rectangle() {
		super(50,50);
		this.x = 0;
		this.y = 0;
	}
	public Rectangle(double x, double y, double width, double height) {
		super(width, height);
		this.x = x;
		this.y = y;
		
	}
	
	/***********************************\
	 * 								   *
	 * 			Getter/Setter		   *
	 * 								   *
	\***********************************/

	
	@Override
	public double[] position() {
		double[] position = {this.x, this.y};
		// Set the position to the center of the rectangle.
		position[0] += width/2;
		position[1] += height/2;
		return position;
	}

	@Override
	public void drawShape(GraphicsContext gc, Color rgb) {
		super.drawShape(gc, rgb);
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
