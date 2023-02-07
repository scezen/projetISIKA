package fr.isika.cda23.projet1Front;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
    	SceneDAccueil sceneDAccueil = new SceneDAccueil();
        primaryStage.setScene(sceneDAccueil.getScene());
        primaryStage.setTitle("LMWY Technologie");
        primaryStage.show();
    }
}