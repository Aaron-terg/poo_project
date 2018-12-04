package Models;

import java.util.ArrayList;

import Models.Spaceship.SpaceshipType;
import Models.planet.Planet;

public class Spacefleet {
	
	private ArrayList<SpaceshipType> spaceships;
	private Planet start, destination; 
	private double[][] checkpoint; // tableau de point 
	
	public Spacefleet(int nbShip, SpaceshipType spaceshipType,Planet start) {
		this.spaceships = new ArrayList<>();
		while(nbShip > 0) {
			try {
				SpaceshipType spaceship = spaceshipType.getClass().newInstance();
				spaceship.getSpaceshipShape().setPosition(start.getPlanetShape().getX(), start.getPlanetShape().getY());
				spaceships.add(spaceship);
			} catch (InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				nbShip--;
			}
		}
		this.start = start;
		//this.destination = destination;
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
	
}
