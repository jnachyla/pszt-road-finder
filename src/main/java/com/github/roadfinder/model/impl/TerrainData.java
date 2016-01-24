package com.github.roadfinder.model.impl;

import com.github.roadfinder.algortihm.impl.City;
import com.github.roadfinder.algortihm.impl.Place;
import com.github.roadfinder.model.utils.Point;
import com.github.roadfinder.model.utils.PointInterface;
import com.github.roadfinder.model.utils.RandomUtil;

/**
 * Created by jnachyla on 2016-01-22.
 */
public class TerrainData
{

    public static final int DEFAULT_SIZE = 5;
    private static final int MAX_MASS = 10;

    /**
     * Size is dimension of terrain Size x Size.
     */
    private int size;

    public int getSize()
    {
        return size;
    }

    public void setSize( int size )
    {
        this.size = size;
    }

    private final City[] cities;
    private int[] orderedCities = null;
    private final Place[] forbidPlaces;

    public TerrainData( int numCities, int numForbidPlaces, int size )
    {

        this.size = size;
        validateSize();

        numCities = validateNumCities( numCities );

        this.cities = generateCities( numCities );

        numForbidPlaces = validateNumForbidPlaces( numCities, numForbidPlaces );

        this.forbidPlaces = generateForbidPlaces( numForbidPlaces );
    }

    private void validateSize()
    {
        if ( this.size <= 0 ) {
            this.size = DEFAULT_SIZE;
        }
    }

    private int validateNumForbidPlaces( int numCities, int numForbidPlaces )
    {
        if ( numForbidPlaces < 0 ) {
            numForbidPlaces = 0;
        }

        if ( numForbidPlaces > ( size * size - numCities ) ) {
            numForbidPlaces = ( size * size - numCities );
        }
        return numForbidPlaces;
    }

    private int validateNumCities( int numCities )
    {
        if ( numCities < 0 ) {
            numCities = 0;
        }
        if ( numCities > ( size * size ) ) {
            numCities = size * size;
        }
        return numCities;
    }

    private Place[] generateForbidPlaces( int numForbidPlaces )
    {
        Place[] forbidPlaces = new Place[ numForbidPlaces ];
        PointInterface[] points = RandomUtil.randomPointsNoRepeats( numForbidPlaces, 0, size, this.cities );
        for ( int i = 0; i < numForbidPlaces; i++ ) {
            PointInterface point = points[ i ];
            forbidPlaces[ i ] = new Place( point.getX(), point.getY() );
        }
        return forbidPlaces;
    }

    private City[] generateCities( int numCities )
    {
        City[] cities = new City[ numCities ];

        Point[] points = RandomUtil.randomPointsNoRepeats( numCities, 0, size );
        int[] masses = RandomUtil.random( numCities, 1, MAX_MASS );

        for ( int i = 0; i < numCities; i++ ) {
            Point point = points[ i ];
            cities[ i ] = new City( point.x, point.y, masses[ i ] );
        }
        return cities;
    }

    public City[] getCities()
    {
        return cities;
    }

    public Place[] getForbidPlaces()
    {
        return forbidPlaces;
    }

    public int[] getOrderedCities()
    {
        return orderedCities;
    }

    public void setOrderedCities( int[] orderedCities )
    {
        this.orderedCities = orderedCities;
    }


}
