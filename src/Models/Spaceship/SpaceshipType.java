package Models.Spaceship;

import java.io.Serializable;
import java.util.Random;

import Models.GameObject;
import Models.Player;
import Models.Spacefleet;
import Models.planet.Planet;
import Models.shape.Polygon;
import Models.shape.Renderable;
import Models.shape.Shape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

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
	protected Polygon spaceshipShape;
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

	public SpaceshipType(int attPower, int speed, long productionTime, Polygon spaceshipShape, Player player) {
		this.attPower = attPower;
		this.speed = speed;
		this.productionTime = productionTime;
		this.spaceshipShape = spaceshipShape;
		this.player= player;
	}
	
	public SpaceshipType(SpaceshipType s) {
		this(s.attPower, s.speed, s.productionTime, new Polygon(s.spaceshipShape), s.player);
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
	
	public Polygon getSpaceshipShape() {
		return spaceshipShape;
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	public void setPlayer(Player p) {
		this.player = p;
	}
	/**
	 * Display the ship 
	 * @see Shape#drawShape(GraphicsContext)
	 */
	public void render(GraphicsContext gc) {
		gc.setStroke(player.getColor());
		this.spaceshipShape.drawShape(gc, Color.BLACK);
		gc.setStroke(Color.BLACK);
	}
	/**
	 * Update the position of the ship
	 * Do a translation of the ship's position according to the parameters
	 * @param dx :x coordinate's for the translation
	 * @param dy : y coordinate's translation
	 * 
	 */
	public void newPosition(double dx, double dy) {
		this.x = getX() - dx*speed;
		this.y = getY() - dy*speed;
//		this.spaceshipShape.setPosition(this.x, this.y);
		for(int i = 0; i<this.spaceshipShape.getX().length; i++) {
			this.spaceshipShape.getX()[i]-=dx*speed;
			this.spaceshipShape.getY()[i]-=dy*speed;
		}
	}

	/**
	 * Calculate the director vector of the line between the ship's actual position and the destination
	 * @param destination The planet the ship go to destination 
	 * @see SpaceshipType#newPosition(double, double)
	 */
	public void goTo(Planet destination) {
		
		double distX = this.spaceshipShape.getX()[1]-destination.getPlanetShape().position()[0];
		double distY = this.spaceshipShape.getY()[1]-destination.getPlanetShape().position()[1];
		double dist = Math.sqrt((distX*distX)+(distY*distY));
		double newPosX = distX/dist;
		double newPosY = distY /dist;
		this.newPosition(newPosX, newPosY);
	}
	

	/**
	 * Find && calculate a path around a circle
	 * Move away the ship from the planet and find the new position thanks to this.newPosition
	 * @param obstacle, the planet to avoid
	 * @param fleet : to know which planet is the destination of the ships
	 * @see SpaceshipType#newPosition(double, double)
	 */
	public void getAround(Planet obstacle, Spacefleet fleet) {
		Planet destination = fleet.getDestination();
		double cos=0, sin=0; 
		double a = (destination.getY()-obstacle.getY())/(destination.getX()-obstacle.getX());//the coefficient of the line a*x+b
		double b = destination.getY()-a*destination.getX()+15;
		double line = a*this.spaceshipShape.getX()[1]+b;
		boolean destinationisDown = destination.getY()>= obstacle.getY();
		boolean destinationisOnLeft = destination.getX()<=obstacle.getX();
		
		//"Cut" the obstacle in four parts and choose in each part the calculation to apply
		if(this.getSpaceshipShape().getX()[1]> obstacle.getX() && this.getSpaceshipShape().getY()[1]<= obstacle.getY()) {
			
			//Check where is the ship compared to the line between obstacle's center && destination's center
			
			if(destinationisOnLeft && destinationisDown&& this.spaceshipShape.getY()[1]>=line||!destinationisOnLeft && destinationisDown){//spread the ship away from the planet
				cos=-1; //move away from the circle
			}else 
				sin=1;
			
		}else if(this.getSpaceshipShape().getX()[1]>obstacle.getX() && this.getSpaceshipShape().getY()[1]>=obstacle.getY()){
			if(destinationisOnLeft && !destinationisDown && this.spaceshipShape.getY()[1]<=line ||!destinationisDown && !destinationisOnLeft) {
				cos = -1;
			}else 
				sin = -1;
		}else if(this.getSpaceshipShape().getX()[1]<= obstacle.getX() && this.getSpaceshipShape().getY()[1]>obstacle.getY()) {
			
			if(!destinationisOnLeft && !destinationisDown&& this.spaceshipShape.getY()[1]<=line|| destinationisOnLeft && !destinationisDown){
				cos =1;
			}else 
				sin = -1;
		
		}else {
			if(!destinationisOnLeft && destinationisDown&& this.spaceshipShape.getY()[1]<=line &&  this.spaceshipShape.getY()[1]!=obstacle.getY()||(!destinationisOnLeft && !destinationisDown)) {
					sin = 1;
			}else
				cos=1;

		}	
		this.newPosition(cos, sin);
	}
	
	@Override
	public String toString() {
		return "spaceship owner: " + player;
	}
}
