package views;

import java.util.ArrayList;

import models.GameObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class SplashScreen extends UserInterface{
	
	
	public SplashScreen() {
		super();
		
		GameObject btnInit = new GameObject((Game.WIDTH / 2), (Game.HEIGHT /2)- 50, 400, 100, "Init") {
			@Override
			public void render(GraphicsContext gc) {
				gc.setStroke(Color.CADETBLUE);
				gc.setLineWidth(5);
				super.render(gc);
				gc.setTextAlign(TextAlignment.CENTER);
				gc.setFont(Font.font(18));
				gc.fillText("New Game", x + width/2 , y +height/2, 400);
			}
		};
		
		GameObject btnLoad = new GameObject((Game.WIDTH / 2), (Game.HEIGHT /2) + 100, 400, 100, "Continue") {
			@Override
			public void render(GraphicsContext gc) {
				gc.setStroke(Color.CADETBLUE);
				gc.setLineWidth(5);
				super.render(gc);
				gc.setTextAlign(TextAlignment.CENTER);
				gc.setFont(Font.font(18));
				gc.fillText("Load Game", x + width/2 , y +height/2, 400);
			}
		};
		
		this.clickable.add(btnInit);
		this.clickable.add(btnLoad);
		
		
	}
}
