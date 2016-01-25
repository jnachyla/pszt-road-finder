package com.github.roadfinder.controler;

import com.github.roadfinder.model.api.Model;
import com.github.roadfinder.model.impl.ModelImpl;
import com.github.roadfinder.view.DeafultScene;
import com.github.roadfinder.view.ResultChartLin;
import com.github.roadfinder.view.TerrainGraphic;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by jnachyla on 2016-01-21.
 */
public class ResultControllerImpl
{

    @FXML
    public TextField maxGenerationTextField;
    @FXML
    public TextField populationSizeTextField;

    private Model model = ModelImpl.getInstance();

    public void findRoad( ActionEvent actionEvent )
    {
        float result =
                model.findRoad( intValue( maxGenerationTextField ), intValue( populationSizeTextField ) );

        if ( result >= 0 ) {
            showNextScene();
            showResultChart();
        }
    }

    private void showResultChart()
    {
        Float[] minFitPlot = model.getPathSearcher().getMinFitPlot();
        Float[] meanFitPlot = model.getPathSearcher().getMeanFitPlot();

        /*Stage stage = new Stage( );
        stage.setTitle( "RoadFinderChartLog" );
        Scene scene = new Scene( new ResultChartLog(meanFitPlot,minFitPlot) );
        stage.setScene( scene );
        stage.show();*/

        Stage stage2 = new Stage();
        stage2.setTitle( "RoadFinderChartLin" );
        Scene scene2 = new Scene( new ResultChartLin( meanFitPlot, minFitPlot ) );
        stage2.setScene( scene2 );
        stage2.show();
    }

    private void showNextScene()
    {
        try {
            GridPane gridPane = FXMLLoader.load( getClass().getResource( "/view/resultGraphicPanel.fxml" ) );
            TerrainGraphic terrainGraphic = new TerrainGraphic( ModelImpl.getInstance().getData() );
            terrainGraphic.drawConnections( ModelImpl.getInstance().getPathSearcher().getActBestPath() );
            Scene nextScene = new DeafultScene( terrainGraphic );

            Stage stage = (Stage) populationSizeTextField.getScene().getWindow();
            stage.setScene( nextScene );
            stage.show();
        }
        catch ( IOException e ) {
            e.printStackTrace();
        }
    }


    private int intValue( TextField field )
    {
        String text = field.getText();
        if ( text == null || text.isEmpty() ) {
            return 0;
        }
        return Integer.valueOf( text );
    }
}
