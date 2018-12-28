package views.button;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class RectangleButton extends Group{
	
	protected double x;
	protected double y;

	public RectangleButton(double x, double y, double w, double h) {
		this.x = x - w/2;
		this.y = y - h/2;
		
		Rectangle rectBtn = new Rectangle(this.x , this.y, w, h);
		rectBtn.setStroke(Color.CADETBLUE);
		rectBtn.setStrokeWidth(5);
		rectBtn.setFill(Color.TRANSPARENT);
		this.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				setCursor(Cursor.HAND);
			}

		});
        this.getChildren().add(rectBtn);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}
	

}
