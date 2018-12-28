package models.planet;

import java.io.Serializable;
import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import models.GameObject;
import models.Player;
import models.shape.Renderable;
import models.shape.Shape;
import models.spaceship.SpaceshipType;
import views.Game;

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
	protected SpaceshipType spaceshipType = null; 
	
	/**
	 * The ratio of to create a Spaceship
	 * 
	 * @see Planet#getProductionRate()
	 */
	protected int productionRate;
	
	/**
	 * The number of spaceship on the planet
	 * 
	 * @see Planet#NbShipOnPlanet()
	 */
	protected int shipOnPlanet;
	
	/**
	 * The models.shape of the planet
	 * 
	 * @see javafx.scene.shape
	 * @see Planet#getPlanetShape()
	 * @see Planet#setPlanetShape(PlanetShape)
	 */
	protected Shape planetShape;
	
	/**
	 * Planet Constructor
	 * 
	 */
	public Planet(SpaceshipType spaceshipType) {
		Random randomNumber = new Random();
		double radius = randomNumber.nextInt(100)+50;
		double frameWidth = Game.WIDTH;
		double frameHeight = Game.HEIGHT;
		double pointX = (frameWidth - radius)*randomNumber.nextDouble() + radius ;
		double pointY = (frameHeight - radius)*randomNumber.nextDouble() + radius;
		
		this.shipOnPlanet = randomNumber.nextInt(100)+1;
		this.spaceshipType = spaceshipType;
		this.productionRate = (int)((20 + 50*(radius/100)) / this.spaceshipType.getProductionTime());// (int)(20 + 50*(radius/100));
		this.x = pointX - radius;
		this.y = pointY - radius;
		this.height = radius*2;
		this.width = this.height;
		
		this.circonstrictRadius = Math.sqrt((width*width) + (height*height)) / 2 + 10;

		validPosition(frameWidth, frameHeight);
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
		return spaceshipType;
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
		if(this.shipOnPlanet > 0)
			this.shipOnPlanet = 0;
		this.spaceshipType = spaceshipType;
		this.productionRate = (int)((20 + 50*(width/200)) / this.spaceshipType.getProductionTime());
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
	 * Get the models.shape of the planet.
	 * 
	 * @return the models.shape of the planet.
	 * 
	 * @see javafx.scene.shape
	 * @see Planet#planetShape
	 */
	public Shape getPlanetShape() {
		return this.planetShape;
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
	 * @param planet
	 * @return
	 */
	public boolean superimposed(Planet planet, int minDist) {
		if(this.intersects(planet))
			return true;
		
		// TODO fixed evalation of distance
		double radiusP, radiusThis;
		if(planet instanceof RoundPlanet) 
			radiusP = ((planet.width() <= planet.height())? planet.height() : planet.width()) / 2;
		else
			radiusP = planet.circonstrictRadius();
		
		if(this instanceof RoundPlanet)
			radiusThis = ((this.width() <= this.height())? this.height() : this.width())/2;
		else
			radiusThis = this.circonstrictRadius();

		return this.distanceCarre(planet.getX(), planet.getY()) 
				<=  minDist*minDist + (radiusThis + radiusP)*(radiusThis + radiusP);
		
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
		(new models.shape.Circle(getX(), getY(), circonstrictRadius*2)).drawShape(gc, Color.TRANSPARENT);
		gc.setFill(Color.BLACK);
		
		gc.fillText(""+shipOnPlanet, getX(),getY());
		
	}

	public static Planet planetGenerator() {
		Random randomNumber = new Random();
		Planet planet;
		int nbTypeOfPlanet = 3;
		int i = randomNumber.nextInt(nbTypeOfPlanet);
		switch (i) {
		case 0:
			planet = new RoundPlanet();
			break;
		case 1:
			planet = new SquarePlanet();
			break;
			
		case 2:
			planet = new TrianglePlanet();
			break;
		default:
			planet = new RoundPlanet();
			break;
		}
		
		return planet;
	}
	
}
