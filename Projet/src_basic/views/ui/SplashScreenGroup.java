package views.ui;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import views.Game;
import views.button.LabelledRectangleButton;

public class SplashScreenGroup extends UserInterface {

	
	public SplashScreenGroup(Game game) {
		Text text = new Text(Game.WIDTH/2, Game.HEIGHT/4, "The Game");
		text.setFont(Font.font(80));
		text.setFill(Color.CADETBLUE);
		text.setWrappingWidth(400);
		text.setTranslateX(-text.getWrappingWidth()/2);
		text.setTextAlignment(TextAlignment.CENTER);
		
		
		LabelledRectangleButton initButton = new LabelledRectangleButton("start game", Game.WIDTH/2, Game.HEIGHT/2, 400, 100);
		initButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				System.out.println("clicked " +  initButton.label);
				game.gameSetting();
			}
        });
        
        
		LabelledRectangleButton loadButton = new LabelledRectangleButton("load game", Game.WIDTH/2 , Game.HEIGHT/2 + 150, 400, 100);
        loadButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				System.out.println("clicked " +  loadButton.label);
				game.loadGame();
				
			}
        });
        
        this.getChildren().addAll(text, initButton,loadButton);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String msg = "";
		for (int i = 0; i < this.getChildren().size(); i++) {
			msg += this.getChildren().get(i) + "\n";
			
		}
		return msg;
	}
}
