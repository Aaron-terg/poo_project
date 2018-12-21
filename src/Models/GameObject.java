package Models;

import java.io.Serializable;

import Controllers.UserInput;
import Models.shape.Renderable;
import javafx.scene.canvas.GraphicsContext;

/**
 * <b>The GameObject is the basis of every element the user will interact with</b>
 * 
 * @author meryl, Virginie
 * @since src_basic
 */
public class GameObject implements Renderable, Serializable{
	
	protected String label;
	/**
	 * x and y are the position of the game object in the canvas
	 * @see GameObject#getX()
	 * @see GameObject#getY()
	 * @see GameObject#isInside(double, double)
	 */
	protected double x, y;
	
	/**
	 * width and height are the size of the game object.
	 * With the position they form the boundaries of the game object.
	 * @see GameObject#isInside(double, double)
	 */
	protected double height, width;
	
	/**
	 * The current GameObject focused by the user
	 * 
	 * @see GameObject#isSelected()
	 */
	public static GameObject selected = null;
	
	/**
	 * GameObject default constructor: set every attribute to 0
	 */
	public GameObject() {
		this.x = 0;
		this.y = 0;
		this.height = 0;
		this.width = 0;
		label = "GameObject";
	}
	
	/**
	 * GameObject constructor set the x, y, width, and height attributes
	 * @param x the abscisse coordinate
	 * @param y the height coordinate
	 * @param height the height of the GameObject
	 * @param width the width of the GameObject
	 */
	public GameObject(double x, double y, double width, double height) {
		this.x = x - width/2;
		this.y = y - height/2;
		this.height = height;
		this.width = width;
		label = "GameObject";
	}
	
	public GameObject(double x, double y, double width, double height, String label) {
		this.x = x - width/2;
		this.y = y - height/2;
		this.height = height;
		this.width = width;
		this.label = label;
	}
	

	/**
	 * Get the x position of center of the GameObject
	 * @return The x coordinate
	 */
	public double getX() {
		return x + width/2;
	}

	/**
	 * Get the y position of center of the GameObject
	 * @return The y coordinate
	 */
	public double getY() {
		return y + height/2;
	}

	public double height() {
		return height;
	}

	public double width() {
		return width;
	}
	
	public String label() {
		return label;
	}
	
	/***********************************\
	 * 								   *
	 * 				Method			   *
	 * 								   *
	\***********************************/
	
	
	public void resize(double w, double h) {
		width = w;
		height = h;
	}

	/**
	 * Set this GameObject to selected
	 * 
	 * @see GameObject#selected
	 * @see UserInput#mousePressed()
	 */
	public void isSelected() {
		selected = this;
	}
	
	/**
	 * Check if the couple of coordinate are inside the boundaries
	 * @param x the x coordinate 
	 * @param y the y coordinate
	 * @return true if the couple of coordinate is inside the boundaries
	 */
	public boolean isInside(double x, double y) {
		
		return x <= (this.x + this.width) && x >= this.x
				 && y >= this.y && y <= (this.y + this.height);
	}
	
	/**
	 * Check if the boundaries of the gameObject intersect with the gameObject in argument
	 * @param gameObject the gameObject to test
	 * @return true if they intersect
	 */
	public boolean intersects(GameObject gameObject) {
		// TODO find the condition
		if(gameObject.x >= this.x + this.width
				|| gameObject.x + gameObject.width <= this.x
				|| gameObject.y >= this.y + this.height
				|| gameObject.y + gameObject.height <= this.y)
			return false;
		else	
			return true;
	}
	
	public double distance(double p1X, double p1Y) {
		return Math.sqrt((p1X - this.x)*(p1X - this.x) + (p1Y - this.y)*(p1Y - this.y));
	}
	
	public void validPosition(double frameWidth, double frameHeight) {
		double offset = this.width + 5;
		if(this.x + this.width >= frameWidth) 
			this.x -= offset;	
		
		if(this.x <= 0)
			this.x += offset;
		
		if(this.y + this.height >= frameHeight)
			this.y -= offset;
		
		if(this.y <= 0)
			this.y += offset;
		
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "x :" + x + ", y: "+ y
				+ ", height: "+ height +", width: " + width +"\n"
				+ ", object selected :" + selected + "\n";
	}
	
	@Override
	public void render(GraphicsContext gc) {
		gc.strokeLine(x, y, x, y+height);
		gc.strokeLine(x, y+height, x+width, y + height);
		gc.strokeLine(x+width, y + height, x+width, y);
		gc.strokeLine(x+width, y, x, y);
		gc.stroke();
		
	}
}
