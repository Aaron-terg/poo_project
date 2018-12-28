package models;

import java.io.Serializable;

import javafx.scene.canvas.GraphicsContext;
import models.shape.Polygon;
import models.shape.Renderable;
import models.shape.Shape;

/**
 * <b>SpaceShipType abstract class represents the ship</b>
 * <p> SpaceshipType extends {@link GameObject}</p>
 * <p> SpaceshipType implements {@link Renderable}, {@link Serializable}</p>
 * <p>
 *     A SpaceShip has the following property:
 *     <ul>
 *         <li>The power of its attack</li>
 *         <li>The speed </li>
 *         <li>The necessary time for its production</li>
 *         <li>The shape of the ship</li>
 *         <li> its own player 
 *         
 *     </li>
 * </p>
 * 
 * @see BasicSpaceshipType
 * @author meryl
 * 
 * @version src_basic
 * **/
public class SpaceshipType extends GameObject implements Renderable, Serializable{
	
	/**
	 * The power of its attack
	 */
	protected int attPower;
	/**
	 * its speed
	 */
	protected int speed;
	/**
	 * The necessary time for its production
	 */
	protected long productionTime;
	/**
	 * The shape of the ship
	 */
	protected Shape spaceshipShape;
	/**
	 * its own player
	 */
	protected Player player;
	
	
	/**
	 * SpaceshipType constructor
	 * 
	 */
	public SpaceshipType() {
		this(5, 3, 10, new Polygon());
		this.width = 20;
		this.height = 20;
	}

	public SpaceshipType(int attPower, int speed, long productionTime, Polygon spaceshipShape) {
		this.attPower = attPower;
		this.speed = speed;
		this.productionTime = productionTime;
		this.spaceshipShape = spaceshipShape;
	}
	
	/***********************************\
	 * 								   *
	 * 			Getter/Setter		   *
	 * 								   *
	\***********************************/
	
	/**
	 * 
	 * @return the attack power of the spaceship
	 */
	public int getAttPower() {
		return this.attPower;
	}
	
	/**
	 * 
	 * @return the speed of ths spaceship
	 */
	public int getSpeed() {
		return this.speed;
	}
	
	/**
	 * 
	 * @return the production time of the spaceship
	 */
	public long getProductionTime() {
		return this.productionTime;
	}
	
	/**
	 * 
	 * @return the spaceship shape
	 */
	public Shape getSpaceshipShape() {
		return spaceshipShape;
	}
	
	/**
	 * 
	 * @return its owner
	 */
	public Player getPlayer() {
		return this.player;
	}
	
	/**
	 * set the new owner
	 * @param p the new owner
	 */
	public void setPlayer(Player p) {
		this.player = p;
	}
	
	/***********************************\
	 * 								   *
	 * 				Method			   *
	 * 								   *
	\***********************************/

	/**
	 * Display the ship 
	 * @see Shape#drawShape(GraphicsContext)
	 */
	public void render(GraphicsContext gc) {
		this.spaceshipShape.drawShape(gc, player.getColor());

	}
	
	/**
	 * Update the position of the ship. <br/>
	 * Do a translation of the ship's position according to the parameters
	 * @param dx :x coordinate's for the translation
	 * @param dy : y coordinate's translation
	 * 
	 */
	public void moveTo(double dx, double dy) {
		this.x -= dx*speed;
		this.y -= dy*speed;
		this.spaceshipShape.setPosition(this.x, this.y);
	}

	/**
	 * Calculate the director vector of the line between the ship's actual position and the destination
	 * @param destination The planet the ship go to destination 
	 * @see SpaceshipType#moveTo(double, double)
	 */
	public void goTo(Planet destination) {
		
		double distX = (this.getX()-destination.getX());
		double distY = (this.getY()-destination.getY());
		double dist = Math.sqrt((distX*distX)+(distY*distY));
		double newPosX = distX/dist;
		double newPosY = distY /dist;
		this.moveTo(newPosX, newPosY);
	}
	

	/**
	 * Find and calculate a path around a circle
	 * Move away the ship from the planet and find the new position thanks to this.moveTo
	 * @param obstacle, the planet to avoid
	 * @see SpaceshipType#moveTo(double, double)
	 */
	public void getAround(Planet obstacle) {
		double norm = (obstacle.distance(this.getX(), this.getY()));
		double vY = obstacle.getY() - this.getY(),
				vX = obstacle.getX() - this.getX();
		
		double newPointX = vX / norm,
				newPointY = vY / norm;
		
		moveTo(newPointX, newPointY);
	}

	
	@Override
	public String toString() {
		return "spaceship owner: " + player;
	}
}
