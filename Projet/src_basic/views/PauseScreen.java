package views;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import models.GameObject;

public class PauseScreen extends ContextMenu{

	public PauseScreen(String txt, int nbBtn) {
		super(txt, nbBtn);
		
		double btnWidth = 200,
				btnHeight = 100,
				centerX = (Game.WIDTH / 2),
				centerY = (Game.HEIGHT /2);
		
		GameObject saveBtn = new GameObject(centerX + (btnWidth % (btnWidth*nbBtn)) /nbBtn, centerY + btnHeight, btnWidth, btnHeight, "Save") {
			@Override
			public void render(GraphicsContext gc) {
				gc.setFill(Color.BLACK);
				gc.setStroke(Color.CADETBLUE);
				gc.setLineWidth(5);
				super.render(gc);
				gc.setTextAlign(TextAlignment.CENTER);
				gc.setFont(Font.font(18));
				gc.fillText("Save Game", x + width/2 , y +height/2, width);
			}
		};
		
		clickable.add(saveBtn);
				
	}
}
