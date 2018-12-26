package views.ui;

import controllers.UniverseSetting;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import views.Game;
import views.button.AddRectangleButton;
import views.button.LabelledRectangleButton;

public class SettingUniverseScreen extends UserInterface{

	public UniverseSetting universe;
	
	public SettingUniverseScreen(Game game, UniverseSetting universe) {
		
		Text text = new Text(Game.WIDTH/2, 200, "Setting");
		text.setFont(Font.font(80));
		text.setFill(Color.CADETBLUE);
		text.setWrappingWidth(400);
		text.setTranslateX(-text.getWrappingWidth()/2);
		text.setTextAlignment(TextAlignment.CENTER);
		
		this.universe = universe;
		
		Group addbtnGroup = new Group();
		Text textPlayer = new Text(Game.WIDTH/2 -200, 300, "Number of player :");
		textPlayer.setFont(Font.font(40));
		textPlayer.setFill(Color.CADETBLUE);
		textPlayer.setWrappingWidth(400);
		textPlayer.setTranslateX(-textPlayer.getWrappingWidth()/2);
		textPlayer.setTextAlignment(TextAlignment.CENTER);
		
		
		AddRectangleButton addBtn1 = new AddRectangleButton(Game.WIDTH/2 - 200, Game.HEIGHT /2, 100, 100);
		for (int i = 0; i < universe.nbPlayer; i++) {
			double translation = addBtn1.getX() + 120;
			Rectangle rect = new Rectangle(addBtn1.getX(), addBtn1.getY(), 100, 100);
			rect.setFill(universe.color[i]);
			rect.setStroke(Color.BLACK);
			addbtnGroup.getChildren().add(rect);
			addBtn1.relocate(translation, addBtn1.getY());
			addBtn1.setX(translation);
		}
		addBtn1.setOnMouseClicked(new EventHandler<MouseEvent>() {
			double translation;
			@Override
			public void handle(MouseEvent event) {
				translation = addBtn1.getX() + 120;
				Rectangle rect = new Rectangle(addBtn1.getX(), addBtn1.getY(), 100, 100);
				rect.setFill(universe.color[universe.nbPlayer]);
				rect.setStroke(Color.BLACK);
				
				rect.setOnMouseClicked(new EventHandler<MouseEvent>() {
					
					@Override
					public void handle(MouseEvent e) {
						addbtnGroup.getChildren().remove(rect);
						universe.nbPlayer--;
						translation = rect.getX();
						addBtn1.relocate(translation, addBtn1.getY());
						addBtn1.setX(translation);
						addBtn1.setVisible(true);

					}
					
				});
				
				if(addbtnGroup.getChildren().add(rect))
					System.out.println(rect);
				
				
				addBtn1.relocate(translation, addBtn1.getY());
				addBtn1.setX(translation);
				System.out.println("player added");
				universe.nbPlayer++;
				if(universe.nbPlayer == 4)
					addBtn1.setVisible(false);
				else
					addBtn1.setVisible(true);
				
			};
			
		});
		
		CheckBox checkbox = new CheckBox("human player");
		checkbox.relocate( Game.WIDTH/2 -250, Game.HEIGHT/2 - 100 );
		if(universe.humanPlayer)
			checkbox.setSelected(true);
		else
			checkbox.setSelected(false);
		checkbox.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if(checkbox.isSelected())
					universe.humanPlayer = true;
				else
					universe.humanPlayer = false;
			}
		});
		
		addbtnGroup.getChildren().addAll(textPlayer, addBtn1, checkbox);
		
		Group planetbtnGroup = new Group();
		Text textPlanet = new Text(Game.WIDTH/2 -200, addBtn1.getY() + 200, "Number of planet :");
		textPlanet.setFont(Font.font(40));
		textPlanet.setFill(Color.CADETBLUE);
		textPlanet.setWrappingWidth(400);
		textPlanet.setTranslateX(-textPlanet.getWrappingWidth()/2);
		textPlanet.setTextAlignment(TextAlignment.CENTER);
		
		planetbtnGroup.getChildren().add(textPlanet);
		
		LabelledRectangleButton startGame = new LabelledRectangleButton("Start", Game.WIDTH/2, Game.HEIGHT  - 150, 400, 100);
		startGame.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if(universe.nbPlayer > 1) {
					System.out.println("clicked " +  startGame.label);
					game.initGame();
					game.gameRenderer();
				}
				
			}
        });
		
		this.getChildren().addAll(text, addbtnGroup, planetbtnGroup, startGame);
	}
	
}
