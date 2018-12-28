package models;

import java.io.Serializable;
import java.util.Random;

import controllers.UserInput;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import models.shape.Circle;
import models.shape.Renderable;
import models.shape.Shape;
import views.Game;

/**
 * <b>Planet abstract class representing the planet</b>
 * <p> Planet extends {@link GameObject}</p>
 *  <p> Planet implements {@link Renderable}, {@link Serializable}</p>
 * <p>
 *     A planet has the following property:
 *     <ul>
 *         <li>The state of the planet showing if it is own or not</li>
 *         <li>The spaceship type it produce</li>
 *         <li>The production rate of spaceship</li>
 *         <li>The number of ship on it</li>
 *     </li>
 * </p>
 * 
 * 
 * @see GameObject
 * @see RoundPlanet
 * @see SquarePlanet
 * @see TrianglePlanet
 * @see Player
 *
 * 
 * @author meryl
 * @version src_basic
 *
 */
public class Planet extends GameObject implements Renderable, Serializable{

	/**
	 * The owner of the planet.
	 * 
	 * @see Player
	 * @see Planet#owner()
	 * @see Planet#newOwner(PlanetState)
	 */
	private Player owner = null;
	
	/**
	 * The type of Spaceship the planet produce
	 * 
	 * @see SpaceshipType
	 * @see Planet#getSpaceShipeType()
	 */
	protected SpaceshipType spaceshipType = null; 
	
	/**
	 * The ratio of to create a Spaceship
	 * 
	 * @see Planet#getProductionRate()
	 */
	protected int productionRate;
	
	/**
	 * The number of spaceships on the planet
	 * 
	 * @see Planet#NbShipOnPlanet()
	 */
	protected int shipOnPlanet;
	
	/**
	 * The models.shape of the planet
	 * 
	 * @see javafx.scene.shape
	 */
	protected Circle planetShape;
	
	/**
	 * Planet Constructor to be called in the child class.<br/>
	 * set a random new position, size, number of spaceships on planet and specify the production rate.
	 * 
	 */
	
	/**
	 * Planet Constructor
	 * 
	 */
	public Planet() {
		Random randomNumber = new Random();
		double radius = randomNumber.nextInt(100)+50;
		double frameWidth = Game.WIDTH;
		double frameHeight = Game.HEIGHT;
		double pointX = (frameWidth - radius)*randomNumber.nextDouble() + radius ;
		double pointY = (frameHeight - radius)*randomNumber.nextDouble() + radius;
		this.shipOnPlanet = randomNumber.nextInt(100)+1;
		
		this.spaceshipType = new SpaceshipType();
		
		this.x = pointX - radius;
		this.y = pointY - radius;
		this.height = radius*2;
		this.width = this.height;
		validPosition(frameWidth, frameHeight);
		this.planetShape = new Circle(this.x + radius, this.y + radius, radius);
		this.productionRate = (int)((20 + 50*(width/200)) / this.spaceshipType.getProductionTime());

	}
	
	/***********************************\
	 * 								   *
	 * 			Getter/Setter		   *
	 * 								   *
	\***********************************/

	/**
	 /**
	 * Get the current owner of the planet.
	 * 
	 * @return the player owning this planet or null if the planet is neutral.
	 * 
	 * @see PlayerState#getOwner()
	 * @see Player
	 * @see Planet#planetState
	 */
	public Player owner() {
		return this.owner;
	}
	
	/**
	 * set the current owner of the planet.
	 * and  remove this planet of the territory of the previous owner.
	 * @param player
	 * 			The new owner of the planet
	 * 
	 * @see PlayerState#setOwner(Player)
	 * @see Player
	 * @see Planet#planetState
	 */
	public void newOwner(Player player) {
		if(isOwn())
			owner.getTerritory().remove(this);
		this.owner = player;
	}
	
	/**
	 * Get the type of spaceship the planet produce.
	 * 
	 * @return the type of spaceship the planet produce.
	 * 
	 * @see SpaceshipType
	 * @see Planet#spaceshipType
	 */
	public SpaceshipType getSpaceShipeType() {
		return spaceshipType;
	}
	
	/**
	 * Get the production rate of the planet.
	 * 
	 * @return the production rate of the planet.
	 * 
	 * @see Planet#productionRate
	 */
	public int getProductionRate() {
		return this.productionRate;
	}

	/**
	 * Get the number of ships on the planet.
	 * 
	 * @return the number of ships on the planet.
	 * 
	 * @see Planet#shipOnPlanet
	 */
	public int nbShipOnPlanet() {
		return this.shipOnPlanet;
	}
	
	/**
	 * Set the number of ships on the planet.
	 * 
	 * @param nb the number of ships to add.
	 * 
	 * @see Planet#shipOnPlanet
	 */
	public void nbShipOnPlanet(int nb) {
		this.shipOnPlanet += nb;
	}
	
	
	/**
	 * Get the models.shape of the planet.
	 * 
	 * @return the models.shape of the planet.
	 * 
	 * @see javafx.scene.shape
	 * @see Planet#planetShape
	 */
	public Circle getPlanetShape() {
		return this.planetShape;
	}
	
	/***********************************\
	 * 								   *
	 * 				Method			   *
	 * 								   *
	\***********************************/
	
	/**
		/**
	 * Test if the planetState is currently own.
	 * 
	 * @return a boolean showing if the planetState is currently own.
	 * 
	 * @see PlayerState
	 * @see Neutral
	 * @see Owned
	 * @see Planet#planetState
	 */
	public boolean isOwn() {
		return (this.owner != null); 
	}
	/**
	 * Check if two planets are too close
	 * @param p
	 * @return
	 */
	public boolean superimposed(Planet p) {
		return this.distance(p.getX(), p.getY()) <= 50 + (p.width + this.width) /2;
	}
	

	/**
	 * Decrease the number of spaceships on a planet in case of an attack
	 * Increase this number if the player has the planet
	 * @param s
	 */
	public void spaceShipEnter(SpaceshipType spaceship) {
		
		if(spaceship.getPlayer().equals(this.owner))
			this.shipOnPlanet++;
		else 
			this.shipOnPlanet -= spaceship.getAttPower();
		if(this.shipOnPlanet < 0) {
			this.shipOnPlanet -= this.shipOnPlanet;
			spaceship.getPlayer().myPlanet(this);
			if(this.equals(selected))
				selected = null;
		}
			
	}
	
	@Override
	public boolean equals(Object o) {
		if(o != null && (o instanceof Planet)) {
			Planet planet = (Planet)o;
			return this.x == planet.x && this.y == planet.y;
		}
		return false;
					
	}
	
	@Override
	public String toString() {
		return "**************************\n"
				+ "\tPlanet\n"
				+ "**************************\n\n"
				+ "\towner: \n\t" + owner + "\n--------------------------\n"
				+ "\n shipOnPlanet: " + shipOnPlanet+ "\n production rate: "+ productionRate + "\n"
				+ "\n shape: \n\t" + planetShape + "\n";
	}
	
	@Override
	public void render(GraphicsContext gc) {
		if(this.equals(selected))
			gc.setLineWidth(5f);
		else
			gc.setLineWidth(1f);

		this.planetShape.drawShape(gc, (isOwn())? owner.getColor() : Color.CORNFLOWERBLUE);
		gc.setLineWidth(1f);
		gc.setFill(Color.BLACK);
		
		gc.fillText(""+shipOnPlanet, getX(),getY());
		
	}
	
}
