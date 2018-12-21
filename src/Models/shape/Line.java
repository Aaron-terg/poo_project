package Models.shape;

import java.io.Serializable;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Line extends Shape implements Serializable{
	
	private double x, y;
	
	public Line(double originX, double originY) {
		this(originX, originY, originX, originY);
	}
	
	public Line(double originX, double originY, double x, double y) {
		super(x, y);
		this.x = originX;
		this.y = originY;
	}
	
	@Override
	public double[] position() {
		return new double[] {width, height};
	}

	@Override
	public void setPosition(double posX, double posY) {
		this.width += posX -this.width;
		this.height += posY - this.height;
	}
	
	public void drawShape(GraphicsContext gc, Color rgb) {
		super.drawShape(gc, rgb);
		gc.setLineWidth(2f);
		gc.strokeLine(x, y, width, height);
		gc.setLineWidth(1f);
	}

}
