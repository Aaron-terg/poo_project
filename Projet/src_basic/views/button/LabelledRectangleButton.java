package views.button;

import javafx.geometry.VPos;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class LabelledRectangleButton extends RectangleButton {

	public String label;
	
	public LabelledRectangleButton(String label, double x, double y, double w, double h) {
		super(x, y, w, h);
		this.label = label;
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

}
