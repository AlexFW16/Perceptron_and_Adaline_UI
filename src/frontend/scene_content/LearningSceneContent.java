package frontend.scene_content;

import frontend.language.De;
import frontend.style.Style;
import frontend.util.ValidationUtil;
import javafx.beans.Observable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import main.Main;
import tasks.LearningTask;

import java.io.File;

public class LearningSceneContent {

    public LearningSceneContent(){initialize();}

    public Parent getParent(){
        return gridPane;
    }

    //panes - PARENT
    private GridPane gridPane;

    //labels
    private Label dataPathLabel;
    private Label epochsLabel;
    private Label crossValidationLabel;

    //text field - INPUT
    private TextField dataPathInputField;
    private TextField epochsField;

    //Buttons
    private Button backButton;
    private Button learnButton;

    //CheckBoxes
    private CheckBox cvBox;
    //Progress
    private ProgressBar progressBar;

    //Tasks and Threads
    private LearningTask learningTask;


    private void initialize() {
        setParent();
        setTasksAndThreads();
        setComp();
        addComponentsToParent();
    }

    private void setParent(){
        gridPane = new GridPane();
        gridPane.setGridLinesVisible(false);
        gridPane.setHgap(10d);
        gridPane.setVgap(5d);
        gridPane.setPadding(new Insets(5));

    }
    private void setTasksAndThreads(){
        learningTask = new LearningTask();


    }

    private void setComp(){


        //labels CREATION
        dataPathLabel = new Label(De.DATA_PATH);
        epochsLabel = new Label(De.EPOCHS);
        crossValidationLabel = new Label(De.CROSS_VALIDATION);

        //buttons - CREATION
        backButton = new Button(De.BACK_BUTTON_TEXT);
        learnButton = new Button(De.LEARN_BUTTON);

        //input CREATION
        dataPathInputField = new TextField();
        epochsField = new TextField();

        //Box
        cvBox = new CheckBox();

        //Progress CREATION
        progressBar = new ProgressBar(-1.0);


        //ACTION
        backButton.addEventFilter(MouseEvent.MOUSE_CLICKED, this::backButtonClickAction);
        learnButton.addEventFilter(MouseEvent.MOUSE_CLICKED, this::learnButtonClickAction);

        dataPathInputField.textProperty().addListener(this::textFieldChangeAction);
        epochsField.textProperty().addListener(this::textFieldChangeAction);
        cvBox.selectedProperty().addListener(this::cvBoxChangeAction);

    }

    private void addComponentsToParent(){

        gridPane.add(dataPathLabel, 0, 0);
        gridPane.add(epochsLabel, 0, 1);
        gridPane.add(crossValidationLabel, 0, 2);

        gridPane.add(backButton, 0, 4);
        gridPane.add(learnButton, 1, 4);

        gridPane.add(dataPathInputField, 1, 0);
        gridPane.add(epochsField, 1, 1);

        gridPane.add(progressBar, 0, 3);

        gridPane.add(cvBox, 1, 2);
    }

    private void cvBoxChangeAction(Observable observable, boolean oldVal, boolean newVal){

        if(oldVal)
            System.out.println("was active");

        if(newVal)
            System.out.println("is now active");

    }

    private void textFieldChangeAction(Observable observable, String oldVal, String newVal){

        if(ValidationUtil.validateIntText(epochsField.getText()))
            epochsField.setStyle(Style.TEXT_FIELD_GREEN);
        else
            epochsField.setStyle(Style.TEXT_FIELD_RED);


        if(ValidationUtil.validateFilePath(dataPathInputField.getText()))
            dataPathInputField.setStyle(Style.TEXT_FIELD_GREEN);
        else
            dataPathInputField.setStyle(Style.TEXT_FIELD_RED);
    }

    private void backButtonClickAction(MouseEvent event){

        learningTask.cancel();
        Main.sceneHandler.setScene(SceneType.OVERVIEW_SCENE);
    }

    private void learnButtonClickAction(MouseEvent event) {

        //If the Task is currently working, return
        if(learningTask.isRunning() && !learningTask.isDone())
            return;

        learningTask = new LearningTask();

        //Checks if the inputs are valid
        if (!ValidationUtil.validateFilePath(dataPathInputField.getText()))
            return;

        if (!ValidationUtil.validateIntField(epochsField))
            return;

            progressBar.progressProperty().bind(learningTask.progressProperty());

            learningTask.setDataFile(new File(dataPathInputField.getText()));
            learningTask.setEpochs(Integer.valueOf(epochsField.getText()));

            new Thread(learningTask).start();
        System.out.println(learningTask);





    }

}

