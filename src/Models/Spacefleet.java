package Models;

import java.util.ArrayList;
import java.util.Iterator;

import Models.Spaceship.SpaceshipType;
import Models.planet.Planet;

public class Spacefleet {
	
	private ArrayList<SpaceshipType> spaceships;
	private Planet destination; 
	private int index;
	private int nbShip;
	
	public Spacefleet(int nbShip, Planet start, int index) {
		this.spaceships = new ArrayList<>();
		this.nbShip = nbShip;
		double radius = start.width;
		double angle = 100000/radius;
		double[] spaceport = new double[2];
		while(nbShip > 0) {
			try {
				
				spaceport[0] = (radius+15)*Math.cos(nbShip*angle*Math.PI/180) + start.x;
				spaceport[1] = -(radius+15)*Math.sin(nbShip*angle*angle*Math.PI/180) + start.y;
				
				SpaceshipType spaceship = start.getSpaceShipeType().getClass().newInstance();
				spaceship.getSpaceshipShape().setPosition(spaceport[0], spaceport[1]);
				spaceship.setPlayer(start.owner());
				spaceships.add(spaceship);
			} catch (InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				nbShip--;
			}
		}
		this.index = index;
	}
	
	public int fleetSize() {
		return nbShip;
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
		Iterator<SpaceshipType> spaceshipIt = spaceships.iterator();
		while(spaceshipIt.hasNext() && !result) {
			SpaceshipType spaceship = spaceshipIt.next();
			result = spaceship.isInside(x, y);
		}
		return result;
	}
	
	public boolean isArrived() {
		return spaceships.isEmpty();
	}
	
}
