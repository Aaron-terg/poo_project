package Views;



import java.util.ArrayList;
import java.util.Iterator;

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
				System.out.println("<left click");		
				for(int i = 0; i<player.getTerritory().size();i++) {
					Planet p = player.getTerritory().get(i);
					if(p.getPlanetShape().isInside(e.getX(), e.getY())) {
						SpaceshipType spaceship= new BasicSpaceshipType();
						listOfShips.add(spaceship);
						spaceship.setPositionAtStart(p.getPlanetShape().position()[0],  p.getPlanetShape().position()[1]);
						spaceship.render(gc);
						
					}

				}
				}
			}};
		
	
		EventHandler<MouseEvent> click = new EventHandler<MouseEvent>() {
			public void handle(MouseEvent f) { 
				if(f.getButton().equals(MouseButton.SECONDARY)) {
					System.out.println("Right click");
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
				Iterator<Planet> it = universe.getPlanets().iterator();
				while (it.hasNext()) {
					Planet planet = it.next();
					if((planet.getIs_destination())) {
						for(int j = 0; j<listOfShips.size();j++) {
							listOfShips.get(j).goTo(planet);
							listOfShips.get(j).render(gc);
						}
						
					}
				
				}
			}
	
			
		}.start();
		
	}
	
}
