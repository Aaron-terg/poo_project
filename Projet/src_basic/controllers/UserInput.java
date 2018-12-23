package controllers;

import java.io.Serializable;
import java.util.Iterator;

import models.GameObject;
import models.GameState;
import models.Player;
import models.Spacefleet;
import models.spaceship.SpaceshipType;
import models.planet.Planet;
import models.shape.Line;
import views.Game;
import javafx.event.EventHandler;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

/**
 * <b>User control utilities</b>
 * <p> The UserInput class define the control provide to the user in {@link javafx}</p>
 * 
 * @author meryl, Virginie
 * @since src_basic
 */
public class UserInput implements Serializable{
	
	/**
	 * The user being granted the following control
	 * 
	 * @see UserInput#keyPressed()
	 * @see UserInput#mousePressed()
	 * @see UserInput#mouseDragged()
	 * @see UserInput#mouseReleased(Universe)
	 * @see UserInput#mouseClicked()
	 */
	private Player user;
	
	/**
	 * specify if the user is active
	 */
	public boolean action;
	
	/**
	 * A line joining the selecting planet and the mouse while the player is dragging away the mouse.
	 * 
	 * @see Line
	 * @see UserInput#mouseDragged()
	 */
	public Line lineJoint;
	/**
	 * UserInput constructor
	 * 
	 * @param user The user to be grant the javafx control
	 */
	public UserInput(Player user) {
		this.user = user;
	} 
	
	/**
	 * Get the user of this controller
	 * @return the owner of the controller
	 */
	public Player getUser() {
		return this.user;
	}
	
	/***********************************\
	 * 								   *
	 * 				Method			   *
	 * 								   *
	\***********************************/

	/**
	 * The keyboard listener manager
	 * @return a {@link KeyEvent} to be assign to a scene
	 * @see Game
	 */
	public EventHandler<KeyEvent> keyPressed(EventHandler<KeyEvent> keyEv) {
		
		return new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) { 
				keyEv.handle(e);
				if(e.isShiftDown() && user.percent < 100) 
					user.percent+=5;
				if(e.isAltDown() && user.percent>0) 
					user.percent-=5;
					
			}
		};
	}
	
	/**
	 * The click listener for the game
	 * @return a {@link MouseEvent} to be assign to a scene
	 * 
	 * @see Game
	 */
	public static EventHandler<MouseEvent> mouseClicked() {

		return new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if(event.getButton().equals(MouseButton.SECONDARY)) {
					
				}
			}
		};
	}
	
	/**
	 * The mouse pressed listener for the game.
	 * All the selection of the user's planet are done here
	 * 
	 * @return a {@link MouseEvent} to be assign to a scene
	 * @see Game
	 */	
	public EventHandler<MouseEvent> mousePressed(){
		return new EventHandler<MouseEvent>() {
			
			public void handle(MouseEvent e) { 	
				
				if(e.getButton().equals(MouseButton.PRIMARY)) {
					
					//iterate over territory
					Iterator<Planet> planetIt = user.getTerritory().iterator();
					while(planetIt.hasNext() && !action) {
						
						Planet planet = planetIt.next();
						if(planet.isInside(e.getX(), e.getY())) {

							// special shortcut for user planet only
							if(e.isControlDown()) {

								if(GameObject.selected instanceof Planet) {
									Planet currentPlanet = (Planet)Planet.selected;
									
									if(currentPlanet != planet) {
										
										Spacefleet currentSpacefleet = user.newLaunch(user.percent, currentPlanet);
										currentSpacefleet.setDestination(planet); 

									}
								}
							}else {

								lineJoint = new Line(planet.getX(), planet.getY());
								planet.isSelected();
							}
							action = true;
						}else 
							action = false;
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
		};
	}
	
	/**
	 * The mouse drag listener for the game.
	 * draw a line between the {@link GameObject#selected} planet and the mouse
	 * @return a {@link MouseEvent} to be assign to a scene
	 * @see Game
	 */
	public EventHandler<MouseEvent> mouseDragged() {
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
		
	/**
	 * The mouse released listener for the game
	 * when triggered, the planet where the mouse was over:
	 * <ul>
	 * 		<li> left button: became the destination
	 *  	of a spacefleet if there is one ready to be send</li>
	 *  	<li> left button: its information are displayed in the console </li>
	 *  </ul>
	 * @param universe the universe for the planet set to 
	 * @return a {@link MouseEvent} to be assign to a scene
	 * @see Game
	 */
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
						if(planet.isInside(e.getX(), e.getY())) {


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
						if(planet.isInside(e.getX(), e.getY())) {
							if(GameObject.selected instanceof Spacefleet) {
								((Spacefleet)GameObject.selected).setDestination(planet);
							}else // show information about the planet 
								if(GameObject.selected instanceof Planet)
									System.out.println(planet);

						}
					}
				}

			}
			
		};
	}
	
}
