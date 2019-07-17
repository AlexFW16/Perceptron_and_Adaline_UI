package frontend.exception;

import javafx.scene.control.TextField;

public class DoubleFieldNotValidException extends NumberFieldNotValidException {

    public DoubleFieldNotValidException(TextField field){
        super(field);
    }

}
