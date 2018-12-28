package models.spaceship;

import java.io.Serializable;
import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import models.GameObject;
import models.Player;
import models.Spacefleet;
import models.planet.Planet;
import models.shape.Polygon;
import models.shape.Renderable;
import models.shape.Shape;

/**
 * <b>SpaceShipType class represent the ship</b>
 * <p>
 *     A SpaceShip has the following property:
 *     <ul>
 *         <li>The power of its attack</li>
 *         <li>The speed </li>
 *         <li>The necessary time for its production</li>
 *         <li>The shape of the ship</li>
 *         <li> His own player 
 *         
 *     </li>
 * </p>
 * 
 *
 * 
 * @author Virginie
 * @version 2.1
 * **/
public abstract class SpaceshipType extends GameObject implements Renderable, Serializable{
	
	protected int attPower;
	protected int speed;
	protected long productionTime;
	protected Shape spaceshipShape;
	protected Player player;
	
	/**
	 * SpaceshipType constructor
	 * 
	 */
	public SpaceshipType() {
		Random randomNumber = new Random();
		this.attPower = randomNumber.nextInt(10)+2;
		this.speed = randomNumber.nextInt(3)+1;
		this.productionTime = randomNumber.nextInt(3000)+1000;	
		this.spaceshipShape = new Polygon();
		this.width = 20;
		this.height = 20;
	}

	public SpaceshipType(int attPower, int speed, long productionTime) {
		this.attPower = attPower;
		this.speed = speed;
		this.productionTime = productionTime;
		this.width = 20;
		this.height = 20;
		this.circonstrictRadius = (Math.sqrt((width*width) + (height*height)) / 2) + 15;

	}
	
	/***********************************\
	 * 								   *
	 * 			Getter/Setter		   *
	 * 								   *
	\***********************************/
	
	public int getAttPower() {
		return this.attPower;
	}
	
	public int getSpeed() {
		return this.speed;
	}
	
	public long getProductionTime() {
		return this.productionTime;
	}
	
	public Shape getSpaceshipShape() {
		return spaceshipShape;
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	public void setPlayer(Player p) {
		this.player = p;
	}
	
	
	@Override
	public boolean intersects(GameObject gameObject) {
		
		Polygon poly  = (Polygon)spaceshipShape;
		boolean result = false;
		for (int i = 0; i < poly.getX().length; i++) {
			result |= gameObject.isInside(poly.getX()[i], poly.getY()[i]);
		}
		
		return super.intersects(gameObject) && result;
	}
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
	 * Find && calculate a path around a circle
	 * Move away the ship from the planet and find the new position thanks to this.newPosition
	 * @param obstacle, the planet to avoid
	 * @param fleet : to know which planet is the destination of the ships
	 * @see SpaceshipType#moveTo(double, double)
	 */
	public void getAround(Planet obstacle) {
		double norm = Math.sqrt(obstacle.distanceCarre(this.getX(), this.getY()));
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
