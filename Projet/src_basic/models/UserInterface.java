package models;

import java.util.ArrayList;
import java.util.Iterator;

import models.shape.Renderable;
import views.Game;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * The main user interface containing the basis of an ui
 * 
 * @author meryl
 *
 */
public class UserInterface implements Renderable{

	/**
	 * An arrayList of {@link GameObject} used as button
	 */
	protected ArrayList<GameObject> clickable;
	
	/**
	 * The default {@link UserInterface} constructor adding an exit button to the clickable arrayList
	 */
	public UserInterface() {
		clickable = new ArrayList<GameObject>();
		GameObject btnClose = new GameObject(Game.WIDTH - 20 - 50 , 50, 50,50, "Exit") {
			@Override
			public void render(GraphicsContext gc) {
				// TODO Auto-generated method stub
				gc.setStroke(Color.CADETBLUE);
				gc.setLineWidth(1);
				super.render(gc);
				gc.strokeLine(x + 5 , y +5, x +width -5,y + height -5);
				gc.strokeLine(x + width -5,  y + 5, x +5, y + height -5);
			}
		};
		this.clickable.add(btnClose);

	}
	
	/**
	 * The user interface containing the basis
	 * @param clickable
	 */
	public UserInterface(ArrayList<GameObject> clickable) {
		this.clickable = clickable;
	}
	
	/**
	 * Getter of the clickable arrayList
	 * @see UserInterface#clickable
	 * @return clickable the arrraylist of clickable object
	 */
	public ArrayList<GameObject> getClickable() {
		return clickable;
	}

	/**
	 * renderer of the ui
	 */
	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		for (Iterator clickIt = clickable.iterator(); clickIt.hasNext();) {
			GameObject gameObject = (GameObject) clickIt.next();
			gameObject.render(gc);
		}
		
	}
}
