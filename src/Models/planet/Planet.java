package Models.planet;

import java.util.Random;


import Models.Player;
import Models.Spaceship.SpaceshipType;
import Models.shape.Circle;
import Models.shape.Renderable;
import Views.TestObject;
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
public class Planet implements Renderable{

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
	private int productionRate = 0;
	
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
	private boolean is_destination=false;
	

	/**
	 * Planet Constructor
	 * 
	 */
	
	
	public Planet() {
		Random randomNumber = new Random();
		double radius = randomNumber.nextInt(50)+25;
		double frameWidth = TestObject.WIDTH;
		double frameHeight = TestObject.HEIGHT;
		double pointX = (frameWidth - radius)*randomNumber.nextDouble() + radius;
		double pointY = (frameHeight - radius)*randomNumber.nextDouble() + radius;
		Color color = Color.LIGHTBLUE;
		this.shipOnPlanet = randomNumber.nextInt(100)+1;
		this.planetShape = new Circle(pointX, pointY, radius, color);
		planetShape.validPosition(frameWidth, frameHeight);

	}
	
	public Planet(double pointX, double pointY, double radius, Color color) {
		this.planetShape = new Circle(pointX, pointY, radius, color);
		double frameWidth = TestObject.WIDTH;
		double frameHeight = TestObject.HEIGHT;
		planetShape.validPosition(frameWidth, frameHeight);
		
	}
	public Planet(int shipOnPlanet, Circle planetShape) {
		this.shipOnPlanet = shipOnPlanet;
		this.planetShape = planetShape;
	}
	
	public Planet(Planet planet) {
		this(planet.shipOnPlanet, planet.planetShape);
		this.is_destination = planet.isDestination();
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
		return this.spaceshipType;
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
		return ((this.getPlanetShape().distance(p.getPlanetShape()) > 5));
	}
	

	
	@Override
	public void render(GraphicsContext gc) {
		this.planetShape.drawShape(gc);
		gc.setFill(Color.BLACK);
	
		gc.fillText(""+shipOnPlanet, planetShape.position()[0],planetShape.position()[1]);
	}
	
	 
	/**
	 * Add 1 spaceship in the planet according to production time
	 * @param s spaceship type
	 * @param gc 
	 */
/*	public void production(SpaceshipType s, GraphicsContext gc) {
		Timer timer = new Timer();
		TimerTask duplication = new TimerTask() {
			public void run() {
				shipOnPlanet++;
				System.out.println("Nb of ships :"+shipOnPlanet);
			    gc.fillText("" + shipOnPlanet, planetShape.position()[0],planetShape.position()[1] );
				
				
			}
			public void stop() {
				timer.cancel();
				timer.purge();
			}
				
			};
				timer.scheduleAtFixedRate(duplication, s.getProductionTime(), s.getProductionTime());
			
	}*/
	
	/**
	 * Decrease the number of spaceships on a planet in case of an attack
	 * Increase this number if the player has the planet
	 * @param s
	 */
	
	public void spaceShipEnter(Player p) {
			if(!(p.getTerritory().contains(this))&& this.shipOnPlanet>0) {
			this.shipOnPlanet-=1;
		}else {
			this.shipOnPlanet++;
		}
		
	}
	/**
	 * check if a planet is lose
	 * @return
	 */

	public boolean planetLose() {
		return (this.shipOnPlanet<0);
	}
	public Planet getDestination() {
		return this;
	}

	public boolean isDestination() {
		return is_destination;
	}

	public void setIs_destination(boolean is_destination) {
		this.is_destination = is_destination;
	}
	
	
	
		

	
}
