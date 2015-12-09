package com.github.roadfinder.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Jarek on 2015-12-09.
 */
public class View extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        showHelloWorld(primaryStage);
    }

    private void showHelloWorld(Stage primaryStage) throws java.io.IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

}
