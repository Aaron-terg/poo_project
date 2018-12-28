package views.button;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 * <b>A javafx "minus" button with custom property</b>
 * <p>extend {@link RectangleButton}</p>
 * 
 * @author meryl
 * @see AddRectangleButton
 * @see RemoveRectangleButton 
 * @version src_advanced
 */
public class RemoveRectangleButton extends RectangleButton {

	/**
	 * create a rectangle with current parameters
	 * and draw a "minus" in the rectangle
	 * @param x the x position
	 * @param y the y position
	 * @param w the width of the rectangle
	 * @param h the height of the rectangle
	 */
	public RemoveRectangleButton(double x, double y, double w, double h) {
		super(x, y, w, h);
		
		Line line2 = new Line(this.x + 5, y, this.x +w -5 , y);
		line2.setStroke(Color.CADETBLUE);
		line2.setStrokeWidth(5);
		
		this.getChildren().addAll(line2);
	}
}
