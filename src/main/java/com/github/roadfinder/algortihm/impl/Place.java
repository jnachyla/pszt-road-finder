package com.github.roadfinder.algortihm.impl;

import com.github.roadfinder.model.utils.PointInterface;

/**
 * Created by jnachyla on 2016-01-22.
 */
public class Place implements PointInterface
{

    public final int x;
    public final int y;

    public Place( int x, int y )
    {
        this.x = x;
        this.y = y;
    }

    public int getX()
    {
        return this.x;
    }

    public int getY()
    {
        return this.y;
    }

    public int getSize()
    {
        return 30;
    }

    @Override
    public boolean equals( Object o )
    {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }

        Place place = (Place) o;

        if ( x != place.x ) {
            return false;
        }
        return y == place.y;

    }

    @Override
    public int hashCode()
    {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
