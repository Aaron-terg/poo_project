package Controllers;

import java.util.Iterator;

import Models.Player;
import Models.Spacefleet;
import Models.Universe;
import Models.planet.Planet;
import Models.shape.Line;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class UserInput {
	
	private Scene scene;
	private Player user;
	public boolean action;
	public Line lineJoint;
	
	public UserInput(Scene scene, Player user) {
		this.scene = scene;
		this.user = user;
	}
	
	public Player getUser() {
		return this.user;
	}
	
	public void gameControl(Universe universe) {
		
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) { 
				if(e.isShiftDown() && user.percent<100) 
					user.percent+=5;
				
				if(e.isControlDown()&&user.percent>0) 
					user.percent-=5;

				
			}
			
		});
	
		scene.setOnMousePressed(new EventHandler<MouseEvent>() {
			
			
			public void handle(MouseEvent e) { 	
				if(e.getButton().equals(MouseButton.PRIMARY)) {
					Iterator<Planet> planetIt = user.getTerritory().iterator();
					while(planetIt.hasNext() && !action) {
						Planet p = planetIt.next();
						if(p.getPlanetShape().isInside(e.getX(), e.getY())) {
							
							action = true;
							lineJoint = new Line(p.getX(), p.getY());
							p.isSelected();
						}
					} 
					if(!action) {
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
		});

		scene.setOnMouseDragged(new EventHandler<MouseEvent>() {

			
			public void handle(MouseEvent e) { 	
				if(e.getButton().equals(MouseButton.PRIMARY)) {
					if(action)
						lineJoint.setPosition(e.getX(), e.getY());
				}	
			}
			
		});
		
		scene.setOnMouseReleased( new EventHandler<MouseEvent>() {
			Spacefleet currentSpacefleet;
			Planet currentPlanet;
			
			@Override
			public void handle(MouseEvent e) {
				
				lineJoint = null;
				action = false;
				
				Iterator<Planet> it = universe.getPlanets().iterator();
				while (it.hasNext()) {
					Planet planet = it.next();
					if(planet.getPlanetShape().isInside(e.getX(), e.getY())) {
						if(Planet.selected instanceof Planet) {
							currentPlanet = (Planet)Planet.selected;
							currentSpacefleet = user.newLaunch(user.percent, currentPlanet);
							currentSpacefleet.setDestination(planet); 
						}
					}

				}
				
			}
			
		});
	}
	
}
