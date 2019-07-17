package frontend.util;

import javafx.scene.control.TextField;

import java.io.FileReader;

public class ValidationUtil {

    /*
    Returns false if field is null, NaN, etc.
     */
    public static boolean validateIntField(TextField field){
        String text = field.getText();
        return validateIntText(text);
    }

    public static boolean validateDoubleField(TextField field){

        String text = field.getText();
        return validateDoubleText(text);

    }

    /*
    Returns false if one of the Strings in the Array is null, NaN, etc.
     */
    public static boolean validateDoubleStringArray(String[] stringList){

        for(int i = 0; i<stringList.length; i++){

            if(!validateDoubleText(stringList[i]))
                return false;
        }
        return true;
    }

    /*
    Returns false if the String is null, NaN, etc.
   */
    public static boolean validateDoubleText(String text){

        if(text == null)
            return false;

        if(text.trim().isEmpty())
            return false;

        try{
            Double.parseDouble(text);
        }

        catch (NumberFormatException e){
            return false;
        }

        return true;

    }

    public static boolean validateIntText(String text){

        if(text == null)
            return false;

        if(text.trim().isEmpty())
            return false;

        try{
            Integer.parseInt(text);
        }

        catch (NumberFormatException e){
            return false;
        }

        return true;

    }


    //Returns true if the File with the given Path exists
    public static boolean validateFilePath(String path){

        try{
            FileReader r = new FileReader(path);
            return true;
        }
        catch (Exception e){
            return false;
        }


    }





}
