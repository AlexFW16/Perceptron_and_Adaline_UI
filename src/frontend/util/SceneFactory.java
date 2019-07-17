package frontend.util;

import frontend.scene_content.*;
import javafx.scene.Scene;

public class SceneFactory {

    public static Scene createScene(SceneType sceneType, String stylesheet){

        switch (sceneType){

            case CREATION_SCENE:
                Scene creationScene = new Scene(new CreationSceneContent().getParent());
                creationScene.getStylesheets().add(stylesheet);
                return creationScene;

            case EDIT_SCENE:
                Scene editScene = new Scene(new EditSceneContent().getParent());
                editScene.getStylesheets().add(stylesheet);
                return editScene;

            case PREDICT_SCENE:
                Scene predictScene = new Scene(new PredictSceneContent().getParent());
                predictScene.getStylesheets().add(stylesheet);
                return predictScene;

            case LEARNING_SCENE:
                Scene learningScene = new Scene(new LearningSceneContent().getParent());
                learningScene.getStylesheets().add(stylesheet);
                return learningScene;

            case OVERVIEW_SCENE:
                Scene overviewScene = new Scene(new OverviewSceneContent().getParent());
                overviewScene.getStylesheets().add(stylesheet);
                return overviewScene;

            default:
                throw new IllegalArgumentException("Unknown Scene Type");
        }

    }
}
