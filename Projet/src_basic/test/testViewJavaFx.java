package test;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class testViewJavaFx extends Application{

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		 primaryStage.setTitle("Hello World");
	        Group root = new Group();
	        Scene scene = new Scene(root, 1400, 800, Color.LIGHTGREEN);
	        Button btn = new Button();
	        btn.setLayoutX(scene.getWidth()/2);
	        btn.setTranslateX(- btn.getWidth()/2);
	        btn.setLayoutY(scene.getHeight()/2);
	        btn.setTranslateY(-btn.getHeight()/2);
	        btn.setText("Hello World");
	        btn.setOnAction(new EventHandler<ActionEvent>() {

	            public void handle(ActionEvent event) {
	                System.out.println("Hello World");
	            }
	        });
	        root.getChildren().add(btn);    
	        
	        primaryStage.setScene(scene);
	        primaryStage.show();
		
	}
}
