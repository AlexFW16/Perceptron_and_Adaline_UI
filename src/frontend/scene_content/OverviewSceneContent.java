package frontend.scene_content;

import frontend.language.De;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import main.Main;


public class OverviewSceneContent {

    public OverviewSceneContent() {initialize(); }

    //panes - PARENT
    private GridPane gridPane;

    //lists
    private ListView<String> weightsList;

    //labels
    private Label thresholdLabel;
    private Label learnrateLabel;

    //buttons
    Button learnButton;
    Button editButton;
    Button predictButton;

    //seperator
    Separator separatorH;

    private void initialize(){
        setParent();
        setComp();
        addComponentsToParent();
    }

    public Parent getParent(){
        return gridPane;
    }

    private void setParent(){
        gridPane = new GridPane();
        gridPane.setGridLinesVisible(false);
        gridPane.setHgap(10d);
        gridPane.setVgap(5d);
        gridPane.setPadding(new Insets(5));

    }

    private void setComp(){

        //labels CREATION
        thresholdLabel = new Label(De.THRESHOLD + ": 10");
        learnrateLabel = new Label(De.LEARN_RATE + ": 0.12");

        //lists CREATION
        weightsList = new ListView<>();

        //buttons
        learnButton = new Button(De.LEARN_BUTTON);
        editButton = new Button(De.EDIT_BUTTON_TEXT);
        predictButton = new Button(De.PREDICT_BUTTON);

        //separators
      //  separatorH = new Separator();
       // separatorH.setOrientation(Orientation.HORIZONTAL);

        //List
        weightsList.setPrefHeight(200);
        weightsList.setOrientation(Orientation.VERTICAL);

        //IDs
        thresholdLabel.setId("thresholdLabel");
        learnrateLabel.setId("learnrateLabel");

        weightsList.setId("weightsList");

        learnButton.setId("learnButton");
        editButton.setId("editButton");
        predictButton.setId("predictButton");

        //Content
        thresholdLabel.setText("Threshold:\n" + Main.perceptron.getThreshold());
        learnrateLabel.setText("Learning Rate:\n" + Main.perceptron.getLearningRate());

        ObservableList<String> weights = FXCollections.observableArrayList();
        weights.add(String.valueOf(Main.perceptron.getW_0()));

        for(int i  = 0; i< Main.perceptron.getWeights().size(); i++){
            weights.add(String.valueOf(Main.perceptron.getWeights().get(i)));

        }

        for(int i = 0; i<weights.size(); i++){
            weights.set(i, "w" + i + ": " + weights.get(i));
        }

        weightsList.setItems(weights);

        //Events
        learnButton.addEventFilter(MouseEvent.MOUSE_CLICKED, this::learnButtonClickAction);
        editButton.addEventFilter(MouseEvent.MOUSE_CLICKED, this::editButtonClickAction);
        predictButton.addEventFilter(MouseEvent.MOUSE_CLICKED, this::predictButtonClickAction);


    }

    private void addComponentsToParent(){
        gridPane.add(weightsList, 0, 0);

        gridPane.add(thresholdLabel, 1, 0);
        gridPane.add(learnrateLabel, 2, 0);

       // gridPane.add(separatorH, 1, 1);

        gridPane.add(learnButton, 0, 2);
        gridPane.add(editButton, 1, 2);
        gridPane.add(predictButton, 2, 2);

    }

    //ACTIONS
    private void learnButtonClickAction(MouseEvent event){

        Main.sceneHandler.setScene(SceneType.LEARNING_SCENE);
    }

    private void editButtonClickAction(MouseEvent event){
        Main.sceneHandler.setScene(SceneType.EDIT_SCENE);

    }

    private void predictButtonClickAction(MouseEvent event){
        Main.sceneHandler.setScene(SceneType.PREDICT_SCENE);

    }

}
