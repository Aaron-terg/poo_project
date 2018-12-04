package Views;



import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import Models.Player;
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
		player.firstPlanet(universe, gc);
		universe.render(gc);
		ArrayList<SpaceshipType> listOfShips = new ArrayList<SpaceshipType>();
		stage.setScene(scene);
		stage.show();
		
	
		 EventHandler<MouseEvent> firstclick = new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) { 	
				if(e.getButton().equals(MouseButton.PRIMARY)) {
				for(int i = 0; i<player.getTerritory().size();i++) {
					Planet p = player.getTerritory().get(i);
					if(p.getPlanetShape().isInside(e.getX(), e.getY())&&player.getTerritory().contains(p)) {
						SpaceshipType spaceship= new BasicSpaceshipType(p.getSpaceShipeType());
						listOfShips.add(spaceship);
						spaceship.setPositionAtStart(p.getPlanetShape().position()[0],  p.getPlanetShape().position()[1]);
						spaceship.render(gc);
						System.out.println("size   "+listOfShips.size());
						
					}

				}
				}
			}};
		
	
		EventHandler<MouseEvent> click = new EventHandler<MouseEvent>() {
			public void handle(MouseEvent f) { 
				if(f.getButton().equals(MouseButton.SECONDARY)) {
					Iterator<Planet> it = universe.getPlanets().iterator();
					while (it.hasNext()) {
						Planet planet = it.next();
						if(planet.getPlanetShape().isInside(f.getX(), f.getY())) {
							planet.setIs_destination(true);	
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
				Iterator<Planet> it2 = universe.getPlanets().iterator();
				Iterator<Planet> it = universe.getPlanets().iterator();
				while (it.hasNext()) {
					Planet planet = it.next();
					if((planet.getIs_destination())) {	
						for(int i = 0; i<listOfShips.size();i++) {
							SpaceshipType spaceShip = listOfShips.get(i);
							if(planet.getPlanetShape().isInside(spaceShip.getSpaceshipShape().getX()[0], spaceShip.getSpaceshipShape().getY()[0])) {
								planet.spaceShipEnter(player);
								listOfShips.remove(spaceShip);
								planet.setIs_destination(false);

							}
						while(it2.hasNext()) {
							Planet obstacle = it2.next();
							for(int j = 0; j<spaceShip.getSpaceshipShape().getX().length;j++) {
								double length = Math.abs(spaceShip.getSpaceshipShape().getY()[0]-spaceShip.getSpaceshipShape().getY()[1]);
								if(!(obstacle.getIs_destination()) &&!(player.getTerritory().contains(obstacle))&&
									(Math.abs(obstacle.getPlanetShape().distPoint(spaceShip.getSpaceshipShape().getX()[j], spaceShip.getSpaceshipShape().getY()[i]))<=obstacle.getPlanetShape().getRadius()+length+20)){
									//spaceShip.get_around(obstacle);
								}
							}
						}
						spaceShip.goTo(planet);
						spaceShip.render(gc);
						}
					}
						
					}
				
				}
			
				
	
			
		}.start();
	}
	
}
