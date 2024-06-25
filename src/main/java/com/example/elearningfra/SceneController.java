package com.example.elearningfra;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {
    private static FXMLLoader[] loaders = new FXMLLoader[10];
    private static Parent[] roots = new Parent[10];
    private static Scene[] scenes = new Scene[10];

    public static FXMLLoader getLoader(int index) {
        if (index >= 0 && index < loaders.length) {
            return loaders[index];
        } else {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
    }


    private static Stage scenecontrollerstage;

    public static Stage getScenecontrollerstage() {
        return scenecontrollerstage;
    }

    public static void setScenecontrollerstage(Stage stage)
    {
        scenecontrollerstage = stage;
    }


    public static void init(Stage stage) throws IOException {
        loaders[0] = new FXMLLoader(SceneController.class.getResource("homepage.fxml"));
        roots[0] = loaders[0].load();
        scenes[0] = new Scene(roots[0],800,600);
        //stage.setResizable(false);
        stage.setScene(scenes[0]);
        stage.show();

        loaders[1] = new FXMLLoader(SceneController.class.getResource("adminlogin.fxml"));
        roots[1] = loaders[1].load();

        loaders[2] = new FXMLLoader(SceneController.class.getResource("modulpage.fxml"));
        roots[2] = loaders[2].load();



        loaders[4] = new FXMLLoader(SceneController.class.getResource("modulselect.fxml"));
        roots[4] = loaders[4].load();


        loaders[5] = new FXMLLoader(SceneController.class.getResource("modulcontent.fxml"));
        roots[5] = loaders[5].load();

        loaders[6] = new FXMLLoader(SceneController.class.getResource("moduladmin.fxml"));
        roots[6] = loaders[6].load();

        loaders[7] = new FXMLLoader(SceneController.class.getResource("add.fxml"));
        roots[7] = loaders[7].load();

        loaders[8] = new FXMLLoader(SceneController.class.getResource("edit.fxml"));
        roots[8] = loaders[8].load();

        loaders[9] = new FXMLLoader(SceneController.class.getResource("newuser.fxml"));
        roots[9] = loaders[9].load();

    }

    public static void switchToPage(int i)
    {
        try {

            if (scenes[i] == null) {
                System.out.println("new scene");
                scenes[i] = new Scene(roots[i]);
                scenecontrollerstage.setScene(scenes[i]);
            } else {
                System.out.println("old scene");
                scenes[i].setRoot(roots[i]);
                scenecontrollerstage.setScene(scenes[i]);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
