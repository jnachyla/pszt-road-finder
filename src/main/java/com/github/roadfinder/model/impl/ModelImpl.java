package com.github.roadfinder.model.impl;

import com.github.roadfinder.algortihm.api.PathSearcher;
import com.github.roadfinder.algortihm.impl.PathSearcherGenetic;
import com.github.roadfinder.model.api.Model;

import java.util.Arrays;

/**
 * TODO Mutable Singleton, bad idea.
 * Created by Jarek on 2015-12-09.
 */
public class ModelImpl implements Model {

    private static Model model = null;
    private PathSearcher pathSearcher = null;
    private TerrainData data = null;

    private ModelImpl( PathSearcher ps )
    {

        pathSearcher = ps;
    }

    public static Model getInstance()
    {
        if ( model == null ) {
            model = new ModelImpl( new PathSearcherGenetic() );
        }
        return model;
    }


    public void setData( TerrainData data )
    {
        this.data = data;
    }

    public TerrainData getData()
    {
        return data;
    }

    public PathSearcher getPathSearcher()
    {
        return pathSearcher;
    }

    public float findRoad( int maxGeneration, int population_size )
    {
        if ( maxGeneration <= 0 ) {
            maxGeneration = 100;
        }
        if ( population_size <= 0 ) {
            population_size = 20;
        }

        pathSearcher.setCitiesToMap( data.getCities(), 0 );
        pathSearcher.setForbidPlaces( data.getForbidPlaces() );
        pathSearcher.setMaxGeneration( maxGeneration );
        pathSearcher.setPopulationSize( population_size );
        pathSearcher.startSearch();

        System.out.println( "Poczatek obliczen" );

        while ( !pathSearcher.hasFinished() ) {
            ;
        }

        pathSearcher.getActBestPath();

        System.out.println( "Koniec obliczen" );

        float actMaxFit = pathSearcher.getActMaxFit();

        System.out.println( "Miasta " + Arrays.toString( getData().getCities() ) );
        System.out.println( "Miasta " + Arrays.toString( pathSearcher.getActBestPath() ) );

        data.setOrderedCities( pathSearcher.getActBestPath() );

        return actMaxFit;
    }
}
