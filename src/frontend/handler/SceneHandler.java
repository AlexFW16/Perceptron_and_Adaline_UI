package frontend.handler;

import frontend.scene_content.SceneType;
import frontend.util.SceneFactory;
import javafx.stage.Stage;

public class SceneHandler {

    private Stage stage;
    private String stylesheet;

    public SceneHandler(Stage stage, String stylesheet) {
        this.stage = stage;
        this.stylesheet = stylesheet;
    }

    public void setScene(SceneType type){
        stage.setScene(SceneFactory.createScene(type, stylesheet));
        stage.show();

    }
}
