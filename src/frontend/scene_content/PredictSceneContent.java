package frontend.scene_content;

import frontend.language.De;
import frontend.style.Style;
import frontend.util.ValidationUtil;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import main.Main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class PredictSceneContent {

    public PredictSceneContent(){
        initialize();
    }

    public Parent getParent(){
        return gridPane;
    }

    //panes - PARENT
    private GridPane gridPane;

    //labels
    private Label dataPathLabel;
    private Label normalInputLabel;

    private Label normalOutputLabel;

    //text fields
    private TextField dataPathField;
    private TextField normalInputField;

    //lists
    private ListView<String> predictedDataList;

    //buttons
    private Button normalPredictButton;
    private Button dataPathPredictButton;
    private Button backButton;


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
        dataPathLabel = new Label(De.DATA_PATH);
        normalInputLabel = new Label(De.NORMAL_PREDICTION_INPUT);
        normalOutputLabel = new Label();

        //text fields CREATION
        normalInputField = new TextField();
        dataPathField = new TextField();

        //lists CREATION
        predictedDataList = new ListView<>();

        //buttons CREATION
        backButton = new Button(De.BACK_BUTTON_TEXT);
        normalPredictButton = new Button(De.NORMAL_PREDICTION_BUTTON);
        dataPathPredictButton = new Button(De.PREDICT_FROM_DATA_PATH_BUTTON);

        //IDs
        dataPathLabel.setId("dataPathLabel");
        normalInputLabel.setId("normalInputLabel");
        normalOutputLabel.setId("normalOutputLabel");

        normalInputField.setId("normalInputField");
        dataPathField.setId("dataPathField");

        predictedDataList.setId("predictedDataList");

        backButton.setId("backButton");
        normalPredictButton.setId("normalPredictButton");
        dataPathPredictButton.setId("dataPathPredictButton");

        predictedDataList.setEditable(true);

        //ACTION
        backButton.addEventFilter(MouseEvent.MOUSE_CLICKED, this::backButtonClickAction);
        normalPredictButton.addEventFilter(MouseEvent.MOUSE_CLICKED, this::normalPredictButtonClickAction);
        dataPathPredictButton.addEventFilter(MouseEvent.MOUSE_CLICKED, this::dataPathPredictButtonClickAction);

        dataPathField.textProperty().addListener(this::textFieldChangeAction);
        normalInputField.textProperty().addListener(this::textFieldChangeAction);
    }

    private void addComponentsToParent(){

        gridPane.add(dataPathLabel, 0, 0);
        gridPane.add(normalInputLabel, 0, 2);
        gridPane.add(normalOutputLabel, 1, 3);

        gridPane.add(normalInputField, 1, 2);
        gridPane.add(dataPathField, 1, 0);

        gridPane.add(predictedDataList, 1, 1);

        gridPane.add(backButton, 0, 4);
        gridPane.add(normalPredictButton, 0, 3);
        gridPane.add(dataPathPredictButton, 0, 1);
    }

    private void textFieldChangeAction(Observable observable, String oldVal, String newVal){

        if(ValidationUtil.validateFilePath(dataPathField.getText()))
            dataPathField.setStyle(Style.TEXT_FIELD_GREEN);
        else
            dataPathField.setStyle(Style.TEXT_FIELD_RED);

        String[] inputs = normalInputField.getText().split(Main.SEPARATOR);

        if(inputs.length != Main.perceptron.getWeights().size()){
            normalInputField.setStyle(Style.TEXT_FIELD_RED);
            return;
        }

        normalInputField.setStyle(Style.TEXT_FIELD_GREEN);
        for (String s: inputs){
            if(!ValidationUtil.validateDoubleText(s)){
                normalInputField.setStyle(Style.TEXT_FIELD_RED);
                break;
            }

        }

    }

    private void backButtonClickAction(MouseEvent event){

        Main.sceneHandler.setScene(SceneType.OVERVIEW_SCENE);

    }

    private void normalPredictButtonClickAction(MouseEvent event){

        String[] inputs = normalInputField.getText().split(Main.SEPARATOR);

        //Validates the inputs
        for(String s : inputs)
            if(!ValidationUtil.validateDoubleText(s))
                return;

        //Converts the inputs into double values
        double[] doubleInputs = new double[inputs.length];

        for(int i = 0; i<inputs.length; i++){
            doubleInputs[i] = Double.parseDouble(inputs[i]);

        }

        normalOutputLabel.setText(String.valueOf(Main.perceptron.predict(doubleInputs)));

    }

    private void dataPathPredictButtonClickAction(MouseEvent event){

        if(!ValidationUtil.validateFilePath(dataPathField.getText()))
            return;

        try{
            FileReader reader = new FileReader(dataPathField.getText());
            BufferedReader bufferedReader = new BufferedReader(reader);

            ArrayList<String> predictions = predict(bufferedReader);

            ObservableList data = FXCollections.observableArrayList();
            data.addAll(predictions);
            predictedDataList.setItems(data);
        }

        catch (Exception e){
            e.printStackTrace();
        }

    }

    private ArrayList<String> predict(BufferedReader reader) throws IOException {

        String[] input;
        ArrayList<String> predictions = new ArrayList<>();

        while(true){

            try{
               input = reader.readLine().split(Main.SEPARATOR);
            }
            catch (NullPointerException e){
                break;
            }

           if(ValidationUtil.validateDoubleStringArray(input)){

               double[] doubleInput = Arrays.stream(input)
                       .mapToDouble(Double::parseDouble)
                       .toArray();

               predictions.add(String.valueOf(Main.perceptron.predict(doubleInput)));
           }
           else
               break;

        }

        return predictions;
    }

}
