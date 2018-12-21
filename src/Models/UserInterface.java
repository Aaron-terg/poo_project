package Models;

import java.util.ArrayList;
import java.util.Iterator;

import Models.shape.Renderable;
import javafx.scene.canvas.GraphicsContext;

public class UserInterface implements Renderable{

	protected ArrayList<GameObject> clickable;
	
	public UserInterface() {
		clickable = new ArrayList<GameObject>();
	}
	
	public UserInterface(ArrayList<GameObject> clickable) {
		this.clickable = clickable;
	}
	
	public ArrayList<GameObject> getClickable() {
		return clickable;
	}

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		for (Iterator clickIt = clickable.iterator(); clickIt.hasNext();) {
			GameObject gameObject = (GameObject) clickIt.next();
			gameObject.render(gc);
		}
		
	}
}
