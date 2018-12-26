package views.ui;

import java.util.Iterator;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import views.Game;
import views.button.LabelledRectangleButton;

public class InterfaceMenu extends UserInterface{

	public InterfaceMenu(String txt, ObservableList<Node> btn, int nbBtn) {
		super();

		double btnWidth = 200,
				btnHeight = 100,
				centerX = (Game.WIDTH / 2),
				centerY = (Game.HEIGHT /2);
		Text textMenu = new Text(centerX, centerY - btnHeight, txt);
		textMenu.setFont(Font.font(50));
		textMenu.setFill(Color.CADETBLUE);
		textMenu.setWrappingWidth(400);
		textMenu.setTranslateX(-textMenu.getWrappingWidth()/2);
		textMenu.setTextAlignment(TextAlignment.CENTER);

		for (Iterator btnit = btn.iterator(); btnit.hasNext();) {
			Node node = (Node) btnit.next();
			node.relocate(centerX -btnWidth,  centerY - btnHeight + ((btnHeight * nbBtn))  + 25 * (nbBtn -1));
			nbBtn--;
		}
		
		
		this.getChildren().add(textMenu);
		this.getChildren().addAll(btn);
		
	}
}
