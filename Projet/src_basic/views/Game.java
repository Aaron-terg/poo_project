package views;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import controllers.AI;
import controllers.Universe;
import controllers.UserInput;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import models.GameObject;
import models.GameState;
import models.Player;
import models.Spacefleet;
import models.planet.Planet;
import models.spaceship.SpaceshipType;

/**
 * <b>The Game application</b>
 * <p>the updating and rendering of spaceship and view are manage in this class<p>
 * 
 * @author meryl, Virginie
 * @version src_basic
 * @since src_basic
 *
 */
public class Game extends Application{
	/**
	 * The dimension of the window application
	 */
	public final static double WIDTH = 1400;
	public final static double HEIGHT = 900;
	
	/**
	 * Canvas context to be draw on
	 */
	private GraphicsContext gc;
	
	/**
	 * the scene of the application need for the loading
	 * @see Game#loadGame()
	 */
	private Scene scene;
	
	/**
	 * user controller setting every mouse movement or keyEvent for the player
	 */
	private UserInput userIn;
	/**
	 * The universe of the game for playing with.
	 * COntain a set of planet.
	 */
	private static Universe universe;
	
	/**
	 * An arrayList of player. containing all the player
	 */
	private ArrayList<Player> players;
	
	/**
	 * an enumeration of gameState. Allow communication between the different loop and controller.
	 */
	public static GameState gameState;
	
	/**
	 * user interface, (more view to come in an update)
	 */
	private UserInterface ui, intermediaryUi;
	
	public static void main(String[] args) {
		
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		// setting of the application
		stage.setTitle("Space Game");
		stage.setResizable(false);
		Group root = new Group();
		scene = new Scene(root);
		Canvas canvas = new Canvas(WIDTH, HEIGHT);
		gc = canvas.getGraphicsContext2D();
		root.getChildren().add(canvas);
		
		// Basic interaction for the user
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				KeyCode code = e.getCode();
				if(code.equals(KeyCode.P) || code.equals(KeyCode.ESCAPE)) 
					Game.gameState = (Game.gameState.equals(GameState.PAUSED))? GameState.RUNNING: GameState.PAUSED;
				if (code.equals(KeyCode.S) && e.isControlDown()) 
					Game.gameState = GameState.SAVED;
				if (code.equals(KeyCode.P) && e.isControlDown()) 
					Game.gameState = GameState.LOADED;
				if (code.equals(KeyCode.Q) && e.isControlDown())
					System.exit(0);	
				if (code.equals(KeyCode.R) && e.isControlDown())
					Game.gameState = GameState.INIT;
			};
		});
		scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(event.getButton().equals(MouseButton.PRIMARY)){
					UserInterface currentUi;
					if(gameState.equals(GameState.RUNNING))
						currentUi = ui;
					else {
						currentUi = intermediaryUi;
					}
					for (Iterator clickIt = currentUi.getClickable().iterator(); clickIt.hasNext();) {
						GameObject gameObject = (GameObject) clickIt.next();
						if(gameObject.isInside(event.getX(), event.getY())) {
							if(gameObject.label() == "Exit")
								System.exit(0);
							if(gameObject.label() == "Init")
								gameState = GameState.STARTED;
							if(gameObject.label() == "Save")
								gameState = GameState.SAVED;
							if(gameObject.label() == "Continue")
								gameState = GameState.LOADED;
							if(gameObject.label() == "Restart") {
								gameState  = GameState.INIT;
								System.out.println(gameObject.label() + ", gameState: " + gameState);
							}

						}
					}
				}

			}

		});

		stage.setScene(scene);
		stage.show();
		initGame();
	}
	// TODO fix endGame noreaction
	public void initGame() {
		intermediaryUi = new SplashScreen();
		
		// SplashScreen renderer
		gameState  = GameState.INIT;
		new AnimationTimer() {
			@Override
			public void handle(long arg0) {
				gc.clearRect(0, 0, WIDTH, HEIGHT);
				intermediaryUi.render(gc);
				if(gameState.equals(GameState.STARTED)) {
					Player user = new Player("Aaron Terg", Color.RED);

					players = new ArrayList<Player>();
					userIn = new UserInput(user);
					universe = new Universe(10);
					Player ia = new AI(universe, "AI", Color.GREEN);
					players.add(ia);
					
//					Player ia2 = new AI(universe, "IA2", Color.ALICEBLUE);
//					players.add(ia2);

					// setting of the controller for the player
//					scene.setOnMouseClicked(UserInput.mouseClicked());
					user.firstPlanet(universe);
					players.add(user);
					scene.setOnKeyPressed(userIn.keyPressed((EventHandler<KeyEvent>) scene.getOnKeyPressed()));
					scene.setOnMousePressed(userIn.mousePressed());
					scene.setOnMouseDragged(userIn.mouseDragged());
					scene.setOnMouseReleased(userIn.mouseReleased(universe));
					gameState = gameState.RUNNING;
				}else if(gameState.equals(GameState.LOADED))
					loadGame();		
				//start the game rendering
				if(gameState.equals(GameState.RUNNING)) {
					this.stop();
					gameRenderer();
				}
			}
		}.start();
	}
	
	public void gameRenderer() {
		//start the rendering loop when the gameStep is Runnning
		if(gameState.equals(GameState.RUNNING)) {
			ui = new UniverseSetting();
			intermediaryUi = null;
			new AnimationTimer() {	

				long prevTime = System.nanoTime();
				double step = 0.0166f, maxStep = 0.05f;
				double accTime = 0, accTimeFrameRate = 0, accTimeLaunch=0;

				public void handle(long now) {
					gc.clearRect(0, 0, WIDTH, HEIGHT);
					
					if(gameState.equals(GameState.RUNNING)) {
						ui.render(gc);
						double elapsedTime = (now - prevTime) / 1E9f;
						double elapsedTimeCaped = Math.min(elapsedTime, maxStep);
						accTime += elapsedTimeCaped;
						accTimeFrameRate += elapsedTimeCaped;
						accTimeLaunch += elapsedTimeCaped;
						prevTime =now;

						// update every seconds
						while(accTime >= step) {
							for (Iterator<Planet> planetIt = universe.getPlanets().iterator(); planetIt.hasNext();) {
								Planet planet = (Planet) planetIt.next();
								if(planet.isOwn())
									planet.nbShipOnPlanet(planet.getProductionRate());
							}

							accTime-= step*60; //tweak that value to change the frequency 60 = 1s, 30 = 0.5s, etc...
						}

						//bond the frame rate
						while(accTimeFrameRate >= step) {
							update();
							accTimeFrameRate-=step;
						}

						// send ship every 1/3 seconds
						while(accTimeLaunch >= step) {
							for (Iterator<Player> playerIt = players.iterator(); playerIt.hasNext();) {
								Player player = playerIt.next();

								if(player.hasTerritory()) {
									for (Iterator<Spacefleet> fleetIt = player.getFleets().iterator(); fleetIt.hasNext();) {
										Spacefleet spacefleet = fleetIt.next();

										if(spacefleet.hasDestination() && spacefleet.getNbWave() > 0)
											spacefleet.takeOff();
									}
								}
							}

							accTimeLaunch -= step*40; //same here
						}


						universe.render(gc);

						//rendering of spaceships
						for (Iterator playerIt = players.iterator(); playerIt.hasNext();) {
							Player player = (Player) playerIt.next();
							for (Iterator fleetIt = player.getFleets().iterator(); fleetIt.hasNext();) {
								Spacefleet spacefleet = (Spacefleet) fleetIt.next();
								spacefleet.render(gc);
							}
						}

						// drag'n drop utilities
						if(userIn != null && userIn.action && userIn.lineJoint != null)
							userIn.lineJoint.drawShape(gc, Color.BLACK);

						// status of the game
						String gameStatusText = gameStatus();
						gc.fillText(gameStatusText, 1, 20);
						gc.strokeText(gameStatusText, 1, 20);
						gc.setTextAlign(TextAlignment.LEFT);
						if(players.size() <= 1)
							gameState = GameState.ENDED;

					}
					// End the game if their is no more player
					else if(gameState.equals(GameState.ENDED) || players.size() <= 1) {
						gameState = GameState.ENDED;
						endGame();
						intermediaryUi.render(gc);

					}
					else if(gameState.equals(GameState.PAUSED)) {
						pause();
						intermediaryUi.render(gc);
					}
					else if(gameState.equals(GameState.SAVED))
						saveGame();
					else if(gameState.equals(GameState.LOADED))
						loadGame();
					else if(gameState.equals(GameState.INIT)) {
						this.stop();
						initGame();
					}
				}

			}.start();
		}
	}
	
	/**
	 * update all the game object
	 * if their is the need
	 * 
	 */
	public void update() {
		
		// loop over players
		for (Iterator<Player> playerIt = players.iterator(); playerIt.hasNext();) {
			Player player = playerIt.next();
			
			// test if the player can play
			if(player.hasTerritory()) { 
			
				// player have spacefleet ready to be sent?
				if(player.inAction()) { 

					for (Iterator<Spacefleet> fleetIt = player.getFleets().iterator(); fleetIt.hasNext();) {
						Spacefleet spacefleet = fleetIt.next();
						
						// launch the first wave of a spacefleet
						if(spacefleet.fleetSize() == 0)
							spacefleet.takeOff();
						
						for (Iterator<SpaceshipType> iterator = spacefleet.fleet().iterator(); iterator.hasNext();) {
							
							SpaceshipType spaceshipType = iterator.next();

							Planet planet = spacefleet.getDestination();
							
							if(planet == null) //exit if the spaceship doesn't have destination
								break;
							
							// spaceship collision with planet
							if(planet.getPlanetShape().intersects(spaceshipType.getSpaceshipShape())) {
								planet.spaceShipEnter(spaceshipType);
								iterator.remove();
							}
							else{
								for(Planet obstacle : universe.getPlanets()) {
									while(obstacle.getPlanetShape().distPoint(spaceshipType.getSpaceshipShape().getX()[0], spaceshipType.getSpaceshipShape().getY()[0])
											< obstacle.getPlanetShape().getRadius()+15 && !obstacle.equals(planet)) {
										spaceshipType.getAround(obstacle,spacefleet);
									}
								}
								spaceshipType.goTo(planet);
							}
						}
						if(spacefleet.isArrived())
							fleetIt.remove();
					}
				}
			}
			else {
				playerIt.remove();
			}
		}
	}
	
	/**
	 * Information about the current state of the user.
	 * <ul>
	 * It display:
	 * <li>	the user actual percentage.</li>
	 * <li>the number of player left</li>
	 * 	<li>the number of spacefleet send by the user</li>
	 * 	
	 * </ul>
	 * @return
	 */
	public String gameStatus() {
		int nbPlayers = players.size();
		Player user = userIn.getUser();
		String txt = "";
		if(nbPlayers > 1) {
			txt = "Ships to send: "+user.percent +"%\n"
					+ "nb players: "+ nbPlayers+ "\n";
		}
		else if(nbPlayers == 1)
			txt = "winner: " + players.toString()+ "\n"; 
		else
			txt = "draw \n";
		return txt + user.getFleets().size() + "\n";
	}
	
	/**
	 * Set the game to pause, and display the pause menu (to be implemented)
	 */
	public void pause() {
			intermediaryUi = new PauseScreen("Game paused\n", 2);
	}
	
	/**
	 * Stop the rendering loop when the game has reached the end.
	 * Display the winner of the game
	 * @param loop the animationTier loop to be stop
	 */
	public void endGame() {
			intermediaryUi = new endMenuScreen( "winner:\n" + players.get(0).toString()+ "\n", 1);
	}
	
	/**
	 * Save the current state to a file and set the game state to RUNNING
	 */
	public void saveGame() {
		try(ObjectOutputStream oos =
				new ObjectOutputStream(
						new BufferedOutputStream(
								new FileOutputStream(
										new File("Saved_data.txt"))))
		){
			
			oos.writeObject(this.universe);
			oos.writeObject(players);
			oos.writeObject(userIn);
			oos.writeObject(gameState);
			
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally {
			System.out.println("saved");
			gameState = GameState.RUNNING;
		}
	}
	
	/**
	 * The game loader, it reload the previous save and set the gameState to RUNNING
	 */
	public void loadGame() {
		try(ObjectInputStream ois = new ObjectInputStream(
              		new BufferedInputStream(
              			new FileInputStream(
          					new File("saved_data.txt")))))
		{
			universe = (Universe)ois.readObject();
			players = (ArrayList<Player>)ois.readObject();
			for (Iterator playIt = players.iterator(); playIt.hasNext();) {
				Player player = (Player) playIt.next();
				if (player instanceof AI) {
					AI ai = (AI) player;
					ai.universe = universe;
					
				}
			}
			userIn = (UserInput)ois.readObject();
			scene.setOnKeyPressed(userIn.keyPressed((EventHandler<KeyEvent>) scene.getOnKeyPressed()));
			scene.setOnMousePressed(userIn.mousePressed());
			scene.setOnMouseDragged(userIn.mouseDragged());
			scene.setOnMouseReleased(userIn.mouseReleased(universe));
			System.out.println("check ! ");
		}catch(FileNotFoundException e) {
			System.out.println("no file found");
			gameState = GameState.STARTED;
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			gameState = GameState.STARTED;

			System.out.println(e1.getMessage());
		} catch (ClassNotFoundException e) {
			gameState = GameState.STARTED;

			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		finally {
			System.out.println("load");
			gameState = (gameState.equals(GameState.LOADED))? GameState.RUNNING : GameState.STARTED;
		}
	} 
}
