package Models.shape;

import javafx.scene.canvas.GraphicsContext;

public interface Shape {
	public double[] position();
	public boolean intersects(Shape shape);
	public void drawShape(GraphicsContext gc);
	public void validPosition();
	
}
