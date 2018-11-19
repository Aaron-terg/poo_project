package Views;

import Models.Player;
import Models.Spaceship.BasicSpaceshipType;
import Models.Spaceship.SpaceshipType;
import Models.planet.Owned;
import Models.planet.Planet;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class TestObject extends Application{
	private final static int WIDTH = 600;
	private final static int HEIGHT = 600;
	public static void main(String[] args) {
		Planet planet = new Planet();
		
		System.out.println(planet.isOwn());
		planet.setPlanetState(new Owned(new Player()));
		System.out.println(planet.isOwn());
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

		Planet planet = new Planet();
		SpaceshipType spaceship = new BasicSpaceshipType();
		
		planet.render(gc);
		spaceship.render(gc);
		stage.setScene(scene);
		stage.show();
		
	}
}
