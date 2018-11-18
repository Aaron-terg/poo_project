package Views;

import java.awt.Paint;
import java.awt.PaintContext;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.ColorModel;

import Models.Player;
import Models.planet.Owned;
import Models.planet.Planet;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
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
		
		Shape planetShape = planet.getPlanetShape();
		planetShape.setFill(Color.BLUE);
		planetShape.setStroke(Color.BLACK);
		planetShape.setStrokeWidth(3);
		Circle c = ((Circle)planetShape);
		gc.fillOval(c.getCenterX(), c.getCenterY(), c.getRadius(), c.getRadius());

		root.getChildren().add(planetShape);
		stage.setScene(scene);
		stage.show();
		
	}
}
