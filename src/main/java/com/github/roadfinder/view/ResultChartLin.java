package com.github.roadfinder.view;


import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;

import java.util.Arrays;
import java.util.Collections;

/**
 * Created by jnachyla on 2016-01-25.
 */
public class ResultChartLin extends ResultChart
{

    public ResultChartLin( Float[] meanFit, Float[] minFit )
    {
        super( new CategoryAxis(),
                new NumberAxis
                        (
                                Float.min(
                                        Collections.min( Arrays.asList( meanFit ) )
                                        , minFit[ 0 ]
                                ),
                                Collections.max( Arrays.asList( meanFit ) ), 1
                        )
        );

        setTitle( "Wykres zbieznosci algorytmu genetycznego (liniowy)" );
        createChart( meanFit, minFit );

    }
}
