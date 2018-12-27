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
import views.button.RemoveRectangleButton;

public class SettingUniverseScreen extends UserInterface{

	public UniverseSetting universe;
	
	public SettingUniverseScreen(Game game, UniverseSetting universe) {
		
		this.universe = universe;

		// title
		Text title = new Text(Game.WIDTH/2, 200, "Setting");
		title.setFont(Font.font(80));
		title.setFill(Color.CADETBLUE);
		title.setWrappingWidth(400);
		title.setTranslateX(-title.getWrappingWidth()/2);
		title.setTextAlignment(TextAlignment.CENTER);
		
		// number of player button group
		Group addbtnGroup = new Group();
		Text textPlayer = new Text(Game.WIDTH/2 -200, 300, "Number of player :");
		AddRectangleButton addBtn1 = new AddRectangleButton(Game.WIDTH/2 - 200, Game.HEIGHT /2, 100, 100);
		
		// number of planet button group
		Group planetbtnGroup = new Group();
		Text textPlanet = new Text(Game.WIDTH/2 -200, addBtn1.getY() + 200, "Number of planet :");
		RemoveRectangleButton rmBtn = new RemoveRectangleButton(textPlanet.getX() + 300, textPlanet.getY() , 50, 50);
		AddRectangleButton addBtn2 = new AddRectangleButton(textPlanet.getX() + 500, textPlanet.getY() , 50, 50);
		Text nbplanet = new Text(textPlanet.getX() + 400, textPlanet.getY(), ""+ universe.nbPlanet );
		
		// subtitle player setting
		textPlayer.setFont(Font.font(40));
		textPlayer.setFill(Color.CADETBLUE);
		textPlayer.setWrappingWidth(400);
		textPlayer.setTranslateX(-textPlayer.getWrappingWidth()/2);
		textPlayer.setTextAlignment(TextAlignment.CENTER);
		
		// making of box for the default number of player
		for (int i = 0; i < universe.nbPlayer; i++) {
			double translation = addBtn1.getX() + 120;
			Rectangle rect = new Rectangle(addBtn1.getX(), addBtn1.getY(), 100, 100);
			rect.setFill(universe.color[i]);
			rect.setStroke(Color.BLACK);
			addbtnGroup.getChildren().add(rect);
			addBtn1.relocate(translation, addBtn1.getY());
			addBtn1.setX(translation);
		}
		
		// adding of a player/box
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
						if(universe.nbPlayer < universe.nbPlanet) {
							rmBtn.setVisible(true);
						}else
							rmBtn.setVisible(false);
					}
					
				});
				
				// add a new rectangle to the group
				addbtnGroup.getChildren().add(rect);
				addBtn1.relocate(translation, addBtn1.getY());
				addBtn1.setX(translation);
				System.out.println("player added");
				universe.nbPlayer++;
				if(universe.nbPlayer == 4)
					addBtn1.setVisible(false);
				else
					addBtn1.setVisible(true);
				
				if(universe.nbPlayer >= universe.nbPlanet) {
					rmBtn.setVisible(false);
					universe.nbPlanet = universe.nbPlayer;
					nbplanet.setText("" + universe.nbPlayer);
				}else
					rmBtn.setVisible(true);
			};
			
		});
		
		// check if there is a user
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
		
		// subtitle planet setting 
		textPlanet.setFont(Font.font(40));
		textPlanet.setFill(Color.CADETBLUE);
		textPlanet.setWrappingWidth(400);
		textPlanet.setTranslateX(-textPlanet.getWrappingWidth()/2);
		textPlanet.setTextAlignment(TextAlignment.CENTER);
		
		// displayer of current number
		nbplanet.setFont(Font.font(20));
		nbplanet.setWrappingWidth(400);
		nbplanet.setTranslateX(-textPlanet.getWrappingWidth()/2);
		nbplanet.setTextAlignment(TextAlignment.CENTER);
		
		//decreasing planet number button controller
		rmBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				if(universe.nbPlanet > universe.nbPlayer) {
					universe.nbPlanet--;
					nbplanet.setText("" + universe.nbPlanet);
				}
				if(universe.nbPlanet == universe.nbPlayer)
					rmBtn.setVisible(false);
				else 
					rmBtn.setVisible(true);

			}
		});
		
		// increasing planet number button controller
		addBtn2.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				if(universe.nbPlanet < 15) {
					universe.nbPlanet++;
					nbplanet.setText("" + universe.nbPlanet);
				}
				if(universe.nbPlanet == 15)
					addBtn2.setVisible(false);
				else 
					addBtn2.setVisible(true);

				if(universe.nbPlanet == universe.nbPlayer)
					rmBtn.setVisible(false);
				else 
					rmBtn.setVisible(true);
			}
		});
		
		planetbtnGroup.getChildren().addAll(textPlanet,nbplanet, addBtn2, rmBtn);
	
		// start the game when the setting is done
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
		
		this.getChildren().addAll(title, addbtnGroup, planetbtnGroup, startGame);
	}
	
}