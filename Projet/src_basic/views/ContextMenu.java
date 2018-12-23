package views;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import models.GameObject;

public class ContextMenu extends UserInterface{

	public ContextMenu(String txt, int nbBtn) {
		super();

		double btnWidth = 200,
				btnHeight = 100,
				centerX = (Game.WIDTH / 2),
				centerY = (Game.HEIGHT /2);
		GameObject textMenu = new GameObject(centerX, centerY -20, 400, btnHeight, "") {
			@Override
			public void render(GraphicsContext gc) {
				gc.setFill(Color.CADETBLUE);
				gc.setStroke(Color.CADETBLUE);
				gc.setTextAlign(TextAlignment.CENTER);
				gc.setFont(Font.font(18));
				gc.fillText(txt, x + width/2 , y +height/2);
				gc.strokeText(txt, x + width/2 , y +height/2);
			}
		};

		GameObject restartBtn = new GameObject(centerX - (btnWidth % (btnWidth * nbBtn))/nbBtn,  centerY+ btnHeight, btnWidth, btnHeight, "Restart") {
			@Override
			public void render(GraphicsContext gc) {
				gc.setFill(Color.BLACK);
				gc.setStroke(Color.CADETBLUE);
				gc.setLineWidth(5);
				super.render(gc);
				gc.setTextAlign(TextAlignment.CENTER);
				gc.setFont(Font.font(18));
				gc.fillText("New Game", x + width/2 , y +height/2, width);
			}
		};
		
		clickable.add(textMenu);
		clickable.add(restartBtn);

		
	}
}
