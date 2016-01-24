package com.github.roadfinder.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

/**
 * Created by Jarek on 2015-12-09.
 */
public class View extends Application {

    public static final String APP_NAME = "Road-Finder";
    public static final int WIDTH = 1200;
    public static final int HEIGHT = 800;
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        show( primaryStage );
    }

    private void show( Stage primaryStage ) throws java.io.IOException
    {

        primaryStage.setTitle( APP_NAME );

        Parent root = FXMLLoader.load( getClass().getResource( "/view/inputDataPanel.fxml" ) );
        primaryStage.setScene( new DeafultScene( root ) );
        primaryStage.show();
    }

    public Stage getPrimaryStage()
    {
        return primaryStage;
    }
}
