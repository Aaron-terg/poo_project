package views.ui;

import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;

/**
 * The main user interface containing the basis of an canvas
 * 
 * @author meryl
 *
 */
public class UserInterface extends Group{

	/**
	 * The default {@link UserInterface} constructor adding an exit button to the clickable arrayList
	 */
	public UserInterface() {
		
	}
	
	/**
	 * The user interface containing the basis
	 * @param clickable
	 */
	public UserInterface(ObservableList<Node> clickable) {
		this.getChildren().addAll(clickable);

	}
	
	public void refreshInterface(ObservableList<Node> clickable) {
		if(this.getChildren() != null) {
			int size = this.getChildren().size();
			this.getChildren().remove(0, size);
		}	
		this.getChildren().addAll(clickable);
		System.out.println("refresh ui");
	}
}
