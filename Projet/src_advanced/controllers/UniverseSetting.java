package controllers;

import java.io.Serializable;

import javafx.scene.paint.Color;
import models.Player;
import views.ui.UserInterface;

/**
 * <b>The universe's parameters adjustable from the user interface.</b>
 * <p> Universe implements{@link Serializable}</p>
 *
 * @see Universe
 * @see Player
 * @see Planet
 * @see UserInterface
 * @see UserInput
 * @author meryl
 *
 */
public class UniverseSetting implements Serializable{
	/**
	 * <ul>
	 * <li> nbPlanet: number of planets for the universe </li>
	 * <li> nbPlayer: number of players for the game</li>
	 * </ul>
	 */
	public int nbPlanet, nbPlayer;
	/**
	 * The max number allowed for the src_advanced
	 */
	public final static int PLANET_MAX = 10;
	
	/**
	 * Defined if there is a need for a user controller.
	 */
	public boolean humanPlayer;
	
	/**
	 * a set of color for the players
	 */
	public transient Color[] color;
	
	/**
	 * Universe setting constructor
	 * @param nbPlanet
	 * @param nbPlayer
	 * @param humanPlayer boolean taking true if there is a non cpu player.
	 */
	public UniverseSetting(int nbPlanet, int nbPlayer, boolean humanPlayer) {
		this.nbPlanet = nbPlanet;
		this.nbPlayer = nbPlayer;
		this.humanPlayer = humanPlayer;
		color = new Color[] {
				Color.RED,
				Color.GREEN,
				Color.AQUAMARINE,
				Color.BLUEVIOLET
				
		};
	}
	 
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "current setting: \n"
		+"\tnb planet: " + nbPlanet + "\n"
		+ "\tnbPlayer: " + nbPlayer + "\n"
		+ "\thuman player: " + humanPlayer + "\n";
	}
}
