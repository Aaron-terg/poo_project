package controllers;

import java.io.Serializable;

import javafx.scene.paint.Color;

/**
 * The universe's parameters adjustable from the user interface.
 * 
 * @author meryl
 *
 */
public class UniverseSetting implements Serializable{
	/**
	 * <ul>
	 * <li> nbPlanet: number of planet for the universe </li>
	 * <li> nbPlayer: number of player for the game</li>
	 * </ul>
	 */
	public int nbPlanet, nbPlayer;
	
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
				Color.GREEN,
				Color.AQUAMARINE,
				Color.BLUEVIOLET,
				Color.RED
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
