package com.github.roadfinder.view;

import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;

/**
 * Created by jnachyla on 2016-01-25.
 */
public abstract class ResultChart extends LineChart
{

    public ResultChart( Axis xAxis, Axis yAxis )
    {
        super( xAxis, yAxis );
    }

    protected void createChart( Float[] meanFit, Float[] minFit )
    {
        getXAxis().setLabel( "Generacja" );

        Series meanFitSerie = new Series();
        meanFitSerie.setName( "MeanFit of Generation" );


        for ( int i = 1; i < meanFit.length; i++ ) {
            System.out.println( "Mean" + meanFit[ i ] );
            meanFitSerie.getData().add( new Data( String.valueOf( i ), meanFit[ i ] ) );
        }

        Series minFitSerie = new Series();
        minFitSerie.setName( "MinFit of Generation" );

        for ( int i = 1; i < minFit.length; i++ ) {
            System.out.println( "Min" + minFit[ i ] );
            minFitSerie.getData().add( new Data( String.valueOf( i ), minFit[ i ] ) );
        }

        getData().add( meanFitSerie );
        getData().add( minFitSerie );
    }
}
