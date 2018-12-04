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
		universe.render(gc);
		//ArrayList<SpaceshipType> listOfShips = new ArrayList<SpaceshipType>();
		stage.setScene(scene);
		stage.show();
		
	
		 EventHandler<MouseEvent> firstclick = new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) { 	
				if(e.getButton().equals(MouseButton.PRIMARY)) {
				System.out.println("<left click");		
				for(int i = 0; i<player.getTerritory().size();i++) {
					Planet p = player.getTerritory().get(i);
					if(p.getPlanetShape().isInside(e.getX(), e.getY())) {
						//SpaceshipType spaceship= new BasicSpaceshipType(player, p.getPlanetShape().getX(), p.getPlanetShape().getY());
						Spacefleet spacefleet = new Spacefleet(5,new BasicSpaceshipType(), p);
						//listOfShips.add(spaceship);
						player.newLaunch(spacefleet);
					}

				}
				}
			}};
		
	
		EventHandler<MouseEvent> click = new EventHandler<MouseEvent>() {
			public void handle(MouseEvent rightClickEvent) { 
				if(rightClickEvent.getButton().equals(MouseButton.SECONDARY)) {
					System.out.println("Right click");
					Iterator<Planet> it = universe.getPlanets().iterator();
					while (it.hasNext()) {
						Planet planet = it.next();
						if(planet.getPlanetShape().isInside(rightClickEvent.getX(), rightClickEvent.getY())) {
							player.getFleets().get(0).setDestination(planet);
									
					}
				
					}
							}
						}	
				};
				
				scene.setOnMouseClicked(click);
				scene.setOnMousePressed(firstclick);
				
		
		

		new AnimationTimer() {
			public void handle(long arg0) {
				gc.clearRect(0, 0, WIDTH, HEIGHT);
				universe.render(gc);
				if(player.inAction()) {
					Iterator<Spacefleet> it = player.getFleets().iterator();
					while (it.hasNext()) {
						Spacefleet spacefleet = it.next();
						for (Iterator iterator = spacefleet.fleet().iterator(); iterator.hasNext();) {
							SpaceshipType spaceshipType = (SpaceshipType) iterator.next();
							Planet planet = spacefleet.getDestination();
							if(planet.getPlanetShape().intersects(spaceshipType.getSpaceshipShape()))
								iterator.remove();
							else {
								spaceshipType.goTo(planet);
								spaceshipType.render(gc);
							}
						}
							
					}
				}
			}
	
			
		}.start();
		
	}
	
}
