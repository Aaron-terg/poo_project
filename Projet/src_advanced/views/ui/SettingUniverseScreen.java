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
import views.button.RectangleButton;
import views.button.RemoveRectangleButton;

/**
 * <b>The setting screen</b>
 * <p>Here you can set the number  of player / planet and choose if you want to play or just watch the AI fighting each other</b>
 * @author meryl
 *
 * @version src_advanced
 */
public class SettingUniverseScreen extends UserInterface{

	/**
	 * Create a group of button linked to the universe setting. Every action on one of the button modify the current setting.
	 * 
	 * @param game the current game
	 * @param universe the universe setting to tickle with
	 * @see Game
	 * @see UniverseSetting
	 */
	public SettingUniverseScreen(Game game, UniverseSetting universe) {
		super();

		// title
		Text title = new Text(Game.WIDTH/2, 200, "Setting");
		title.setFont(Font.font(80));
		title.setFill(Color.CADETBLUE);
		title.setWrappingWidth(400);
		title.setTranslateX(-title.getWrappingWidth()/2);
		title.setTextAlignment(TextAlignment.CENTER);
		
		// number of player button group
		Group addbtnGroup = new Group();
		Text textPlayer = new Text(Game.WIDTH/2 -200, title.getY() + 100, "Number of player :");
		AddRectangleButton addBtn1 = new AddRectangleButton(Game.WIDTH/2 - 200, textPlayer.getY() + 100, 100, 100);
		
		// check if there is a user
		CheckBox checkbox = new CheckBox("human player");
		checkbox.relocate( addBtn1.getX(),addBtn1.getY() + 150 );
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
				
		// number of planet button group
		Group planetbtnGroup = new Group();
		Text textPlanet = new Text(Game.WIDTH/2 -200, checkbox.getLayoutY() + 100, "Number of planet :");
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
				
				int maxPlayer = 4;
				// add a new rectangle to the group
				addbtnGroup.getChildren().add(rect);
				addBtn1.relocate(translation, addBtn1.getY());
				addBtn1.setX(translation);
				System.out.println("player added");
				universe.nbPlayer++;
				if(universe.nbPlayer == maxPlayer)
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
				int maxPlanet = 15;
				if(universe.nbPlanet < maxPlanet) {
					universe.nbPlanet++;
					nbplanet.setText("" + universe.nbPlanet);
				}
				if(universe.nbPlanet == maxPlanet)
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
		RectangleButton startGame = new RectangleButton("Start", Game.WIDTH/2, textPlanet.getY() + 200, 400, 100);
		startGame.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if(universe.nbPlayer > 1) {
					game.initGame();
					game.gameRenderer();
				}
				
			}
        });
		
		this.getChildren().addAll(title, addbtnGroup, planetbtnGroup, startGame);
	}
	
}
