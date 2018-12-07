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
		double dist_x = this.spaceshipShape.getX()[1]-destination.getPlanetShape().position()[0];
		double dist_y = this.spaceshipShape.getY()[1]-destination.getPlanetShape().position()[1];
		double dist = Math.sqrt((dist_x*dist_x)+(dist_y*dist_y));
		double new_pos_x = dist_x/dist;
		double new_pos_y = dist_y /dist;
		this.newPosition(new_pos_x, new_pos_y);
	}
	

	public void get_around(Planet obstacle) {
		double hypotenuse =obstacle.getPlanetShape().distPoint(this.x, this.y);
		double opposite = obstacle.getPlanetShape().getRadius();
		double sinus = opposite / hypotenuse;
		double cosinus = Math.sqrt(hypotenuse * hypotenuse - opposite * opposite) / hypotenuse;
		double new_X = obstacle.getX() + obstacle.getPlanetShape().getRadius()*cosinus;
		double new_Y = obstacle.getY() + obstacle.getPlanetShape().getRadius()*sinus;
		
	}
}
