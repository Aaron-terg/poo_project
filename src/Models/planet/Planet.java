package Models.planet;

import Models.Player;
import Models.Spaceship.SpaceshipType;
import Models.shape.Circle;
import Models.shape.Renderable;
import Models.shape.Shape;
import javafx.scene.canvas.GraphicsContext;

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
 * @see PlanetState
 * @see SpaceshipType
 * @see Player
 * 
 * @author meryl
 * @version 2.1
 *
 */
public class Planet implements Renderable{

	/**
	 * The number of spaceship on the planet.
	 * 
	 * @see PlanetState
	 * @see Planet#owner()
	 * @see Planet#newOwner(PlanetState)
	 */
	private PlanetState planetState = new Neutral();
	
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
	
	private Shape planetShape;

	/**
	 * Planet Constructor
	 * 
	 */
	
	public Planet() {
		if(isOwn())
			this.productionRate = (int)((float)1/spaceshipType.getProductionTime());
		
	}
	public Planet(double x, double y, double r, int w, int h) {
		this.planetShape = new Circle(x,y,r,100,100,100, w, h);
		planetShape.validPosition();
		
	}
	/***********************************\
	 * 								   *
	 * 			Getter/Setter		   *
	 * 								   *
	\***********************************/

	/**
	 * set the state of the planet.
	 * <p>
	 * 	Specify if the planet is owned or get neutral
	 * </p>
	 * @param state
	 * 			The new state of the planet.
	 * 
	 * @see PlanetState
	 * @see Owned
	 * @see Neutral
	 * @see Planet#planetState
	 * 
	 */
	public void setPlanetState(PlanetState state) {
		this.planetState = state;
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
	public int NbShipOnPlanet() {
		return this.shipOnPlanet;
	}
	
	/**
	 * Get the Models.shape of the planet.
	 * 
	 * @return the Models.shape of the planet.
	 * 
	 * @see javafx.scene.shape
	 * @see Planet#planetShape
	 */
	public Shape getPlanetShape() {
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
	public void setPlanetShape(Shape shape) {
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
		return (this.planetState instanceof Owned); 
	}
	
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
		return planetState.getOwner();
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
		this.planetState.setOwner(player);
	}

	@Override
	public void render(GraphicsContext gc) {
		this.planetShape.drawShape(gc);
	}
	public boolean superimposed(Planet p) {
		return(!(this.planetShape.intersects(p.planetShape)));
	}
	
	
}
