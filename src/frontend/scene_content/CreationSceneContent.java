package frontend.scene_content;


import frontend.exception.DoubleFieldNotValidException;
import frontend.exception.NumberFieldNotValidException;
import frontend.language.De;
import frontend.style.Style;
import frontend.util.ValidationUtil;
import javafx.application.Platform;
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


public class CreationSceneContent{


    public CreationSceneContent(){
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
    private Button exitButton;
    private Button createButton;

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
        featureAmountField = new TextField("4");
        thresholdField = new TextField("0");
        learnRateField = new TextField("0.01");

        //buttons CREATION
        exitButton = new Button(De.EXIT);
        createButton = new Button(De.CREATE);

        //IDs
        featureAmountLabel.setId("featureAmountLabel");
        thresholdLabel.setId("thresholdLabel");
        learnRateLabel.setId("learnRateLabel");

        featureAmountField.setId("featureAmountField");
        thresholdField.setId("thresholdField");
        learnRateField.setId("learnRateField");

        exitButton.setId("exitButton");
        createButton.setId("createButton");

        //ACTION
        exitButton.addEventFilter(MouseEvent.MOUSE_CLICKED, this::exitButtonClickAction);
        createButton.addEventFilter(MouseEvent.MOUSE_CLICKED, this::createButtonClickAction);

        featureAmountField.textProperty().addListener( this::textFieldChangeAction);
        thresholdField.textProperty().addListener(this::textFieldChangeAction);
        learnRateField.textProperty().addListener(this::textFieldChangeAction);

    }

    private void addComponentsToParent(){

        gridPane.add(featureAmountLabel, 0, 0);
        gridPane.add(thresholdLabel, 0, 1);
        gridPane.add(learnRateLabel, 0, 2);

        gridPane.add(featureAmountField, 1, 0);
        gridPane.add(thresholdField, 1, 1);
        gridPane.add(learnRateField, 1, 2);

        gridPane.add(exitButton, 0, 3);
        gridPane.add(createButton, 1, 3);

    }

    private void exitButtonClickAction(MouseEvent event){
       Platform.exit();
    }


    private void createButtonClickAction(MouseEvent event){
        try{
            createPerceptron();
        }
        catch (NumberFieldNotValidException e){
            return;
        }

        Main.sceneHandler.setScene(SceneType.OVERVIEW_SCENE);
    }

    /*
    Checks for all text-fields if they´re valid and sets the appropriate colour
     */
    private void textFieldChangeAction(Observable observable, String oldVal, String newVal){

       if(ValidationUtil.validateIntText(featureAmountField.getText()))
           featureAmountField.setStyle(Style.TEXT_FIELD_GREEN);
       else
           featureAmountField.setStyle(Style.TEXT_FIELD_RED);

        if(ValidationUtil.validateDoubleText(thresholdField.getText()))
            thresholdField.setStyle(Style.TEXT_FIELD_GREEN);
        else
            thresholdField.setStyle(Style.TEXT_FIELD_RED);

        if(ValidationUtil.validateDoubleText(learnRateField.getText())){

            if(Double.valueOf(learnRateField.getText()) >0 || Double.valueOf(learnRateField.getText()) <=1)
                learnRateField.setStyle(Style.TEXT_FIELD_GREEN);
            else
                learnRateField.setStyle(Style.TEXT_FIELD_ORANGE);

        }
        else
            learnRateField.setStyle(Style.TEXT_FIELD_RED);

    }

    /*
       Validates the User Input and passes it
       on to instantiate the Main.perceptron.
       Throws @NumberFieldNotValidException with the incorrect field.
   */

    private void createPerceptron() throws DoubleFieldNotValidException{

        if(!ValidationUtil.validateDoubleField(featureAmountField))
            throw new DoubleFieldNotValidException(featureAmountField);


        if(!ValidationUtil.validateDoubleField(thresholdField))
            throw new DoubleFieldNotValidException(thresholdField);

        if(!ValidationUtil.validateDoubleField(learnRateField))
            throw new DoubleFieldNotValidException(learnRateField);


        //Fetches the input
        double learningRate = Double.parseDouble(learnRateField.getText());
        double threshold = Double.parseDouble(thresholdField.getText());
        int featureAmount = (int) Double.parseDouble(featureAmountField.getText());

       Main.perceptron = new Perceptron(learningRate, threshold, featureAmount);

    }


}
