package main;

import frontend.scene_content.SceneType;
import frontend.handler.SceneHandler;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import perceptron.Perceptron;


public class Main extends Application {

    public static SceneHandler sceneHandler;
    public static Perceptron perceptron;

    public static final String SEPARATOR = ",";
    private static final String TITLE = "erceptron Visualisation | by AlexFW";

    private String stylesheet = "frontend/style/stylesheet.css";

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.getIcons().clear();

        primaryStage.getIcons().add(new Image("file:images/p.png"));
        primaryStage.setTitle(TITLE);

        sceneHandler = new SceneHandler(primaryStage, stylesheet);

        sceneHandler.setScene(SceneType.CREATION_SCENE);


    }


}
