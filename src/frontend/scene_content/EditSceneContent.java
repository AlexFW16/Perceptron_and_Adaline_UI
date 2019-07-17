package frontend.scene_content;

import frontend.exception.DoubleFieldNotValidException;
import frontend.language.De;
import frontend.style.Style;
import frontend.util.ValidationUtil;
import javafx.beans.Observable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import main.Main;
import perceptron.Perceptron;


public class EditSceneContent {

    public EditSceneContent(){
        initialize();
    }

    //Returns the Parent for this scene
    public Parent getParent(){
        return gridPane;
    }

    //panes - PARENT
    private GridPane gridPane;

    //labels
    private Label featureAmountLabel;
    private Label thresholdLabel;
    private Label learnRateLabel;

    //TextFields
    private TextField featureAmountField;
    private TextField thresholdField;
    private TextField learnRateField;

    //buttons
    private Button backButton;
    private Button finishButton;

    private void initialize(){
        setParent();
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

    private void setComp(){

        //labels CREATION
        featureAmountLabel = new Label(De.AMOUNT_OF_FEATURES);
        thresholdLabel = new Label(De.THRESHOLD);
        learnRateLabel = new Label(De.LEARN_RATE);

        //text fields CREATION
        featureAmountField = new TextField(String.valueOf(Main.perceptron.getWeights().size()));
        thresholdField = new TextField();
        learnRateField = new TextField();

        //buttons CREATION
        backButton = new Button(De.BACK_BUTTON_TEXT);
        finishButton = new Button(De.FINISH_BUTTON);

        //text field EDIT
        featureAmountField.setEditable(false);

        //IDs
        featureAmountLabel.setId("featureAmountLabel");
        thresholdLabel.setId("thresholdLabel");
        learnRateLabel.setId("learnRateLabel");

        featureAmountField.setId("featureAmountField");
        thresholdField.setId("thresholdField");
        learnRateField.setId("learnRateField");

        backButton.setId("backButton");
        finishButton.setId("finishButton");

        //ACTION
        backButton.addEventFilter(MouseEvent.MOUSE_CLICKED, this::backButtonClickAction);
        finishButton.addEventFilter(MouseEvent.MOUSE_CLICKED, this::finishButtonClickAction);

        thresholdField.textProperty().addListener(this::textFieldChangeAction);
    }

    private void addComponentsToParent(){

        gridPane.add(featureAmountLabel, 0, 0);
        gridPane.add(thresholdLabel, 0, 1);
        gridPane.add(learnRateLabel, 0, 2);

        gridPane.add(featureAmountField, 1, 0);
        gridPane.add(thresholdField, 1, 1);
        gridPane.add(learnRateField, 1, 2);

        gridPane.add(backButton, 0, 3);
        gridPane.add(finishButton, 1, 3);
    }

    private void backButtonClickAction(MouseEvent event){
        Main.sceneHandler.setScene(SceneType.OVERVIEW_SCENE);
    }

    private void finishButtonClickAction(MouseEvent event) {
        try{
            editPerceptron();
        }
        catch (DoubleFieldNotValidException e){
            e.getField().setText("");
        }


        Main.sceneHandler.setScene(SceneType.OVERVIEW_SCENE);
    }

    private void textFieldChangeAction(Observable observable, String oldVal, String newVal){

        if(ValidationUtil.validateDoubleText(thresholdField.getText()))
            thresholdField.setStyle(Style.TEXT_FIELD_GREEN);
        else
            thresholdField.setStyle(Style.TEXT_FIELD_RED);

        if(ValidationUtil.validateDoubleText(learnRateField.getText()))
            learnRateField.setStyle(Style.TEXT_FIELD_GREEN);
        else
            learnRateField.setStyle(Style.TEXT_FIELD_RED);
    }


    private void editPerceptron() throws DoubleFieldNotValidException{

        if(!ValidationUtil.validateDoubleField(thresholdField) && !thresholdField.getText().trim().isEmpty())
            throw new DoubleFieldNotValidException(thresholdField);

        if(!ValidationUtil.validateDoubleField(learnRateField) && !learnRateField.getText().trim().isEmpty())
            throw new DoubleFieldNotValidException(thresholdField);

        //Cloning the perceptron content
        int featureAmount = Main.perceptron.getWeights().size();
        double threshold = Main.perceptron.getThreshold();
        double learningRate = Main.perceptron.getLearningRate();

        if(!thresholdField.getText().trim().isEmpty())
            threshold = Double.parseDouble(thresholdField.getText());

        if(!learnRateField.getText().trim().isEmpty())
            learningRate = Double.parseDouble(learnRateField.getText());

        //CREATION
        Main.perceptron = new Perceptron(learningRate, threshold, featureAmount);

    }
}
