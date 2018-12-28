package views.button;

import javafx.event.EventHandler;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * <b>A javafx button with a custom rectangle</b>
 * 
 * @author meryl
 * @see AddRectangleButton
 * @see RemoveRectangleButton 
 * @version src_advanced
 */
public class RectangleButton extends Group{
	
	/**
	 * Position of the rectangle button
	 */
	protected double x, y;

	/**
	 * create a rectangle with current parameters
	 * 
	 * @param x the x position
	 * @param y the y position
	 * @param w the width of the rectangle
	 * @param h the height of the rectangle
	 */
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
	
	/**
	 * create a rectangle with current parameters and a label inside it.
	 * 
	 * @param label String to display inside the rectangle
	 * @param x the x position
	 * @param y the y position
	 * @param w the width of the rectangle
	 * @param h the height of the rectangle
	 */
	public RectangleButton(String label, double x, double y, double w, double h) {
		this(x, y, w, h);
        Text text = new Text(this.x ,this.y  +50, label);
        text.setFont(new Font(18));
        text.setWrappingWidth(w);
        text.maxWidth(w);
        text.maxHeight(h);
        text.setTextAlignment(TextAlignment.CENTER);
        text.setTextOrigin(VPos.CENTER);
        text.setFill(Color.BLACK);
        
        this.getChildren().add(text);

	}

	/***********************************\
	 * 								   *
	 * 			Getter/Setter		   *
	 * 								   *
	\***********************************/
	
	/**
	 * @return the x coordinate
	 */
	public double getX() {
		return x;
	}

	/**
	 * @return the y coordinate
	 */
	public double getY() {
		return y;
	}

	/**
	 * @param x the new x position
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * @param y the new y position
	 */
	public void setY(double y) {
		this.y = y;
	}
	

}
