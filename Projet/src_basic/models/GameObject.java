package models;

import java.io.Serializable;

import controllers.UserInput;
import javafx.scene.canvas.GraphicsContext;
import models.shape.Renderable;

/**
 * <b>The GameObject is the basis of every element the user will interact with</b>
 *  <p> GameObject implements {@link Renderable}, {@link Serializable}</p>
 *  
 *  @see Spacefleet
 *  @see SpaceshipType
 *  @see Planet
 *  @see Player
 *  @see UserInput
 * @author meryl, Virginie
 * @version src_advanced
 * @since src_basic
 */
public class GameObject implements Renderable, Serializable{
	
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
	}
	
	/**
	 * GameObject constructor set the x, y, width, and height attributes
	 * construct a circonstrict circle with the height and width.
	 * @param x the abscisse coordinate
	 * @param y the height coordinate
	 */
	public GameObject(double x, double y) {
		this.x = x - width/2;
		this.y = y - height/2;

	}
	

	/**
	 * Get the x position of the center of the GameObject
	 * @return The x coordinate
	 */
	public double getX() {
		return x + width/2;
	}

	/**
	 * Get the y position of the center of the GameObject
	 * @return The y coordinate
	 */
	public double getY() {
		return y + height/2;
	}

	/**
	 * Get the height of the GameObject
	 * @return the absolute value of the height
	 */
	public double height() {
		return (height < 0)? -height : height;
	}

	/**
	 * Get the width of the GameObject
	 * @return the absolute value of the width
	 */
	public double width() {
		return (width < 0)? -width : width;
	}
	
	/***********************************\
	 * 								   *
	 * 				Method			   *
	 * 								   *
	\***********************************/
	
	/**
	 * Resize the boundaries of the game object.
	 * 
	 * @see UserInput#mouseDragged()
	 * 
	 * @param w the new width
	 * @param h the new height
	 */
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
	 * Check if the couple of coordinates is inside the boundaries
	 * @param x the x coordinate 
	 * @param y the y coordinate
	 * @return true if the couple of coordinate is inside the boundaries
	 */
	public boolean isInside(double x, double y) {
		return ((width < 0)? x >= (this.x + this.width) && x <= this.x :  x <= (this.x + this.width) && x >= this.x)
				&& ((height < 0)? y <= this.y && y >= (this.y + this.height) : y >= this.y && y <= (this.y + this.height));
	}
	
	/**
	 * Check if the boundaries of the gameObject intersect with the gameObject in argument
	 * @param gameObject the gameObject to test
	 * @return true if they intersect
	 */
	public boolean intersects(GameObject gameObject) {
		if((
				(width < 0)? gameObject.x < this.x + this.width
				|| gameObject.x + gameObject.width > this.x
				: gameObject.x > this.x + this.width
				|| gameObject.x + gameObject.width < this.x
			)
			|| (
					(height <0)?  gameObject.y < this.y + this.height
					|| gameObject.y + gameObject.height > this.y
					: gameObject.y > this.y + this.height
					|| gameObject.y + gameObject.height < this.y
				)
		)
			return false;
		else	
			return true;
	}
	
	/**
	 * return the  distance between the center of this game object and a point.<br>
	 * @param p1X x coordinate of the point
	 * @param p1Y y coordinate of the point
	 * @return return the distance between the center of this game object and a point
	 */
	public double distance(double p1X, double p1Y) {
		return Math.sqrt((p1X - this.getX())*(p1X - this.getX()) + (p1Y - this.getY())*(p1Y - this.getY()));
	}
	
	/**
	 * check the position of the gameObject. if it's out of bound then replace it.
	 * @param frameWidth the scene width
	 * @param frameHeight the scene height
	 */
	public void validPosition(double frameWidth, double frameHeight) {
		double offset = this.width() + 5;
		if(this.x + this.width() >= frameWidth) 
			this.x -= offset;	
		
		if(this.x <= 0)
			this.x += offset;
		 offset = this.height() + 5;
		if(this.y + this.height() >= frameHeight)
			this.y -= offset;
		
		if(this.y <= 200)
			this.y += offset;
		
	}
	
	@Override
	public String toString() {
		return "x :" + x + ", y: "+ y
				+ ", height: "+ height() +", width: " + width() +"\n"
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
