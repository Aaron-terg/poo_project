package Models;

import Models.shape.Renderable;
import javafx.scene.canvas.GraphicsContext;

public class UserInterface implements Renderable{

	private GameObject[] clickable;
	
	public UserInterface(GameObject[] clickable) {
		this.clickable = clickable;
	}
	
	public GameObject[] getClickable() {
		return clickable;
	}

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		
	}
}
