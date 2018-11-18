package shape;

import javafx.scene.canvas.GraphicsContext;

public interface Shape {
	
	public double[] position();
	public boolean intersects(Shape shape);
	public void render(GraphicsContext gc);
}
