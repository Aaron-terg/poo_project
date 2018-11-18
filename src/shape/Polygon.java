package shape;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Polygon implements Shape{

	private double headX, headY;
	private double[] x, y; 
	private Color rgba;
	
	@Override
	public double[] position() {
		double[] position = {this.headX, this.headY};
		return position;
	}

	@Override
	public boolean intersects(Shape shape) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void render(GraphicsContext gc) {

		Paint gcFill = gc.getFill();
		Paint gcStroke = gc.getStroke();
		double gcLineWidth = gc.getLineWidth();
		gc.setFill(rgba);
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(1);
		gc.fillPolygon(x, y, x.length);;
		gc.setFill(gcFill);
		gc.setStroke(gcStroke);
		gc.setLineWidth(gcLineWidth);
	}

}
