package com.github.roadfinder.model.utils;

/**
 * Created by jnachyla on 2016-01-22.
 */
public class Point implements PointInterface
{

    public final int x;
    public final int y;

    public Point( int x, int y )
    {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals( Object o )
    {
        if ( this == o ) {
            return true;
        }

        PointInterface point = (PointInterface) o;

        if ( x != point.getX() ) {
            return false;
        }
        return y == point.getY();

    }

    @Override
    public int hashCode()
    {
        int result = x;
        result = 31 * result + y;
        return result;
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
}
