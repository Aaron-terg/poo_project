package views.button;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class RemoveRectangleButton extends RectangleButton {

	public RemoveRectangleButton(double x, double y, double w, double h) {
		super(x, y, w, h);
		
		Line line2 = new Line(this.x + 5, y, this.x +w -5 , y);
		line2.setStroke(Color.CADETBLUE);
		line2.setStrokeWidth(5);
		
		this.getChildren().addAll(line2);
	}
}
