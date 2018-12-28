package models.spaceship;

import java.io.Serializable;
import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import models.GameObject;
import models.Player;
import models.Spacefleet;
import models.planet.Planet;
import models.shape.Polygon;
import models.shape.Renderable;
import models.shape.Shape;

/**
 * <b>SpaceShipType class represents the ship</b>
 * <p>
 *     A SpaceShip has the following properties:
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
	/**
	* the power of its attack
	*/ 
	protected int attPower;
	/**
	*	its speed
	*/
	protected int speed;
	/**
	* The necessary time for its production
	*/
	protected long productionTime;
	/**
	*  its shape
	*/
	protected Shape spaceshipShape;
	/**
	* Its own player
	*/
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
		
		double distX = this.getX()-destination.getX();
		double distY = this.getY()-destination.getY();
		double dist = Math.sqrt((distX*distX)+(distY*distY));
		double newPosX = distX/dist;
		double newPosY = distY /dist;
		this.moveTo(newPosX, newPosY);
	}
	

	/**
	 * Find && calculate a path around a circle
	 * 
	 * Move away the ship from the planet and find the new position thanks to this.moveTo
	 * @param obstacle, the planet to avoid
	 * @param fleet : to know which planet is the destination of the ships
	 * @see SpaceshipType#moveTo(double, double)
	 */
	public void getAround(Planet obstacle, Spacefleet fleet) {
		Planet destination = fleet.getDestination();
		double cos=0, sin=0; // Apply an angle with a cosinus and a sinus and consider the planet as a trigonometric circle
		double a = (destination.getY()-obstacle.getY())/(destination.getX()-obstacle.getX());//the coefficient of the line a*x+b
		double b = destination.getY()-a*destination.getX() + width;
		double line = a*this.getX()+b;
		boolean destinationisDown = destination.getY()>= obstacle.getY();
		boolean destinationisOnLeft = destination.getX()<=obstacle.getX();
		
		//"Cut" the obstacle in four parts and choose in each part the calculation to apply
		if(this.getX()> obstacle.getX() && this.getY()<= obstacle.getY()) {
			
			//Check where is the ship compared to the line between obstacle's center && destination's center
			
			if(destinationisOnLeft && destinationisDown&& this.getY() >=line ||!destinationisOnLeft && destinationisDown){//spread the ship away from the planet
				cos=-1; //consider the obstacle as a trigonometric circle so cos = X axis and sin = Y axis and apply this to move away the ship from the circle
			}else 
				sin=1;
			
		}else if(this.getX()>obstacle.getX() && this.getY() >=obstacle.getY()){
			if(destinationisOnLeft && !destinationisDown && this.getY() <=line ||!destinationisDown && !destinationisOnLeft) {
				cos = -1;
			}else 
				sin = -1;
		}else if(this.getX()<= obstacle.getX() && this.getY() >obstacle.getY()) {
			
			if(!destinationisOnLeft && !destinationisDown&& this.getY() <=line|| destinationisOnLeft && !destinationisDown){
				cos =1;
			}else 
				sin = -1;
		
		}else {
			if(!destinationisOnLeft && destinationisDown&& this.getY()<=line &&  this.getY() !=obstacle.getY()||(!destinationisOnLeft && !destinationisDown)) {
					sin = 1;
			}else
				cos=1;

		}	
		this.moveTo(cos, sin);
	}
	
	@Override
	public String toString() {
		return "spaceship owner: " + player;
	}
}
