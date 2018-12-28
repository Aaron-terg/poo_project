package controllers;

import java.io.Serializable;
import java.util.Iterator;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import models.GameObject;
import models.Planet;
import models.Player;
import models.Spacefleet;
import models.SpaceshipType;
import models.shape.Line;
import views.Game;

/**
 * <b>User control utilities</b>
 * <p> Universe implements {@link Serializable}</p>

 * <p> The UserInput class defines the control provide to the user in {@link javafx}</p>
 * 
 * @see Universe
 * @see Player
 * @see Planet
 * @see SpaceshipType
 * @see Spacefleet
 * @see Line
 * @see GameObject
 * 
 * @author meryl, Virginie
 * @version src_basic
 * @since src_basic
 */
public class UserInput implements Serializable{
	
	/**
	 * The user being granted the following control
	 * 
	 * @see UserInput#scrollEvent()
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
	 * A line joining the selected planet and the mouse while the player is dragging away the mouse.
	 * 
	 * @see Line
	 * @see UserInput#mouseDragged()
	 */
	public Line lineJoint;
	
	/**
	 * A gameObject used as a boundary box. help catching a space fleet.
	 * <a>@see UserInput#mouseDragged()
	 */
	public GameObject boundaries;
	
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
	 * The keyboard listener manager for the game.
	 * It specifies action when digits key, A or E keys are pressed.
	 * <ul> 
	 * <li> Digits key: manage the spaceship type of the selected planet.</li>
	 * <li> A / E : manage the percentage of spaceships sent.</li>
	 * </ul>
	 * @return a {@link KeyEvent} to be assign to a scene
	 * @see Game#initGame()
	 */
	public EventHandler<KeyEvent> keyPressed(EventHandler<KeyEvent> keyEv) {
		
		return new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) { 
				keyEv.handle(e);
				KeyCode code = e.getCode();
				if(code.equals(KeyCode.A) && user.percent < 100 )
					user.percent+=5;
				if(code.equals(KeyCode.E)  && user.percent>0) 
					user.percent-=5;
					
			}
		};
	}
	
	/**
	 * The scroll listener for the game.
	 * Manage the percentage of the player
	 * @return a {@link ScrollEvent} to be assign to a scene
	 * 
	 * @see Game
	 */
	public EventHandler<ScrollEvent> scrollEvent(){
		
		return new EventHandler<ScrollEvent>() {
			
			@Override
			public void handle(ScrollEvent event) {
				if(event.getDeltaY() < 0  && user.percent > 0 )
					user.percent--;
				else if(event.getDeltaY() > 0 && user.percent < 100 )
					user.percent++;
			};
		};
	}
	
	/**
	 * The click listener for the game.
	 * display info about a planet in the console with a right click. if th e CTRL key is pressed and the cursor is inside
	 * one of the user planet, then set the destination to this planet.
	 * @return a {@link MouseEvent} to be assign to a scene
	 * 
	 * @see Game
	 */
	public EventHandler<MouseEvent> mouseClicked() {

		return new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				if(e.getButton().equals(MouseButton.SECONDARY)) {
					
						Iterator<Planet> planetIt = Game.universe.getPlanets().iterator();
						boolean result = false;
						while(planetIt.hasNext() && !result) {
							
							Planet planet = planetIt.next();
							if(planet.isInside(e.getX(), e.getY())) {
								if(e.isControlDown()) {
									System.out.println(planet);
									result = true;
								}else if(GameObject.selected != null){
									user.newDest(planet);
									result = true;
								}
							}
						}
					}
				}
		};
	}
	
	/**
	 * The mouse pressed listener for the game.
	 * All the selection of the user's planet / spacefleet are done here
	 * if a spacefleet / planet is selected it creates a line with the center of object selected as origin.
	 * @return a {@link MouseEvent} to be assigned to a scene
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

								user.newDest(planet);
							}else {

								lineJoint = new Line(planet.getX(), planet.getY());
								planet.isSelected();
							}
							action = true;
						}else 
							action = false;
					} 

					// click not in planet 
					if(!action) {
						Iterator<Spacefleet> spacefleetIt = user.getFleets().iterator();
						while(spacefleetIt.hasNext() && !action) {
							Spacefleet spacefleet = spacefleetIt.next();
								if(spacefleet.inside(e.getX(), e.getY())) {
									spacefleet.isSelected();
									lineJoint = new Line(spacefleet.getX(), spacefleet.getY());
									action = true;
								}
						}
					}
					
					if(!action) {
						boundaries = new GameObject(e.getX(), e.getY());
					}
				}
				
				
			}
		};
	}
	
	/**
	 * The mouse drag listener for the game.
	 * draw a line between the {@link GameObject#selected} planet / spacefleet and the mouse.
	 * or draw the boundaries if there is no selected element underneath the cursor.
	 * @return a {@link MouseEvent} to be assign to a scene
	 * @see Game
	 */
	public EventHandler<MouseEvent> mouseDragged() {
		return new EventHandler<MouseEvent>() {

			
			public void handle(MouseEvent e) { 	
				if(e.getButton().equals(MouseButton.PRIMARY)) {

					if(action && lineJoint != null) {
						lineJoint.setPosition(e.getX(), e.getY());
					}
					else if(!action && boundaries != null){
						boundaries.resize(e.getX() - boundaries.getX(), e.getY() - boundaries.getY());

						Iterator<Spacefleet> spacefleetIt = user.getFleets().iterator();
						while(spacefleetIt.hasNext() && !action) {
							Spacefleet spacefleet = spacefleetIt.next();
							Iterator<SpaceshipType> spaceIt = spacefleet.fleet().iterator();
							while(spaceIt.hasNext() && !action) {
								SpaceshipType spaceship = spaceIt.next();
								if(boundaries.isInside(spaceship.getX(), spaceship.getY())) {
									spacefleet.isSelected();
									lineJoint = new Line(spaceship.getX(), spaceship.getY());
									action = true;
								}
							}
								
								
						}
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
	 *  </ul>
	 * @param universe the universe for the planet set to 
	 * @return a {@link MouseEvent} to be assign to a scene
	 * @see Game
	 */
	public EventHandler<MouseEvent> mouseReleased(){
		return new EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent e) {
				lineJoint = null;
				boundaries = null;
				if(action) {
					Iterator<Planet> it = Game.universe.getPlanets().iterator();
					boolean result = false;
					while (it.hasNext() && !result) {

						Planet planet = it.next();
						if(planet.isInside(e.getX(), e.getY())) {
								user.newDest(planet);
								result = true;
								action = false;
						}
						
					}
				}
				

			}
			
		};
	}
	
	
}
