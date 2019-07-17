package frontend.handler;


import javax.swing.*;
import java.util.ArrayList;

public class WindowStack {

    private static ArrayList<JFrame> windowStack = new ArrayList<>();
    private WindowStack(){}

    public static void push(JFrame window){
        windowStack.add(window);

    }
    public static JFrame pop(){
        return windowStack.get(windowStack.size() - 1);
    }

}
