package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import controllers.UserInput;
import javafx.scene.canvas.GraphicsContext;
import models.shape.Renderable;

/**
 * <b>A commando of spaceship braving the universe to conquer planets</b>
 * <p> Spacefleet extends {@link GameObject}</p>
 *  <p> Spacefleet implements {@link Renderable}, {@link Serializable}</p>
 *  
 *  @see GameObject
 *  @see SpaceshipType
 *  @see Planet
 *  @see Player
 *  @see UserInput
 * @author meryl, Virginie
 * @version src_basic
 * @since src_basic
 *
 */
public class Spacefleet extends GameObject implements Renderable, Serializable {
	
	/**
	 * the set of spaceship of the fleet
	 * @see SpaceshipType
	 */
	private ArrayList<SpaceshipType> spaceships;
	
	/**
	 * THe planet of departure and the destination of the fleet
	 * 
	 * @see Planet
	 */
	private Planet start, destination; 
	
	/**
	 * <ul>
	 * <li>nbShip: the total number of spaceships defined by the percentage</li>
	 * <li>nbWave: the number of launch needed to send nbShipToSend in order to reach the nbShip</li>
	 * <li>nbShipToSend: the number of ships to send by wave defined with the radius of the starting planet</li>
	 * <li>nbShipSend: the number of ship sent </li>
	 * </ul>
	 */
	private int nbShip, nbWave, nbShipToSend, nbShipSend;
	
	/**
	 * the angle between each gate of the starting planet defined by its radius.
	 */
	private double angle; 
	
	/**
	 * the spaceship type of the spacefleet to avoid conflict while the starting planet change of spaceship type.
	 * 
	 * @since src_advanced
	 */
	private SpaceshipType spaceshipType;
	
	/**
	 * Spacefleet constructor.<br/>
	 * set a new fleet with a total of ship and a starting planet.<br/>
	 * It also defined the number of waves and number of ships to be sent from the size of the planets.
	 * @param nbShip the number of spaceship to be sent (bound by the number of spaceships on the starting planet)
	 * @param start  planet of departure
	 */
	public Spacefleet(int nbShip, Planet start) {
		super(start.getX(), start.getY());
		this.spaceships = new ArrayList<>();
		this.nbShip = Math.min(nbShip, start.nbShipOnPlanet());
		this.start = start;
		this.spaceshipType = start.getSpaceShipeType();
		double radius = start.width;
		angle = 0;
		nbShipToSend = 0;
		nbShipSend = 0;
		
		if(radius <= 10) {
			nbShipToSend = 4;
			angle = 90;
		}
		else if(radius <= 50 ) {
			angle = 45;
			nbShipToSend = 8;

		}
		else {
			angle = 30;
			nbShipToSend = 12;
		}
		nbShipToSend = Math.min(nbShipToSend, this.nbShip);
		
		start.nbShipOnPlanet(-this.nbShip);

		angle*=Math.PI/180;
		nbWave = Math.round(this.nbShip / nbShipToSend);
	}
	
	/***********************************\
	 * 								   *
	 * 				Method			   *
	 * 								   *
	\***********************************/

	/**
	 * Get the current number of ships already sent
	 * @return the current number of ship in the fleet
	 */
	public int fleetSize() {
		return nbShipSend;
	}
	
	/**
	 * @return the number of waves left
	 */
	public int getNbWave() {
		return this.nbWave;
	}
	
	/**
	 * Get the set of spaceships sent
	 * @return an ArrayList of spaceship
	 */
	public ArrayList<SpaceshipType> fleet(){
		return spaceships;
	}
	
	/**
	 * Get the destination of the fleet
	 * @return A planet
	 */
	public Planet getDestination() {
		return destination;
	}
	
	/**
	 * Set the new destination of the fleet
	 * @param planet the new destination of the fleet
	 */
	public void setDestination(Planet planet) {
		this.destination = planet;
	}
	
	/***********************************\
	 * 								   *
	 * 				Method			   *
	 * 								   *
	\***********************************/

	/**
	 * Check if the fleet has a destination
	 * @return true if the spaceship destination is set
	 */
	public boolean hasDestination() {
		return destination != null;
	}
	
	/**
	 * Check if the couple of coordinates is inside one of the spaceships
	 * @param x the x coordinate 
	 * @param y the y coordinate
	 * @return true if the couple of coordinates is inside one of the spaceships
	 */
	public boolean inside(double x, double y) {
		boolean result = false;
		Iterator<SpaceshipType> spaceshipIt = spaceships.iterator();
		while(spaceshipIt.hasNext() && !result) {
			SpaceshipType spaceship = spaceshipIt.next();
			result |= spaceship.isInside(x, y);
			
			if(result) {
				this.isSelected();
				this.x = spaceship.getX();
				this.y = spaceship.getY();
			}
				
		}
		return result;
	}
	
	/**
	 * Send a number of ships defined in the constructor every time the method is called and the number of wave left is >0.
	 * The spaceships are sent from "gate" around the planet specify by the angle and radius of the planet.
	 * 
	 * @see Spacefleet#Spacefleet(int, Planet)
	 * @see Spacefleet#angle
	 * @see Spacefleet#nbWave
	 * 
	 */
	public void takeOff() {
		
		if(nbWave > 0) {
			
			// create the gate for the departure of the spaceship
			double[] spaceport = new double[2];
			double radius = start.width / 2;
			
			int ShipToSend = (nbShipSend + nbShipToSend > nbShip)? nbShip - nbShipSend : nbShipToSend;
			nbShipSend += ShipToSend;
			
			while(ShipToSend > 0) {
				try {
					
					// Positioned the gate around the planet
					spaceport[0] = (radius+15)*Math.cos(ShipToSend*angle) + start.getX();
					spaceport[1] = -(radius+15)*Math.sin(ShipToSend*angle) + start.getY();
					
					// instantiate a new spaceship from the type of the departure planet
					SpaceshipType spaceship = this.spaceshipType.getClass().newInstance();
					spaceship.x = spaceport[0];
					spaceship.y = spaceport[1];
					spaceship.getSpaceshipShape().setPosition(spaceport[0], spaceport[1]);
					spaceship.setPlayer(start.owner());
					spaceships.add(spaceship);
				} catch (InstantiationException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					ShipToSend--;
				}
			}
			nbWave--;
			
		}
	}
	
	/**
	 * Check if the fleet is arrived to destination
	 * @return true of the spaceship is empty
	 */
	public boolean isArrived() {
		return spaceships.isEmpty();
	}
	
	/**
	 * Render the spaceship of the fleet.<br>
	 * iterates over its fleets array to render every spaceship in it.
	 * 
	 * @see Spacefleet#fleet()
	 */
	public void render(GraphicsContext gc) {
		if(this.equals(selected)) 
			gc.setLineWidth(5f);
		else
			gc.setLineWidth(1f);
		
		for (Iterator<SpaceshipType> spaceshipsIt = spaceships.iterator(); spaceshipsIt.hasNext();) {
			SpaceshipType spaceship =spaceshipsIt.next();
			spaceship.render(gc);
			
		}
		gc.setLineWidth(1f);
	}
	
	@Override
	public String toString() {
		return "starting point: \n\t" + start
				+ "destination: \n\t" + destination
				+"nbSp: " + nbShip + "\n";
	}
}
