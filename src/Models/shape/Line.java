package Models.shape;

import java.io.Serializable;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Line extends Shape implements Serializable{
	
	private double x, y;
	
	public Line(double originX, double originY) {
		this(originX, originY, originX, originY, Color.BLACK);
	}
	
	public Line(double originX, double originY, double x, double y) {
		this(originX, originY, x, y, Color.BLACK);
	}
	
	public Line(double originX, double originY, double x, double y, Color rgb) {
		super(x, y, rgb);
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
	
	public void drawShape(GraphicsContext gc) {
		super.drawShape(gc);
		gc.setLineWidth(2f);
		gc.strokeLine(x, y, width, height);
		gc.setLineWidth(1f);
	}

}
