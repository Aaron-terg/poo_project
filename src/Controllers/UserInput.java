package Controllers;

import java.util.Iterator;

import Models.Player;
import Models.Spacefleet;
import Models.Universe;
import Models.planet.Planet;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class UserInput {
	
	private Scene scene;
	private Universe universe;
	private Player user;
	
	public UserInput(Scene scene, Player user) {
		this.scene = scene;
		this.user = user;
		
	}
	
	public Player getUser() {
		return this.user;
	}
	
	public void gameControl(Universe universe) {
		EventHandler<KeyEvent> keyEvent = new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) { 
				if(e.isShiftDown() && user.percent<100) 
					user.percent+=5;
				
				if(e.isControlDown()&&user.percent>0) 
					user.percent-=5;

				
			}
			
		};
		scene.setOnKeyPressed(keyEvent);
	
		EventHandler<MouseEvent> mouseEvent = new EventHandler<MouseEvent>() {
			boolean fleetSet = false;
			int indexSpacefleet = 0;
			Spacefleet currentSpacefleet;
			Planet currentPlanet;
			public void handle(MouseEvent e) { 	
				if(e.getButton().equals(MouseButton.PRIMARY)) {
					Iterator<Planet> planetIt = user.getTerritory().iterator();
					boolean isFound = false;
					while(planetIt.hasNext() && !isFound) {
						Planet p = planetIt.next();
						if(p.getPlanetShape().isInside(e.getX(), e.getY())) {
							currentPlanet = p;
							user.newLaunch(user.percent, p);
							indexSpacefleet = user.getFleets().size() -1;
							currentSpacefleet = user.getFleets().get(indexSpacefleet);
							fleetSet = true;
							isFound = true;
						}
					}
					if(!isFound) {
						Iterator<Spacefleet> spacefleetIt = user.getFleets().iterator();
						while(spacefleetIt.hasNext() && !fleetSet) {
							Spacefleet spacefleet = spacefleetIt.next();
							if(spacefleet.inside(e.getX(), e.getY())) {
								
								currentSpacefleet = spacefleet;
								fleetSet = true;
							}
						}
					}
				}
				
				if(fleetSet && e.getButton().equals(MouseButton.SECONDARY)) {
					Iterator<Planet> it = universe.getPlanets().iterator();
					while (it.hasNext()) {
						Planet planet = it.next();
						if(planet.getPlanetShape().isInside(e.getX(), e.getY())) {
							indexSpacefleet = currentSpacefleet.getIndex();
							if(indexSpacefleet >= user.getFleets().size())
								currentSpacefleet.setIndex(--indexSpacefleet);
							currentSpacefleet.setDestination(planet); //user.getFleets().get(indexSpacefleet)
							currentPlanet.nbShipOnPlanet(-currentSpacefleet.fleetSize());
							fleetSet = false;

						}

					}
				}
			}};
		
		scene.setOnMouseClicked(mouseEvent);
	}
	
}
