package com.github.roadfinder.model.utils;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandomUtil
{

    public static int[] random( int number, int min, int max )
    {
        if ( max < min ) {
            throw new IllegalArgumentException( "Max cant be lower than min!" );
        }

        Random random = new Random();
        int[] result = new int[ number ];

        for ( int i = 0; i < number; i++ ) {
            result[ i ] = random.nextInt( max ) + min;
        }
        return result;
    }

    public static Point[] randomPointsNoRepeats( int number, int min, int max )
    {

        if ( min <= 0 ) {
            min = 1;
        }

        if ( max < min ) {
            throw new IllegalArgumentException( "Max cant be lower than min!" );
        }

        Set<Point> points = new HashSet( number );

        Random random = new Random();
        while ( points.size() < number ) {
            int y = random.nextInt( max ) + min;
            int x = random.nextInt( max ) + min;

            points.add( new Point( x, y ) );
        }
        return points.toArray( ( new Point[ points.size() ] ) );
    }

    public static PointInterface[] randomPointsNoRepeats( int number, int min, int max, PointInterface[] excludePoints )
    {

        if ( min <= 0 ) {
            min = 1;
        }

        if ( max < min ) {
            throw new IllegalArgumentException( "Max cant be lower than min!" );
        }

        Set<PointInterface> points = new HashSet( number + excludePoints.length );
        for ( PointInterface p : excludePoints ) {
            points.add( p );
        }

        Random random = new Random();
        while ( ( points.size() - excludePoints.length ) < number ) {
            int y = random.nextInt( max ) + min;
            int x = random.nextInt( max ) + min;

            points.add( new Point( x, y ) );
        }

        for ( PointInterface p : excludePoints ) {
            points.remove( p );
        }

        return points.toArray( ( new PointInterface[ points.size() ] ) );
    }
}
