package Views;



import java.util.ArrayList;
import java.util.Iterator;

import Models.Player;
import Models.Spacefleet;
import Models.Universe;
import Models.Spaceship.BasicSpaceshipType;
import Models.Spaceship.SpaceshipType;
import Models.planet.Planet;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;


public class TestObject extends Application{
	public final static double WIDTH = 1200;
	public final static double HEIGHT = 800;
	public static int percent=10;

		
	public static void main(String[] args) {
		
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Render test");
		stage.setResizable(false);
		
		Group root = new Group();
		Scene scene = new Scene(root);
		Canvas canvas = new Canvas(WIDTH, HEIGHT);
		root.getChildren().add(canvas);
		GraphicsContext gc = canvas.getGraphicsContext2D();			
		
		
		Universe universe = new Universe(10);
		Player player = new Player();
		player.firstPlanet(universe);
		Player ia = new Player("IA");
		ia.firstPlanet(universe);
		
		EventHandler<KeyEvent> keyEvent = new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) { 
				if(e.isShiftDown()&&percent<100) 
					percent+=5;
				
				if(e.isControlDown()&&percent>0) 
					percent-=5;

				
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
					for(Planet p : player.getTerritory()) {	
						if(!fleetSet && p.getPlanetShape().isInside(e.getX(), e.getY())) {
							indexSpacefleet = player.getFleets().size();
							currentPlanet = p;
							Spacefleet spacefleet = new Spacefleet(percent, new BasicSpaceshipType(player), p, indexSpacefleet);
							currentSpacefleet = spacefleet;
							player.newLaunch(spacefleet);
							fleetSet = true;
							break;
						}
					}
					if(!fleetSet) {
						Iterator<Spacefleet> spacefleetIt = player.getFleets().iterator();
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
							if(indexSpacefleet >= player.getFleets().size())
								currentSpacefleet.setIndex(--indexSpacefleet);
							player.getFleets().get(indexSpacefleet).setDestination(planet);
							currentPlanet.nbShipOnPlanet(-(int)percent*currentPlanet.nbShipOnPlanet()/100);
							fleetSet = false;

						}

					}
				}
			}};
		
			scene.setOnMouseClicked(mouseEvent);
		
		

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
					universe.render(gc);
					
					
					if(player.inAction()) {
						ArrayList<Spacefleet> playersFleet =player.getFleets();
						for (int indexSpaceFleet = 0; indexSpaceFleet < playersFleet.size(); indexSpaceFleet++) {
							
							Spacefleet spacefleet =	playersFleet.get(indexSpaceFleet);
							spacefleet.setIndex(indexSpaceFleet);
							
							for (Iterator iterator = spacefleet.fleet().iterator(); iterator.hasNext();) {
								
								SpaceshipType spaceshipType = (SpaceshipType) iterator.next();

								Planet planet = spacefleet.getDestination();
								if(planet == null)
									break;
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
					String txt = "Ships to send: "+percent +"%";
					gc.fillText(txt, 1, 20);
					gc.strokeText(txt, 1, 20);
					gc.setTextAlign(TextAlignment.LEFT);
				}
				

			}.start();
		
		stage.setScene(scene);
		stage.show();
	}
	
}
