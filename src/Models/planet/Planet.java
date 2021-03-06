package Models.planet;

import java.io.Serializable;
import java.util.Random;

import Models.GameObject;
import Models.Player;
import Models.Spaceship.BasicSpaceshipType;
import Models.Spaceship.SpaceshipType;
import Models.shape.Circle;
import Models.shape.Renderable;
import Views.Game;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * <b>Planet class represent the planet</b>
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
 * @see SpaceshipType
 * @see Player
 *
 * 
 * @author meryl
 * @version 2.1
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
	 * @see Planet#setSpaceShipeType(SpaceshipType)
	 */
	private SpaceshipType spaceshipType = null; 
	
	/**
	 * The ratio of to create a Spaceship
	 * 
	 * @see Planet#getProductionRate()
	 */
	private int productionRate = 15;
	
	/**
	 * The number of spaceship on the planet
	 * 
	 * @see Planet#NbShipOnPlanet()
	 */
	private int shipOnPlanet;
	
	/**
	 * The Models.shape of the planet
	 * 
	 * @see javafx.scene.shape
	 * @see Planet#getPlanetShape()
	 * @see Planet#setPlanetShape(PlanetShape)
	 */
	private Circle planetShape;
	
	/**
	 * Planet Constructor
	 * 
	 */
	public Planet() {
		Random randomNumber = new Random();
		double radius = randomNumber.nextInt(80)+30;
		double frameWidth = Game.WIDTH;
		double frameHeight = Game.HEIGHT;
		double pointX = (frameWidth -200 - radius)*randomNumber.nextDouble() + radius ;
		double pointY = (frameHeight - radius)*randomNumber.nextDouble() + radius;
		this.shipOnPlanet = randomNumber.nextInt(100)+1;
		
		
		this.spaceshipType = new BasicSpaceshipType();
		
		this.x = pointX - radius;
		this.y = pointY - radius;
		this.height = radius*2;
		this.width = this.height;
		validPosition(frameWidth, frameHeight);
		this.planetShape = new Circle(this.x + radius, this.y + radius, radius);
	}
	
	public Planet(double pointX, double pointY, double radius, Color color) {
		super(pointX, pointY, radius*2, radius*2);
		this.planetShape = new Circle(pointX, pointY, radius);
		double frameWidth = Game.WIDTH;
		double frameHeight = Game.HEIGHT;
		validPosition(frameWidth, frameHeight);
		this.spaceshipType = new BasicSpaceshipType();
		
	}
	
	/***********************************\
	 * 								   *
	 * 			Getter/Setter		   *
	 * 								   *
	\***********************************/

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
	 * 
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
		return (new BasicSpaceshipType(this.spaceshipType));
	}
	
	/**
	 * set the type of spaceship the planet will now produce.
	 * 
	 * @param spaceshipType
	 * 			the type of spaceship the planet will produce.
	 * 
	 * @see SpaceshipType
	 * @see Planet#spaceshipType
	 */
	public void setSpaceShipeType(SpaceshipType spaceshipType) {
		this.spaceshipType = spaceshipType;
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
	 * Get the number of ship on the planet.
	 * 
	 * @return the number of ship on the planet.
	 * 
	 * @see Planet#shipOnPlanet
	 */
	public int nbShipOnPlanet() {
		return this.shipOnPlanet;
	}
	
	/**
	 * Set the number of ship on the planet.
	 * 
	 * @param nb the number of ship to add.
	 * 
	 * @see Planet#shipOnPlanet
	 */
	public void nbShipOnPlanet(int nb) {
		this.shipOnPlanet += nb;
	}
	
	/**
	 * Get the Models.shape of the planet.
	 * 
	 * @return the Models.shape of the planet.
	 * 
	 * @see javafx.scene.shape
	 * @see Planet#planetShape
	 */
	public Circle getPlanetShape() {
		return this.planetShape;
	}
	
	/**
	 * set the Models.shape of the planet.
	 * 
	 * @param Models.shape
	 * 			The new Models.shape of the planet.
	 * 
	 * @see javafx.scene#shape
	 * @see Planet#planetShape
	 * 
	 */
	public void setPlanetShape(Circle shape) {
		this.planetShape = shape;
	}
	
	/***********************************\
	 * 								   *
	 * 				Method			   *
	 * 								   *
	\***********************************/
	
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
		return this.getPlanetShape().distance(p.getPlanetShape()) <= 40;
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
			spaceship.getPlayer().myPlanet(this);;
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
