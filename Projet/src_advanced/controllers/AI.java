package controllers;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Random;

import javafx.scene.paint.Color;
import models.Player;
import models.Spacefleet;
import models.planet.Planet;
import models.spaceship.BasicSpaceshipType;
import models.spaceship.FastSpaceship;
import models.spaceship.SpaceshipType;
import models.spaceship.StrongSpaceShip;
import views.Game;

/**
 * <b>The AI allows the user to measure himself to a challenging AI</b>
 *  <p> AI extends {@link Player} </p>
 *  <p> AI implements {@link Serializable}</p>
 *  
 * @author meryl
 * @see Player
 * @see Universe
 * @see Planet
 * @version src_basic
 *
 */
public class AI extends Player implements Serializable{

	/**
	 * The universe containing the set of planets
	 * @see Universe#getPlanets()
	 */
	public Universe universe; 
	
	/**
	 * an intermediary Planet variable for decision making
	 * 
	 * @see AI#expansion()
	 * @see AI#endGameAttack()
	 */
	private Planet nextTarget;
	
	/**
	 * The distance between the current planet selected and the targetted planet.
	 * 
	 * @see AI#expansion()
	 */
	private double nextTargetDistance;
	
	/**
	 * A boolean marker verifying the availability of every  planet of the universe.
	 * If it true, then start the phase 2 of the attack
	 */
	private boolean allOwned;
	
	/**
	 * AI Constructor for the autonomous spacefleet.
	 * @param universe the universe containing the set of planet
	 */
	public AI(Universe universe) {
		super("PIRATE_AI", Color.DARKCYAN);
		this.universe = universe;
	}

	/**
	 * The AI constructor calling the super 
	 * constructor and setting the universe, the boolean and intermediary planet variable
	 * @param universe
	 * @param string
	 * @param rgb
	 */
	public AI(Universe universe, String playerName, Color rgb) {
		super(playerName, rgb);
		this.universe = universe;
		this.firstPlanet(universe);
		nextTarget = universe.getPlanets().get(0);
		allOwned = false;
		nextTargetDistance = Game.HEIGHT + Game.WIDTH;
	}

	/**
	 * launch an attack every time this function is called 
	 * @see Game#update()
	 */
	@Override
	public boolean inAction() {
		if(this.playerTag != "PIRATE_AI") 
			expansion();
		return super.inAction();
	}
	
	/**
	 * the principal "algorithm" for the decision making of the AI
	 * it iterates over the universe's set of planets and then tests if there is one owned near or not
	 */
	public void expansion() {
		
		boolean currentTest = true;
		//prioritize the planet not owned
		for (Iterator<Planet> univ = universe.getPlanets().iterator(); univ.hasNext();) {
			Planet planet = (Planet) univ.next();
			if(allOwned){
				for (Iterator<Planet> territoryIt = this.territory.iterator(); territoryIt.hasNext();) {
					Planet planetOwned = territoryIt.next();
					Spacefleet spacefleet;
					
					if(planetOwned.nbShipOnPlanet() > 40) {
						newPercent(planetOwned.nbShipOnPlanet());
						spacefleet = newLaunch(percent, planetOwned);
						if(spacefleet != null) {
							double newDist = planet.distanceCarre(spacefleet.getX(), spacefleet.getY());
							if(!planet.owner().equals((Player)this) || newDist < nextTargetDistance) {
								nextTarget = planet;
								nextTargetDistance = newDist;
	
							}
							spacefleet.setDestination(nextTarget);
						}
					}
				}
			}
			else{
				currentTest &= planet.isOwn();
				for (Iterator<Planet> territoryIt = this.territory.iterator(); territoryIt.hasNext();) {
					Planet planetOwned = territoryIt.next();
					Spacefleet spacefleet;
					
					if(planetOwned.nbShipOnPlanet() > 40) {
						newPercent(planetOwned.nbShipOnPlanet());
						spacefleet = newLaunch(percent, planetOwned);
						if(spacefleet != null && !nextTarget.isOwn()) {
							double newDist = planet.distanceCarre(spacefleet.getX(), spacefleet.getY());

							if((!nextTarget.isOwn() || nextTarget.owner().equals((Player)this)) || newDist < nextTargetDistance){
								nextTarget = planet;
								nextTargetDistance = newDist;
							}
							
							spacefleet.setDestination(nextTarget);
							
						}
					}
				}
			}
		}
		allOwned |= currentTest;
		//if there is a planet nearer take it
		for (Iterator<Spacefleet> spaceIt = spacefleets.iterator(); spaceIt.hasNext();) {
			Spacefleet spacefleet = (Spacefleet) spaceIt.next();
			for (Iterator<Planet> univ = universe.getPlanets().iterator(); univ.hasNext();) {
				Planet planet = (Planet) univ.next();
				if(!planet.isOwn()) {
					double newDist = planet.distanceCarre(spacefleet.getX(), spacefleet.getY());

					if((!nextTarget.isOwn() || nextTarget.owner().equals((Player)this)) ||newDist < nextTargetDistance){
						nextTarget = planet;
						nextTargetDistance = newDist;
					}

					spacefleet.setDestination(nextTarget);
				}
			}
		}
	}
	
	/**
	 * choose a percentage randomly given a bound which is the max Spacefleet the planet owned
	 * @param bound the bound for the random picking
	 */
	public void newPercent(int bound) {
		Random rand = new Random();
		percent= rand.nextInt(bound);
	}

	/**
	 * The algorithm managing the autonomous spacefleet.
	 * It create a spacefleet of 10 ships or less.
	 * call its own spacefleet constructor designed for him
	 * 
	 * @see Spacefleet#Spacefleet(double, double, Player, int, Planet, SpaceshipType)
	 */
	public void randomAttack() {
		Random random = new Random();

		if(random.nextInt(100) < 33) {
			int indexPlanet = random.nextInt(universe.getPlanets().size());
			int nbShip = random.nextInt(10) + 1;
			int typeOfSpaceShip = random.nextInt(3);
			nextTarget = universe.getPlanets().get(indexPlanet);
			SpaceshipType spaceshipType;
			
			switch(typeOfSpaceShip) {
				case 0:
					spaceshipType = new BasicSpaceshipType();
					break;
				case 1:
					spaceshipType = new StrongSpaceShip();
					break;
				case 2:
					spaceshipType = new FastSpaceship();
					break;
				default:
					spaceshipType = new BasicSpaceshipType();
			}
			double posX = random.nextDouble()*Game.WIDTH,
					posY = random.nextDouble()*Game.HEIGHT;
			Spacefleet spacefleet = new Spacefleet(posX, posY, this, nbShip, nextTarget, spaceshipType);
			this.spacefleets.add(spacefleet);
		}
	}
	
}
