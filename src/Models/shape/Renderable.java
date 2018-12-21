package Models.shape;

import javafx.scene.canvas.GraphicsContext;
/**
 * The class implementing the Renderable interface can have access to the render utilities
 * 
 * @author meryl
 * @since src_basic
 *
 */
public interface Renderable {
	/**
	 * The render function to be implemented.
	 * 
	 * @param gc a graphic context target
	 */
	public void render(GraphicsContext gc);
}
