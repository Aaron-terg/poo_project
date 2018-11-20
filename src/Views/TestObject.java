package Views;

import java.util.ArrayList;
import java.util.Random;

import Models.planet.Planet;
import Models.shape.Circle;
import Models.shape.Shape;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;


public class TestObject extends Application{
	public final static double WIDTH = 1200;
	public final static double HEIGHT = 800;
	
	public void test(GraphicsContext gc) {
		Planet planet1 = new Planet(200,100,100, new int[] {100,100,100});
		Planet planet2 = new Planet(300, 100, 20, new int[] {50,50,50});
		Shape shapep1 = planet1.getPlanetShape();
		Shape shapep2 = planet2.getPlanetShape();
		System.out.println("planets intersect? " + shapep1.intersects(shapep2));
		System.out.println("distance between planets: " + shapep1.distance(shapep2));
		System.out.println("shapep1 : x " + shapep1.position()[0] + ", y " + shapep1.position()[1]);
		System.out.println("shapep1 : x " + shapep2.position()[0] + ", y " + shapep2.position()[1]);
		planet1.render(gc);
		planet2.render(gc);
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
		
		// Making of a set of planet
		Random randomNumber = new Random();
		ArrayList<Planet> planets = new ArrayList<Planet>();
		boolean superImposedTest = true;
		int i = 0;
		int numberOfPlanets = randomNumber.nextInt(100)+4;
		System.out.println(numberOfPlanets);
		while(planets.size() < numberOfPlanets) {
			
			double radius = randomNumber.nextInt(50)+25;
			double posX = (WIDTH- radius)*randomNumber.nextDouble() + radius;
			double posY = (HEIGHT- radius)*randomNumber.nextDouble() + radius;
			int[] rgb = {
					randomNumber.nextInt(256),
					randomNumber.nextInt(256),
					randomNumber.nextInt(256)
			};
			Planet planet = new Planet(posX, posY, radius, rgb);
			int j = planets.size() - 1;
			while(j >= 0) {
				Planet prevPlanet = planets.get(j);
				superImposedTest &= planet.superimposed(prevPlanet);
				j--;
				}
			if(superImposedTest) {
				planets.add(planet);
				planet.render(gc);
				
			}
			superImposedTest = true;
		}	
		stage.setScene(scene);
		stage.show();
		
	}
}
