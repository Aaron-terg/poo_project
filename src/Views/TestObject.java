package Views;

import java.util.ArrayList;
import java.util.Iterator;

import Models.Universe;
import Models.Spaceship.BasicSpaceshipType;
import Models.Spaceship.SpaceshipType;
import Models.planet.Planet;
import Models.shape.Shape;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;


public class TestObject extends Application{
	public final static double WIDTH = 600;
	public final static double HEIGHT = 600;
	
	public void test(GraphicsContext gc) {
		Planet planet = new Planet(120,50, 20, new int[] {50,50,50});
		SpaceshipType spaceshipType = new BasicSpaceshipType();
		planet.nbShipOnPlanet(5);
		System.out.println(planet.getPlanetShape().intersects(spaceshipType.getSpaceshipShape()));
		
		Shape shapep1 = planet.getPlanetShape();
		Shape shape2 = spaceshipType.getSpaceshipShape();
		
		System.out.println("distance between planetsand ship: " + shapep1.distance(shape2));
		System.out.println("shapep1 : x " + shapep1.position()[0] + ", y " + shapep1.position()[1]);
		System.out.println("shapep1 : x " + shape2.position()[0] + ", y " + shape2.position()[1]);
		planet.render(gc);
		spaceshipType.render(gc);
	}
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
		//test(gc);
		Universe universe = new Universe(20);
		universe.render(gc);
		stage.setScene(scene);
		stage.show();
		
	}
}
