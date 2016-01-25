package com.github.roadfinder.view;


import javafx.scene.chart.CategoryAxis;

import java.util.Arrays;
import java.util.Collections;

/**
 * Created by jnachyla on 2016-01-25.
 */
public class ResultChartLog extends ResultChart
{

    private final Float[] meanFit;
    private final Float[] minFit;

    public ResultChartLog( Float[] meanFit, Float[] minFit )
    {

        super( new CategoryAxis(), new LogarithmAxis( Float.min( Collections.min( Arrays.asList( meanFit ) ), minFit[ 0 ] ), Collections.max( Arrays.asList( meanFit ) ) ) );
        this.meanFit = meanFit;
        this.minFit = minFit;
        setTitle( "Wykres zbieznosci algorytmu genetycznego(logarytmiczny)" );
        createChart( meanFit, minFit );
    }
}
