package Models;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.event.CellEditorListener;

import Models.Spaceship.SpaceshipType;
import Models.planet.Planet;

public class Spacefleet {
	
	private ArrayList<SpaceshipType> spaceships;
	private Planet start, destination; 
	private int index, nbShip, nbWave, nbShipToSend, nbShipSend = 0;
	private double angle;
	
	public Spacefleet(int nbShip, Planet start, int index) {
		this.spaceships = new ArrayList<>();
		this.nbShip = nbShip;
		this.index = index;
		this.start = start;
		double radius = start.width;
		angle = 0;
		nbShipToSend = 0;

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
		return nbShip;
	}
	
	public int getIndex() {
		return this.index;
	}
	
	public void setIndex(int index) {
		this.index = index;
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
	
	public boolean inside(double x, double y) {
		boolean result = false;
		Iterator<SpaceshipType> spaceshipIt = spaceships.iterator();
		while(spaceshipIt.hasNext() && !result) {
			SpaceshipType spaceship = spaceshipIt.next();
			result = spaceship.isInside(x, y);
		}
		return result;
	}
	
	public void takeOff() {
		if(nbWave > 0) {
			System.out.println("takeOff");
			double[] spaceport = new double[2];
			double radius = start.width;
			
			nbShipToSend = (nbShipSend + nbShipToSend > nbShip)? nbShip - nbShipSend : nbShipToSend;
			nbShipSend += nbShipToSend;
			while(nbShipToSend > 0) {
				try {
					
					spaceport[0] = (radius+15)*Math.cos(nbShipToSend*angle) + start.x;
					spaceport[1] = -(radius+15)*Math.sin(nbShipToSend*angle*angle) + start.y;
					
					SpaceshipType spaceship = start.getSpaceShipeType().getClass().newInstance();
					spaceship.getSpaceshipShape().setPosition(spaceport[0], spaceport[1]);
					spaceship.setPlayer(start.owner());
					spaceships.add(spaceship);
				} catch (InstantiationException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					nbShipToSend--;
				}
			}
			nbWave--;
			
		}
	}
	
	public boolean isArrived() {
		return spaceships.isEmpty();
	}
	
}
