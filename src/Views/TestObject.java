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
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class TestObject extends Application{
	public final static double WIDTH = 600;
	public final static double HEIGHT = 600;

		
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
		
	
		 EventHandler<MouseEvent> mouseEvent = new EventHandler<MouseEvent>() {
			boolean fleetSet = false;
			int indexSpacefleet = 0;
			int nbShip = 5;
			Spacefleet currentSpacefleet;
			Planet currentPlanet;
			public void handle(MouseEvent e) { 	
				
				if(e.getButton().equals(MouseButton.PRIMARY)) {
					System.out.println("<left click");		
					for(int i = 0; i<player.getTerritory().size();i++) {
						Planet p = player.getTerritory().get(i);
						if(!fleetSet && p.getPlanetShape().isInside(e.getX(), e.getY())) {
							//SpaceshipType spaceship= new BasicSpaceshipType(player, p.getPlanetShape().getX(), p.getPlanetShape().getY());
							indexSpacefleet = player.getFleets().size();
							
							currentPlanet = p;
							Spacefleet spacefleet = new Spacefleet(nbShip, new BasicSpaceshipType(player), p, indexSpacefleet);
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
					System.out.println("Right click");
					Iterator<Planet> it = universe.getPlanets().iterator();
					while (it.hasNext()) {
						Planet planet = it.next();
						if(planet.getPlanetShape().isInside(e.getX(), e.getY())) {
							indexSpacefleet = currentSpacefleet.getIndex();
							if(indexSpacefleet >= player.getFleets().size())
								currentSpacefleet.setIndex(--indexSpacefleet);
							player.getFleets().get(indexSpacefleet).setDestination(planet);
							currentPlanet.nbShipOnPlanet(-nbShip);
							fleetSet = false;

						}

					}
				}
			}};
		
			scene.setOnMouseClicked(mouseEvent);
		
		

			new AnimationTimer() {
				public void handle(long arg0) {
					gc.clearRect(0, 0, WIDTH, HEIGHT);
					universe.render(gc);
					if(player.inAction()) {
						Iterator<Spacefleet> it = player.getFleets().iterator();
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
								else {
									spaceshipType.goTo(planet);
									spaceshipType.render(gc);
								}
							}
							if(spacefleet.isArrived())
								player.getFleets().remove(indexSpaceFleet);
						}
/**			
 *						while (it.hasNext()) {
 *							Spacefleet spacefleet = it.next();
 *							for (Iterator iterator = spacefleet.fleet().iterator(); iterator.hasNext();) {
 *								SpaceshipType spaceshipType = (SpaceshipType) iterator.next();
 *								Planet planet = spacefleet.getDestination();
 *								if(spaceshipType == null)
 *									break;
 *								if(planet.getPlanetShape().intersects(spaceshipType.getSpaceshipShape()))
 *									iterator.remove();
 *								else {
 *									spaceshipType.goTo(planet);
 *									spaceshipType.render(gc);
 *								}
 *							}
 *							if(spacefleet.isArrived())
 *								it.remove();
 *						}
 */
 
					}
				}


			}.start();
		
		stage.setScene(scene);
		stage.show();
	}
	
}
