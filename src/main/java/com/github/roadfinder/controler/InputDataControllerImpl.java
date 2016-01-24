package com.github.roadfinder.controler;

import com.github.roadfinder.model.impl.ModelImpl;
import com.github.roadfinder.model.impl.TerrainData;
import com.github.roadfinder.view.DeafultScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author jnachyla
 */
public class InputDataControllerImpl
{

    @FXML
    public TextField terrainSize;

    @FXML
    public TextField citiesNumTextField;

    @FXML
    public TextField forbiddenPointsNum;

    public void onGenerateData( ActionEvent actionEvent )
    {
        TerrainData terrainData =
                new TerrainData( intValue( citiesNumTextField ), intValue( forbiddenPointsNum ), intValue( terrainSize ) );
        ModelImpl.getInstance().setData( terrainData );
        showNextScene();
    }

    private int intValue( TextField field )
    {
        String text = field.getText();
        if ( text == null || text.isEmpty() ) {
            return 0;
        }
        return Integer.valueOf( text );
    }

    private void showNextScene()
    {
        try {
            GridPane gridPane = FXMLLoader.load( getClass().getResource( "/view/resultsPanel.fxml" ) );
            Scene nextScene = new DeafultScene( gridPane );

            Stage stage = (Stage) citiesNumTextField.getScene().getWindow();
            stage.setScene( nextScene );
            Scene previousScene = stage.getScene();
            stage.show();
        }
        catch ( IOException e ) {
            e.printStackTrace();
        }
    }
}
