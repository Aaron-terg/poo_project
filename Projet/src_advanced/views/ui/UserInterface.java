package views.ui;

import java.util.Iterator;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import views.Game;
import views.button.RectangleButton;

/**
 * The main user interface containing the basis of an canvas
 * 
 * @author meryl
 *
 * @version src_advanced
 * @since src_basic
 */
public class UserInterface extends Group{

	/**
	 * The default {@link UserInterface} constructor
	 */
	public UserInterface() {};
	
	/**
	 * The splash screen displayed at the beginning.
	 * @param game the current game
	 */
	public UserInterface(Game game) {
		Text text = new Text(Game.WIDTH/2, Game.HEIGHT/4, "The Game");
		text.setFont(Font.font(80));
		text.setFill(Color.CADETBLUE);
		text.setWrappingWidth(400);
		text.setTranslateX(-text.getWrappingWidth()/2);
		text.setTextAlignment(TextAlignment.CENTER);
		
		
		RectangleButton initButton = new RectangleButton("start game", Game.WIDTH/2, Game.HEIGHT/2, 400, 100);
		initButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				game.gameSetting();
			}
        });
        
        
		RectangleButton loadButton = new RectangleButton("load game", Game.WIDTH/2 , Game.HEIGHT/2 + 150, 400, 100);
        loadButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				game.loadGame();
				
			}
        });
        
        this.getChildren().addAll(text, initButton,loadButton);
	}
	
	/**
	 * The user interface containing the basic structure of the interface
	 * 
	 * @param txt a String containing the title of the srceen 
	 * @param clickable the group to be add to the ui
	 * @param nbBtn number of button passed on arguments
	 */
	public UserInterface(String txt, ObservableList<Node> clickable, int nbBtn) {
		
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

		for (Iterator<Node> btnit = clickable.iterator(); btnit.hasNext();) {
			Node node = (Node) btnit.next();
			node.relocate(centerX -btnWidth,  centerY - btnHeight + ((btnHeight * nbBtn))  + 25 * (nbBtn -1));
			nbBtn--;
		}
		
		this.getChildren().add(textMenu);
		this.getChildren().addAll(clickable);
	}
	
	/**
	 * exchange the current group children with the new one
	 * @param clickable the group to be added to the ui
	 */
	public void refreshInterface(ObservableList<Node> clickable) {
		if(this.getChildren() != null) {
			int size = this.getChildren().size();
			this.getChildren().remove(0, size);
		}	
		this.getChildren().addAll(clickable);
	}
}
