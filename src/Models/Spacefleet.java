package Models;

import java.util.ArrayList;
import java.util.Iterator;

import Models.Spaceship.SpaceshipType;
import Models.planet.Planet;

public class Spacefleet {
	
	private ArrayList<SpaceshipType> spaceships;
	private Planet start, destination; 
	private double[][] checkpoint; // tableau de point
	private int index;
	
	public Spacefleet(int nbShip, SpaceshipType spaceshipType,Planet start, int index) {
		this.spaceships = new ArrayList<>();
		while(nbShip > 0) {
			try {
				SpaceshipType spaceship = spaceshipType.getClass().newInstance();
				spaceship.getSpaceshipShape().setPosition(start.getPlanetShape().getX(), start.getPlanetShape().getY());
				spaceship.setPlayer(spaceshipType.getPlayer());
				spaceships.add(spaceship);
			} catch (InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				nbShip--;
			}
		}
		this.start = start;
		this.index = index;
		//this.destination = destination;
	}
	
	public int getIndex() {
		return this.index;
	}
	
	public void setIndex(int index) {
		this.index = index;
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
	
	public boolean inside(double x, double y) {
		boolean result = false;
		for (Iterator spaceshipIt = spaceships.iterator(); spaceshipIt.hasNext();) {
			SpaceshipType spaceship = (SpaceshipType) spaceshipIt.next();
			result |= spaceship.getSpaceshipShape().isInside(x, y);
			if(result)
				break;
		}
		return result;
	}
	
	public boolean isArrived() {
		return spaceships.isEmpty();
	}
	
}
