package Views;

import java.util.ArrayList;
import java.util.Iterator;

import Controllers.UserInput;
import Models.Player;
import Models.Spacefleet;
import Models.Universe;
import Models.Spaceship.SpaceshipType;
import Models.planet.Planet;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Game extends Application {
	public final static double WIDTH = 1200;
	public final static double HEIGHT = 800;
	private GraphicsContext gc;
	private UserInput userIn;
	private Universe universe;
	private ArrayList<Player> players;
		
	public static void main(String[] args) {
		
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Render TestObject");
		stage.setResizable(false);
		
		Group root = new Group();
		Scene scene = new Scene(root);
		Canvas canvas = new Canvas(WIDTH, HEIGHT);
		gc = canvas.getGraphicsContext2D();
		
		root.getChildren().add(canvas);

		Player user = new Player();
		
		userIn = new UserInput(scene, user);
		
		
		
		
		stage.setScene(scene);
		stage.show();
		

		initGame();
	}
	
	public void initGame() {
		universe = new Universe(10);
		
		Player user = userIn.getUser();
		userIn.gameControl(universe);
		user.firstPlanet(universe);
		
		Player ia = new Player("IA");
		ia.firstPlanet(universe);
		
		players = new ArrayList<Player>();
		players.add(user);
		players.add(ia);
		
		
		
		
		new AnimationTimer() {
			
			long prevTime = System.nanoTime();
			double step = 0.0166f, maxStep = 0.005f;
			double accTime = 0;
			public void handle(long now) {
				
				double elapsedTime = (now - prevTime) / 1E9f;
				double elapsedTimeCaped = Math.min(elapsedTime, maxStep);
				accTime += elapsedTimeCaped;
				while(accTime >= step) {
					for (Iterator<Planet> planetIt = universe.getPlanets().iterator(); planetIt.hasNext();) {
						Planet planet = (Planet) planetIt.next();
						if(planet.isOwn())
							planet.nbShipOnPlanet(planet.getProductionRate());
					}
					
					accTime--;
				}
				
				gc.clearRect(0, 0, WIDTH, HEIGHT);
				update();
				universe.render(gc);
				//update();
				int nbPlayers = players.size();
				String txt = "";
				if(nbPlayers > 1) {
					txt = "Ships to send: "+user.percent +"%\n"
							+ "nb players: "+ nbPlayers+ "\n";
					
				}
				else
					txt = "winner: " + players.toString()+ "\n"; 
				
				txt += user.getFleets().size() + "\n";
				gc.fillText(txt, 1, 20);
				gc.strokeText(txt, 1, 20);
				gc.setTextAlign(TextAlignment.LEFT);
			}

		}.start();
	}
	
	public void update() {
		// loop over players
		for (Iterator playerIt = players.iterator(); playerIt.hasNext();) {
			Player player = (Player) playerIt.next();
			
			if(player.hasTerritory()) {
			
				if(player.inAction()) {
					ArrayList<Spacefleet> playersFleet = player.getFleets();
					for (int indexSpaceFleet = 0; indexSpaceFleet < playersFleet.size(); indexSpaceFleet++) {
						
						Spacefleet spacefleet =	playersFleet.get(indexSpaceFleet);
						spacefleet.setIndex(indexSpaceFleet);
						if(spacefleet.getNbWave() > 0)
							spacefleet.takeOff();
						
						for (Iterator iterator = spacefleet.fleet().iterator(); iterator.hasNext();) {
							
							SpaceshipType spaceshipType = (SpaceshipType) iterator.next();

							Planet planet = spacefleet.getDestination();
							if(planet == null)
								continue;
							if(planet.getPlanetShape().intersects(spaceshipType.getSpaceshipShape())) {
								planet.spaceShipEnter(spaceshipType);
								iterator.remove();
							}
							else{
								for(Planet obstacle : universe.getPlanets()) {
									if(obstacle.getPlanetShape().distPoint(spaceshipType.getSpaceshipShape().getX()[0], spaceshipType.getSpaceshipShape().getY()[0])<obstacle.getPlanetShape().getRadius()+15
											&&!obstacle.equals(planet)) {
										spaceshipType.get_around(obstacle);
									}
								}
								spaceshipType.goTo(planet);
								spaceshipType.render(gc);
						
								
							}
						}
						if(spacefleet.isArrived())
							player.getFleets().remove(indexSpaceFleet);
					}

				}
			}
			else
				playerIt.remove();
		}
		
	
	}
	
	public void endGame() {
		try {
			stop();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
