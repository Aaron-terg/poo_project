package Controllers;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Random;

import Models.Player;
import Models.Spacefleet;
import Models.planet.Planet;
import javafx.scene.paint.Color;

/**
 * The AI allow the user to measure himself to a challenging AI
 * @author meryl
 * @see Player
 * @see Universe
 * @see Planet
 * @version src_basic
 *
 */
public class AI extends Player implements Serializable{

	/**
	 * The universe containing the set of planet
	 * @see Universe#getPlanets()
	 */
	public Universe universe; 
	/**
	 * an intermediary Planet variable for decision making
	 * 
	 * @see AI#attack()
	 * @see AI#attackPhase2()
	 */
	private Planet nextTarget;
	
	/**
	 * A boolean marker to if every  planet of the universe is owned.
	 * If it does, then start the phase 2 of the attack
	 */
	private boolean allOwned;
	
	/**
	 * AI Constructor
	 * @param universe the universe containing the set of planet
	 */
	public AI(Universe universe) {
		this(universe, "IA_test", Color.DARKCYAN);
	}

	/**
	 * The AI constructor calling the super 
	 * constructor and setting the universe, the boolean and intermediary planet variable
	 * @param universe
	 * @param string
	 * @param rgb
	 */
	public AI(Universe universe, String string, Color rgb) {
		super(string, rgb);
		this.universe = universe;
		nextTarget = universe.getPlanets().get(0);
		allOwned = false;
	}

	/**
	 * launch an attack every time this function is called 
	 * @see Game#update()
	 */
	@Override
	public boolean inAction() {
		if(allOwned)
			attackPhase2();
		attack();
		return super.inAction();
	}
	
	/**
	 * the principal "algorithm" for the decision making of the AI
	 * it iterate over the universe's set of planet and then test if their is own near and not owned
	 */
	public void attack() {
		
		//prioritize the planet not owned
		for (Iterator univ = universe.getPlanets().iterator(); univ.hasNext();) {
			Planet planet = (Planet) univ.next();
			allOwned &= planet.isOwn();
			for (Iterator<Planet> territoryIt = this.territory.iterator(); territoryIt.hasNext();) {
				Planet planetOwned = territoryIt.next();
				Spacefleet spacefleet;
				
				if(planetOwned.nbShipOnPlanet() > 40) {
					newPercent(planetOwned.nbShipOnPlanet());
					spacefleet = newLaunch(percent, planetOwned);
					if(!planetOwned.owner().equals(planet.owner())) {
						if(!nextTarget.isOwn() || nextTarget.owner().equals((Player)this) || planet.distance(spacefleet.getX(), spacefleet.getY()) < nextTarget.distance(spacefleet.getX(), spacefleet.getY())){
							nextTarget = planet;
						}
						
						spacefleet.setDestination(nextTarget);
					}
				}
			}
		}
		
		//if there is a planet nearer take it
		for (Iterator spaceIt = spacefleets.iterator(); spaceIt.hasNext();) {
			Spacefleet spacefleet = (Spacefleet) spaceIt.next();
			for (Iterator univ = universe.getPlanets().iterator(); univ.hasNext();) {
				Planet planet = (Planet) univ.next();
				if(!planet.isOwn()) {
					if(planet.distance(spacefleet.getX(), spacefleet.getY()) < nextTarget.distance(spacefleet.getX(), spacefleet.getY())){
						nextTarget = planet;
					}

					spacefleet.setDestination(nextTarget);
				}
			}
		}
	}
	
	/**
	 * The phase two should fix the freeze of the AI at the end game
	 * TODO
	 */
	public void attackPhase2() {
		
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
