package Models.Spaceship;

import java.util.Random;

import Models.GameObject;
import Models.Player;
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
 *         <li>The shape</li>
 *         
 *     </li>
 * </p>
 * 
 *
 * 
 * @author Virginie
 * @version 2.1
 * **/
public abstract class SpaceshipType extends GameObject implements Renderable{
	
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
		this.spaceshipShape.drawShape(gc);
	}
	/**
	 * Update the position of the ship
	 * Do a translation of the ship's position according to the parameters
	 * @param x :x coordinate's for the translation
	 * @param y : y coordinate's translation
	 * 
	 */
	public void newPosition(double x, double y) {
		this.x -= x*speed;
		this.y -= y*speed;
//		this.spaceshipShape.setPosition(this.x, this.y);
		for(int i = 0; i<this.spaceshipShape.getX().length; i++) {
			this.spaceshipShape.getX()[i]-=x*speed;
			this.spaceshipShape.getY()[i]-=y*speed;
		}
	}

	/**
	 * Calculate a new point between the ship's actual position && the destination
	 * @param destination
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
	

	public void get_around(Planet obstacle) {
		double hypotenuse =obstacle.getPlanetShape().distPoint(this.getSpaceshipShape().getX()[1], this.getSpaceshipShape().getY()[1]);
		double opposite = obstacle.getPlanetShape().getRadius()+5;
		double adjacent =Math.sqrt(Math.abs((hypotenuse*hypotenuse)-(opposite*opposite)));
		double newPosX = adjacent/hypotenuse;
		double newPosY =opposite/hypotenuse;
		if(this.getSpaceshipShape().getX()[0]> obstacle.getX() && this.getSpaceshipShape().getY()[0]<= obstacle.getY())
			this.newPosition(-newPosX, newPosY);
		else if(this.getSpaceshipShape().getX()[0]>= obstacle.getX() && this.getSpaceshipShape().getY()[0]> obstacle.getY())
			this.newPosition(-newPosX, -newPosY);
		else if(this.getSpaceshipShape().getX()[0]< obstacle.getX() && this.getSpaceshipShape().getY()[0]>= obstacle.getY())
			this.newPosition(newPosX, -newPosY);
		else if(this.getSpaceshipShape().getX()[0]<= obstacle.getX() && this.getSpaceshipShape().getY()[0]< obstacle.getY())
			this.newPosition(newPosX, newPosY);
		
	}
}
