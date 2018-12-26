package controllers;

import java.io.Serializable;

import javafx.scene.paint.Color;

public class UniverseSetting implements Serializable{
	public int nbPlanet, nbPlayer;
	public boolean humanPlayer;
	public transient Color[] color;
	
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
