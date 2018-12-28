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
import controllers.UniverseSetting;
import controllers.UserInput;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import models.Planet;
import models.Player;
import models.Spacefleet;
import models.SpaceshipType;
import views.button.RectangleButton;
import views.ui.SettingUniverseScreen;
import views.ui.UserInterface;

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
	public final static double WIDTH = 1400, HEIGHT = 900;


	/**
	 * the scene of the application need for the loading
	 * @see Game#loadGame()
	 */
	private Scene scene;
	
	/**
	 * The animation loop need for the rendering
	 */
	private static AnimationTimer gameLoop;
	
	/**
	 * the canvas needed for setting the control while loading a game.
	 * @see Game#loadGame()
	 */
	private Canvas canvas;
	
	/**
	 * the user interface for configuring a new game
	 */
	public UserInterface intermediaryUi;
	
	/**
	 * user controller setting every mouse movement or keyEvent for the player
	 */
	private UserInput userIn;
	
	/**
	 * the setting of the universe configured by the user interface
	 */
	public static UniverseSetting uSet;
	
	/**
	 * The universe of the game for playing with.
	 * Contain a set of planet.
	 */
	public static Universe universe;

	/**
	 * An arrayList of player. containing all the player
	 */
	private ArrayList<Player> players;

	public static void main(String[] args) {

		launch(args);
	}

	/**
	 * Init the application.
	 * defined the canvas, user interface, game loop scene interaction.
	 * 
	 * @see Game#canvas
	 * @see Game#scene
	 * @see Game#gameLoop
	 * @see Game#intermediaryUi
	 * @param stage
	 * @throws Exception
	 */
	@Override
	public void start(Stage stage) throws Exception {

		System.out.println("basic version");
		// setting of the application
		stage.setTitle("Space Game");
		stage.setResizable(false);
		scene = new Scene(new Group(), WIDTH, HEIGHT);
		canvas = new Canvas(WIDTH, HEIGHT);
		GraphicsContext gc = canvas.getGraphicsContext2D();

		intermediaryUi = new UserInterface(this);

		((Group)scene.getRoot()).getChildren().addAll(canvas, intermediaryUi);

		intermediaryUi.setVisible(true);
		canvas.setVisible(false);

		// Basic interaction for the user
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				KeyCode code = e.getCode();
				if(code.equals(KeyCode.P))
					pause();
				if(code.equals(KeyCode.ESCAPE))
					gameRenderer();
				if (code.equals(KeyCode.S) && e.isControlDown()) {
					saveGame();
				}
				if (code.equals(KeyCode.P) && e.isControlDown()) {
					loadGame();
				}
				if (code.equals(KeyCode.Q) && e.isControlDown())
					System.exit(0);	
				if (code.equals(KeyCode.R) && e.isControlDown()) {
					((UserInterface) intermediaryUi).refreshInterface((new UserInterface(Game.this)).getChildren());
					intermediaryUi.setVisible(true);
					canvas.setVisible(false);
					gameLoop.stop();


				}

			};
		});
		stage.setScene(scene);
		stage.show();
		gameLoop = new AnimationTimer() {	

			long prevTime = System.nanoTime();
			double step = 0.0166f, maxStep = 0.05f;
			double accTime = 0, accTimeFrameRate = 0, accTimeLaunch=0;

			public void handle(long now) {
				gc.setFill(Color.WHITE);
				gc.fillRect(0, 0, WIDTH, HEIGHT);

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
				for (Iterator<Player> playerIt = players.iterator(); playerIt.hasNext();) {
					Player player = (Player) playerIt.next();
					for (Iterator<Spacefleet> fleetIt = player.getFleets().iterator(); fleetIt.hasNext();) {
						Spacefleet spacefleet = (Spacefleet) fleetIt.next();
						spacefleet.render(gc);
					}
				}

				// drag'n drop utilities
				if(userIn != null) {
					if(userIn.action) {
						if(userIn.lineJoint != null)
							userIn.lineJoint.drawShape(gc, Color.BLACK);
					}
					else if(userIn.boundaries != null)
						userIn.boundaries.render(gc);
				}


				// status of the game
				String gameStatusText = gameStatus();
				gc.fillText(gameStatusText, 1, 20);
				gc.strokeText(gameStatusText, 1, 20);
				gc.setTextAlign(TextAlignment.LEFT);
				if(players.size() <= 1) {
					System.out.println("ending");
					endGame();
				}

			}

		};

	}

	/**
	 * Configure the universe to be create thanks to the user interface.
	 */
	public void gameSetting() {
		intermediaryUi.setVisible(true);
		canvas.setVisible(false);
		uSet = new UniverseSetting(20, 1, true);

		intermediaryUi.refreshInterface((new SettingUniverseScreen(this, uSet)).getChildren());
	}
	
	/**
	 * set the universe with the value of the universe setting: number of player, planet user control if there is an user.
	 */
	@SuppressWarnings("unchecked")
	public void initGame() {
		gameLoop.stop();
		System.out.println("new game");
		
		players = new ArrayList<Player>();
		
		
		universe = new Universe(uSet.nbPlanet);

		// setting of the controller for the player
		if(uSet.humanPlayer) {
			Player user = new Player("Aaron Terg", uSet.color[0]);
			userIn = new UserInput(user);

			user.firstPlanet(universe);
			canvas.setOnMouseClicked(userIn.mouseClicked());
			EventHandler<KeyEvent> onKeyPressed = (EventHandler<KeyEvent>) scene.getOnKeyPressed();
			scene.setCursor(Cursor.CROSSHAIR);
			scene.setOnKeyPressed(userIn.keyPressed(onKeyPressed));
			canvas.setOnMousePressed(userIn.mousePressed());
			canvas.setOnMouseDragged(userIn.mouseDragged());
			canvas.setOnMouseReleased(userIn.mouseReleased());
			canvas.setOnScroll(userIn.scrollEvent());
			
			players.add(user);
			uSet.nbPlayer--;
		}
		
		// create the AI
		for (int i = 0; i < uSet.nbPlayer; i++) {
			int colorIndex = players.size();
			Player ia = new AI(universe, "AI_" + i+1, uSet.color[colorIndex]);
			players.add(ia);
		}
		
	}

	/**
	 * start the game loop renderer 
	 */
	public void gameRenderer() {

		if(universe != null) {
		//start the rendering loop when the gameStep is Runnning
			intermediaryUi.setVisible(false);
			canvas.setVisible(true);
			gameLoop.start();
		}else {
			gameSetting();
		}
	}

	/**
	 * update all the game object
	 */
	public void update() {

		// loop over players
		for (Iterator<Player> playerIt = players.iterator(); playerIt.hasNext();) {
			Player player = playerIt.next();

			// test if the player can play
			if(player.inAction() || player.hasTerritory()) { 

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
						if(planet.intersects(spaceshipType)) {
							planet.spaceShipEnter(spaceshipType);
							iterator.remove();
						}
						else{
							for(Planet obstacle : universe.getPlanets()) {
								// if -> fluidifie les collisions 
								// while assure que les planetes ne soit pas dans la planete
								if(obstacle.distance(spaceshipType.getX(), spaceshipType.getY())
										< obstacle.width()/2 + spaceshipType.width()/2 +15 && !obstacle.equals(planet)) {
									spaceshipType.getAround(obstacle);
								}
							}
							spaceshipType.goTo(planet);
						}
					}
					if(spacefleet.isArrived())
						fleetIt.remove();
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
		String txt = "";
		int nbPlayers = players.size();
		
		if(nbPlayers > 1) {
			
			txt += "nb players: "+ nbPlayers+ "\n";
			if(uSet.humanPlayer) {
				Player user = userIn.getUser();

				txt = "Ships to send: "+ user.percent +"%\n" + txt;
				txt += user.getFleets().size() + "\n";
			}
			
		}
		else if(nbPlayers == 1)
			txt = "winner: " + players.toString()+ "\n"; 
		else
			txt = "draw \n";
		return txt ;
	}

	/**
	 * Set the game to pause, and display the pause menu
	 */
	public void pause() {
		gameLoop.stop();
		double btnWidth = 200,
				btnHeight = 100,
				centerX = (Game.WIDTH / 2),
				centerY = (Game.HEIGHT /2);
		
		int nbBtn = 3;
		RectangleButton restartBtn = new RectangleButton("Restart", centerX ,  centerY + ((btnHeight * nbBtn))  + 25 * (nbBtn -1), 2* btnWidth, btnHeight);
		restartBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				gameSetting();
			}
        });
		
		nbBtn--;
		RectangleButton saveBtn = new RectangleButton("Save", centerX,  centerY + ((btnHeight * nbBtn))  + 25 * (nbBtn -1), 2* btnWidth, btnHeight);
		saveBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				saveGame();
			}
        });
		nbBtn--;
		
		RectangleButton continueBtn = new RectangleButton("Resume", centerX,  centerY + ((btnHeight * nbBtn)) + 25 * (nbBtn -1), 2*btnWidth, btnHeight);
		continueBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				gameRenderer();
			}
        });
		nbBtn--;
		
		Group group = new Group();
		group.getChildren().addAll( restartBtn, saveBtn, continueBtn);
		intermediaryUi.refreshInterface((new UserInterface("Pause", group.getChildren(), 3)).getChildren());
		
		intermediaryUi.setVisible(true);
		canvas.setVisible(false);
	}

	/**
	 * Stop the rendering loop when the game has reached the end.
	 * Display the winner of the game
	 * @param loop the animationTier loop to be stop
	 */
	public void endGame() {
		gameLoop.stop();
		double btnWidth = 200,
				btnHeight = 100,
				centerX = (Game.WIDTH / 2),
				centerY = (Game.HEIGHT /2);
		
		String txt = (!uSet.humanPlayer || players.get(0) instanceof AI )? "Too Bad..." : "Congratulations!";
		int nbBtn = 3;
		RectangleButton restartBtn = new RectangleButton("Restart", centerX ,  centerY + ((btnHeight * nbBtn))  + 25 * (nbBtn -1), 2* btnWidth, btnHeight);
		restartBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				gameSetting();
			}
        });
		
		nbBtn--;
		RectangleButton quitBtn = new RectangleButton("Quit", centerX,  centerY + ((btnHeight * nbBtn))  + 25 * (nbBtn -1), 2* btnWidth, btnHeight);
		quitBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				System.exit(0);
			}
        });
		nbBtn--;
		
		
		Group group = new Group();
		group.getChildren().addAll(quitBtn, restartBtn);
		intermediaryUi.refreshInterface((new UserInterface(txt, group.getChildren(), 2)).getChildren());
		
		intermediaryUi.setVisible(true);
		canvas.setVisible(false);

	}

	/**
	 * Save the current state to a file
	 */
	public void saveGame() {
		try(ObjectOutputStream oos =
				new ObjectOutputStream(
						new BufferedOutputStream(
								new FileOutputStream(
										new File("Saved_data.txt"))))
				){

			oos.writeObject(Game.universe);
			oos.writeObject(players);
			oos.writeObject(uSet);
			oos.writeObject(userIn);

		}catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally {
			System.out.println("saved");
		}
	}

	/**
	 * The game loader, it reload the previous save
	 */
	@SuppressWarnings("unchecked")
	public void loadGame() {
		try(ObjectInputStream ois = new ObjectInputStream(
				new BufferedInputStream(
						new FileInputStream(
								new File("saved_data.txt")))))
		{
			universe = (Universe)ois.readObject();
			players = (ArrayList<Player>)ois.readObject();
			for (Iterator<Player> playIt = players.iterator(); playIt.hasNext();) {
				Player player = (Player) playIt.next();
				if (player instanceof AI) {
					AI ai = (AI) player;
					ai.universe = universe;

				}
			}
			uSet = (UniverseSetting)ois.readObject();
			if(uSet.humanPlayer) {
				userIn = (UserInput)ois.readObject();
				canvas.setOnMouseClicked(userIn.mouseClicked());
				scene.setOnKeyPressed(userIn.keyPressed((EventHandler<KeyEvent>) scene.getOnKeyPressed()));
				canvas.setOnMousePressed(userIn.mousePressed());
				canvas.setOnMouseDragged(userIn.mouseDragged());
				canvas.setOnMouseReleased(userIn.mouseReleased());
				canvas.setOnScroll(userIn.scrollEvent());

			}
			System.out.println("information checked ! ");

		}catch(FileNotFoundException e) {
			System.out.println("no file found");
		} catch (IOException e1) {
			// TODO Auto-generated catch block

			System.out.println(e1.getMessage());
		} catch (ClassNotFoundException e) {

			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		finally {
			System.out.println("load");
			intermediaryUi.setVisible(false);
			canvas.setVisible(true);
			gameRenderer();
		}
	} 
}
