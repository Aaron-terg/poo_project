package Models.shape;

import Models.Spaceship.SpaceshipType;
import javafx.scene.canvas.GraphicsContext;

/**
 * <b>The shape interface contain basic function of a shape</b>
 * 
 * @see Planet
 * @see SpaceshipType
 * @author meryl
 * @version 1.2
 */
public interface Shape {
	public double[] position();
	public boolean intersects(Shape shape);
	public void drawShape(GraphicsContext gc);
	public void validPosition();
	
}
