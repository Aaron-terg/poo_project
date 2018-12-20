package Models;

import java.util.ArrayList;
import java.util.Iterator;

import Models.Spaceship.SpaceshipType;
import Models.planet.Planet;
import javafx.scene.canvas.GraphicsContext;

public class Spacefleet extends GameObject {
	
	private ArrayList<SpaceshipType> spaceships;
	private Planet start, destination; 
	private int nbShip, nbWave, nbShipToSend, nbShipSend;
	private double angle;
	
	public Spacefleet(int nbShip, Planet start) {
		this.spaceships = new ArrayList<>();
		this.nbShip = nbShip;
		this.start = start;
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
		angle*=Math.PI/180;
		nbWave = Math.round(nbShip / nbShipToSend);
	}
	
	public int fleetSize() {
		return nbShipSend;
	}
	
	public int getNbWave() {
		return this.nbWave;
	}
	
	public ArrayList<SpaceshipType> fleet(){
		return spaceships;
	}
	
	public Planet getDestination() {
		return destination;
	}
	
	public void setDestination(Planet planet) {
		this.destination = planet;
	}
	
	public boolean hasDestination() {
		return destination != null;
	}
	
	public boolean inside(double x, double y) {
		boolean result = false;
		Iterator<SpaceshipType> spaceshipIt = spaceships.iterator();
		while(spaceshipIt.hasNext() && !result) {
			SpaceshipType spaceship = spaceshipIt.next();
			result = spaceship.isInside(x, y);
			if(result) {
				this.x = spaceship.x;
				this.y = spaceship.y;
			}
				
		}
		return result;
	}
	
	public void takeOff() {
		System.out.println("takeOff: " + nbWave);
		if(nbWave > 0) {
			

			double[] spaceport = new double[2];
			double radius = start.width;
			
			int ShipToSend = (nbShipSend + nbShipToSend > nbShip)? nbShip - nbShipSend : nbShipToSend;
			nbShipSend += ShipToSend;
			start.nbShipOnPlanet(-ShipToSend);
			while(ShipToSend > 0) {
				try {
					
					spaceport[0] = (radius+15)*Math.cos(ShipToSend*angle) + start.x;
					spaceport[1] = -(radius+15)*Math.sin(ShipToSend*angle*angle) + start.y;
					
					SpaceshipType spaceship = start.getSpaceShipeType().getClass().newInstance();
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
	
	public boolean isArrived() {
		return spaceships.isEmpty();
	}
	
	public void render(GraphicsContext gc) {
		for (Iterator<SpaceshipType> spaceshipsIt = spaceships.iterator(); spaceshipsIt.hasNext();) {
			SpaceshipType spaceship =spaceshipsIt.next();
			spaceship.render(gc);
			
		}
	}
	
}
