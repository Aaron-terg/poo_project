package views.button;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 * <b>A javafx "plus" button with custom property</b>
 * <p>extend {@link RectangleButton}</p>
 * 
 * @author meryl
 * @see AddRectangleButton
 * @see RemoveRectangleButton 
 * @version src_advanced
 */
public class AddRectangleButton extends RectangleButton {

	/**
	 * create a rectangle with current parameters
	 * and draw a "plus" in the rectangle
	 * @param x the x position
	 * @param y the y position
	 * @param w the width of the rectangle
	 * @param h the height of the rectangle
	 */
	public AddRectangleButton(double x, double y, double w, double h) {
		super(x, y, w, h);
		
		Line line1 = new Line(x, this.y + 5, x, this.y + h - 5);
		line1.setStroke(Color.CADETBLUE);
		line1.setStrokeWidth(5);
		Line line2 = new Line(this.x + 5, y, this.x +w -5 , y);
		line2.setStroke(Color.CADETBLUE);
		line2.setStrokeWidth(5);
		
		this.getChildren().addAll(line1, line2);
	}

	
}
