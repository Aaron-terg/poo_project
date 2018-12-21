package Controllers;

import java.io.Serializable;
import java.util.Iterator;

import Models.GameObject;
import Models.GameState;
import Models.Player;
import Models.Spacefleet;
import Models.planet.Planet;
import Models.shape.Line;
import Views.Game;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class UserInput implements Serializable{
	
	private Player user;
	public boolean action;
	public Line lineJoint;
	
	public UserInput(Player user) {
		this.user = user;
	} 
	
	public Player getUser() {
		return this.user;
	}
	
	public EventHandler<KeyEvent> keyPressed() {
		
		return new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) { 
				
				if(e.isShiftDown() && user.percent < 100) 
					user.percent+=5;
				if(e.isAltDown() && user.percent>0) 
					user.percent-=5;
				KeyCode code = e.getCode();
				if(code.equals(KeyCode.P)) {
					Game.gameState = (Game.gameState.equals(GameState.PAUSED))? GameState.RUNNING: GameState.PAUSED;
				}
				if (code.equals(KeyCode.S) && e.isControlDown()) {
					Game.gameState = GameState.SAVED;
				}
				if (code.equals(KeyCode.P) && e.isControlDown()) {
					Game.gameState = GameState.LOADED;
				}
				if (code.equals(KeyCode.ESCAPE)) {
					System.exit(0);				}
			}
		};
	}
	
	public static EventHandler<MouseEvent> mouseClicked() {

		return new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if(event.getButton().equals(MouseButton.SECONDARY)) {
					
				}
			}
		};
	}
		
	public EventHandler<MouseEvent> mousePresse(){
		return new EventHandler<MouseEvent>() {
			
			public void handle(MouseEvent e) { 	
				
				if(e.getButton().equals(MouseButton.PRIMARY)) {
					
					Iterator<Planet> planetIt = user.getTerritory().iterator();
					while(planetIt.hasNext() && !action) {
						
						Planet planet = planetIt.next();
						if(planet.getPlanetShape().isInside(e.getX(), e.getY())) {

							if(e.isControlDown()) {

								if(GameObject.selected instanceof Planet) {
									Planet currentPlanet = (Planet)Planet.selected;
									
									if(currentPlanet != planet) {
										
										Spacefleet currentSpacefleet = user.newLaunch(user.percent, currentPlanet);
										currentSpacefleet.setDestination(planet); 

									}
								}
							}else {
								System.out.println("bug not here!");

								lineJoint = new Line(planet.getX(), planet.getY());
								planet.isSelected();
							}
							action = true;
						}else 
							action = false;
					} 

					if(!action) {
						// TODO create square bound to select ship 
						Iterator<Spacefleet> spacefleetIt = user.getFleets().iterator();
						while(spacefleetIt.hasNext() && !action) {
							Spacefleet spacefleet = spacefleetIt.next();
							if(spacefleet.inside(e.getX(), e.getY())) {
								
								System.out.println("inside! wouhouuu");
								spacefleet.isSelected();
								lineJoint = new Line(spacefleet.getX(), spacefleet.getY());
								action = true;
							}
						}
					}
				}
				
				if(e.getButton().equals(MouseButton.SECONDARY)) {
					
				}
			}
		};
	}

	public EventHandler<MouseEvent> moouseDragged() {
		return new EventHandler<MouseEvent>() {

			
			public void handle(MouseEvent e) { 	
				if(e.getButton().equals(MouseButton.PRIMARY)) {

					if(action) {
						lineJoint.setPosition(e.getX(), e.getY());
					}
				}	
			}
			
		};
	}
		
	public EventHandler<MouseEvent> mouseReleased(Universe universe){
		return new EventHandler<MouseEvent>() {
			
			Spacefleet currentSpacefleet;
			Planet currentPlanet;
			@Override
			public void handle(MouseEvent e) {
				lineJoint = null;
				if(action) {
					Iterator<Planet> it = universe.getPlanets().iterator();
					while (it.hasNext()) {

						Planet planet = it.next();
						if(planet.getPlanetShape().isInside(e.getX(), e.getY())) {


							if(GameObject.selected instanceof Planet) {
								currentPlanet = (Planet)Planet.selected;

								if(currentPlanet != planet) {

									currentSpacefleet = user.newLaunch(user.percent, currentPlanet);
									currentSpacefleet.setDestination(planet); 

								}


							}
						}

					}
				}
				action = false;
				if(e.getButton().equals(MouseButton.SECONDARY)) {
					Iterator<Planet> it = universe.getPlanets().iterator();
					while (it.hasNext()) {

						Planet planet = it.next();
						if(planet.getPlanetShape().isInside(e.getX(), e.getY())) {

							System.out.println(planet);

						}
					}
				}

			}
			
		};
	}
	
}
