package controllers;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Random;

import javafx.scene.paint.Color;
import models.Planet;
import models.Player;
import models.Spacefleet;
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
							double newDist = planet.distance(spacefleet.getX(), spacefleet.getY());
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
							double newDist = planet.distance(spacefleet.getX(), spacefleet.getY());

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
					double newDist = planet.distance(spacefleet.getX(), spacefleet.getY());

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

}
