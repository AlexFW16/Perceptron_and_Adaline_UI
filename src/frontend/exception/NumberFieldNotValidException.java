package frontend.exception;

import javafx.scene.control.TextField;

public abstract class NumberFieldNotValidException extends Exception {

    private TextField field;

    public TextField getField() {
        return field;
    }


    public  NumberFieldNotValidException(TextField field){
        this.field = field;
    }
}
